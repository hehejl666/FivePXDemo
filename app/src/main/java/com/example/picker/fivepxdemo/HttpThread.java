package com.example.picker.fivepxdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 17930 on 2016/2/3.
 */
public class HttpThread extends Thread {

    private String url;
    private String postdata;
    private int doflag;
    private String Httprequest=null;

    public HttpThread(String url) {
        this.url = url;
        doflag=0;


    }
    public HttpThread(String url, String postdata) {
        this.url = url;
        this.postdata = postdata;
        doflag=1;


    }

    public String getHttprequest() {
        return Httprequest;
    }

    public void setHttprequest(String httprequest) {
        Httprequest = httprequest;
    }

    private void doPOST() {
        try {
            URL httpurl=new URL(url);

            HttpURLConnection conn= (HttpURLConnection)httpurl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            OutputStream out=conn.getOutputStream();
            String data=postdata;
            out.write(data.getBytes());
            BufferedReader bfread=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sgb=new StringBuffer();
            while ((str=bfread.readLine())!=null){
                sgb.append(str);
            }



            this.setHttprequest(sgb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doGET() {
        try {
            URL httpurl=new URL(url);

            HttpURLConnection conn= (HttpURLConnection)httpurl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader bfread=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sgb=new StringBuffer();
            while ((str=bfread.readLine())!=null){
                sgb.append(str);
            }


            Httprequest=sgb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (doflag){
            case 0:{
                doGET();
                //Log.e("request", this.getHttprequest());
                break;
            }
            case 1:{
                doPOST();
               // Log.e("request", this.getHttprequest());
                break;
            }
        }
        Log.e("request", Httprequest);


    }
}
