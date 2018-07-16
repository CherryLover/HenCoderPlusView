package me.monster.hencoderplusview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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

    private Bitmap avatarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx_avatat);
    private float padding = ValueUtil.dpToPixel(8);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


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

        canvas.drawBitmap(avatarBitmap, padding, padding, mPaint);
    }
}
