package com.flashmob_team.usr.flashmob_project.Main;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.flashmob_team.usr.flashmob_project.Chat.Chat;
import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

/*
채팅 목록 프래그먼트
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    ListView listView;
    private ArrayList<listItem> list;
    private ChatListAdapter chatlistadapt;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        list = new ArrayList<>();

        //목록에 추가
        list.add(new listItem(R.drawable.flash, "[이태원] 같이 볼링쳐요!!", "안녕!"));
        list.add(new listItem(R.drawable.flash, "[대학로] 연극볼사람!!", "ㅎㅎ"));

        listView = (ListView)view.findViewById(R.id.chat_list);
        chatlistadapt = new ChatListAdapter(list, getContext());
        listView.setAdapter(chatlistadapt);


        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);

    }

    //    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        listItem item = (listItem) l.getItemAtPosition(position);
//
//        /*
//        if(item.getProfile()==(DB에서)getprofile && item.getName() == getname)
//            ==> 해당하는 채팅방 입장
//            ==> intent시 putdata로 채팅방, 유저네임 전달
//         */
//
//
//
//    }
    //프로필 사진, 이름, 마지막 내용
    public class listItem{
        private int profile;
        private String name;
        private String chat;

        public listItem(int profile, String name, String chat){
            this.profile = profile;
            this.name = name;
            this.chat = chat;
        }

        public int getProfile(){
            return this.profile;
        }
        public String getName(){
            return this.name;
        }
        public String getChat(){
            return this.chat;
        }
    }

    public class ChatListAdapter extends BaseAdapter {
        private ArrayList<listItem> list;
        private Context context;
        LayoutInflater inflater;

        public ChatListAdapter(ArrayList<listItem> list, Context context){
            this.list=list;
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public listItem getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            //View v = convertView;
//            final Context context = parent.getContext();

            if(convertView == null) {
//                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.single_chat_list, parent, false);
            }
            ImageView profile = (ImageView)convertView.findViewById(R.id.profile_img);
            TextView name = (TextView)convertView.findViewById(R.id.list_name);
            TextView chat = (TextView)convertView.findViewById(R.id.list_chat);

            profile.setImageResource(list.get(pos).getProfile());
            name.setText(list.get(pos).getName());
            chat.setText(list.get(pos).getChat());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), Chat.class);
                    startActivity(intent);
                }
            });

            return convertView;
        }

    }

}