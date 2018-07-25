package me.monster.hencoderplusview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
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
    private static float LABEL_SIZE = ValueUtil.dpToPixel(12);
    private static float LABEL_PADDING = ValueUtil.dpToPixel(6);
    private static float LABEL_OFFSET = ValueUtil.dpToPixel(4);
    private static float LABEL_OFFSET_Y = ValueUtil.dpToPixel(16);
    private static float ERROR_SPACE = ValueUtil.dpToPixel(8);
    Rect errorTextRect = new Rect();
    private int labelColor;
    private ObjectAnimator alphaAnimator;
    private boolean showLabel = false;
    private float labelAlpha = 0;
    private String labelText = "";
    private boolean errorEnable = true;
    private String errorText = "";
    private float errorSize = ValueUtil.dpToPixel(12);
    private int errorColor = Color.parseColor("#D50000");
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        setBackground(null);
        setPadding(getPaddingLeft(), (int) (getPaddingTop() + LABEL_PADDING + LABEL_SIZE), getPaddingRight(), (int) (getPaddingBottom() + errorSize));

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

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MaterialEdit.this.invalidate();
            }
        });
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

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEdit);
        errorEnable = typedArray.getBoolean(R.styleable.MaterialEdit_errorEnable, true);
        errorColor = typedArray.getColor(R.styleable.MaterialEdit_errorColor, Color.parseColor("#D50000"));
        errorSize = typedArray.getDimension(R.styleable.MaterialEdit_errorTextSize, ValueUtil.dpToPixel(12));
        errorText = typedArray.getText(R.styleable.MaterialEdit_errorText).toString();
        typedArray.recycle();
    }

    public boolean isErrorEnable() {
        return errorEnable;
    }

    public void setErrorEnable(boolean errorEnable) {
        this.errorEnable = errorEnable;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public float getErrorSize() {
        return errorSize;
    }

    public void setErrorSize(float errorSize) {
        this.errorSize = errorSize;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public void setErrorColor(int errorColor) {
        this.errorColor = errorColor;
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
            mPaint.reset();
            mPaint.setAlpha((int) (labelAlpha * 0xff));
            mPaint.setColor(labelColor);
            mPaint.setTextSize(LABEL_SIZE);
            float moveLength = (1 - labelAlpha) * LABEL_OFFSET_Y;
            canvas.drawText(labelText, LABEL_OFFSET, LABEL_PADDING + LABEL_SIZE + moveLength, mPaint);
            mPaint.setAlpha(1);
            setHint("");
        }

        if (errorEnable) {
            String inputText = getText().toString();
            if (inputText.length() < 0 || inputText.length() > 11) {
                if (inputText.startsWith("135")
                        || inputText.startsWith("138")
                        || inputText.startsWith("152")
                        || inputText.startsWith("189")
                        || inputText.startsWith("139")) {
                    return;
                }
                mPaint.reset();
                mPaint.setColor(errorColor);
                mPaint.setTextSize(errorSize);
                mPaint.getTextBounds(errorText, 0, errorText.length(), errorTextRect);
                canvas.drawText(errorText, getWidth() - errorTextRect.right - ValueUtil.dpToPixel(8)
                        , getHeight() - errorTextRect.bottom - ValueUtil.dpToPixel(4), mPaint);

            }
            mPaint.reset();
            mPaint.setColor(labelColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(ValueUtil.dpToPixel(2));
            canvas.drawLine(ValueUtil.dpToPixel(4), getHeight() - errorSize - LABEL_PADDING
                    , getWidth() - ValueUtil.dpToPixel(4), getHeight() - errorSize - LABEL_PADDING, mPaint);
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
