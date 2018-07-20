package me.monster.hencoderplusview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.monster.hencoderplusview.R;


/**
 * @author PVer
 */
public class ImageTextFragment extends Fragment {
    public ImageTextFragment() {
    }


    public static ImageTextFragment newInstance() {
        return new ImageTextFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_text, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
