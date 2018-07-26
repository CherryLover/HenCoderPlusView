package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
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
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode xMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#00D45C"));
        int saveLayer = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);
        canvas.drawCircle(radius + padding, radius + padding, radius, mPaint);
        mPaint.setXfermode(xMode);
        canvas.drawBitmap(ValueUtil.getBitMap(getResources(), radius * 2), padding, padding, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (padding + radius) * 2;
        int height = (padding + radius) * 2;
        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, 0), resolveSizeAndState
                (height, heightMeasureSpec, 0));
    }
}
