package com.cse4471.ohiostate.localloc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Liz on 7/16/2015.
 */
public class CSZBluetoothFragment extends Fragment {
/*
    public static interface OnCompleteListener1 {
        public abstract void onComplete1();
    }
    private OnCompleteListener1 mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener1)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener1");
        }
    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bluetooth_safe_zone, parent, false);
    //    mListener.onComplete1();
        return v;
    }
}
