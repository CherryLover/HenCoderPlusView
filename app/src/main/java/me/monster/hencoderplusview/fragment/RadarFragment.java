package me.monster.hencoderplusview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.view.RadarView;

/**
 * @description
 * @author: jiangjiwei
 * Created in  2018/10/23 17:53
 */
public class RadarFragment extends Fragment {
    RadarView radarView;

    public RadarFragment() {
    }

    public static RadarFragment newInstance() {
        return new RadarFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_radar, container, false);
        radarView = rootView.findViewById(R.id.radar_view);

        radarView.setShowReference(false);

        rootView.findViewById(R.id.btn_start_ani).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radarView.setShowReference(true);
                radarView.startAnimator();
            }
        });

        rootView.findViewById(R.id.btn_stop_ani).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radarView.pauseAnimator();
            }
        });

        rootView.findViewById(R.id.btn_cancel_ani).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radarView.cancelAnimator();
            }
        });
        return rootView;
    }
}
