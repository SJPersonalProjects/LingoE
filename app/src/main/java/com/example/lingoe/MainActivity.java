package com.example.lingoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lingoe.advance.AdvanceActivity;
import com.example.lingoe.beginners.BeginnersActivity;
import com.example.lingoe.interactivestories.InteractiveStoryActivity;
import com.example.lingoe.gamesandflashcards.GamesAndFlashcardsActivity;
import com.example.lingoe.interactivebooks.InteractiveBooksActivity;
import com.example.lingoe.intermediate.IntermediateActivity;
import com.example.lingoe.newsfeeds.NewsFeedsActivity;
import com.example.lingoe.playandlearn.PlayAndLearnActivity;
import com.example.lingoe.readingtracker.ReadingTrackerActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<CategoryCard> categoryCardItems = new ArrayList<>();
        categoryCardItems.add(new CategoryCard(R.drawable.cake_slice, "Beginners Practice", "Learn the alphabets of English language..."));
        categoryCardItems.add(new CategoryCard(R.drawable.bulb_icon, "Intermediate Practice", "Increase your vocabulary and nouns..."));
        categoryCardItems.add(new CategoryCard(R.drawable.trophy_icon, "Advance Practice", "Become good at sentences..."));
        categoryCardItems.add(new CategoryCard(R.drawable.interactive_books_icon, "Interactive Books", "Books to speed-up your reading here..."));
        categoryCardItems.add(new CategoryCard(R.drawable.notification_bell_icon, "News Feeds", "Get the latest news from here..."));
        categoryCardItems.add(new CategoryCard(R.drawable.interactive_stories_icon, "Interactive Stories", "Stories to empower your listening here..."));
        categoryCardItems.add(new CategoryCard(R.drawable.play_and_learn_icon, "Play And Learn", "Play and get used to the daily-life objects..."));
        categoryCardItems.add(new CategoryCard(R.drawable.flash_cards_icon, "Games And Flashcards", "Solve the quizzes and earn more rewards..."));
        categoryCardItems.add(new CategoryCard(R.drawable.reading_books_icon, "Reading Tracker", "Track your reading speed and mistakes here..."));


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        CategoryAdapter mAdapter = new CategoryAdapter(categoryCardItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                categoryCardItems.get(position);
                activitySelector(position);
            }
        });
    }

    private void activitySelector(int position) {
        Intent intent;
        switch(position) {
            case 0 :
                intent = new Intent(getApplicationContext(), BeginnersActivity.class);
                startActivity(intent);
            break;
            case 1 :
                intent = new Intent(getApplicationContext(), IntermediateActivity.class);
                startActivity(intent);
                break;
            case 2 :
                intent = new Intent(getApplicationContext(), AdvanceActivity.class);
                startActivity(intent);
                break;
            case 3 :
                intent = new Intent(getApplicationContext(), InteractiveBooksActivity.class);
                startActivity(intent);
                break;
            case 4 :
                intent = new Intent(getApplicationContext(), NewsFeedsActivity.class);
                startActivity(intent);
                break;
            case 5 :
                intent = new Intent(getApplicationContext(), InteractiveStoryActivity.class);
                startActivity(intent);
                break;
            case 6 :
                intent = new Intent(getApplicationContext(), PlayAndLearnActivity.class);
                startActivity(intent);
                break;
            case 7 :
                intent = new Intent(getApplicationContext(), GamesAndFlashcardsActivity.class);
                startActivity(intent);
                break;
            case 8 :
                intent = new Intent(getApplicationContext(), ReadingTrackerActivity.class);
                startActivity(intent);
                break;
        }
    }
}