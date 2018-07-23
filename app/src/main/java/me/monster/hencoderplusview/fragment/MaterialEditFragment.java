package me.monster.hencoderplusview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
        View root =inflater.inflate(R.layout.fragment_material_edit, container, false);
        EditText editText = root.findViewById(R.id.material_edit);
//        editText.setPadding(0, 5, 0, 6);
        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
