package com.flashmob_team.usr.flashmob_project.Chat;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
채팅 방 액티비티
 */
//http://mailmail.tistory.com/44
public class Chat extends AppCompatActivity {
    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    private TextView nameTxt;
    private TextView locaTxt;
    private TextView timeTxt;


    private boolean side = false;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_chat);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

//        private void getPreferences(){
////            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
////            pref.getString("hi", "");
////        }

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        Log.d("Log", pref.getString("pref", ""));

        buttonSend = (Button) findViewById(R.id.btnSend);
        listView = (ListView) findViewById(R.id.chatListView); //채팅 내용
        nameTxt = (TextView) findViewById(R.id.chat_usrName); //채팅방 이름
        locaTxt = (TextView)findViewById(R.id.chat_location); //모임 장소
        timeTxt = (TextView) findViewById(R.id.chat_time); //모임 시간

        //nametxt.setText(" ");
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.single_msg);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.chatText);
        int color = Color.parseColor("#4d4d4d");
        chatText.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.single_msg);
        listView.setAdapter(chatArrayAdapter);


        //nameTxt, localTxt, timeTxt 설정
    }
/*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }
    /*
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    */
    //   @SuppressWarnings("StatementWithEmptyBody")
    //   @Override
 /*   public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(Chat.this, ChatFragment.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    */

    private boolean sendChatMessage(){

        String CHAT_NAME = "안드로이드";//채팅방 이름 설정

        //Firebase
        ChatDTO chat = new ChatDTO(CHAT_NAME, chatText.getText().toString()); //ChatDTO를 이용하여 데이터를 묶는다.
        databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat); //databaseReference를 이용해 데이터 푸쉬

        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString()));
        chatText.setText("");
        side = !side;
        return true;
    }


}