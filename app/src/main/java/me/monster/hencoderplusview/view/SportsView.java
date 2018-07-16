package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description 运动类 APP 完成度 显示
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:10
 */
public class SportsView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float circleRadius = ValueUtil.dpToPixel(170);
    private float strokeWidth = ValueUtil.dpToPixel(20);
    private String totalColor = "#bdbdbd";
    private String doneColor = "#f57c00";
    private RectF mArcRectF = new RectF();
    private Random mRandom = new Random((long) circleRadius);

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(Color.parseColor(totalColor));

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.parseColor(doneColor));

        mArcRectF.set(getWidth() / 2 - circleRadius, getHeight() / 2 - circleRadius, getWidth() / 2 + circleRadius, getHeight() / 2 + circleRadius);

        int nowPosition = mRandom.nextInt(360);
        if (nowPosition < 0) {
            nowPosition = -nowPosition;
        }
        canvas.drawArc(mArcRectF, 90, nowPosition, false, mPaint);
    }
}
