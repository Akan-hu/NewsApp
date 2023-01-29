package android.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebView extends AppCompatActivity {

    Toolbar toolbar;
    android.webkit.WebView webView;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        bar = findViewById(R.id.progress_bar_web);

        toolbar = findViewById(R.id.toolbar_web);
        webView = findViewById(R.id.web);
         setSupportActionBar(toolbar);
         getSupportActionBar().setTitle("Full Description");
        bar.setVisibility(View.VISIBLE);
         Intent intent = getIntent();
        bar.setVisibility(View.INVISIBLE);
         String url = intent.getStringExtra("url");
         webView.setWebViewClient(new WebViewClient());
         webView.loadUrl(url);

    }
}