package com.example.mymvp2.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mymvp2.R;
import com.example.mymvp2.persenter.Homepersenter;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;


public class MainActivity extends Activity {
    private int theme = 0;
    private ImageView image;
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent=new Intent(MainActivity.this,Homeactivity.class);
//                    startActivity(intent);
//                }
//            },3000);
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        image = (ImageView) findViewById(R.id.main_image);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent=new Intent(MainActivity.this,Homeactivity.class);
                 startActivity(intent);
                 finish();
             }
         },2000);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//
//    }


}
