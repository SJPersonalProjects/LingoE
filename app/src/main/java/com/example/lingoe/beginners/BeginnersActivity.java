package com.example.lingoe.beginners;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lingoe.R;

import java.util.ArrayList;

public class BeginnersActivity extends AppCompatActivity {

    //Handles playback of all the audio files.
    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@Link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayerResources();
        }
    };

    //Handles the audio focus when playing a sound file.
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@Link AudioManager} audio focus
     * on the device is changed.
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                /**
                 * AUDIOFOCUS_LOSS_TRANSIENT : case means we've lost the audio focus for a short/impermanent amount of time
                 *              so we can pause the audio focus and start from the beginning since the files are short.
                 * AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK : case means we've lost the audio focus for a short/impermanent amount of time
                 *              where the loser/programmer of the audio focus can lower its output volume if it wants to continue playing
                 *              (also referred to as "Ducking") as the new focus owner doesn't require others to be silent.
                 */
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                /**
                 * AUDIOFOCUS_GAIN : case means we've regained the audio focus
                 *                  and can resume the playback.
                 */
                mMediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                /**
                 * AUDIOFOCUS_LOSS : case means we've lost the audio focus for a unknown period of time
                 * so we stop the playback and cleanup the resources.
                 */
                releaseMediaPlayerResources();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginners);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Getting the audio services from the SystemService
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Custom object.
        final ArrayList<BeginnersAlphabets> alphaCardsList = new ArrayList<>();

        //Adding objects to the list.
        alphaCardsList.add(new BeginnersAlphabets("Aa", "/eɪ:/", R.raw.a));
        alphaCardsList.add(new BeginnersAlphabets("Bb", "/bi:/", R.raw.b));
        alphaCardsList.add(new BeginnersAlphabets("Cc", "/siː/", R.raw.c));
        alphaCardsList.add(new BeginnersAlphabets("Dd", "/diː/", R.raw.d));
        alphaCardsList.add(new BeginnersAlphabets("Ee", "/iː/", R.raw.e));
        alphaCardsList.add(new BeginnersAlphabets("Ff", "/ɛf:/", R.raw.f));
        alphaCardsList.add(new BeginnersAlphabets("Gg", "/dʒi/", R.raw.g));
        alphaCardsList.add(new BeginnersAlphabets("Hh", "/eɪtʃ/", R.raw.h));
        alphaCardsList.add(new BeginnersAlphabets("Ii", "/ʌɪ/", R.raw.i));
        alphaCardsList.add(new BeginnersAlphabets("Jj", "/dʒeɪ/", R.raw.j));
        alphaCardsList.add(new BeginnersAlphabets("Kk", "/keɪ/", R.raw.k));
        alphaCardsList.add(new BeginnersAlphabets("Ll", "/ɛl/", R.raw.l));
        alphaCardsList.add(new BeginnersAlphabets("Mm", "/ɛm/", R.raw.m));
        alphaCardsList.add(new BeginnersAlphabets("Nn", "/ɛn/", R.raw.n));
        alphaCardsList.add(new BeginnersAlphabets("Oo", "/əʊ/", R.raw.o));
        alphaCardsList.add(new BeginnersAlphabets("Pp", "/piː/", R.raw.p));
        alphaCardsList.add(new BeginnersAlphabets("Qq", "/kjuː/", R.raw.q));
        alphaCardsList.add(new BeginnersAlphabets("Rr", "/ɑː/", R.raw.r));
        alphaCardsList.add(new BeginnersAlphabets("Ss", "/ɛs/", R.raw.s));
        alphaCardsList.add(new BeginnersAlphabets("Tt", "/tiː/", R.raw.t));
        alphaCardsList.add(new BeginnersAlphabets("Uu", "/juː/", R.raw.u));
        alphaCardsList.add(new BeginnersAlphabets("Vv", "/viː/", R.raw.v));
        alphaCardsList.add(new BeginnersAlphabets("Ww", "/ˈdʌb(ə)lˌjuː/", R.raw.w));
        alphaCardsList.add(new BeginnersAlphabets("Xx", "/ɛks/", R.raw.x));
        alphaCardsList.add(new BeginnersAlphabets());
        alphaCardsList.add(new BeginnersAlphabets("Yy", "/wʌɪ/", R.raw.y));
        alphaCardsList.add(new BeginnersAlphabets("Zz", "/zɛd/", R.raw.z));
        alphaCardsList.add(new BeginnersAlphabets());

        BeginnersAlphabetAdapter adapter = new BeginnersAlphabetAdapter(this, alphaCardsList);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BeginnersAlphabets beginnersAlphabet = alphaCardsList.get(position);
                /**
                 * Only execute the condition when the user clicks
                 * the cards with the music...
                 * Otherwise the playAudio function will be caused to throw the runtime exception.
                 */
                if(position != 24 && position != 27) {
                    int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                //use the music stream.
                                AudioManager.STREAM_MUSIC,
                                //request temporary focus.
                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        //We've the audio focus now...
                        playAudio(beginnersAlphabet);
                    }
                }
            }
        });
    }

    /**
     * Method to create the MediaPlayer object and play the specific audio file when clicked by the user.
     * @param beginnersAlphabets : variable is used to pass the specific resource ID of the audio file
     *                           when clicked by the user.
     */
    private void playAudio(BeginnersAlphabets beginnersAlphabets) {
        //Release the media player resource if it exists
        //because we're about to play a different sound file.
        releaseMediaPlayerResources();

        if(mMediaPlayer == null) {
            /**
             * Create and setup the {@Link MediaPlayer} for the audio resource
             * associated with the current beginnersAlphabets.
             */
            mMediaPlayer = MediaPlayer.create(this, beginnersAlphabets.getAudioResourceID());

            /**
             * Setup a listener on the media player so that we can stop and release
             * the media player once the sound has finished playing.
             */
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        }

        //Start the audio file...
        mMediaPlayer.start();
    }

    //Method to release the media player resources when the audio file length has been reached to the end.
    private void releaseMediaPlayerResources() {
        if(mMediaPlayer != null) {
            /**
             * Regardless of the current state of the media player release its resources
             * because we no longer need it.
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

        //When the activity is stopped release the media player resources
        //because we won't be playing any more sounds.
        releaseMediaPlayerResources();
    }
}