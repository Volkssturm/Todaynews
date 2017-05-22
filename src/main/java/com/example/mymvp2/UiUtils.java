package com.example.mymvp2;

import android.content.Context;

public class UiUtils {

    //��ȡ����
    public static int getAppTheme(Context ctx) {
        String value = Preferences.getString(ctx, "activity_theme", "1");
        switch (Integer.valueOf(value)) {
            case 1:
                return R.style.AppTheme;//��ɫ����
            case 2:
                return R.style.AppTheme_Black;
            default:
                return R.style.AppTheme;//Ĭ�ϰ�ɫ
        }
    }


    //�л�����
    //��ȻҲ����ʹ����ԴID�����б��
    public static void switchAppTheme( Context ctx){
        String value = Preferences.getString(ctx, "activity_theme", "1");
        switch (Integer.valueOf(value)){
            case 1:
                Preferences.setString(ctx, "activity_theme", "2");
                break;
            case 2:
                Preferences.setString(ctx, "activity_theme", "1");
                break;
            default:
                Preferences.setString(ctx, "activity_theme", "1");
                break;
        }
    }
}
