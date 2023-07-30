package com.example.lingoe;

public class CategoryCard {
    /**
     * Instance fields.
     * Specific image for each card in the category list.
     */
    private final int mImageResourceID;

    //Title for each card in the category list.
    private final String mTitle;

    //Description for each card in the category list.
    private final String mDescription;

    /**
     * Constructor to create new object for each card in the category list.
     *
     * @param mImageResourceID : Specific image resource ID for each card.
     * @param mTitle : Different title for each card.
     * @param mDescription : Different description for each card.
     */
    public CategoryCard(int mImageResourceID, String mTitle, String mDescription) {
        this.mImageResourceID = mImageResourceID;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    /**
     * Getting image resource ID for any time its needed.
     * @return : image resource ID.
     */
    public int getImageResourceID() {
        return mImageResourceID;
    }

    /**
     * Getting title for any time its needed.
     * @return : title.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Getting description for any time its needed.
     * @return : description.
     */
    public String getDescription() {
        return mDescription;
    }
}
