package com.flashmob_team.usr.flashmob_project.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.List;

public class SearchAdapter extends BaseAdapter{

    private Context context;
    private List<SearchItem> list;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    public SearchAdapter(List<SearchItem> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = inflater.inflate(R.layout.search_item_listview, null);

            viewHolder = new ViewHolder();
            viewHolder.title_tv = (TextView)view.findViewById(R.id.title_tv);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.title_tv.setText(list.get(i).getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return view;
    }

    class ViewHolder {
        public TextView title_tv;
        public TextView date_tv;
        public TextView category_tv;
    }
}
