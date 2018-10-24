package me.monster.hencoderplusview.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.view.RadarView;

/**
 * @description
 * @author: jiangjiwei
 * Created in  2018/10/23 17:53
 */
public class RadarFragment extends Fragment {
    ObjectAnimator sweepAnimator;


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
        RadarView radarView = rootView.findViewById(R.id.radar_view);
        Button startAni = rootView.findViewById(R.id.btn_start_ani);
        Button stopAni = rootView.findViewById(R.id.btn_stop_ani);

        sweepAnimator = ObjectAnimator.ofFloat(radarView, "sweepStartAngle", 0, 360);
        sweepAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        sweepAnimator.setRepeatMode(ObjectAnimator.RESTART);
        sweepAnimator.setDuration(1500);
        Interpolator interpolator = new LinearInterpolator();
        sweepAnimator.setInterpolator(interpolator);

        startAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sweepAnimator.isPaused()) {
                    sweepAnimator.resume();
                } else {
                    sweepAnimator.start();
                }
            }
        });

        stopAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sweepAnimator.isRunning()) {
                    sweepAnimator.pause();
                }
            }
        });
        return rootView;
    }
}
