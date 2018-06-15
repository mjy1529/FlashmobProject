package com.flashmob_team.usr.flashmob_project.SignUp;


import com.flashmob_team.usr.flashmob_project.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignViewHolder extends RecyclerView.ViewHolder {
    // location
    public TextView VH_loca_name;
    public ImageView VH_loca_image;

    // category
    public TextView VH_cate_name;
    public ImageView VH_cate_image;

    public SignViewHolder(View itemView) {
        super(itemView);

        // location
        VH_loca_name = (TextView)itemView.findViewById(R.id.sign_loca_recycler_textView);
        VH_loca_image = (ImageView) itemView.findViewById(R.id.sign_loca_recycler_imageView);

        // category
        VH_cate_name = (TextView)itemView.findViewById(R.id.sign_cate_recycler_textView);
        VH_cate_image = (ImageView)itemView.findViewById(R.id.sign_cate_recycler_imageView);
    }

}
