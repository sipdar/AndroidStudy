package com.example.sunny.criminaintent;

import java.util.UUID;

/**
 * Created by sunny on 16/6/12.
 */

public class Crime {
    private  String mTitle;
    private UUID mID;

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

    public Crime() {
        mID = UUID.randomUUID();
    }

}
