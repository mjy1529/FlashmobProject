package com.flashmob_team.usr.flashmob_project.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flashmob_team.usr.flashmob_project.Application.ApplicationController;
import com.flashmob_team.usr.flashmob_project.Network.NetworkService;
import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignCategoryActivity extends AppCompatActivity {
    Button sign_cate_left_button;
    public static Button sign_cate_right_button;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CategoryAdapter categoryAdapter;

    private NetworkService service;

    private ArrayList<CategoryData> categoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_category);

        service = ApplicationController.getInstance().getNetworkService();

        sign_cate_left_button = (Button) findViewById(R.id.sign_cate_left_button);
        sign_cate_right_button = (Button) findViewById(R.id.sign_cate_right_button);

        recyclerView = (RecyclerView) findViewById(R.id.sign_cate_recyclerView);
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(15));

        categoryData = new ArrayList<CategoryData>();

        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoryData, clickEvent);
        recyclerView.setAdapter(categoryAdapter);

        Networking();
    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildPosition(v);
            int tempId = categoryData.get(itemPosition).cate_id;
            //Intent intent = new Intent(getApplicationContext(), SignCategoryActivity.class);
            //intent.putExtra("id", String.valueOf(tempId));
            //startActivity(intent);
        }
    };

    public void Networking() {
        Call<CategoryResult> categoryResultCall = service.getCategoryResult();
        categoryResultCall.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Successful Get Category Data")) {
                        categoryData = response.body().data;
                        categoryAdapter.setAdapter(categoryData);
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }
}
