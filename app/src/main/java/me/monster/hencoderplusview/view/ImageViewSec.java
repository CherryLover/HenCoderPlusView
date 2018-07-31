package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-31 8:11
 */
public class ImageViewSec extends View implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String TAG = "ImageViewSec";
    private final float MAX_SCALE = 2;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    private ObjectAnimator scaleAnimator;
    private float imageWidth = ValueUtil.dpToPixel(300);
    private float imageHeight;
    private float startOffsetX = 0;
    private float startOffsetY = 0;
    private float offsetX = 0;
    private float offsetY = 0;
    private float bigScale = 0;
    private float smallScale = 0;
    private float scaleFraction = 0;
    private boolean big = false;
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
        mGestureDetector.setOnDoubleTapListener(this);
    }

    public ImageViewSec(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
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
        if (big) {
            Log.e(TAG, "onScroll: distanceX: " + distanceX + " distanceY: " + distanceY);
            //取反是因为，向右、向下移动得到的 distance 为 负，与 canvas.translate 坐标系正好相反.
            offsetX -= distanceX;
            //设置的最大能向左滑动多远
            offsetX = Math.min(offsetX, (imageWidth * bigScale - getWidth()) / 2);
            //设置 最大能向右滑动多远  因为向右滑动是负数  下面的 y同理
            offsetX = Math.max(offsetX, -(imageWidth * bigScale - getWidth()) / 2);
            offsetY -= distanceY;
            offsetY = Math.min(offsetY, (imageHeight * bigScale - getHeight()) / 2);
            offsetY = Math.max(offsetY, -(imageHeight * bigScale - getHeight()) / 2);
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        big = !big;
        if (big) {
            offsetX = getWidth() / 2 - e.getX();
            offsetY = getHeight() / 2 - e.getY();
            getAnimator().start();
        } else {
            getAnimator().reverse();
        }
//        invalidate();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    private ObjectAnimator getAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
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
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
//        float scale = big ? bigScale : smallScale;
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
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
            bigScale = getHeight() / imageHeight * MAX_SCALE;
        } else {
            smallScale = imageWidth / getWidth();
            bigScale = imageHeight / getHeight() * MAX_SCALE;
        }
    }
}
