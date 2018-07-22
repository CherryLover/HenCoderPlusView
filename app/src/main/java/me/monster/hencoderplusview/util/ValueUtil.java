package me.monster.hencoderplusview.util;

import android.content.res.Resources;
import android.util.TypedValue;

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
}
