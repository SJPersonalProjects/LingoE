package com.example.lingoe.intermediate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lingoe.R;

import java.util.ArrayList;

public class IntermediateActivity extends AppCompatActivity {

    private RecyclerView mIntermediateVocabularyRecyclerView;
    private IntermediateVocabularyAdapter mIntermediateVocabularyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Handles the playback of all the audio files.
    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the media player has finished playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayerResources();
        }
    };

    //Handles the audio focus when playing a sound file.
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                /**
                 * AUDIOFOCUS_LOSS_TRANSIENT : case means we've lost the audio focus for a short/impermanent amount of time
                 * so we can pause the audio focus and start from the beginning since the files are short.
                 *
                 * AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK : case means we've lost the audio focus for a short/impermanent amount of time
                 *                          where the loser/programmer of the audio focus lower its output volume if its want to continue playing
                 *                          (also referred to ask "Ducking") as the new focus owner doesn't require others to be silent.
                 */
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                /**
                 * AUDIOFOCUS_GAIN : case means we've regained the audio focus and can resume the playback.
                 */
                mMediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                /**
                 * AUDIOFOCUS_LOSS : case means we've lost the audio focus for a unknown period of time
                 *              so we can stop the playback and cleanup the resources.
                 */
                releaseMediaPlayerResources();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Getting audio services from the system services.
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<IntermediateVocabulary> intermediateVocabularyList = new ArrayList<>();
        intermediateVocabularyList.add(new IntermediateVocabulary("All", "/ɔːl/","Everyone in a group.", R.drawable.all, R.raw.all));
        intermediateVocabularyList.add(new IntermediateVocabulary("And", "/ənd,(ə)n,and/", "A conjunction that joins parts of speech together in a sentence.", R.drawable.and, R.raw.and));
        intermediateVocabularyList.add(new IntermediateVocabulary("Boy", "/bɔɪ/", "A male child.", R.drawable.boy, R.raw.boy));
        intermediateVocabularyList.add(new IntermediateVocabulary("Book", "/bʊk/", "A long text of words that people read.", R.drawable.book, R.raw.book));
        intermediateVocabularyList.add(new IntermediateVocabulary("Call", "/kɔːl/", "To yell out or speak loudly; to contact someone by phone.", R.drawable.call, R.raw.call));
        intermediateVocabularyList.add(new IntermediateVocabulary("Car", "/kɑː/", "A four-wheeled vehicle that transport people from one place to another.", R.drawable.car, R.raw.car));
        intermediateVocabularyList.add(new IntermediateVocabulary("Chair", "/tʃɛː/", "A piece of furniture that can hold one person.", R.drawable.chair, R.raw.chair));
        intermediateVocabularyList.add(new IntermediateVocabulary("Child", "/tʃʌɪld/", "Young pupil who haven't reached adulthood.", R.drawable.child, R.raw.child));
        intermediateVocabularyList.add(new IntermediateVocabulary("City", "/ˈsɪti/", "A place where many people live.", R.drawable.city, R.raw.city));
        intermediateVocabularyList.add(new IntermediateVocabulary("Dog", "/dɒɡ/", "An animal that many people have as household pet.", R.drawable.dog, R.raw.dog));
        intermediateVocabularyList.add(new IntermediateVocabulary("Door", "/dɔː/", "A passageway from which you can enter or exit a room or a building.", R.drawable.door, R.raw.door));
        intermediateVocabularyList.add(new IntermediateVocabulary("Enemy", "/ˈɛnəmi/", "An opposite of a friend, a competitor or a rival.", R.drawable.enemy, R.raw.enemy));
        intermediateVocabularyList.add(new IntermediateVocabulary("End", "/ɛnd/", "To finish something or come to a conclusion", R.drawable.finish, R.raw.end));
        intermediateVocabularyList.add(new IntermediateVocabulary("Enough", "/ɪˈnʌf/", "To have more than one need of something.", R.drawable.enough, R.raw.enough));
        intermediateVocabularyList.add(new IntermediateVocabulary("Eat", "/iːt/", "To consume food.", R.drawable.eat, R.raw.eat));
        intermediateVocabularyList.add(new IntermediateVocabulary("Friend", "/frɛnd/", "Someone on your side and with whom you enjoy spending time.", R.drawable.friend, R.raw.friend));
        intermediateVocabularyList.add(new IntermediateVocabulary("Father", "/ˈfɑːðə/", "A male parent.", R.drawable.father, R.raw.father));
        intermediateVocabularyList.add(new IntermediateVocabulary("Go", "/ɡəʊ/", "To travel to and from a location.", R.drawable.go, R.raw.go));
        intermediateVocabularyList.add(new IntermediateVocabulary("Good", "/ɡʊd/", "To behave well or in a kind manner.", R.drawable.good, R.raw.good));
        intermediateVocabularyList.add(new IntermediateVocabulary("Girl", "/ɡəːl/", "A female child.", R.drawable.girl, R.raw.girl));
        intermediateVocabularyList.add(new IntermediateVocabulary("Food", "/fuːd/", "An edible substance that people, animals and plants eat to live.", R.drawable.food, R.raw.food));
        intermediateVocabularyList.add(new IntermediateVocabulary("Hear", "/hɪə/", "To listen to something.", R.drawable.hear, R.raw.hear));
        intermediateVocabularyList.add(new IntermediateVocabulary("House", "/haʊs/", "A place where people, often families live.", R.drawable.house, R.raw.house));
        intermediateVocabularyList.add(new IntermediateVocabulary("Inside", "/ɪnˈsʌɪd/", "The internal part of something.", R.drawable.inside, R.raw.inside));
        intermediateVocabularyList.add(new IntermediateVocabulary("Laugh", "/lɑːf/", "To express that you find something amusing.", R.drawable.laugh, R.raw.laugh));
        intermediateVocabularyList.add(new IntermediateVocabulary("Listen", "/ˈlɪs(ə)n/", "To hear something.", R.drawable.listen, R.raw.listen));
        intermediateVocabularyList.add(new IntermediateVocabulary("Man", "/man/", "An adult male.", R.drawable.man, R.raw.man));
        intermediateVocabularyList.add(new IntermediateVocabulary("Name", "/neɪm/", "The title of a place, book, person etc.", R.drawable.name, R.raw.name));
        intermediateVocabularyList.add(new IntermediateVocabulary("Never", "/ˈnɛvə/", "Not ever.", R.drawable.never, R.raw.never));
        intermediateVocabularyList.add(new IntermediateVocabulary("Next", "/nɛkst/", "The thing that happens after something else in a sequence.", R.drawable.next, R.raw.next));
        intermediateVocabularyList.add(new IntermediateVocabulary("New", "/njuː/", "Unused or unopened", R.drawable.new_image, R.raw.new_voice));
        intermediateVocabularyList.add(new IntermediateVocabulary("Noise", "/nɔɪz/", "Loud sounds, especially made by music or a group of people.", R.drawable.noise, R.raw.noise));
        intermediateVocabularyList.add(new IntermediateVocabulary("Often", "/ˈɒf(ə)n,ˈɒft(ə)n/", "To happen frequently.", R.drawable.often, R.raw.often));
        intermediateVocabularyList.add(new IntermediateVocabulary("Pair", "/pɛː/", "Two things that go together.", R.drawable.pair, R.raw.pair));
        intermediateVocabularyList.add(new IntermediateVocabulary("Pick", "/pɪk/", "To choose or select.", R.drawable.pick, R.raw.pick));
        intermediateVocabularyList.add(new IntermediateVocabulary("Play", "/pleɪ/", "To have fun with someone or engage in an activity or sport.", R.drawable.play, R.raw.play));
        intermediateVocabularyList.add(new IntermediateVocabulary("Room", "/ruːm,rʊm/", "A part of a home, building, office or another structure.", R.drawable.room, R.raw.room));
        intermediateVocabularyList.add(new IntermediateVocabulary("See", "/siː/", "To watch or observe something.", R.drawable.see, R.raw.see));
        intermediateVocabularyList.add(new IntermediateVocabulary("Sell", "/sɛl/", "To offer a service or a good for a price.", R.drawable.sell, R.raw.sell));
        intermediateVocabularyList.add(new IntermediateVocabulary("Sit", "/sɪt/", "To rest on a floor, chair or another surface.", R.drawable.sit, R.raw.sit));
        intermediateVocabularyList.add(new IntermediateVocabulary("Speak", "/spiːk/", "To say something.", R.drawable.speak, R.raw.speak));
        intermediateVocabularyList.add(new IntermediateVocabulary("Smile", "/smʌɪl/", "To grin or show pleasure.", R.drawable.smile, R.raw.smile));
        intermediateVocabularyList.add(new IntermediateVocabulary("Sister", "/ˈsɪstə/", "The female child in relation to another children of the same parents.", R.drawable.sister, R.raw.sister));
        intermediateVocabularyList.add(new IntermediateVocabulary("Think", "/θɪŋk/", "To contemplate something or have an idea or belief.", R.drawable.think, R.raw.think));
        intermediateVocabularyList.add(new IntermediateVocabulary("Then", "/ðɛn/", "Something that comes after an event in a sequence.", R.drawable.then, R.raw.then));
        intermediateVocabularyList.add(new IntermediateVocabulary("Walk", "/wɔːk/", "To travel on foot.", R.drawable.walk, R.raw.walk));
        intermediateVocabularyList.add(new IntermediateVocabulary("Water", "/ˈwɔːtə/", "A substance plants, people animals, and the earth need to survive.", R.drawable.water, R.raw.water));
        intermediateVocabularyList.add(new IntermediateVocabulary("Work", "/wəːk/", "To make a living, engage in an activity for pay, or to reach a goal.", R.drawable.work, R.raw.work));
        intermediateVocabularyList.add(new IntermediateVocabulary("Write", "/rʌɪt/", "To put something on paper with a pen or pencil.", R.drawable.write, R.raw.write));
        intermediateVocabularyList.add(new IntermediateVocabulary("Woman", "/ˈwʊmən/", "A female adult.", R.drawable.woman, R.raw.woman));


        mIntermediateVocabularyRecyclerView = (RecyclerView) findViewById(R.id.intermediate_recycler_view);
        mIntermediateVocabularyRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mIntermediateVocabularyAdapter = new IntermediateVocabularyAdapter(intermediateVocabularyList);

        mIntermediateVocabularyRecyclerView.setLayoutManager(mLayoutManager);
        mIntermediateVocabularyRecyclerView.setAdapter(mIntermediateVocabularyAdapter);

        mIntermediateVocabularyAdapter.setOnItemClickListener(new IntermediateVocabularyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                IntermediateVocabulary currentItemSelected = intermediateVocabularyList.get(position);


                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //use the music stream.
                        AudioManager.STREAM_MUSIC,
                        //request the temporary focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We've the audio focus now...
                    playAudio(currentItemSelected);
                }
            }
        });
    }

    /**
     * Method to create a MediaPlayer object and play a specific audio file when clicked by the user.
     * @param currentVocabulary : variable is used to pass the current object selected by the user.
     */
    private void playAudio(IntermediateVocabulary currentVocabulary) {
        /**
         * Release the media player resource if it exists
         * because we're about to play a different sound file.
         */
        releaseMediaPlayerResources();
        /**
         * Create and setup the {@Link MediaPlayer} for the audio resource
         * associated with the current vocabulary word.
         */
        if(mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, currentVocabulary.getAudioResourceID());
        }

        /**
         * Setup a listener on the media player so that we can stop and release
         * the media player once the sound file has finished playing.
         */
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

        //Start the audio file...
        mMediaPlayer.start();
    }

    //Method to release the media player resources when the audio file length has reached to an end.
    private void releaseMediaPlayerResources() {
        if(mMediaPlayer != null) {
            /**
             * Regardless of the current state of the media player release its resources
             * because we no longer need them.
             */
            mMediaPlayer.release();

            /**
             * Set the media player back to null. For our code, we've decided that
             * setting the media player to null is an easy way to tell that the media player
             * is not configured to play the audio file at the moment.
             */
            mMediaPlayer = null;

            /**
             * Regardless of whether or not we were granted the audio focus, abandon it. This also
             * unregisters the mOnAudioFocusChangeListener so we don't get anymore callbacks.
             */
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped release its resources
        //because we won't be playing any more sounds.
        releaseMediaPlayerResources();
    }
}