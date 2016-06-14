package com.example.sunny.criminaintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sunny on 16/6/12.
 */

public class CrimeLab {
    private static CrimeLab mCrimeLab;
    private List<Crime> mCrimes;

    public static  CrimeLab get(Context context) {
        if (mCrimeLab == null) {
            mCrimeLab = new CrimeLab(context);
        }
        return  mCrimeLab;
    }



    private CrimeLab(Context context) {
        mCrimes = new ArrayList<Crime>();
//        for (int i = 0; i < 100 ; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
    }
    public void addCrime(Crime crime) {
        mCrimes.add(crime);
    }

    public List<Crime> getCrimes() {
        return  mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime: mCrimes) {
            if (crime.getID().equals(id)) {
                return  crime;
            }
        }

        return null;
        
    }
}
