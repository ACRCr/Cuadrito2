package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageUtil {
    public static Bitmap getBitmapFromView(View view) {
        view.measure(0, 0);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getWidth(), view.getHeight());
        Log.d("", "combineImages: width: " + view.getWidth());
        Log.d("", "combineImages: height: " + view.getHeight());
        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap combineImages(ArrayList<Bitmap> listOfBitmapsToStitch) {
        int width = 0;
        int height = 0;
        Iterator<Bitmap> it = listOfBitmapsToStitch.iterator();
        while (it.hasNext()) {
            Bitmap bitmap = it.next();
            width = Math.max(width, bitmap.getWidth());
            height += bitmap.getHeight();
        }
        Bitmap bitmapResult = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImageCanvas = new Canvas(bitmapResult);
        int currentHeight = 0;
        Iterator<Bitmap> it2 = listOfBitmapsToStitch.iterator();
        while (it2.hasNext()) {
            Bitmap bitmap2 = it2.next();
            comboImageCanvas.drawBitmap(bitmap2, 0.0f, (float) currentHeight, (Paint) null);
            currentHeight += bitmap2.getHeight();
        }
        return bitmapResult;
    }
}