package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-25 8:11
 */
public class TagLayout extends ViewGroup {

    private Rect[] childs;

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = 0;
        int maxHeight = 0;
        int widthUsed = 0;
        int heightUsed = getPaddingTop();
        int childCount = getChildCount();

        if (childs == null) {
            childs = new Rect[childCount];
        } else if (childs.length < childCount) {
            childs = Arrays.copyOf(childs, childCount);
        }

        // TODO: 2018-07-30 增加 margin
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childRect = childs[i];
            measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec,
                    heightUsed);
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED && childWidth + widthUsed >= parentWidth) {
                widthUsed = 0;
                heightUsed += maxHeight;
                heightUsed += ValueUtil.dpToPixel(8);
                maxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            }
            if (childRect == null) {
                childRect = childs[i] = new Rect();
            }
            childRect.set(widthUsed, heightUsed, widthUsed + child.getMeasuredWidth(), heightUsed
                    + child.getMeasuredHeight());
            widthUsed += child.getMeasuredWidth();
            maxWidth = Math.max(maxWidth, widthUsed);
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }
        int width = maxWidth;
        int height = heightUsed + maxHeight + getPaddingBottom();
        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, 0), resolveSizeAndState
                (height, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childRect = childs[i];
            child.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
