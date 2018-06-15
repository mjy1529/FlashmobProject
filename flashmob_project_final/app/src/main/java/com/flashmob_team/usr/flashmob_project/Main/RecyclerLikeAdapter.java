package com.flashmob_team.usr.flashmob_project.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class RecyclerLikeAdapter extends RecyclerView.Adapter<RecyclerLikeAdapter.ItemViewHolder>{

    ArrayList<RecyclerLikeItem> mItems;

    public RecyclerLikeAdapter(ArrayList<RecyclerLikeItem> items) {
        mItems = items;
    }

    @Override
    public RecyclerLikeAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_like_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerLikeAdapter.ItemViewHolder holder, int position) {
        holder.like_title.setText(mItems.get(position).getTitle());
        holder.like_date.setText(mItems.get(position).getDate());
        holder.like_place.setText(mItems.get(position).getPlace());
        holder.like_peoplenum.setText(mItems.get(position).getCurrent_peoplenum() + " / " + mItems.get(position).getTotal_peoplenum());

        if(mItems.get(position).getCurrent_peoplenum() < mItems.get(position).getTotal_peoplenum()) {
            holder.joinAvailable.setText("참여 가능");
        } else {
            holder.joinAvailable.setText("참여 불가");
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView like_title;
        private TextView like_date;
        private TextView like_place;
        private TextView like_peoplenum;
        private TextView joinAvailable;

        public ItemViewHolder(View itemView) {
            super(itemView);
            like_title = (TextView)itemView.findViewById(R.id.like_title);
            like_date = (TextView)itemView.findViewById(R.id.like_date);
            like_place = (TextView)itemView.findViewById(R.id.like_place);
            like_peoplenum = (TextView)itemView.findViewById(R.id.like_peoplenum);
            joinAvailable = (TextView)itemView.findViewById(R.id.joinAvailable);
        }
    }
}
