package com.example.lingoe.advance;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.lingoe.R;

import java.util.ArrayList;

public class AdvanceActivity extends AppCompatActivity {

    //Handles the playback of all the audio files.
    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@Link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //Release the MediaPlayer resources once the file reaches the end.
            releaseMediaPlayerResources();
        }
    };

    //Handles the playback of the audio files.
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@Link AudioManager} audio focus on the device is changed.
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                /**
                 * AUDIOFOCUS_LOSS_TRANSIENT: case means we've lost the audio focus for a short/impermanent amount of time
                 *                      so we can pause the audio focus and start from the beginning since the files are short.
                 * AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: case means that we've lost the audio focus for a short and impermanent amount of time
                 *                          where the loser of the audio focus can lower its output volume if its want to continue playing
                 *                          (also referred to as "Ducking") as the new focus owner doesn't require others to be silent.
                 */
                //Pause the audio.
                mMediaPlayer.pause();
                //Play from the very beginning since the files are short.
                mMediaPlayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                /**
                 * AUDIOFOCUS_GAIN: case means that we've regained the audio focus
                 *              and can resume the playback now.
                 */
                mMediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                /**
                 * AUDIOFOCUS_LOSS: case means we've lost the audio focus for an unknown period of time
                 *              so stop the playback and cleanup the resources.
                 */
                releaseMediaPlayerResources();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Getting audio services from the system services.
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<AdvanceSentence> mAdvanceSentenceList = new ArrayList<>();
        mAdvanceSentenceList.add(new AdvanceSentence("Be careful.", "bi ˈkɛrfəl.", "Ex. Be careful, he said when I was signing the property documents.", R.drawable.archeology_icon, R.raw.be_careful));
        mAdvanceSentenceList.add(new AdvanceSentence("Don't worry.", "doʊnt ˈwɜri.", "Ex. Don't worry, we'll face it together.", R.drawable.baloon_icon, R.raw.dont_worry));
        mAdvanceSentenceList.add(new AdvanceSentence("Everyone knows it.", "ɛvriˌwʌn noʊz ɪt.", "Ex. Everyone knows it, he's going to jail.", R.drawable.bag_pack_icon, R.raw.everyone_knows_it));
        mAdvanceSentenceList.add(new AdvanceSentence("Everything is ready.", "ˈɛvriˌθɪŋ ɪz ˈrɛdi.", "Ex. Everything is ready to be taken to the judge.", R.drawable.cannon_icon, R.raw.everything_is_ready));
        mAdvanceSentenceList.add(new AdvanceSentence("Excellent.", "ˈɛksələnt.", "Ex. He did excellent job for the community.", R.drawable.cards_icon, R.raw.excellent));
        mAdvanceSentenceList.add(new AdvanceSentence("From time to time.", "frʌm taɪm tu taɪm.", "Ex. He managed to study from time to time.", R.drawable.company_icon, R.raw.from_time_to_time));
        mAdvanceSentenceList.add(new AdvanceSentence("Good luck.", "gʊd lʌk.", "Ex. The grandma waved and shouted good luck!", R.drawable.crown_icon, R.raw.good_luck));
        mAdvanceSentenceList.add(new AdvanceSentence("Help!", "hɛlp!", "Ex. He really needed help to comeplete the homework before the school bus.", R.drawable.crystalball_icon, R.raw.help));
        mAdvanceSentenceList.add(new AdvanceSentence("He's right.", "hiz raɪt.", "Ex. He's right in his perspective.", R.drawable.cupcake_icon, R.raw.he_is_right));
        mAdvanceSentenceList.add(new AdvanceSentence("How are you?", "haʊ ɑr ju?", "Ex. Teacher said how are you when she saw me.", R.drawable.dog_icon, R.raw.how_are_you));
        mAdvanceSentenceList.add(new AdvanceSentence("Hurry!", "ˈhɜri!", "Ex. Hurry up the captain shouted when the ship started sinking.", R.drawable.fish_icon, R.raw.hurry));
        mAdvanceSentenceList.add(new AdvanceSentence("I don't like it.", "aɪ doʊnt laɪk ɪt.", "Ex. I don't like it when the waiter doesn't serve hot coffee.", R.drawable.flower_icon, R.raw.i_dont_like_it));
        mAdvanceSentenceList.add(new AdvanceSentence("I don't understand.", "aɪ doʊnt ˌʌndərˈstænd.", "Ex. I don't understand a bit when the professor deliver lectures.", R.drawable.food_icon, R.raw.i_dont_understand));
        mAdvanceSentenceList.add(new AdvanceSentence("I know.", "aɪ noʊ.", "Ex. I know that the tallest mountain is situated in Asia.", R.drawable.fortuneteller_icon, R.raw.i_know));
        mAdvanceSentenceList.add(new AdvanceSentence("I like her.", "aɪ laɪk hɜr.", "Ex. I like her fluent English when she speaks, said the professor.", R.drawable.goal_icon, R.raw.i_like_her));
        mAdvanceSentenceList.add(new AdvanceSentence("I'll pay.", "aɪl peɪ.", "Ex. I'll pay, said my friend when I accidentally dropped my wallet.", R.drawable.iron_man_icon, R.raw.i_will_pay));
        mAdvanceSentenceList.add(new AdvanceSentence("I'll take it.", "aɪl teɪk ɪt.", "Ex. I'll take it said the waiter when a woman dropped spoon on the floor.", R.drawable.jake_icon, R.raw.i_will_take_it));
        mAdvanceSentenceList.add(new AdvanceSentence("I lost my watch.", "aɪ lɔst maɪ wɑʧ.", "Ex. I lost my watch! A man shouted angrily at the airport.", R.drawable.kite_icon, R.raw.i_lost_my_watch));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm an American.", "aɪm ən əˈmɛrəkən.", "Ex. I'm an American said the Donald Trump in his speech at the White House.", R.drawable.leaf_icon, R.raw.i_am_an_american));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm gonna leave.", "aɪm ˈgɑnə liv.", "Ex. I'm gonna leave the house shouted my mother when she faught with father.", R.drawable.macbook_icon, R.raw.i_am_gonna_leave));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm good and you?", "aɪm gʊd ænd ju?", "Ex. When I asked about his health he said I'm good and you?", R.drawable.magic_icon, R.raw.i_am_good_and_you));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm happy.", "aɪm ˈhæpi.", "Ex. I'm happy that we're going to have a world tour this summer.", R.drawable.mountain_icon, R.raw.i_am_happy));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm hungry.", "aɪm ˈhʌŋgri.", "Ex. I'm hungry exclaimed a man at the hotel.", R.drawable.parliament_icon, R.raw.i_am_hungry));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm married.", "aɪm ˈmɛrid.", "Ex. I'm married said a young woman when a prankster was hitting on her.", R.drawable.pokeball_icon, R.raw.i_am_married));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm not busy.", "aɪm nɑt ˈbɪzi.", "Ex. I have a lot of time, I am not busy these days said the housekeeper.", R.drawable.pokemon_icon, R.raw.i_am_not_busy));
        mAdvanceSentenceList.add(new AdvanceSentence("I'm not sure.", "aɪm nɑt ʃʊr.", "Ex. I'm not sure how many people died this summer because of heat-stroke.", R.drawable.restaurant_icon, R.raw.i_am_not_sure));
        mAdvanceSentenceList.add(new AdvanceSentence("Is that enough?", "ɪz ðæt ɪˈnʌf?", "Ex. Is that enough detergent in the tub said the house keeper to the owner.", R.drawable.service_icon, R.raw.is_that_enough));
        mAdvanceSentenceList.add(new AdvanceSentence("Just a little.", "ʤʌst ə ˈlɪtəl.", "Ex. My wife took just a little more time to get ready when we're setting to visit friends.", R.drawable.simpson_icon, R.raw.just_a_little));
        mAdvanceSentenceList.add(new AdvanceSentence("Just a moment.", "ʤʌst ə ˈmoʊmənt.", "Ex. Just a moment he said when his phone started ringing.", R.drawable.smurf_icon, R.raw.just_a_moment));
        mAdvanceSentenceList.add(new AdvanceSentence("Let me check.", "lɛt mi ʧɛk.", "Ex. Let me check your bag said officer at the airport.", R.drawable.snowflake_icon, R.raw.let_me_check));
        mAdvanceSentenceList.add(new AdvanceSentence("More than that.", "mɔr ðæn ðæt.", "Ex. More than that amount of money said the dealer as he wanted more commission.", R.drawable.star_icon, R.raw.more_than_that));
        mAdvanceSentenceList.add(new AdvanceSentence("Never mind.", "ˈnɛvər maɪnd.", "Ex. Never mind the words of your parents as they are not from this generation.", R.drawable. stork_icon, R.raw.never_mind));
        mAdvanceSentenceList.add(new AdvanceSentence("No.", "noʊ.", "Ex. We all said no when teacher asked to teach more that day.", R.drawable.sun_icon, R.raw.no));
        mAdvanceSentenceList.add(new AdvanceSentence("Next time.", "nɛkst taɪm.", "Ex. My father said we shall go there next time as it was already late.", R.drawable.supermario_icon, R.raw.next_time));
        mAdvanceSentenceList.add(new AdvanceSentence("No thank you.", "noʊ θæŋk ju.", "Ex. The waiter said no thank you as I offered him a tip.", R.drawable.swinging_boat_icon, R.raw.no_thank_you));
        mAdvanceSentenceList.add(new AdvanceSentence("Nothing else.", "nʌθɪŋ ɛls.", "Ex. Nothing else worries me but the darkness of the nights of the winter.", R.drawable.telescope_icon, R.raw.nothing_else));
        mAdvanceSentenceList.add(new AdvanceSentence("Of course.", "ʌv kɔrs.", "Ex. Of course the teacher will teach us at any cost.", R.drawable.tricycle_icon, R.raw.of_course));
        mAdvanceSentenceList.add(new AdvanceSentence("Okay.", "ˌoʊˈkeɪ.", "Ex. Okay, all these paper will be delivered to the judge.", R.drawable.umbrella_icon, R.raw.okay));
        mAdvanceSentenceList.add(new AdvanceSentence("Really?", "ˈrɪli?", "Ex. Really, said he when I spilled the beans about her.", R.drawable.vr_icon, R.raw.really));
        mAdvanceSentenceList.add(new AdvanceSentence("Right there.", "raɪt ðɛr.", "Ex. It's right there, said the waiter when I asked the washroom directions.", R.drawable.wizard_hat_icon, R.raw.right_there));
        mAdvanceSentenceList.add(new AdvanceSentence("See you later.", "si ju ˈleɪtər.", "Ex. See you later cried my friends when I was leaving for Africa.", R.drawable.cake_slice, R.raw.see_you_later));
        mAdvanceSentenceList.add(new AdvanceSentence("See you tomorrow.", "si ju təˈmɑˌroʊ.", "Ex. See you tomorrow said the principal when I was leaving the school.", R.drawable.jersey_icon, R.raw.see_you_tomorrow));
        mAdvanceSentenceList.add(new AdvanceSentence("She's pretty!", "ʃiz ˈprɪti.", "Ex. She's pretty said my friend at the cafeteria watching a young girl.", R.drawable.arcade_icon, R.raw.she_is_pretty));
        mAdvanceSentenceList.add(new AdvanceSentence("Tell me.", "tɛl mi.", "Ex. Accidentally my father shouted at the house keeper when he couldn't find his watch.", R.drawable.literature_icon, R.raw.tell_me));
        mAdvanceSentenceList.add(new AdvanceSentence("Thank you.", "θæŋk ju.", "Ex. Thank you they said when I served very well.", R.drawable.basket_ball_icno, R.raw.thank_you));
        mAdvanceSentenceList.add(new AdvanceSentence("That's fine.", "ðæts faɪn.", "Ex. That's fine if you forgot the book at home said my teacher.", R.drawable.gaming_controller_icon, R.raw.that_is_fine));
        mAdvanceSentenceList.add(new AdvanceSentence("That's not right.", "ðæts nɑt raɪt.", "Ex. That's not right, shouted the judge at the killer.", R.drawable.horse_riding_icon, R.raw.that_is_not_right));
        mAdvanceSentenceList.add(new AdvanceSentence("He doesn't work.", "hi ˈdʌzənt wɜrk.", "Ex. He doesn't work that good said the manager when he was enquiring yearly report.", R.drawable.knight_icon, R.raw.he_doesnt_work));
        mAdvanceSentenceList.add(new AdvanceSentence("It's too much.", "ɪts tu mʌʧ.", "Ex. It's too much food they said when mother served them.", R.drawable.shuttlecock_icon, R.raw.it_is_too_much));
        mAdvanceSentenceList.add(new AdvanceSentence("Bye bye.", "baɪ baɪ.", "Ex. All of the people cried bye bye and waved to their beloved ones to the ship at the harbour.", R.drawable.terraria_icon, R.raw.bye_bye));

        //Instance fields.
        RecyclerView mRecyclerView = findViewById(R.id.advance_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        AdvanceSentenceAdapter mAdapter = new AdvanceSentenceAdapter(mAdvanceSentenceList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdvanceSentenceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AdvanceSentence currentSentenceCard = mAdvanceSentenceList.get(position);

                //Requesting audio focus.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                //Use the music stream.
                                AudioManager.STREAM_MUSIC,
                                //Request temporary focus.
                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                //In case the audio focus request is granted, play the audio.
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We've the focus now...
                    playAudio(currentSentenceCard);
                }
            }
        });
    }

    /**
     * Method to create the media player object and play the specific audio file when clicked by the user.
     * @param currentSentenceCard: variable refers to the card clicked by the user.
     */
    private void playAudio(AdvanceSentence currentSentenceCard) {
        //Release the media player resources if they exists
        //because we're about to play a different sound file.
        releaseMediaPlayerResources();
        if(mMediaPlayer == null) {
            //Factory method to create a media player object.
            mMediaPlayer = MediaPlayer.create(this, currentSentenceCard.getAudioResourceID());
        }
        /**
         * Setup a listener on the media player so that we can stop and release
         * the media player resources once the sound file has finished playing.
         */
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        //Start the audio file.
        mMediaPlayer.start();
    }
    //Method to release the media player resources once the audio file has reached to the end.
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
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Release the media player resources when the activity has stopped.
        releaseMediaPlayerResources();
    }
}