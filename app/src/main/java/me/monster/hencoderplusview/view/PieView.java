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
 * @description 饼状图
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:13
 */
public class PieView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mActRect = new RectF();
    private float padding = ValueUtil.dpToPixel(8);
    private float offSetLength = ValueUtil.dpToPixel(15);
    private String[] colorArray = {"#c6ff00", "#40c4ff", "#ff80ab", "#ffeb3b"};
    private int[] angles = {90, 120, 75, 75};
    private Random mRandom = new Random(System.currentTimeMillis());
    private int outAngels = mRandom.nextInt(angles.length);

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

        int totalAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            mPaint.setColor(Color.parseColor(colorArray[i]));
            if (i == outAngels) {
                float offSetX = (float) (Math.cos(Math.toRadians(totalAngle + angles[i] / 2)) * offSetLength);
                float offSetY = (float) (Math.sin(Math.toRadians(totalAngle + angles[i] / 2)) * offSetLength);
                mActRect.offset(offSetX, offSetY);
                canvas.drawArc(mActRect, totalAngle, angles[i], true, mPaint);
                mActRect.offset(-offSetX, -offSetY);
            } else {
                canvas.drawArc(mActRect, totalAngle, angles[i], true, mPaint);
            }
            totalAngle += angles[i];
        }
    }

}
