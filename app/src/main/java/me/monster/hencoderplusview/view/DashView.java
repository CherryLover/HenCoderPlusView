package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description 仪表盘刻度
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:11
 */
public class DashView extends View {
    private final float DASH_WIDTH = ValueUtil.dpToPixel(2);
    private final float PADDING = ValueUtil.dpToPixel(8);
    private final float DASH_PATH_WIDTH = ValueUtil.dpToPixel(2);
    private final float DASH_PATH_LENGTH = ValueUtil.dpToPixel(5);

    private final float toWidth = ValueUtil.dpToPixel(5);

    private final int START_ANGLE = 135;
    private final int SWEEP_ANGELE = 270;

    private final int TOTAL_DASH = 10;

    PathMeasure pathMeasure = new PathMeasure();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mDashPath = new Path();
    private Path mArcPath = new Path();
    private PathDashPathEffect mPathDashPathEffect = null;
    private RectF dashRectF = new RectF();
    private float value;

    public DashView(Context context) {
        super(context);

    }

    public DashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        mArcPath.addArc(dashRectF, START_ANGLE, SWEEP_ANGELE);
        canvas.drawPath(mArcPath, mPaint);

        pathMeasure.setPath(mArcPath, false);
        mPathDashPathEffect = new PathDashPathEffect(mDashPath, (pathMeasure.getLength() - DASH_PATH_WIDTH) / TOTAL_DASH, 0, PathDashPathEffect.Style.ROTATE);

        mPaint.setPathEffect(mPathDashPathEffect);
        mArcPath.addArc(dashRectF, START_ANGLE, SWEEP_ANGELE);
        canvas.drawPath(mArcPath, mPaint);
        mPaint.setPathEffect(null);

        //绘制指针
        mPaint.setStrokeWidth(toWidth);
        mPaint.setColor(Color.BLUE);
        canvas.save();
        if (value == 0) {
            canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 4, getHeight() / 2 + getHeight() / 4, mPaint);
        } else {
            canvas.rotate(getAngle() - START_ANGLE, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 4, getHeight() / 2 + getHeight() / 4, mPaint);
        }
    }

    private void init() {
        dashRectF.set(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING);
        mDashPath.addRect(0, 0, DASH_PATH_WIDTH, DASH_PATH_LENGTH, Path.Direction.CCW);

        mPaint.setColor(Color.parseColor("#bdbdbd"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DASH_WIDTH);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
    }

    private float getAngle() {
        return value / TOTAL_DASH * SWEEP_ANGELE + START_ANGLE;
    }

    /**
     * 用于外部调用设置 指针指向位置
     *
     * @param value 具体的 刻度
     */
    public void setValue(float value) {
        this.value = value;
        invalidate();
    }

    public float getValue() {
        return value;
    }
}
