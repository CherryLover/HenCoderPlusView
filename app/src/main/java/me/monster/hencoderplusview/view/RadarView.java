package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description
 * @author: jiangjiwei
 * Created in  2018/10/23 17:42
 */
public class RadarView extends View {
    private Paint mPaint;
    private float drawRadius;

    private final String default_spiderWeb_Color = "#3f52ae";
    private final String default_fill_color = "#66BB6A";

    private int linearGradientStartColor;
    private int linearGradientEndColor;
    private final double max_radius = 120;

    private float sweepStartAngle;


    public RadarView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linearGradientStartColor = Color.parseColor(default_fill_color);
        linearGradientEndColor = Color.parseColor("#FFFFFF");
    }

    /**
     * 实际测量宽高
     * @param widthMeasureSpec 宽度的数据
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

        drawSweep(canvas, centerX, centerY);
        drawBaseLayer(canvas, centerX, centerY);
        canvas.translate(centerX, -centerY);
    }

    /**
     * 绘制参考线
     *
     * @param canvas  Canvas
     * @param centerX 中心点
     * @param centerY 中心点
     */
    private void drawSweep(Canvas canvas, float centerX, float centerY) {
        mPaint.setColor(Color.parseColor(default_fill_color));
        mPaint.setStyle(Paint.Style.FILL);
        Shader linearColor = new LinearGradient(centerX + drawRadius, centerY, centerX, centerY - drawRadius,
                linearGradientStartColor, linearGradientEndColor, Shader.TileMode.CLAMP);

//        Shader SweepGradient = new SweepGradient(centerX,centerY
//                ,new int[]{Color.TRANSPARENT, changeAlpha(linearGradientStartColor, 0), changeAlpha
// (linearGradientStartColor, 168),
//                changeAlpha(linearGradientStartColor, 255), changeAlpha(linearGradientStartColor, 255)
//        }, null);
//        Shader SweepGradient = new SweepGradient(centerX, centerY, linearGradientStartColor, linearGradientEndColor);
        mPaint.setShader(linearColor);

        canvas.drawArc(centerX - drawRadius, centerY - drawRadius, centerX + drawRadius
                , centerY + drawRadius, sweepStartAngle, -70, true, mPaint);
    }

    /**
     * 重置画笔为基础图层做准备
     */
    private void resetPaint4Base() {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(ValueUtil.dpToPixel(1));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(default_spiderWeb_Color));
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

        for (int i = 0; i < 3; i++) {
            float circleRadius = ValueUtil.dpToPixel((int) (max_radius - 40 * i));
            canvas.drawCircle(centerX, centerY, circleRadius, mPaint);
        }
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setSweepStartAngle(float sweepStartAngle) {
        this.sweepStartAngle = sweepStartAngle;
        invalidate();
    }

    public float getSweepStartAngle() {
        return sweepStartAngle;
    }

    private static int changeAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
