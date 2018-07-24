package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @author Awesome
 * @description
 * @date 2018-07-23 7:43
 */
public class MaterialEdit extends android.support.v7.widget.AppCompatEditText {
    private static final String TAG = "MaterialEdit";

    private static float LABEL_SPACE = ValueUtil.dpToPixel(8);
    private static float LABEL_SIZE = ValueUtil.dpToPixel(14);
    private static float LABEL_PADDING = ValueUtil.dpToPixel(12);
    private static float LABEL_OFFSET = ValueUtil.dpToPixel(4);
    private static float LABEL_OFFSET_Y = ValueUtil.dpToPixel(16);
    private static float ERROR_SPACE = ValueUtil.dpToPixel(8);

    private int labelColor;

    private ObjectAnimator alphaAnimator;

    private boolean showLabel = false;

    private float labelAlpha = 0;

    private String labelText = "";

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        setPadding(getPaddingLeft(), (int) (getPaddingTop() + LABEL_PADDING + LABEL_SIZE), getPaddingRight(), getPaddingBottom());
        mPaint.setTextSize(LABEL_SIZE);

        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showLabel = true;
                    getAnimator().start();
                } else {
                    showLabel = false;
                    MaterialEdit.this.setHint(labelText);
                    getAnimator().reverse();
                }
            }
        });

//        this.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.length() > 0 && !showLabel) {
//                    showLabel = true;
//                    getAnimator().start();
//                } else if (showLabel && s.length() <= 0) {
//                    showLabel = false;
//                    getAnimator().reverse();
//                }
//            }
//        });
    }

    public MaterialEdit(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MaterialEdit(Context context, AttributeSet attrs) {
        super(context, attrs);

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            if ("hint".equals(attrs.getAttributeName(i))) {
                labelText = attrs.getAttributeValue(i);
                break;
            }
        }
        setLabelColor(context.getColor(R.color.colorAccent));
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
     *
     * @param labelColor labelColor
     */
    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showLabel) {
            mPaint.setAlpha((int) (labelAlpha * 0xff));
            mPaint.setColor(labelColor);
            float moveLength = (1 - labelAlpha) * LABEL_OFFSET_Y;
            canvas.drawText(labelText, LABEL_OFFSET, LABEL_PADDING + LABEL_SIZE + moveLength, mPaint);
            mPaint.setAlpha(1);
            setHint("");
        }
    }


    private ObjectAnimator getAnimator() {
        if (alphaAnimator == null) {
            alphaAnimator = ObjectAnimator.ofFloat(this, "labelAlpha", (float) 0.9);
            alphaAnimator.setInterpolator(new LinearInterpolator());
            alphaAnimator.setDuration(200);
        }
        return alphaAnimator;
    }
}
