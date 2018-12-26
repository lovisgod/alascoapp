package com.alascoray.ayo.dsapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class webPageActivity extends AppCompatActivity {


    ProgressBar progressBar;

    public class myWebClient extends WebViewClient {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Toast.makeText(webPageActivity.this, "PLEASE WAIT WHILE THE ACCOUNT LINKING PAGE LOADS", Toast.LENGTH_SHORT).show();
            super.onPageStarted(webView, str, bitmap);

        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            webPageActivity.this.progressBar.setVisibility(View.VISIBLE);
            return true;
        }

        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Toast.makeText(webPageActivity.this, "Your Internet Connection May not be active Or " + error , Toast.LENGTH_LONG).show();
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            webPageActivity.this.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        WebView myWebView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.setWebViewClient(new myWebClient());
        myWebView.loadUrl("file:///android_asset/form.html");
    }
}
