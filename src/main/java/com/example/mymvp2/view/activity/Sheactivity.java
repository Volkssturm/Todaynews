package com.example.mymvp2.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mymvp2.R;
import com.example.mymvp2.UiUtils;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * com.example.mymvp2.view.activity
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/17 9:04
 */

public class Sheactivity extends Activity {
    public Button mSheButton;

    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sheactivity);

        mSheButton = (Button)findViewById(R.id.she_button);
        mSheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(Sheactivity.this).deleteOauth(Sheactivity.this, SHARE_MEDIA.QQ,null);
                Toast.makeText(Sheactivity.this, "授权取消", Toast.LENGTH_SHORT).show();
            }
        });


    }




}
