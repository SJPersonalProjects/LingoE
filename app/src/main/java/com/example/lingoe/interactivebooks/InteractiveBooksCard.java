package com.example.lingoe.interactivebooks;

public class InteractiveBooksCard {
    //Instance fields.
    private final int mBookImageResourceID;
    private final String mBookName;
    private final String mPDFName;

    /**
     * Constructor to create a custom object and add in the list.
     * @param mBookImageResourceID: variable is the specific book cover on every book card.
     * @param mBookName: variable is the specific book name on every book card.
     * @param mPDFName: variable is the specific pdf file that user would see.
     */
    public InteractiveBooksCard(int mBookImageResourceID, String mBookName, String mPDFName) {
        this.mBookImageResourceID = mBookImageResourceID;
        this.mBookName = mBookName;
        this.mPDFName = mPDFName;
    }

    //Mutator methods.
    public int getBookImageResourceID() {
        return mBookImageResourceID;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getPDFName() {
        return mPDFName;
    }
}
