package com.example.lingoe.newsfeeds;

public class NewsfeedsArticles {
    //Instance fields.
    private final int mArticleCoverResourceID;
    private final String mArticleTitle;
    private final String mArticleDescription;

    /**
     * Constructor to create a new custom object for every item in the list.
     * @param mArticleCoverResourceID: variable is the cover for every article card in the list.
     * @param mArticleTitle: variable is the title for every article card in the list.
     * @param mArticleDescription: variable is the description for every article card in the list.
     */
    public NewsfeedsArticles(int mArticleCoverResourceID, String mArticleTitle, String mArticleDescription) {
        this.mArticleCoverResourceID = mArticleCoverResourceID;
        this.mArticleTitle = mArticleTitle;
        this.mArticleDescription = mArticleDescription;
    }

    //Accessor Methods.
    public int getArticleCoverResourceID() {
        return mArticleCoverResourceID;
    }
    public String getArticleTitle() {
        return mArticleTitle;
    }
    public String getArticleDescription() {
        return mArticleDescription;
    }
}
