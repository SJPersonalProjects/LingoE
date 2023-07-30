package com.example.lingoe.interactivestories;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lingoe.R;

import java.util.ArrayList;

public class InteractiveStoryActivity extends AppCompatActivity {

    //Handles the playback of the audio files.
    private MediaPlayer mMediaPlayer;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayerResources();
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_story);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        final ArrayList<InteractiveStory> mInteractiveCardList = new ArrayList<>();
        mInteractiveCardList.add(new InteractiveStory(R.drawable.my_old_home, "My Old Home", "Lu Hsun",
                R.raw.my_old_home));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.a_hunger_artist, "A Hunger Artist", "Franz Kafka",
                R.raw.a_hunger_artist));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.the_man_who_planted_trees, "The Man Who Planted Trees", "Jean Giono",
                R.raw.the_man_who_planted_trees));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.the_last_leaf, "The Last Leaf", "O'Henry",
                R.raw.the_last_leaf));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.a_painful_memory_from_childhood, "A Painful Memory From Childhood", "Bjornstjerne Bjornson",
                R.raw.the_painful_memory_from_childhood));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.sophistication, "Sophistication", "S. Anderson",
                R.raw.sophistication));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.the_womans_rose, "The Woman's Rose", "Olive Schreiner",
                R.raw.the_womans_rose));
        mInteractiveCardList.add(new InteractiveStory(R.drawable.love_in_prison, "Love In Prison", "Victor Hugo",
                R.raw.love_in_prison));

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.interactive_story_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        InteractiveStoryAdapter mAdapter = new InteractiveStoryAdapter(this, mInteractiveCardList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new InteractiveStoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(InteractiveStoryActivity.this, mInteractiveCardList.get(position).getInteractiveStoryName(), Toast.LENGTH_SHORT).show();
                InteractiveStory currentInteractiveStoryCard = mInteractiveCardList.get(position);
                playStory(currentInteractiveStoryCard);
            }
        });
    }

    //Story play and pause code here...
    private void playStory(InteractiveStory mInteractiveStory) {
        releaseMediaPlayerResources();
        if(mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, mInteractiveStory.getInteractiveStoryAudioID());
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        }
        mMediaPlayer.start();
    }

    private void releaseMediaPlayerResources() {
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayerResources();
    }
}