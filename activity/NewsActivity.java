package com.example.android.menudietas.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.android.menudietas.R;

public class NewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.content_news);



        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.loadUrl("http://dietasmusculacion.com/noticias");

    }

}

   /* private WebView mWebview ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("News");
        setSupportActionBar(toolbar);

        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

       mWebview.setWebViewClient(new WebViewClient() {
           public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
               viewx.loadUrl(urlx);
              return false;
           }
       });

        mWebview .loadUrl("http://www.youtube.com");
    }
    */

