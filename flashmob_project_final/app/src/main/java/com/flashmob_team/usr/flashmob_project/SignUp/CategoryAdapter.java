package com.flashmob_team.usr.flashmob_project.SignUp;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flashmob_team.usr.flashmob_project.Login.LoginActivity;
import com.flashmob_team.usr.flashmob_project.Main.MainViewActivity;
import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<SignViewHolder> {
    private Context context;
    private ArrayList<CategoryData> categoryData;
    private View.OnClickListener onClickListener;

    private List<Integer> selectCategory;

    boolean isSelect;
    int selectId;

    public CategoryAdapter(final Context context, ArrayList<CategoryData> categoryData, View.OnClickListener onClickListener) {
        this.context = context;
        this.categoryData = categoryData;
        this.onClickListener = onClickListener;

        SignCategoryActivity.sign_cate_right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });
    }

    public void setAdapter(ArrayList<CategoryData> categoryData) {
        this.categoryData = categoryData;
        notifyDataSetChanged();
    }

    @Override
    public SignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_cate_recyclerview_content, parent, false);
        SignViewHolder viewHolder = new SignViewHolder(view);

        view.setOnClickListener(onClickListener);
        selectCategory = new ArrayList<>();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SignViewHolder holder, final int position) {
        holder.VH_cate_name.setText(categoryData.get(position).cate_name);

        final int blackFilter = context.getResources().getColor(R.color.black_color_filter);
        PorterDuffColorFilter blakcColorFilter = new PorterDuffColorFilter(blackFilter, PorterDuff.Mode.SRC_ATOP);

        if (categoryData.get(position).cate_image == null) {
            Log.d("Log", "Category Adapter cate_image x : " + categoryData.get(position).cate_image);
            Glide.with(context)
                    .load(R.drawable.square_button)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_cate_image);

            holder.VH_cate_image.setColorFilter(blakcColorFilter);
        }
        else {
            Log.d("Log", "Category Adapter cate_image o : " + categoryData.get(position).cate_image);
            Glide.with(context)
                    .load(categoryData.get(position).cate_image)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_cate_image);

            holder.VH_cate_image.setColorFilter(blakcColorFilter);
        }

        holder.VH_cate_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log", "loca id : " + String.valueOf(categoryData.get(position).cate_id));

                int yellowFilter = context.getResources().getColor(R.color.yellow_color_filter);
                PorterDuffColorFilter yellowColorFilter = new PorterDuffColorFilter(yellowFilter, PorterDuff.Mode.SRC_ATOP);

                final int blackFilter = context.getResources().getColor(R.color.black_color_filter);
                PorterDuffColorFilter blakcColorFilter = new PorterDuffColorFilter(blackFilter, PorterDuff.Mode.SRC_ATOP);

                for (int i = 0; i < selectCategory.size(); i++) {
                    if (selectCategory.get(i) == categoryData.get(position).cate_id){
                        isSelect = true;
                        break;
                    }
                }

                if (isSelect == true) {
                    for (int j = 0; j < selectCategory.size(); j++) {
                        if (selectCategory.get(j) == categoryData.get(position).cate_id) {
                            selectId = j;
                        }
                    }
                    selectCategory.remove(selectId);
                    Log.d("Log", "remove");
                    Log.d("Log", "size : " + selectCategory.size());
                    holder.VH_cate_image.setColorFilter(blakcColorFilter);
                    isSelect = false;
                } else {
                    selectCategory.add(categoryData.get(position).cate_id);
                    Log.d("Log", "add");
                    Log.d("Log", "size : " + selectCategory.size());
                    holder.VH_cate_image.setColorFilter(yellowColorFilter);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryData != null ? categoryData.size() : 0;
    }
}
