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
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-31 8:11
 */
public class ImageViewSec extends View {

    private static final String TAG = "ImageViewSec";
    private final float MAX_SCALE = 2;
    float oldCurrentScale;
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

    private float oldBigScale = 0;
    private float smallScale = 0;
    //    private float scaleFraction = 0;
    private float currentScale = 0;
    private boolean big = false;
    private GestureDetector mGestureDetector;
    private OverScroller mOverScroller;
    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector.OnGestureListener mOnGestureListener = new MonsterGestureListener();
    private GestureDetector.OnDoubleTapListener mDoubleTapListener = new MonsterDoubleTapListener();
    private Runnable mRunnable = new MonsterPostOnAnimationRunnable();
    private ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener = new MonsterScaleGestureListener();

    {
        mBitmap = ValueUtil.getBitMap(getResources(), (int) imageWidth);
        imageHeight = mBitmap.getHeight();
    }

    public ImageViewSec(Context context) {
        super(context);
        init(context);
    }

    public ImageViewSec(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, mOnGestureListener);
        mGestureDetector.setOnDoubleTapListener(mDoubleTapListener);
        mOverScroller = new OverScroller(context);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
    }

    private ObjectAnimator getAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale);
        }
        return scaleAnimator;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mGestureDetector.onTouchEvent(event);
        if (!result) {
            result = mScaleGestureDetector.onTouchEvent(event);
        }
        if (!result) {
            result = performClick();
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
//        float scale = big ? bigScale : smallScale;
//        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
//        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        canvas.scale(currentScale, currentScale, getWidth() / 2, getHeight() / 2);
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
        oldBigScale = bigScale;
        currentScale = smallScale;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    class MonsterGestureListener implements GestureDetector.OnGestureListener {

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
            mOverScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, (int) (getWidth() - imageWidth * bigScale) / 2, (int) (imageWidth * bigScale - getWidth()) / 2, (int) (getHeight() - imageHeight * bigScale) / 2, (int) (imageHeight * bigScale - getHeight()) / 2);
            postOnAnimation(mRunnable);
            return false;
        }
    }

    class MonsterDoubleTapListener implements GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (currentScale > smallScale) {
                big = true;
            }
            if (currentScale > smallScale && currentScale < bigScale) {
                bigScale = currentScale;
            } else {
                bigScale = oldBigScale;
            }
            big = !big;
            if (big) {
                offsetX = getWidth() / 2 - e.getX();
                offsetY = getHeight() / 2 - e.getY();
                getAnimator().start();
            } else {
                getAnimator().reverse();
            }
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }

    class MonsterPostOnAnimationRunnable implements Runnable {

        @Override
        public void run() {
            if (mOverScroller.computeScrollOffset()) {
                offsetX = mOverScroller.getCurrX();
                offsetY = mOverScroller.getCurrY();
                invalidate();
                postOnAnimation(mRunnable);
            }
        }
    }

    class MonsterScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentScale = oldCurrentScale * detector.getScaleFactor();
            if (currentScale < smallScale) {
                currentScale = smallScale;
            }
            if (currentScale > bigScale) {
                currentScale = bigScale;
            }
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            oldCurrentScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
