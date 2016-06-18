package com.example.sunny.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 16/6/18.
 */

public class BeatBox {
    private static final  String TAG = "BeatBox";
    private static final  String SOUND_FOLDER = "sample_sounds";
    private AssetManager mManager;
    private List<Sound>  mSoundList = new ArrayList();

    public BeatBox(Context context) {
        mManager = context.getAssets();
        loadSounds();
    }

    private  void  loadSounds() {

        String[] soundNames;
        try{
            soundNames = mManager.list(SOUND_FOLDER);
            Log.i(TAG, "loadSounds ============== : soundNames" + soundNames.length);
        } catch (IOException excepiton) {
            Log.e(TAG, "loadSounds: soundNames" + excepiton);
            return;
        }

        for (String filename : soundNames) {
            String assetPath = SOUND_FOLDER + "/" + filename ;
            Sound sound = new Sound(assetPath);
            mSoundList.add(sound);
        }
    }

    public List<Sound> getSounds() {
        return mSoundList;
    }
    
}
