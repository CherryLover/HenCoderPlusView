package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description 带图片的 TextView
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:15
 */
public class ImageTextView extends View {
    public static final String TEXT = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
            "\n" +
            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.";
    public static final int IMAGE_WIDTH = (int) ValueUtil.dpToPixel(150);
    private static final String TAG = "ImageTextView";
    private final float textSize = ValueUtil.dpToPixel(14);

    private Bitmap mBitmap;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] lineWidth = new float[1];
    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();


    {
        getBitmap(IMAGE_WIDTH);
        mPaint.setTextSize(textSize);
        mPaint.getFontMetrics(mFontMetrics);
    }

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // TODO: 2018-07-20 回车换行
    // TODO: 2018-07-20 遇到图片自动移动 宽度

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int totalText = 0;
        int count = mPaint.breakText(TEXT, true, getWidth(), lineWidth);

        float y = -mFontMetrics.top;

        while (count > 0) {
            canvas.drawText(TEXT, totalText, totalText + count, 0, y, mPaint);
            totalText += count;
            y += mPaint.getFontSpacing();
            count = mPaint.breakText(TEXT, totalText, TEXT.length(), true, getWidth(), lineWidth);
        }

        canvas.drawBitmap(mBitmap, getWidth() - IMAGE_WIDTH, 0, mPaint);
    }

    private void getBitmap(int imageWidth) {
        Log.e(TAG, "getBitmap: -----------");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx_avatat, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = imageWidth;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx_avatat, options);
        Log.e(TAG, "getBitmap: -----------");
    }
}
