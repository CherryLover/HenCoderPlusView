package me.monster.hencoderplusview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.monster.hencoderplusview.R;


/**
 * @author PVer
 */
public class PieFragment extends Fragment {

    public PieFragment() {
    }

    public static PieFragment newInstance() {
        return new PieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pie, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
