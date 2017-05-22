package com.example.mymvp2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymvp2.R;
import com.example.mymvp2.model.Bean.Fr_bean;
import com.example.mymvp2.model.Bean.Titlebean;
import com.example.mymvp2.persenter.EndLessOnScrollListener;
import com.example.mymvp2.persenter.ItemDecorationss;
import com.example.mymvp2.view.activity.WebviewActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.mymvp2.view.fragment
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/17 21:20
 */

class BeiJingFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private ArrayList<String> mDatas;
    private SwipeRefreshLayout swipeRefreshLayout;
   private  String url;
    private List<Fr_bean.ResultBean.DataBean> data;

    public BeiJingFragment(ArrayList<String> mDatas, String url) {
        this.mDatas = mDatas;
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_fr_item,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipelt);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.fr2_lv);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow
        );

        mRecyclerView.addItemDecoration(new ItemDecorationss(getActivity()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //我在List最前面加入一条数据
                mDatas.add(0, "嘿，我是“下拉刷新”生出来的");

                //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });

    }
    private void loadMoreData(){
        for (int i =0; i <3; i++){
            mDatas.add("嘿，我是“上拉加载”生出来的"+i);
            Log.e("loadMoreData: ",i+"" );
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mAdapter.notifyDataSetChanged();
                        }
                    });


                }
            }.start();
        }

    }
    protected void initData()
    {
        RequestParams requestParams=new RequestParams();
        requestParams.setUri("http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri="+url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result!=null){
                    Gson gson=new Gson();
                    Fr_bean fr_bean=gson.fromJson(result, Fr_bean.class);
                    data = fr_bean.getResult().getData();

                    mAdapter = new HomeAdapter();
                    mRecyclerView.setAdapter(mAdapter);
                }

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
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.f2_item_note, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position)
        {
            holder.tv.setText(data.get(position).getTitle());
            x.image().bind(holder.image,data.get(position).getThumbnail_pic_s());
            x.image().bind(holder.image2,data.get(position).getThumbnail_pic_s02());
            x.image().bind(holder.image3,data.get(position).getThumbnail_pic_s03());
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),WebviewActivity.class);
                    intent.putExtra("url",data.get(position).getUrl());
                    intent.putExtra("title",data.get(position).getTitle());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }



        @Override
        public int getItemCount()
        {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv,tv2;
            ImageView image,image2,image3;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.item_text);
                image= (ImageView) view.findViewById(R.id.item_image);
                image2= (ImageView) view.findViewById(R.id.item_image2);
                image3= (ImageView) view.findViewById(R.id.item_image3);
            }
        }
    }
}
