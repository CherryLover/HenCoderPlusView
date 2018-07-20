package me.monster.hencoderplusview.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.view.SportsView;

/**
 * @author PVer
 */
public class SportFragment extends Fragment {

    public SportFragment() {
    }


    public static SportFragment newInstance() {
        return new SportFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_sport, container, false);
        final EditText editText = root.findViewById(R.id.et_sport);
        final SportsView sportsView = root.findViewById(R.id.sport_sport);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    int number = Integer.parseInt(editText.getText().toString().trim());
                    ObjectAnimator animator = ObjectAnimator.ofFloat(sportsView, "value", number);
                    animator.start();
                    sportsView.setValue(number);
                }
                return false;
            }
        });
        return root;
    }
}
