package me.monster.hencoderplusview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.view.TagLayout;

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
        View rootView = inflater.inflate(R.layout.fragment_tag_layout, container, false);
        TagLayout tagLayout = rootView.findViewById(R.id.tag_layout);
        Button btnAdd = rootView.findViewById(R.id.btn_add);
        Button btnRemove = rootView.findViewById(R.id.btn_remove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

}
