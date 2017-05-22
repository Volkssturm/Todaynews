package com.example.mymvp2.view.iview;

import com.example.mymvp2.model.Bean.Mybean;

/**
 * com.example.mymvp2.view.iview
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/10 11:03
 */

public interface Homeview extends IMvpView{
        void getcallback(Mybean data);
        void getcallErr(String sErr,int errcode);
}
