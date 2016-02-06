package com.example.picker.fivepxdemo.UI.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.picker.fivepxdemo.Data.HuiMaiJson;
import com.example.picker.fivepxdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Commodity_info2 extends AppCompatActivity {
    Intent myintent;
    HuiMaiJson myhuimai;
    @Bind(R.id.id_commodity_WebView)
    WebView idCommodityWebView;
    private  int postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_info2);
        ButterKnife.bind(this);
        myintent = getIntent();
        myhuimai = (HuiMaiJson) myintent.getSerializableExtra("huimaijson");
        postion= (int) myintent.getExtras().get("position");
        initView();
    }

    private void initView() {
        WebSettings webSettings=idCommodityWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(true);
        webSettings.setBuiltInZoomControls(false);

      webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        idCommodityWebView.loadUrl(myhuimai.getItem_url().get(postion));
        this.finish();
//        idCommodityWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // idCommodityWebView.loadUrl(url);
//                view.loadUrl(url);
//                return true;
//            }
//        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            if(idCommodityWebView.canGoBack())
            {
                idCommodityWebView.goBack();//返回上一页面
                return true;
            }
            else
            {
                this.finish();
              //  System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
