package me.monster.hencoderplusview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.monster.hencoderplusview.R;

public class RoundAvatarFragment extends Fragment {

    public RoundAvatarFragment() {
    }

    public static RoundAvatarFragment newInstance() {
        RoundAvatarFragment fragment = new RoundAvatarFragment();
        return fragment;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_round_avatar, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
