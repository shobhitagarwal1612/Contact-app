package android.com.kisannetwork.adapters;

/**
 * Created by shobhit on 21/7/17.
 */


import android.com.kisannetwork.fragments.ContactsFragment;
import android.com.kisannetwork.fragments.SentSMSFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ContactsFragment.newInstance();
            case 1:
                return SentSMSFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}