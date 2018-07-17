package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.monster.hencoderplusview.R;
import me.monster.hencoderplusview.util.ValueUtil;

/**
 * @description 圆形头像 带边框
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:13
 */
public class RoundAvatar extends View {

    private Bitmap avatarBitmap;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float padding = ValueUtil.dpToPixel(5);
    private Xfermode xMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);


    public RoundAvatar(Context context) {
        super(context);
    }

    public RoundAvatar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundAvatar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RoundAvatar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);


        canvas.translate(getWidth() / 4, getHeight() / 4);

        mPaint.setColor(Color.parseColor("#e9d085"));
        canvas.drawCircle(avatarBitmap.getWidth() / 2, avatarBitmap.getHeight() / 2, avatarBitmap.getWidth() / 2, mPaint);


        int saveLayer = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);
        canvas.drawCircle(avatarBitmap.getWidth() / 2, avatarBitmap.getHeight() / 2, avatarBitmap.getWidth() / 2 - padding, mPaint);

        mPaint.setXfermode(xMode);
        canvas.drawBitmap(avatarBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(saveLayer);

        canvas.translate((getWidth() / 4), -(getHeight() / 4));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        getBitMap((int) (getWidth() - padding));
        getBitMap(getWidth());
    }


    private void getBitMap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        avatarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx_avatat, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        avatarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx_avatat, options);
    }
}
