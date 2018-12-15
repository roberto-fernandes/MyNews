package robfernandes.xyz.mynews.Controller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import robfernandes.xyz.mynews.R;

public class NewsDisplayActivity extends AppCompatActivity {

    protected String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);

        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        WebView webview = findViewById(R.id.activity_news_web_view);
        webview.setWebViewClient(new WebViewClient()); //to display on the app ot in some browser
        webview.loadUrl(url);
    }
}
