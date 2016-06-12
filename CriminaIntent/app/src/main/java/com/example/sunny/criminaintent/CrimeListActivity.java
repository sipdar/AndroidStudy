package com.example.sunny.criminaintent;

import android.support.v4.app.Fragment;

/**
 * Created by sunny on 16/6/12.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
