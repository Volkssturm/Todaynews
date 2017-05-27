package com.example.mymvp2.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.mymvp2.R;
import com.example.mymvp2.UiUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

/**
 * com.example.mymvp2.view.activity
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/21 18:55
 */

public class WebviewActivity extends Activity {
    private int theme = 0;
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(WebviewActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(WebviewActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(WebviewActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewactivity);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        progressDialog.setMessage("玩命加载中.....");
        progressDialog.show();


        WebView web= (WebView) findViewById(R.id.web_web);
        Button but= (Button) findViewById(R.id.web_but);
        Intent intent=getIntent();
        final String url=intent.getStringExtra("url");
        final String title=intent.getStringExtra("title");
        Log.e("onCreate: ", "sg发光时代"+url);
        web.loadUrl(url);

        web.getSettings().setDefaultTextEncodingName("GBK");
        web.setWebViewClient(new WebViewClient(){


            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //当页面开始加载的时候，就会调用


            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //当页面加载完毕的时候，会调用该方法
            progressDialog.dismiss();
            }

        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb(url);
                web.setTitle(title);//标题
                // web.setThumb(thumb);  //缩略图
//                web.setDescription("my description");//描述


                new ShareAction(WebviewActivity.this)
                        .withText("hello")
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
