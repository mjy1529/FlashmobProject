package com.flashmob_team.usr.flashmob_project.Main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.flashmob_team.usr.flashmob_project.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    public ImageView VH_home_image;
    public TextView VH_home_text_title;
    public TextView VH_home_text_content;

    public ImageView VH_main_image;
    public TextView VH_main_title;
    public TextView VH_main_date;
    public TextView VH_main_place;


    public HomeViewHolder(View itemView) {
        super(itemView);

        VH_home_image = (ImageView)itemView.findViewById(R.id.home_recycler_image);
        VH_home_text_title = (TextView)itemView.findViewById(R.id.home_recycler_text_title);
        VH_home_text_content = (TextView)itemView.findViewById(R.id.home_recycler_content);

        VH_main_image = (ImageView)itemView.findViewById(R.id.home_main_imageview);
        VH_main_title = (TextView)itemView.findViewById(R.id.home_main_title);
        VH_main_date = (TextView)itemView.findViewById(R.id.home_main_date);
        VH_main_place = (TextView)itemView.findViewById(R.id.home_main_place);
    }
}