package com.example.mymvp2.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymvp2.R;
import com.example.mymvp2.UiUtils;
import com.example.mymvp2.view.activity.Homeactivity;
import com.example.mymvp2.view.activity.QQactivity;
import com.example.mymvp2.view.activity.Sheactivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.x;

import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * com.example.mymvp2.view.fragment
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/11 20:12
 */

public class MeFg extends Fragment implements View.OnClickListener{
    public View rootView;
    public ImageView mMePhone;
    public ImageView mMeWeixin;
    public ImageView mMeQq;
    public ImageView mMeSina;
    public LinearLayout mLl;
    public TextView mMeT1;
    public RadioButton mMeRd1;
    public RadioButton mMeRd2;
    public RadioButton mMeRd3;
    public RadioGroup mMeRadiog;
    boolean isChanged = false;
    private ImageView mMedenglu;
    private TextView mMeUser_name;
    private TextView mMedongtai;
    private TextView mMefangke;
    private TextView mMefensi;
    public RelativeLayout relativeLayout1;
    public RelativeLayout relativeLayout2;
    private TextView mMeshezhi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.me_fg, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        //relativeLayout1.setVisibility(View.VISIBLE);
        initdata();

        SharedPreferences qq = getActivity().getSharedPreferences("QQ", MODE_PRIVATE);
        boolean frist2=qq.getBoolean("状态",false);
        if (frist2==true){
            relativeLayout1.setVisibility(View.GONE);
            relativeLayout2.setVisibility(View.VISIBLE);

            String tou=qq.getString("头像","");
            x.image().bind(mMedenglu,tou);

            String name=qq.getString("昵称","");
            mMeUser_name.setText(name);
        }else {
            relativeLayout2.setVisibility(View.GONE);
            relativeLayout1.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences qq = getActivity().getSharedPreferences("QQ", MODE_PRIVATE);
                boolean frist1=qq.getBoolean("状态",false);
                if (frist1==true){
                    relativeLayout1.setVisibility(View.GONE);
                    relativeLayout2.setVisibility(View.VISIBLE);
                    String tou=qq.getString("头像","");
                    x.image().bind(mMedenglu,tou);
                    String name=qq.getString("昵称","");
                    mMeUser_name.setText(name);
                }

            }
        },500);
        SharedPreferences ddd = getActivity().getSharedPreferences("lala", MODE_PRIVATE);
        boolean isfrist=ddd.getBoolean("jiaa",false);

        if (isfrist==true){
            relativeLayout2.setVisibility(View.GONE);
            relativeLayout1.setVisibility(View.VISIBLE);
        }


    }

    private void initdata() {

        mMePhone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        mMePhone.setImageDrawable(getResources().getDrawable(R.mipmap.yb));
                        break;
                    case MotionEvent.ACTION_UP:
                        mMePhone.setImageDrawable(getResources().getDrawable(R.mipmap.ya));
                        break;
                }
                return false;
            }
        });
        mMeQq.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        mMeQq.setImageDrawable(getResources().getDrawable(R.mipmap.a_w));
                        break;
                    case MotionEvent.ACTION_UP:
                        mMeQq.setImageDrawable(getResources().getDrawable(R.mipmap.a_v));
                        break;
                }
                return false;
            }
        });
        mMeSina.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        mMeSina.setImageDrawable(getResources().getDrawable(R.mipmap.aci));
                        break;
                    case MotionEvent.ACTION_UP:
                        mMeSina.setImageDrawable(getResources().getDrawable(R.mipmap.ach));
                        break;
                }
                return false;
            }
        });
        mMeWeixin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        mMeWeixin.setImageDrawable(getResources().getDrawable(R.mipmap.agh));
                        break;
                    case MotionEvent.ACTION_UP:
                        mMeWeixin.setImageDrawable(getResources().getDrawable(R.mipmap.agd));
                        break;
                }
                return false;
            }
        });

        mMeRadiog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_rd1:
                        mMeRd1.setChecked(true);
                        break;
                    case R.id.home_rd2:
                        mMeRd2.setChecked(true);
                        break;
                    case R.id.home_rd3:
                        mMeRd3.setChecked(true);
                        //夜间模式
                        break;
                }
            }
        });
    }


    private void initview() {
        rootView = getView();
        mMePhone = (ImageView) rootView.findViewById(R.id.me_phone);
        mMedenglu = (ImageView) rootView.findViewById(R.id.me_denglu);
        mMeWeixin = (ImageView) rootView.findViewById(R.id.me_weixin);
        mMeQq = (ImageView) rootView.findViewById(R.id.me_qq);
        mMeSina = (ImageView) rootView.findViewById(R.id.me_sina);
        mMeT1 = (TextView) rootView.findViewById(R.id.me_t1);
        mMeUser_name = (TextView) rootView.findViewById(R.id.ME_User_name);
        mMedongtai = (TextView) rootView.findViewById(R.id.me_dongtai);
        mMefangke = (TextView) rootView.findViewById(R.id.me_fangke);
        mMefensi = (TextView) rootView.findViewById(R.id.me_fensi);
        mMeshezhi = (TextView) rootView.findViewById(R.id.me_shezhi);
        mMeRd1 = (RadioButton) rootView.findViewById(R.id.me_rd1);
        mMeRd2 = (RadioButton) rootView.findViewById(R.id.me_rd2);
        mMeRd3 = (RadioButton) rootView.findViewById(R.id.me_rd3);
        mMeRadiog = (RadioGroup) rootView.findViewById(R.id.me_radiog);
        relativeLayout1 = (RelativeLayout) rootView.findViewById(R.id.me_Rllt);
        relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.me_Rlltwo);
        mMeRd3.setOnClickListener(this);
        mMePhone.setOnClickListener(this);
        mMeWeixin.setOnClickListener(this);
        mMeQq.setOnClickListener(this);
        mMeSina.setOnClickListener(this);
        mMeshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Sheactivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_phone:



                break;
            case R.id.me_rd3:
                UiUtils.switchAppTheme(getActivity());
                Homeactivity mainActivity = (Homeactivity) getActivity();
                mainActivity.reload();
                break;
            case R.id.me_weixin:
                break;
            case R.id.me_qq:
                Intent in=new Intent(getActivity(),QQactivity.class);
                startActivity(in);
                break;
            case R.id.me_sina:
                break;
        }

    }


}
