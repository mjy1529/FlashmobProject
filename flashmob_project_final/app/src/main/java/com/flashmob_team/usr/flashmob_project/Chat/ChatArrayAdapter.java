package com.flashmob_team.usr.flashmob_project.Chat;

/*
채팅방 리스트 뷰
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private TextView msgName;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private LinearLayout singleMessageContainer;


    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    @SuppressLint("WrongConstant")
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_msg, parent, false);
        }
        singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);


        chatText = (TextView) row.findViewById(R.id.singleMessage);
        msgName = (TextView) row.findViewById(R.id.msg_name);
        ChatMessage chatMessageObj = getItem(position);

        chatText.setText(chatMessageObj.message);




        //상대가 보낸 채팅 resource : bubble2, gravity : Left, msg_name : 상대 이름
        //내가 보낸 채팅 : bubble1, gravity : right
        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.bubble2 : R.drawable.bubble1);
        msgName.setVisibility(chatMessageObj.left ? 1 : 0);
        chatText.setVisibility(chatMessageObj.left ? 1 : 0);

        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}