package com.example.mymvp2.persenter;

import com.example.mymvp2.model.Bean.Mybean;
import com.example.mymvp2.view.iview.Homeview;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * com.example.mymvp2.persent
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/10 11:02
 */

public class Homepersenter extends Basepersenter<Homeview>{

    public  void getFromData(){
       RequestParams requestParams= new RequestParams();
        requestParams.setUri("http://api.expoon.com/AppNews/getNewsList/type/2/p/1");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson= new Gson();
                Mybean mybean=gson.fromJson(result, Mybean.class);
                gett1().getcallback(mybean);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }



}
