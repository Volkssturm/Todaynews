package com.example.mymvp2.persenter;

import com.example.mymvp2.view.iview.IMvpView;

/**
 * com.example.mymvp2.persenter
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/11 9:04
 */

public class Basepersenter <T extends IMvpView>{

    public   T t;
    public void AttchView( T t1)
    {
        this.t=t1;
    }

    public T  gett1(){
        return t;
    }
}
