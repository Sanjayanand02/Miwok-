package com.example.android.miwok;

public class Word {

    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;

    /**
     * Miwok translation for the word
     */
    private String mMiwokTranslation;
    private int mAudioid;

    private int mImageResourceId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * +     * Create a new Word object.
     * +     *
     * +     * @param defaultTranslation is the word in a language that the user is already familiar with
     * +     *                           (such as English)
     * +     * @param miwokTranslation is the word in the Miwok language
     * +
     */
    public Word(String defaultTranslation, String miwokTranslation,int Audioid) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioid = Audioid;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int Audioid) {
               mDefaultTranslation = defaultTranslation;
               mMiwokTranslation = miwokTranslation;
               mImageResourceId = imageResourceId;
               mAudioid = Audioid;
            }

    /**
     * +     * Get the default translation of the word.
     * +
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * +     * Get the Miwok translation of the word.
     * +
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
               return mImageResourceId;
          }
    public boolean hasImage() {
                return mImageResourceId != NO_IMAGE_PROVIDED;
            }
            public  int getmAudioid(){ return mAudioid;}

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mAudioid=" + mAudioid +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
}