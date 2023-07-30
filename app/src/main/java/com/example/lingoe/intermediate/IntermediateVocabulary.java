package com.example.lingoe.intermediate;

public class IntermediateVocabulary {
    //Instance fields.
    private String mVocabulary;
    private String mVocabularyPronunciation;
    private String mVocabularyMeaning;
    private int mImageResourceID;
    private int mAudioResourceID;

    /**
     * Constructor to create custom objects of each vocabulary in the list.
     *
     * @param mVocabulary : a word for each item in the list.
     * @param mVocabularyPronunciation : pronunciation of the word.
     * @param mVocabularyMeaning : meaning of the word.
     * @param mImageResourceID : image of each word for better understanding and UI enhancements.
     * @param mAudioResourceID : specific audio of each word for better pronunciation experience.
     */
    public IntermediateVocabulary(String mVocabulary, String mVocabularyPronunciation, String mVocabularyMeaning, int mImageResourceID, int mAudioResourceID) {
        this.mVocabulary = mVocabulary;
        this.mVocabularyPronunciation = mVocabularyPronunciation;
        this.mVocabularyMeaning = mVocabularyMeaning;
        this.mImageResourceID = mImageResourceID;
        this.mAudioResourceID = mAudioResourceID;
    }

    public String getVocabulary() {
        return mVocabulary;
    }

    public String getVocabularyPronunciation() {
        return mVocabularyPronunciation;
    }

    public String getVocabularyMeaning() {
        return mVocabularyMeaning;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }
}
