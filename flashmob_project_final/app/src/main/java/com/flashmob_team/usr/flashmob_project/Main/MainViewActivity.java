package com.flashmob_team.usr.flashmob_project.Main;


import android.Manifest;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;


import com.flashmob_team.usr.flashmob_project.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class MainViewActivity extends FragmentActivity {

    boolean permissionBoolean = false;

    private HomeLogFragment homeLogFragment;
    private LikeFragment likeFragment;
    private ChatFragment chatFragment;
    private SearchFragment searchFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        init();

        homeLogFragment = new HomeLogFragment();
        likeFragment = new LikeFragment();
        chatFragment = new ChatFragment();
        searchFragment = new SearchFragment();
        userFragment = new UserFragment();

        initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if(tabId == R.id.tab_home){
                    transaction.replace(R.id.contentContainer, homeLogFragment).commit();
                }
                else if(tabId == R.id.tab_like) {
                    transaction.replace(R.id.contentContainer, likeFragment).commit();
                }
                else if(tabId == R.id.tab_chat) {
                    transaction.replace(R.id.contentContainer, chatFragment).commit();
                }
                else if(tabId == R.id.tab_search) {
                    transaction.replace(R.id.contentContainer, searchFragment).commit();
                }
                else if(tabId == R.id.tab_user) {
                    transaction.replace(R.id.contentContainer, userFragment).commit();
                }

            }

        });
    }
        //app 실행시 보여지는 Fragment

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, homeLogFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void init() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() { //권한이 모두 허용되고나서 실행됨
                permissionBoolean = true;
                Toast.makeText(MainViewActivity.this, "권한 허가", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) { //요청한 권한 중에서 거부당한 권한 목록 리턴
                permissionBoolean = false;
                Toast.makeText(MainViewActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("Flashmob을 실행하기 위해서는 위치 접근 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한]에서 권한을 허용할 수 있습니다.")
                .setPermissions(android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .setGotoSettingButton(true)
                .check();
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}

