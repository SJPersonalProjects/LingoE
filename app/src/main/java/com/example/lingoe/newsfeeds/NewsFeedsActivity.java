package com.example.lingoe.newsfeeds;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lingoe.R;

import java.util.ArrayList;

public class NewsFeedsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);



        final ArrayList<NewsfeedsArticles> mNewsFeedsArticlesList = new ArrayList<>();
        mNewsFeedsArticlesList.add(new NewsfeedsArticles(R.drawable.english_learning_tips, getResources().getString(R.string.article_one_title), getResources().getString(R.string.article_one_description)));
        mNewsFeedsArticlesList.add(new NewsfeedsArticles(R.drawable.english_writing_tips, getResources().getString(R.string.article_two_title), getResources().getString(R.string.article_two_description)));
        mNewsFeedsArticlesList.add(new NewsfeedsArticles(R.drawable.english_speaking_tips, getResources().getString(R.string.article_three_title), getResources().getString(R.string.article_three_description)));

        //Log.v(String.valueOf(R.string.article_one_title), "It's working!");

        //RecyclerView.
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.newsfeeds_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        NewsFeedsArticleAdapter mNewsFeedsAdapter = new NewsFeedsArticleAdapter(mNewsFeedsArticlesList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mNewsFeedsAdapter);

        //Click listener to person click events.
        mNewsFeedsAdapter.setOnItemClickListener(new NewsFeedsArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //String titleClicked = String.valueOf(R.string.article_one_title);
                String cardClicked = mNewsFeedsArticlesList.get(position).getArticleTitle();
                Toast.makeText(NewsFeedsActivity.this, cardClicked, Toast.LENGTH_SHORT).show();
                sendData(mNewsFeedsArticlesList.get(position).getArticleTitle(), mNewsFeedsArticlesList.get(position).getArticleDescription());
            }
        });
    }

    public void sendData(String mArticleTitle, String mArticleDescription) {
        Intent intent = new Intent(this, NewsFeedsArticleActivity.class);
        intent.putExtra("articleTitle", mArticleTitle);
        intent.putExtra("articleDescription", mArticleDescription);
        startActivity(intent);
    }
}