package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private ArrayList<Review> reviewList = null;
    private int listCount = 0;

    public ListAdapter(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
        listCount = this.reviewList.size();
    }

    @Override
    public int getCount() {
        return listCount;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            final Context context = parent.getContext();
            if(inflater == null) {
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.review_item, parent, false);
        }

        TextView reviewName = (TextView)convertView.findViewById(R.id.reviewName);
        TextView reviewTime = (TextView)convertView.findViewById(R.id.reviewTime);
        TextView reviewContent = (TextView)convertView.findViewById(R.id.reviewContent);
        ImageView reviewIv = (ImageView)convertView.findViewById(R.id.reviewIv);
        reviewIv.setBackground(new ShapeDrawable(new OvalShape()));
        reviewIv.setClipToOutline(true);

        reviewName.setText(this.reviewList.get(position).reviewName);
        reviewTime.setText(this.reviewList.get(position).reviewTime);
        reviewContent.setText(this.reviewList.get(position).reviewContent);

        return convertView;
    }
}