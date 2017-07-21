package android.com.kisannetwork.adapters;

/**
 * Created by shobhit on 21/7/17.
 */


import android.com.kisannetwork.R;
import android.com.kisannetwork.fragments.PlaceholderFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private int tabIcons[] = {R.drawable.ic_contacts_black_24dp, R.drawable.ic_textsms_black_24dp};

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Contacts";
            case 1:
                return "SMS";
        }
        return null;
    }
}