package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-21 11:11
 */
public class FlipboardView extends View {
    Bitmap mBitmap;
    Camera mCamera = new Camera();
    ObjectAnimator mAnimator = ObjectAnimator.ofInt(this, "degree", 0, 180);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int imageWidth = (int) ValueUtil.dpToPixel(150);
    private int padding = (int) ValueUtil.dpToPixel(50);
    private int imageHeight = 0;
    private int centerX = 0;
    private int centerY = 0;
    private int degree;

    {
        getBitMap(imageWidth);
        imageHeight = mBitmap.getHeight();

        mAnimator.setDuration(2500);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
    }

    public FlipboardView(Context context) {
        super(context);
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动到原点 - 画图 - 顺时针旋转 - camera 旋转 - 切割 - 逆时针旋转 - 移动回去
        centerX = padding + getWidth() / 2;
        centerY = padding + getHeight() / 2;

        canvas.save();
        canvas.clipRect(padding, padding, padding + imageWidth, centerY);
        canvas.drawBitmap(mBitmap, padding, padding, mPaint);
        canvas.restore();

        canvas.save();

        if (degree < 90) {
            canvas.clipRect(padding, centerY, padding + imageWidth, padding + imageHeight);
        }else{
            canvas.clipRect(padding, padding, padding + imageWidth, centerY);
        }

        mCamera.save();
        mCamera.rotateX(degree);
        canvas.translate(centerX, centerY);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();

        canvas.drawBitmap(mBitmap, padding, padding, mPaint);
        canvas.restore();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimator.end();
    }

    private void getBitMap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_avatar, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_avatar, options);
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }
}
