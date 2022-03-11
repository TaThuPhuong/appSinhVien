package com.example.htrsinhvin.NEWS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.htrsinhvin.R;

public class PH18428_detail_News extends AppCompatActivity {

    WebView web;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        web = findViewById(R.id.webnew);
        in=getIntent();

        String link = in.getStringExtra("linkid");
        web.loadUrl(link);
        web.setWebViewClient(new WebViewClient());
    }
}