package com.example.lingoe.advance;

public class AdvanceSentence {
    //Instance fields.
    private final String mPhrase;
    private final String mPhrasePronunciation;
    private final String mPhraseSentence;
    private final int mIconResourceID;
    private final int mAudioResourceID;

    /**
     * Constructor to create objects new custom object for every different card.
     *
     * @param mPhrase: variable is a main phrase user will study about.
     * @param mPhrasePronunciation: variable is the pronunciation of the phrase.
     * @param mPhraseSentence: variable is the usage of the phrase.
     * @param mIconResourceID: variable is a specific icon used in the phrase card.
     * @param mAudioResourceID: variable is a specific audio file used in the phrase card.
     */
    public AdvanceSentence(String mPhrase, String mPhrasePronunciation, String mPhraseSentence, int mIconResourceID, int mAudioResourceID) {
        this.mPhrase = mPhrase;
        this.mPhrasePronunciation = mPhrasePronunciation;
        this.mPhraseSentence = mPhraseSentence;
        this.mIconResourceID = mIconResourceID;
        this.mAudioResourceID = mAudioResourceID;
    }

    //Accessor Methods.
    public String getPhrase() {
        return mPhrase;
    }

    public String getPhrasePronunciation() {
        return mPhrasePronunciation;
    }

    public String getPhraseSentence() {
        return mPhraseSentence;
    }

    public int getIconResourceID() {
        return mIconResourceID;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }
}
