package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-25 8:10
 */
public class CircleView extends View {

    private int radius = (int) ValueUtil.dpToPixel(100);
    private int padding = (int) ValueUtil.dpToPixel(16);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }
}
