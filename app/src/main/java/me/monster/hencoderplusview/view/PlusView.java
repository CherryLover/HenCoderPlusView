package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description 3D 动画旋转 图形
 * @date 2018-07-21 0:57
 */
public class PlusView extends View {

    private static final String TAG = "PlusView";
    private static final float FLIP_ANGLE = 45;
    Bitmap mBitmap;
    Camera mCamera = new Camera();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int imageWidth = (int) ValueUtil.dpToPixel(150);
    private int padding = (int) ValueUtil.dpToPixel(50);
    private int imageHeight = 0;
    private float rightFlip = 0;
    private float leftFlip = 0;
    private float allRotate = 0;

    {
        getBitMap(imageWidth);
        imageHeight = mBitmap.getHeight();
//        mCamera.setLocation(0, 0, ValueUtil.getCameraZ());
    }

    public PlusView(Context context) {
        super(context);
    }

    public PlusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = padding + imageWidth / 2;
        int centerY = padding + imageHeight / 2;

        selfRotate(canvas, centerX, centerY);
//        doneRotate(canvas, centerX, centerY);

    }

    private void selfRotate(Canvas canvas, int centerX, int centerY) {

        canvas.save();
        canvas.clipRect(padding, padding, centerX, padding + imageHeight);
        canvas.drawBitmap(mBitmap, padding, padding, mPaint);
        canvas.restore();

        canvas.save();
        mCamera.save();

        mCamera.rotateY(-rightFlip * FLIP_ANGLE);
        canvas.translate(centerX, centerY);
        canvas.rotate(-allRotate);

        mCamera.applyToCanvas(canvas);

        canvas.rotate(allRotate);
        canvas.translate(-centerX, -centerY);

        canvas.clipRect(centerX, padding, padding + imageWidth, padding + imageHeight);
        canvas.drawBitmap(mBitmap, padding, padding, mPaint);

        mCamera.restore();
        canvas.restore();
    }

    private void doneRotate(Canvas canvas, int centerX, int centerY) {
        mCamera.save();
        mCamera.rotateY(leftFlip * FLIP_ANGLE);

        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-allRotate);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(0, -imageWidth, -imageWidth, imageWidth);
        canvas.rotate(allRotate);
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(mBitmap, padding, padding, mPaint);
        canvas.restore();

        mCamera.restore();
        //绘制右侧翻起

        mCamera.save();
        mCamera.rotateY(-rightFlip * FLIP_ANGLE);
        canvas.save();

        canvas.translate(centerX, centerY);
        canvas.rotate(-allRotate);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(0, -imageWidth, imageWidth, imageWidth);
//        canvas.clipRect(centerX, padding, padding + imageWidth, padding + imageHeight);
        canvas.rotate(allRotate);
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(mBitmap, padding, padding, mPaint);
        canvas.restore();
//        mCamera.restore();
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

    public float getRightFlip() {
        return rightFlip;
    }

    public void setRightFlip(float rightFlip) {
        this.rightFlip = rightFlip;
        invalidate();
    }

    public float getLeftFlip() {
        return leftFlip;
    }

    public void setLeftFlip(float leftFlip) {
        this.leftFlip = leftFlip;
        invalidate();
    }

    public float getAllRotate() {
        return allRotate;
    }

    public void setAllRotate(float allRotate) {
        this.allRotate = allRotate;
        invalidate();
    }
}
