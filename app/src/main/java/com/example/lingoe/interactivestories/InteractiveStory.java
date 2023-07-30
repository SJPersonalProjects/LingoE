package com.example.lingoe.interactivestories;

public class InteractiveStory {
    //Instance fields.
    private final int mInteractiveStoryCoverID;
    private final String mInteractiveStoryName;
    private final String mInteractiveStoryWriter;
    private final int mInteractiveStoryAudioID;

    /**
     * Constructor to create a custom object for every specific item in the list.
     * @param mInteractiveStoryCoverID: variable is the specific ID for every new image in the items list.
     * @param mInteractiveStoryName: variable is the name of the story in every item in the list.
     * @param mInteractiveStoryWriter: variable is the name of the writer of the same story in the items list.
     */
    public InteractiveStory(int mInteractiveStoryCoverID, String mInteractiveStoryName,
                            String mInteractiveStoryWriter, int mInteractiveStoryAudioID) {
        this.mInteractiveStoryCoverID = mInteractiveStoryCoverID;
        this.mInteractiveStoryName = mInteractiveStoryName;
        this.mInteractiveStoryWriter = mInteractiveStoryWriter;
        this.mInteractiveStoryAudioID = mInteractiveStoryAudioID;
    }

    //Empty constructor to create an empty arguments constructor.
    public InteractiveStory() {
        //empty constructor.
        mInteractiveStoryAudioID = 0;
        mInteractiveStoryCoverID = 0;
        mInteractiveStoryName = null;
        mInteractiveStoryWriter = null;
    }

    //Mutator functions.
    public int getInteractiveStoryCoverID() {
        return mInteractiveStoryCoverID;
    }
    public String getInteractiveStoryName() {
        return mInteractiveStoryName;
    }
    public String getInteractiveStoryWriter() {
        return mInteractiveStoryWriter;
    }
    public int getInteractiveStoryAudioID() {
        return mInteractiveStoryAudioID;
    }
}
