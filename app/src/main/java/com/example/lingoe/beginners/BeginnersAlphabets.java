package com.example.lingoe.beginners;

public class BeginnersAlphabets {
    /**
     * Instance fields.
     * Specific of each item in the list.
     */
    private String mAlphabet;

    //Specific pronounciation of each item in the list.
    private String mPronunciation;

    //Specific audio file for each alphabet in the list.
    private int mAudioResourceID;

    /**
     * Constructing overloading here...
     * Constructor with no parameters, making an empty object.
     */
    public BeginnersAlphabets() {
        //to create a null object.
    }

    /**
     * Constructor with 3 parameters.
     * @param mAlphabet: is the alphabet in the card of the grid.
     * @param mPronunciation: is the written pronunciation of the alphabet in the grid.
     * @param mAudioResourceID: is the audio of the pronunciation of the alphabet to listen.
     */
    public BeginnersAlphabets(String mAlphabet, String mPronunciation, int mAudioResourceID) {
        this.mAlphabet = mAlphabet;
        this.mPronunciation = mPronunciation;
        this.mAudioResourceID = mAudioResourceID;
    }

    public String getAlphabet() {
        return mAlphabet;
    }

    public String getPronunciation() {
        return mPronunciation;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }
}
