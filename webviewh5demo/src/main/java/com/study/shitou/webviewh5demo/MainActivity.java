package com.study.shitou.webviewh5demo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.study.shitou.webviewh5demo.webviewh5.Main2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @BindView(R.id.bt01) Button mBt01;
    @BindView(R.id.bt02) Button mBt02;
    @BindView(R.id.bt03) Button mBt03;
    @BindView(R.id.bt04) Button mBt04;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/test.html");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsInteration(), "android");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals("file:///android_asset/test2.html")) {
                    Log.e(TAG, "shouldOverrideUrlLoading: " + url);
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    return true;
                } else {
                    mWebView.loadUrl(url);
                    return false;
                }
            }
        });
    }

    //Android调用有返回值js方法
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {

        mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.e(TAG, "onReceiveValue value=" + value);
            }
        });

    }

    public class JsInteration {

        @JavascriptInterface
        public String back() {
            return "hello world";
        }
    }
}

