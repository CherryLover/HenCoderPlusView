package me.monster.hencoderplusview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.monster.hencoderplusview.R;

/**
 * @author Awesome
 */
public class TagLayoutFragment extends Fragment {
    public TagLayoutFragment() {
    }

    public static TagLayoutFragment newInstance() {
        return new TagLayoutFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tag_layout, container, false);
    }

}
