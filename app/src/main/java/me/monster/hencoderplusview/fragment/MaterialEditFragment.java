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
public class MaterialEditFragment extends Fragment {

    public MaterialEditFragment() {
    }


    public static MaterialEditFragment newInstance() {
        return new MaterialEditFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material_edit, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
