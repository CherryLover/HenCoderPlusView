package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-31 8:11
 */
public class ImageViewSec extends View implements GestureDetector.OnGestureListener {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;

    private float imageWidth = ValueUtil.dpToPixel(300);
    private float imageHeight;

    private float startOffsetX = 0;
    private float startOffsetY = 0;

    private float bigScale = 0;
    private float smallScale = 0;

    private GestureDetector mGestureDetector;


    {
        mBitmap = ValueUtil.getBitMap(getResources(), (int) imageWidth);
        imageHeight = mBitmap.getHeight();
    }

    public ImageViewSec(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, this);
    }

    public ImageViewSec(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mGestureDetector.onTouchEvent(event);
        if (!result) {
            result = performClick();
        }
        return result;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(startOffsetX, startOffsetY);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        startOffsetX = (getWidth() - imageWidth) / 2;
        startOffsetY = (getHeight() - imageHeight) / 2;

        if (imageWidth / imageHeight > (float) getWidth() / getHeight()) {
            smallScale = getWidth() / imageWidth;
            bigScale = getHeight() / imageHeight;
        } else {
            smallScale = imageWidth / getWidth();
            bigScale = imageHeight / getHeight();
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
        return false;
    }
}
