package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description 饼状图
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:13
 */
public class PieView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mActRect = new RectF();
    private float padding = ValueUtil.dpToPixel(8);
    private String[] colorArray = {"#c6ff00", "#40c4ff", "#ff80ab", "#ffeb3b"};

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);

        mActRect.set(0 + padding, 0 + padding, getWidth() - padding, getHeight() - padding);

        mPaint.setColor(Color.parseColor(colorArray[0]));
        canvas.drawArc(mActRect, 0, 170, true, mPaint);

        mPaint.setColor(Color.parseColor(colorArray[1]));
        canvas.drawArc(mActRect, 170, 60, true, mPaint);

        mPaint.setColor(Color.parseColor(colorArray[2]));
        canvas.drawArc(mActRect, 230, 70, true, mPaint);

        canvas.save();
        canvas.translate(10, -10);
        mPaint.setColor(Color.parseColor(colorArray[3]));
        canvas.drawArc(mActRect, 300, 60, true, mPaint);
        canvas.restore();
    }
}
