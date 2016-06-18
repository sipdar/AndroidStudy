package com.example.sunny.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.media.AudioAttributes.CONTENT_TYPE_MUSIC;

/**
 * Created by sunny on 16/6/18.
 */

public class BeatBox {
    private static final  String TAG = "BeatBox";
    private static final  String SOUND_FOLDER = "sample_sounds";
    private static final  int MAX_SOUNDS = 5;
    private AssetManager mManager;
    private List<Sound>  mSoundList = new ArrayList();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);

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
            try {
                String assetPath = SOUND_FOLDER + "/" + filename ;
                Sound sound = new Sound(assetPath);
                loadSound(sound);
                mSoundList.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    public void playSound(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }
    public List<Sound> getSounds() {
        return mSoundList;
    }

    public void loadSound(Sound sound) throws IOException {
        AssetFileDescriptor afd = mManager.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    public void release() {
        mSoundPool.release();
    }
}
