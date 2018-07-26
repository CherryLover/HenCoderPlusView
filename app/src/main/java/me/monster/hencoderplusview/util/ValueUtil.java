package me.monster.hencoderplusview.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import me.monster.hencoderplusview.R;

/**
 * @description
 * @author: Jiang Jiwei
 * Created in 2018/7/16 9:16
 */
public class ValueUtil {

    /**
     * dp 转 像素(Pixel)
     *
     * @param dp dp 尺寸
     * @return 像素大小
     */
    public static float dpToPixel(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float getCameraZ() {
        return - 6 * Resources.getSystem().getDisplayMetrics().density;
    }

    public static Bitmap getBitMap(Resources resources ,int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        BitmapFactory.decodeResource(resources, R.drawable.wx_avatat, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources, R.drawable.wx_avatat, options);
    }
}
