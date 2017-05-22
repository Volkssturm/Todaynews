package com.example.mymvp2.persenter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * com.example.mymvp2.persenter
 * 徐世辉  1503A
 * 类作用：本类---
 * 思路：
 * 1.
 * 2.
 * 3.
 * 2017/5/19 21:35
 */

public class Fristpersenter {
    public   String getitems(String url)  {
        try {
            URL url1=new URL(url);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url1.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);

//            // 要发送给服务器的请求的实体内容
//            OutputStream os = httpURLConnection.getOutputStream();
//            os.write(("channelId="+channelId+"&startNum="+startNum).getBytes());
//            PrintWriter writer = new PrintWriter(os);
//            //把数据刷出去
//            writer.flush();

            int code= httpURLConnection.getResponseCode();
            if (code==200){
                InputStream inputStream= httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                byte[] b =new byte[1024];
                int read = 0;
                while ((read = inputStream.read(b))!= -1){
                    byteArrayOutputStream.write(b,0,read);
                }
                return byteArrayOutputStream.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
