package com.example.mymvp2.application;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * com.example.mymvp2.application
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/10 11:45
 */

public class Myapplication extends Application {
    {
        PlatformConfig.setQQZone("1106085961", "Oxljxh9s4xI4QKvj");
    }
    @Override
    public void onCreate() {

        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        UMShareAPI.get(this);
    }
}
