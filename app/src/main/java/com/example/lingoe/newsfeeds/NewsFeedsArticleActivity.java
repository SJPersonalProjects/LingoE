package com.example.lingoe.newsfeeds;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lingoe.R;

public class NewsFeedsArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds_article);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView mArticleTitle = (TextView) findViewById(R.id.received_article_title);
        TextView mArticleDescription = (TextView) findViewById(R.id.received_article_description);

        //get intent object.
        Intent intent = getIntent();

        String mArticleReceivedTitle = intent.getStringExtra("articleTitle");
        String mArticleReceivedDescription = intent.getStringExtra("articleDescription");

        mArticleTitle.setText(mArticleReceivedTitle);
        mArticleDescription.setText(mArticleReceivedDescription);
    }
}