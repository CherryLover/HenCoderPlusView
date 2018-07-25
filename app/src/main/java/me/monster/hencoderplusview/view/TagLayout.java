package me.monster.hencoderplusview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author Awesome
 * @description
 * @date 2018-07-25 8:11
 */
public class TagLayout extends ViewGroup {

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
