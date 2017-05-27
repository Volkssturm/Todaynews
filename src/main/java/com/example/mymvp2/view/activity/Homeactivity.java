package com.example.mymvp2.view.activity;

import android.app.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mymvp2.R;
import com.example.mymvp2.UiUtils;
import com.example.mymvp2.view.fragment.FristFg;
import com.example.mymvp2.view.fragment.LoveFg;
import com.example.mymvp2.view.fragment.MeFg;
import com.example.mymvp2.view.fragment.VideoFg;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;

/**
 * com.example.mymvp2.view.activity
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/11 19:53
 */

public class Homeactivity extends FragmentActivity {
    private int theme = 0;
    private RadioButton me;
    private RadioButton love;
    private FrameLayout framl;
    private RadioGroup radioGroup;
    private RadioButton frist;
    private RadioButton video;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initview();
        initdata();
        SharedPreferences qq = getSharedPreferences("QQ", MODE_PRIVATE);
        edit = qq.edit();
    }

    private void initdata() {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        final FristFg frisrfg=new FristFg();
        final LoveFg lovefg=new LoveFg();
        final MeFg mefg=new MeFg();
        final VideoFg videofg=new VideoFg();
        fragmentTransaction.add(R.id.home_fl, frisrfg);
        fragmentTransaction.add(R.id.home_fl, lovefg).hide(lovefg);
        fragmentTransaction.add(R.id.home_fl, mefg).hide(mefg);
        fragmentTransaction.add(R.id.home_fl, videofg).hide(videofg).commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_rd1:
                        FragmentTransaction f=fragmentManager.beginTransaction();
                        f.show(frisrfg).hide(videofg).hide(mefg).hide(lovefg).commit();
                        frist.setChecked(true);
                        break;
                    case R.id.home_rd2:
                        FragmentTransaction f1=fragmentManager.beginTransaction();
                        f1.show(videofg).hide(frisrfg).hide(mefg).hide(lovefg).commit();
                        video.setChecked(true);
                        break;
                    case R.id.home_rd3:
                        FragmentTransaction f2=fragmentManager.beginTransaction();
                        f2.show(lovefg).hide(videofg).hide(mefg).hide(frisrfg).commit();
                        love.setChecked(true);
                        break;
                    case R.id.home_rd4:
                        FragmentTransaction f3=fragmentManager.beginTransaction();
                        f3.show(mefg).hide(videofg).hide(frisrfg).hide(lovefg).commit();
                        me.setChecked(true);
                        break;
                }
            }
        });
    }

    private void initview() {
        framl = (FrameLayout) findViewById(R.id.home_fl);
        radioGroup = (RadioGroup) findViewById(R.id.home_radiog);
        frist = (RadioButton) findViewById(R.id.home_rd1);
        video = (RadioButton) findViewById(R.id.home_rd2);
        love = (RadioButton) findViewById(R.id.home_rd3);
        me = (RadioButton) findViewById(R.id.home_rd4);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//       edit.clear();
//    }
public void reload() {
    Intent intent = getIntent();
    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
    finish();
    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    startActivity(intent);
}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences ddd = getSharedPreferences("lala", MODE_PRIVATE);
        ddd.edit().clear().commit();

    }


}
