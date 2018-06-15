package com.flashmob_team.usr.flashmob_project.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class RecyclerMypageAdapter extends RecyclerView.Adapter<RecyclerMypageAdapter.ItemViewHolder>{
    ArrayList<RecyclerMypageItem> mItems;

    public RecyclerMypageAdapter(ArrayList<RecyclerMypageItem> items) {
        mItems = items;
    }

    @Override
    public RecyclerMypageAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_mypage_item, parent, false);
        return new RecyclerMypageAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerMypageAdapter.ItemViewHolder holder, int position) {
        holder.join_title.setText(mItems.get(position).getTitle());
        holder.join_date.setText(mItems.get(position).getDate());
        holder.join_place.setText(mItems.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView join_title;
        private TextView join_date;
        private TextView join_place;

        public ItemViewHolder(View itemView) {
            super(itemView);
            join_title = (TextView)itemView.findViewById(R.id.join_title);
            join_date = (TextView)itemView.findViewById(R.id.join_date);
            join_place = (TextView)itemView.findViewById(R.id.join_place);
        }
    }
}
