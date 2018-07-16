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
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float strokeWidth = ValueUtil.dpToPixel(2);
    private float padding = ValueUtil.dpToPixel(8);
    private Path mDashPath = new Path();
    private Path mArcPath = new Path();
    private PathDashPathEffect mRectPathDash = null;
    private RectF dashRectF = new RectF();

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

    private void init() {
        dashRectF.set(padding, padding, getWidth() - padding, getHeight() - padding);
        mDashPath.addRect(0, 0, ValueUtil.dpToPixel(5), ValueUtil.dpToPixel(2), Path.Direction.CCW);
        PathMeasure pathMeasure = new PathMeasure(mDashPath, false);
        mRectPathDash = new PathDashPathEffect(mArcPath, 0, pathMeasure.getLength() / 20, PathDashPathEffect.Style.ROTATE);

        mPaint.setColor(Color.parseColor("#bdbdbd"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        mArcPath.addArc(dashRectF, 120, 300);
        canvas.drawPath(mArcPath, mPaint);

        mPaint.setPathEffect(mRectPathDash);
        mArcPath.addArc(dashRectF, 120, 300);
        canvas.drawPath(mArcPath, mPaint);
//        mPaint.setPathEffect(null);
//        canvas.drawArc(dashRectF, 120, 300, false, mPaint);
    }
}
