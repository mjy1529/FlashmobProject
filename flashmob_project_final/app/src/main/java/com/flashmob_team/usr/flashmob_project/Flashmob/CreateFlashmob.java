package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFlashmob extends AppCompatActivity {

    Toolbar toolbar;
    EditText titleEt;
    ImageButton calendarBtn;
    TextView selectedDateTv;
    ImageButton timeBtn;
    TextView selectedTimeTv;
    NumberPicker numberPicker;
    EditText memoEt;
    ImageButton locationBtn;
    TextView selectedLocationTv;
    Button createBtn;

    Button[] categoryBtn = new Button[9];
    ArrayList<String> cList = new ArrayList<>();
    boolean categoryCheck = false;

    String date;
    String time;
    CharSequence placeName;
    CharSequence placeAddress;
    Double placeLatitude;
    Double placeLongitude;

    int PLACE_REQUEST_CODE = 1;

    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashmob);

        init();

        // 완료 버튼을 클릭했을 때
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Log", "create click");
                final String title = titleEt.getText().toString(); //제목
                String place = selectedLocationTv.getText().toString(); //장소
                final int peoplenum = numberPicker.getValue(); //인원
                final String memo = memoEt.getText().toString(); //메모

                MeetPost meetPost = new MeetPost();
                meetPost.meet_title = title;
                meetPost.meet_date = date;
                meetPost.meet_people_num = peoplenum;
                meetPost.meet_memo = memo;
                meetPost.meet_place_address = String.valueOf(placeAddress);
                meetPost.meet_place_latitude = placeLatitude;
                meetPost.meet_place_longitude = placeLongitude;
                meetPost.meet_place_name = String.valueOf(placeName);
                meetPost.leader_id = 1;
                meetPost.cate_id = 1;
                meetPost.loca_id = 1;

                Call<MeetPostResult> meetPostResultCall = service.getMeetPostResult(meetPost);
                meetPostResultCall.enqueue(new Callback<MeetPostResult>() {

                    @Override
                    public void onResponse(Call<MeetPostResult> call, Response<MeetPostResult> response) {
                        if (response.isSuccessful()) {
                            if (response.body().message.equals("Sucessful Register Meeting")) {
                                Log.d("Log", "와 제발 성공해라");
                                Intent intent = new Intent(CreateFlashmob.this, InfoActivity.class);
                                intent.putExtra("title", title);
//
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                                finish();
                            }
                            else if (response.body().message.equals("NULL Value")) {
                                Log.d("Log", "null");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MeetPostResult> call, Throwable t) {
                        Log.d("Log", "와 실패");
                    }
                });

            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                LatLngBounds BOUNDS_VIEW = new LatLngBounds(
                        new LatLng(37.566535, 126.97796919999996), new LatLng(37.566535, 126.97796919999996)
                );
                try {
                    builder.setLatLngBounds(BOUNDS_VIEW);
                    startActivityForResult(builder.build(CreateFlashmob.this), PLACE_REQUEST_CODE);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                placeName = place.getName();
                placeAddress = place.getAddress();
                placeLatitude = place.getLatLng().latitude;
                placeLongitude = place.getLatLng().longitude;
                String attributions = (String)place.getAttributions();
                if(attributions == null) {
                    attributions="";
                }

                selectedLocationTv.setText(placeAddress);

                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClick(View v) {
        //클릭된 뷰를 버튼으로 받아옴
        Button newButton = (Button)v;

        //클릭된 버튼을 찾아냄
        for(Button tempButton : categoryBtn) {
            if(tempButton == newButton) { //클릭된 버튼을 찾았으면
                if(categoryCheck == false) {
                    tempButton.setBackgroundResource(R.drawable.btn_background);
//                    int position = (Integer)v.getTag();
//                    Toast.makeText(MainActivity.this, categoryBtn[position].getText().toString(), Toast.LENGTH_LONG).show();
                    cList.add(tempButton.getText().toString());
                    categoryCheck = true;
                } else {
                    tempButton.setBackgroundResource(R.drawable.btn_corner);
                    cList.remove(tempButton.getText().toString());
                    categoryCheck = false;
                }

            }
        }

    }

    public void init() {
        titleEt = (EditText)findViewById(R.id.titleEt);
        calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        selectedDateTv = (TextView) findViewById(R.id.selectedDateTv);
        timeBtn = (ImageButton) findViewById(R.id.timeBtn);
        selectedTimeTv = (TextView) findViewById(R.id.selectedTimeTv);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        memoEt = (EditText) findViewById(R.id.memoEt);
        locationBtn = (ImageButton) findViewById(R.id.locationBtn);
        selectedLocationTv = (TextView) findViewById(R.id.selectedLocationTv);
        createBtn = (Button)findViewById(R.id.createBtn);

        service = ApplicationController.getInstance().getNetworkService();

        categoryBtn[0] = (Button)findViewById(R.id.cBtn1);
        categoryBtn[1] = (Button)findViewById(R.id.cBtn2);
        categoryBtn[2] = (Button)findViewById(R.id.cBtn3);
        categoryBtn[3] = (Button)findViewById(R.id.cBtn4);
        categoryBtn[4] = (Button)findViewById(R.id.cBtn5);
        categoryBtn[5] = (Button)findViewById(R.id.cBtn6);
        categoryBtn[6] = (Button)findViewById(R.id.cBtn7);
        categoryBtn[7] = (Button)findViewById(R.id.cBtn8);
        categoryBtn[8] = (Button)findViewById(R.id.cBtn9);

        for(int i=0; i<categoryBtn.length; i++) {
            //버튼의 index를 태그로 저장
            categoryBtn[i].setTag(i);
        }

        // 커스텀 툴바 세팅
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar( toolbar );
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( false );
        actionBar.setDisplayShowTitleEnabled( false );

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        selectedDateTv.setText(year + "년 " + (month+1) + "월 " + day + "일");
        selectedTimeTv.setText(hour + "시 " + minute + "분");

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateFlashmob.this, dateListener, year, month, day);
                datePickerDialog.show();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateFlashmob.this, timeListener, hour, minute, true);
                timePickerDialog.show();
            }
        });

        memoEt.addTextChangedListener(new TextWatcher() {
            String previousString = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousString = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (memoEt.getLineCount() >= 5) {
                    memoEt.setText(previousString);
                    memoEt.setSelection(memoEt.length());
                }
            }
        });

    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month += 1;
            date = year + "년 " + month + "월 " + dayOfMonth + "일";
            selectedDateTv.setText(date);
        }
    };

    private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time = hourOfDay + "시 " + minute + "분";
            selectedTimeTv.setText(time);
        }
    };
}
