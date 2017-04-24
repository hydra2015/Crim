package com.hydra.criminalintent;

import android.support.v4.app.Fragment;

import com.hydra.criminalintent.CrimeFragment;
import com.hydra.criminalintent.SingleFragmentActivity;

/**
 * Created by Hydra on 2017/4/19.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new CrimeListFragment();
    }
}
