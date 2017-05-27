package com.example.mymvp2.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mymvp2.R;
import com.example.mymvp2.UiUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * com.example.mymvp2.view.activity
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/16 9:44
 */

public class QQactivity extends Activity {
//    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
//    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private int theme = 0;
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Set<String> set = data.keySet();
            SharedPreferences qq = getSharedPreferences("QQ", MODE_PRIVATE);
            SharedPreferences.Editor edit = qq.edit();
            for (String string : set) {
                String str = data.get(string);
                // 设置头像
                String touxiang = data.get("profile_image_url");
                edit.putString("头像",touxiang);
                // 设置昵称
                String nicheng = data.get("screen_name");
                edit.putString("昵称",nicheng);
                edit.putBoolean("状态",true);
                edit.commit();

            }
            String tou= qq.getString("头像","");
            Log.e("onActivityResult: ", "sg发光时代"+tou);


        Toast.makeText(QQactivity.this,"QQ已授权登录",Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(QQactivity.this, "授权错误", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(QQactivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       super.onCreate(savedInstanceState);
        setContentView(R.layout.qqactivity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        UMShareAPI.get(this).getPlatformInfo(this,
                SHARE_MEDIA.QQ, umAuthListener);

    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
            Log.e("onActivityResult: ", "sg发光时代"+data);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            },1000);

    }

}
