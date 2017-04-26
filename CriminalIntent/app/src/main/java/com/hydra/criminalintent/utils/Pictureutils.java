package com.hydra.criminalintent.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Hydra on 2017/4/26.
 */
//Bitmap是个简单对象，他只存储实际像素数据
public class Pictureutils {
    //该类用来处理缩放图片
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight){
        //bitmapFactory类里所有方法都是用来解码创建一个Bitmap，
        //其中一个重要的类是Options该类用来解码bitmap的各种参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        //若设置该参数为true，则只会返回Bitmap的尺寸，详情百度
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
// inSampleSize 值控制缩放大小，若2则四分之一
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
// 创造图片（容器）
        return BitmapFactory.decodeFile(path, options);
    }

    //先获得屏幕尺寸，再缩放
    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }

}
