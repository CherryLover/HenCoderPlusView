package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.util.Map;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-23 7:43
 */
public class MaterialEdit extends android.support.v7.widget.AppCompatEditText {

    private static float LABEL_SPACE = ValueUtil.dpToPixel(8);
    private static float LABEL_SIZE = ValueUtil.dpToPixel(14);
    private static float LABEL_PADDING = ValueUtil.dpToPixel(12);
    private static float LABEL_OFFSET = ValueUtil.dpToPixel(4);
    private static float LABEL_OFFSET_Y = ValueUtil.dpToPixel(16);
    private static float ERROR_SPACE = ValueUtil.dpToPixel(8);

    private int labelColor;

    private ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(this, "labelAlpha", 1);

    private boolean showLabel = false;

    private float labelAlpha = 0;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        setPadding(getPaddingLeft(), (int) (getPaddingTop() + LABEL_PADDING + LABEL_SIZE), getPaddingRight(), getPaddingBottom());
        mPaint.setTextSize(LABEL_SIZE);

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !showLabel) {
                    showLabel = true;
                    alphaAnimator.start();
                } else if (showLabel && s.length() <= 0) {
                    showLabel = false;
                    alphaAnimator.reverse();
                }
            }
        });
    }

    public MaterialEdit(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MaterialEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLabelColor(context.getColor(R.color.colorAccent));;
    }

    public float getLabelAlpha() {
        return labelAlpha;
    }

    public void setLabelAlpha(float labelAlpha) {
        this.labelAlpha = labelAlpha;
        invalidate();
    }

    public int getLabelColor() {
        return labelColor;
    }

    /**
     * Color id
     * @param labelColor labelColor
     */
    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getText().length() != 0) {
            mPaint.setAlpha((int) (labelAlpha * 0xff));
            mPaint.setColor(labelColor);
            float moveLength = (1 - labelAlpha) * LABEL_OFFSET_Y;
            canvas.drawText(getHint().toString(), LABEL_OFFSET, LABEL_PADDING + LABEL_SIZE + moveLength, mPaint);
            mPaint.setAlpha(1);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
