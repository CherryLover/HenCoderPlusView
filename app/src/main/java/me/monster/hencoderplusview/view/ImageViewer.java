package me.monster.hencoderplusview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-28 15:35
 */
public class ImageViewer extends View implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, Runnable {

    private float maxScale = 2;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap = null;
    private GestureDetector mGestureDetector;

    private float imageWidth;
    private float imageHeight;
    private float originOffsetX = 0;
    private float originOffsetY = 0;
    private float offsetX = 0;
    private float offsetY = 0;


    private float bigScale;
    private float smallScale;
    private float scaleFraction;

    private boolean big = false;

    private ObjectAnimator scaleAnimator;
    private OverScroller mOverScroller;

    {
        imageWidth = ValueUtil.dpToPixel(300);
        mBitmap = ValueUtil.getBitMap(getResources(), (int) imageWidth);
        imageHeight = mBitmap.getHeight();
    }

    public ImageViewer(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, ImageViewer.this);
        mGestureDetector.setOnDoubleTapListener(this);
        mOverScroller = new OverScroller(context);
    }

    public ImageViewer(Context context, @Nullable AttributeSet attrs) {
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
            offsetX -= distanceX;
            offsetX = Math.min(offsetX, (imageWidth * bigScale - getWidth()) / 2);
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
        mOverScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, (int)
                (getWidth() - imageWidth * bigScale) / 2, (int) (imageWidth * bigScale - getWidth
                ()) / 2, (int) (getHeight() - imageHeight * bigScale) / 2, (int) (imageHeight *
                bigScale - getHeight()) / 2);
        postOnAnimation(this);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mGestureDetector.onTouchEvent(event);
        if (!result) {
            result = super.onTouchEvent(event);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        float scale = big ? bigScale : smallScale;
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        canvas.translate(originOffsetX, originOffsetY);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        originOffsetX = (getWidth() - imageWidth) / 2;
        originOffsetY = (getHeight() - imageHeight) / 2;

        //图片的宽高比 大于 View 宽高比，说明 图片比较胖（宽度大、高度小），
        //小尺寸：View 的宽度 比 图片的宽度
        //大尺寸：View 的高度 比 图片的高度

        if (imageWidth / imageHeight > (float) getWidth() / getHeight()) {
            smallScale = getWidth() / imageWidth;
            bigScale = getHeight() / imageHeight * maxScale;
        } else {
            bigScale = imageWidth / getWidth() * maxScale;
            smallScale = imageHeight / getHeight();
        }
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
        return false;
    }

    private ObjectAnimator getAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1f);
            scaleAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation, boolean isReverse) {
                    if (isReverse) {
                        offsetX = offsetY = 0;
                    }
                }
            });
        }
        return scaleAnimator;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    @Override
    public void run() {
        if (mOverScroller.computeScrollOffset()) {
            offsetX = mOverScroller.getCurrX();
            offsetY = mOverScroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }
    }
}
