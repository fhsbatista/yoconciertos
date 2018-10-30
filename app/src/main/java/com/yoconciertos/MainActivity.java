package com.yoconciertos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import static com.yoconciertos.Keys.KEY_LINK_NOTIFICATION;
import static com.yoconciertos.Keys.KEY_TOPIC_SUBSCRIPTION;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic(KEY_TOPIC_SUBSCRIPTION);

        mWebView = findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String link = "https://yoconciertos.com";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if((extras.getString(KEY_LINK_NOTIFICATION)) != null){
                link = extras.getString(KEY_LINK_NOTIFICATION);
            }
        }
        mWebView.loadUrl(link);
        mWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack())
            mWebView.goBack();
        else
            super.onBackPressed();
    }
}
