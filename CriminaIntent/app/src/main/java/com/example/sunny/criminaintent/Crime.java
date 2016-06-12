package com.example.sunny.criminaintent;

import java.security.PrivateKey;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sunny on 16/6/12.
 */

public class Crime {
    private  String mTitle;
    private UUID mID;
    private Date mDate;
    private boolean mSolved;


    public Crime() {
        mID = UUID.randomUUID();
        mDate = new Date();
    }


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
