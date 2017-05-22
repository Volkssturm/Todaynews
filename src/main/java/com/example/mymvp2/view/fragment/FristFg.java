package com.example.mymvp2.view.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mymvp2.R;
import com.example.mymvp2.model.Bean.Titlebean;
import com.example.mymvp2.persenter.Getitem;
import com.example.mymvp2.view.activity.AndActivity;
import com.example.mymvp2.view.adapter.HomeFragmentVPFragmentAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

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

public class FristFg extends Fragment {
    private ArrayList<String> listtitle  = new ArrayList<>();;
    private ArrayList<Fragment>  listfragment = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frist_fg,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getVpTitleData();
    }
    //定义一个方法
    public void getVpTitleData() {
        // 找到 TabLayou  控件
        mTabLayout = (TabLayout) getView().findViewById(R.id.frist_tabs);
        // 找到 ViewPager  控件
        mViewPager = (ViewPager) getView().findViewById(R.id.frist_vpr);
        imageView = (ImageView) getView().findViewById(R.id.frist_igv);
        //定义 title 集合 来存储  解析的data数据
        getnote();

    }

    public void getnote() {
        new AsyncTask<String,Void,String>(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson=new Gson();
                Titlebean titlebean=gson.fromJson(s, Titlebean.class);
                Titlebean.ResultBean dateBean=titlebean.getResult();
                for (int i = 0; i <dateBean.getDate().size() ; i++) {
                    listtitle.add(dateBean.getDate().get(i).getTitle());
                    Log.e("onPostExecute: ","发光时代"+dateBean.getDate().get(i).getTitle() );
                }
                //遍历 listtitle 集合 将title 添加经 TabLayou z中
                for (int i = 0; i <listtitle.size() ; i++) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(listtitle.get(i)));

                }

                //创建集合 循环添加创建的Fragment

                for (int i = 0; i <listtitle.size() ; i++) {
                  String url=  dateBean.getDate().get(i).getUri();
                    BeiJingFragment mjingFragment = new BeiJingFragment(listtitle,url);
                    listfragment.add(mjingFragment);
                }
                HomeFragmentVPFragmentAdapter mAdapter = new HomeFragmentVPFragmentAdapter(getFragmentManager(),listfragment,listtitle);

                //给ViewPager设置适配器
                mViewPager.setAdapter(mAdapter);
                //将TabLayout和ViewPager关联起来。
                mTabLayout.setupWithViewPager(mViewPager);
                //给TabLayout设置适配器
                mTabLayout.setTabsFromPagerAdapter(mAdapter);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(getActivity(), AndActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            protected String doInBackground(String... params) {
                String parms=params[0];
                Getitem getitem=new Getitem();
                String json=  getitem.getitems(parms);
                return json;
            }
        }.execute("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news\n");



    }
}
