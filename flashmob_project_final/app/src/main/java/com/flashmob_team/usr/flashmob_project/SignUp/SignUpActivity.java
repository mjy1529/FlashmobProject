package com.flashmob_team.usr.flashmob_project.SignUp;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText signup_email;
    EditText signup_pw;
    EditText signup_pw_confirm;
    EditText signup_name;
    EditText signup_phone;
    EditText signup_age;
    int sex = 1;
    Button signup_button;
    Button signup_man;
    Button signup_woman;
    Button signup_camera;
    ImageView signup_imageView;

    private ProgressDialog mProgressDialog;

    NetworkService service;

    final int REQ_CODE_SELECT_IMAGE = 100;
    String imgUrl = "";
    Uri data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        service = ApplicationController.getInstance().getNetworkService();

        signup_email = (EditText) findViewById(R.id.signup_email_editText);
        signup_pw = (EditText) findViewById(R.id.signup_pw_editText);
        signup_pw_confirm = (EditText) findViewById(R.id.signup_pw_confirm_editText);
        signup_name = (EditText) findViewById(R.id.signup_name_editText);
        signup_phone = (EditText) findViewById(R.id.signup_phone_editText);
        signup_age = (EditText) findViewById(R.id.signup_age_editText);
        signup_man = (Button) findViewById(R.id.signup_man_button);
        signup_woman = (Button) findViewById(R.id.signup_woman_button);
        signup_camera = (Button) findViewById(R.id.signup_camera_button);
        signup_button = (Button) findViewById(R.id.sign_up_button);
        signup_imageView = (ImageView) findViewById(R.id.signup_imageView);

        //editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //
        //출처: http://tiann.tistory.com/10 [티앤의 IT월드]
        signup_email.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        signup_pw.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        signup_pw_confirm.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        signup_name.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        signup_phone.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        signup_age.setImeOptions(EditorInfo.IME_ACTION_DONE);

        ////////////////////////프로그래스 다이얼로그////////////////////////
        mProgressDialog = new ProgressDialog(SignUpActivity.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("등록 중...");
        mProgressDialog.setIndeterminate(true);

        signup_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = 1;
                signup_man.setBackgroundResource(R.drawable.man_select);
                signup_woman.setBackgroundResource(R.drawable.woman);
            }
        });

        signup_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = 1;
                signup_woman.setBackgroundResource(R.drawable.woman_select);
                signup_man.setBackgroundResource(R.drawable.man);
            }
        });

        signup_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signup_email.getText().toString().equals("") && signup_pw.getText().toString().equals("")
                        && signup_pw_confirm.getText().toString().equals(signup_pw.getText())
                        && signup_name.getText().toString().equals("") && signup_phone.getText().toString().equals("")
                        && signup_age.getText().toString().equals("") && sex == 0) {

                } else {
                    // signup 통신 코드
                    Log.d("Log", "SignUp : button_click");

                    mProgressDialog.show();

//                    SignUpPost signUpPost = new SignUpPost();
//
//                    signUpPost.user_email = signup_email.getText().toString();
//                    signUpPost.user_pw = signup_pw.getText().toString();
//                    signUpPost.user_name = signup_name.getText().toString();
//                    signUpPost.user_age = Integer.parseInt(signup_age.getText().toString());
//                    signUpPost.user_sex = sex;
//                    signUpPost.user_phone = signup_phone.getText().toString();
//                    signUpPost.user_image = null;

                    RequestBody user_email = RequestBody.create(MediaType.parse("multipart/form-data"), signup_email.getText().toString());
                    RequestBody user_pw = RequestBody.create(MediaType.parse("multipart/form-data"), signup_pw.getText().toString());
                    RequestBody user_name = RequestBody.create(MediaType.parse("multipart/form-data"), signup_name.getText().toString());
                    RequestBody user_age = RequestBody.create(MediaType.parse("multipart/form-data"), signup_age.getText().toString());
                    RequestBody user_sex = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(sex));
                    RequestBody user_phone = RequestBody.create(MediaType.parse("multipart/form-data"), signup_phone.getText().toString());



                    MultipartBody.Part body;

                    //body = null;

                    if (imgUrl == "") {
                        body = null;
                        Log.d("Log", "Sign up image null");
                    } else {
                        // 이미지 리사이징
                        Log.d("Log", "Sign up image not null");
                        BitmapFactory.Options options = new BitmapFactory.Options();
//                       options.inSampleSize = 4; //얼마나 줄일지 설정하는 옵션 4--> 1/4로 줄이겠다

                        InputStream in = null; // here, you need to get your context.
                        try {
                            in = getContentResolver().openInputStream(data);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // 이미지 압축
                        Bitmap bitmap = BitmapFactory.decodeStream(in, null, options); // InputStream 으로부터 Bitmap 생성
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                        // 압축 옵션( JPEG, PNG ) , 품질 설정 ( 0 - 100까지의 int형 ), 압축된 바이트 배열을 담을 스트림
                        RequestBody photoBody = RequestBody.create(MediaType.parse("user_image/jpg"), baos.toByteArray());

                        Log.d("Log", "Sign up photoBody : " + photoBody);

                        File photo = new File(imgUrl); // 가져온 파일의 이름 가져옴

                        // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용
                        body = MultipartBody.Part.createFormData("user_image", photo.getName(), photoBody);
                        Log.d("Log", "Sign up phto.getName() : " + photo.getName());
                        Log.d("Log", "Sign up body : " + body);

                    }

                    retrofit2.Call<SignUpResult> signUpResultCall = service.getSignUpResult(user_email, user_pw, user_name, user_age,
                            user_sex, user_phone, body);
                    signUpResultCall.enqueue(new Callback<SignUpResult>() {
                        @Override
                        public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                            if (response.isSuccessful()) {
                                if (response.body().message.equals("Already Exists")) {
                                    Toast.makeText(getApplicationContext(), "Already Joined", Toast.LENGTH_SHORT).show();
                                    mProgressDialog.dismiss();
                                }
                                else if (response.body().message.equals("Successfully Sign Up")) {
                                    //finish();
                                    Intent intent = new Intent(getApplicationContext(), SignLocationActivity.class);
                                    startActivity(intent);
                                    Log.d("Log", "sign up ok");

                                    mProgressDialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResult> call, Throwable t) {
                            Log.d("Log", "SignUP : fail");
                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        }
                    });
                }
            }
        });


    }

    // 선택된 이미지 가져오기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());
                    this.data = data.getData();

                    Glide.with(SignUpActivity.this)
                            .load(this.data)
                            .apply(new RequestOptions()
                                    .centerCrop()
                                    .circleCrop())
                            .into(signup_imageView);

//
//                    Glide
//                            .with(this)
//                            .load(R.drawable.image_default_profile_picture)
//                            .apply(new RequestOptions()
//                                    .placeholder(R.mipmap.ic_launcher)
//                                    .fitCenter()
//                                    .into(mUserImage);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                imgUrl = "";
            }
        }
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        imgUrl = imgPath;
        return imgName;
    }
}