package me.monster.hencoderplusview.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.monster.hencoderplusview.ImageActivity;
import me.monster.hencoderplusview.R;

/**
 * @author Awesome
 */
public class ImageViewerFragment extends Fragment {

    public static ImageViewerFragment newInstance() {
        return new ImageViewerFragment();
    }

    public ImageViewerFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_image_viewer, container, false);
        Button button = rootView.findViewById(R.id.btn_toImage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.start(ImageViewerFragment.this.getContext());
            }
        });
        return rootView;
    }

}
