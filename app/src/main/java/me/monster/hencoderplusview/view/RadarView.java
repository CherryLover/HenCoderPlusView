package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description
 * @author: jiangjiwei
 * Created in  2018/10/23 17:42
 */
public class RadarView extends View {

    private final double max_radius = 120;

    private Paint mPaint;
    private ObjectAnimator mSweepAnimator;

    private int spiderWebColor;
    private int fillColor;
    private int backgroundColor;
    private int circleCount;

    private float drawRadius;
    private float sweepStartAngle;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        spiderWebColor = typedArray.getColor(R.styleable.RadarView_spiderColor, Color.WHITE);
        fillColor = typedArray.getColor(R.styleable.RadarView_fillColor, Color.parseColor("#72F64A"));
        backgroundColor = typedArray.getColor(R.styleable.RadarView_bgColor, Color.parseColor("#5F6061"));
        int default_circle_count = 3;
        circleCount = typedArray.getInt(R.styleable.RadarView_circleNumber, default_circle_count);

        typedArray.recycle();

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 实际测量宽高
     *
     * @param widthMeasureSpec  宽度的数据
     * @param heightMeasureSpec 高度的数据
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        float centerX = width / 2;
        float centerY = height / 2;
        drawRadius = ValueUtil.dpToPixel((int) max_radius);

        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(backgroundColor);
        canvas.drawCircle(centerX, centerY, drawRadius, mPaint);
        mPaint.reset();

        drawSweep(canvas, centerX, centerY);
        drawBaseLayer(canvas, centerX, centerY);
        canvas.translate(centerX, -centerY);
        prepareAnimator();
    }

    /**
     * 绘制参考线
     *
     * @param canvas  Canvas
     * @param centerX 中心点
     * @param centerY 中心点
     */
    private void drawSweep(Canvas canvas, float centerX, float centerY) {
        mPaint.setStyle(Paint.Style.FILL);

        SweepGradient sweepGradient = new SweepGradient(centerX, centerY,
                new int[]{Color.TRANSPARENT, fillColor}, null);
        mPaint.setShader(sweepGradient);

        int save = canvas.save();
        canvas.rotate(sweepStartAngle, centerX, centerY);
        canvas.drawCircle(centerX, centerY, drawRadius, mPaint);
        canvas.restoreToCount(save);
    }

    private void prepareAnimator() {
        if (mSweepAnimator == null) {
            mSweepAnimator = ObjectAnimator.ofFloat(RadarView.this, "sweepStartAngle", 0, 360);
            mSweepAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            mSweepAnimator.setRepeatMode(ObjectAnimator.RESTART);
            mSweepAnimator.setDuration(1500);
            Interpolator interpolator = new LinearInterpolator();
            mSweepAnimator.setInterpolator(interpolator);
        }
    }

    /**
     * 开始动画
     */
    public void startAnimator() {
        if (mSweepAnimator == null) {
            return;
        }
        if (mSweepAnimator.isPaused()) {
            mSweepAnimator.resume();
        } else {
            mSweepAnimator.start();
        }
    }

    /**
     * 暂停动画
     */
    public void pauseAnimator() {
        if (mSweepAnimator == null) {
            return;
        }
        if (mSweepAnimator.isRunning()) {
            mSweepAnimator.pause();
        }
    }

    /**
     * 取消动画
     */
    public void cancelAnimator() {
        if (mSweepAnimator == null) {
            return;
        }
        mSweepAnimator.cancel();
    }


    /**
     * 重置画笔为基础图层做准备
     */
    private void resetPaint4Base() {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(ValueUtil.dpToPixel(1));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(spiderWebColor);
    }

    /**
     * 绘制底部图层
     * 1. 重置画笔；
     * 2. 画横纵参考线；
     * 3. 动态个数画圆；
     *
     * @param canvas  Canvas
     * @param centerX 中心点
     * @param centerY 中心点
     */
    private void drawBaseLayer(Canvas canvas, float centerX, float centerY) {
        resetPaint4Base();
        canvas.drawLine(centerX - drawRadius, centerY, centerX + drawRadius, centerY, mPaint);
        canvas.drawLine(centerX, centerY - drawRadius, centerX, centerY + drawRadius, mPaint);

        for (int i = 0; i < circleCount; i++) {
            float circleRadius = ValueUtil.dpToPixel((int) (max_radius - (max_radius / circleCount) * i));
            canvas.drawCircle(centerX, centerY, circleRadius, mPaint);
        }
    }

    public void setSweepStartAngle(float sweepStartAngle) {
        this.sweepStartAngle = sweepStartAngle;
        invalidate();
    }

    public float getSweepStartAngle() {
        return sweepStartAngle;
    }
}
