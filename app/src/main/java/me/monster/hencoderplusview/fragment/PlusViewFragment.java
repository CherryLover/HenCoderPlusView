package me.monster.hencoderplusview.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.view.FlipboardView;
import me.monster.hencoderplusview.view.PlusView;

/**
 * @author Awesome
 */
public class PlusViewFragment extends Fragment {
    public PlusViewFragment() {
    }

    public static PlusViewFragment newInstance() {
        return new PlusViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_plus_view, container, false);
        PlusView plusView = root.findViewById(R.id.plus_plus);
//        ObjectAnimator animator = ObjectAnimator.ofInt(plusView, "rotateLine", -30);
        ObjectAnimator animator = ObjectAnimator.ofFloat(plusView,"rightFlip",1);
        animator.setDuration(500);
//        animator.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(plusView, "leftFlip", 1);
        animator1.setDuration(500);
        animator1.setStartDelay(300);

        ObjectAnimator animator2 = ObjectAnimator.ofInt(plusView, "allRotate", 270);
        animator2.setDuration(1000);
        animator.setStartDelay(300);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator,animator1, animator2);
        animatorSet.setStartDelay(600);
        animatorSet.start();
        return root;
    }

}
