package me.monster.hencoderplusview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.view.DashView;


/**
 * @author PVer
 */
public class DashFragment extends Fragment {

    public DashFragment() {
    }

    public static DashFragment newInstance() {
        return new DashFragment();
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
        View root =inflater.inflate(R.layout.fragment_dash, container, false);
        final DashView dashView = root.findViewById(R.id.dash_test);
        final EditText editText = root.findViewById(R.id.et_dash);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    int number = Integer.parseInt(editText.getText().toString().trim());
                    dashView.setValue(number);
                }
                return false;
            }
        });
        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
