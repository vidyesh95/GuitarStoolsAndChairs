package com.righttickk.guitarstoolsandchairs.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recycler_table")
public class RecyclerViewItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mText3;

    public RecyclerViewItem(int imageResource, String text1, String text2, String text3) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
    }

    // Get/Set field values
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public String getText3() {
        return mText3;
    }
}
