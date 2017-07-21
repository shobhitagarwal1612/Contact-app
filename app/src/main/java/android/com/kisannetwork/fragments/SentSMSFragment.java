package android.com.kisannetwork.fragments;

import android.com.kisannetwork.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shobhit on 21/7/17.
 */

public class SentSMSFragment extends Fragment {

    public SentSMSFragment() {

    }

    public static SentSMSFragment newInstance() {
        return new SentSMSFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        return rootView;
    }
}
