package com.example.myapplication.Rejillas;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class DrawRejillaCompleta extends Rejilla {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public DrawRejillaCompleta(Context contexto, int columns, Object o) {
        super(contexto, columns, o);
    }

    /* access modifiers changed from: protected */
    @Override // com.example.myapplication.Rejillas.Rejilla
    public void onDraw(Canvas canvas) {
        float x0 = getMargenes()[0];
        float y0 = getMargenes()[1];
        float xf = getMargenes()[2];
        float yf = getMargenes()[3];
        Log.i("dim ", " " + y0 + " " + yf);
        canvas.drawLine((float) (x0 - (getDig() * 0.005d)), (float) (y0 - (getDig() * 0.005d)), (float) (xf + (getDig() * 0.005d)), (float) (y0 - (getDig() * 0.005d)), getPintaRejilla());
        canvas.drawLine(x0, y0, x0, yf, getPintaRejilla());
        canvas.drawLine((float) (x0 - (getDig() * 0.005d)), (float) (yf + (getDig() * 0.005d)), (float) (xf + (getDig() * 0.005d)), (float) (yf + ( getDig() * 0.005d)), getPintaRejilla());
        canvas.drawLine(xf, y0, xf, yf, getPintaRejilla());
        for (int j = 1; j <= obtenerFilasColumnas()[1] - 1; j++) {
            double d10 = x0 + (getDig() * 0.005d);
            float f = (float) (d10 + (j * getDisi()));
            canvas.drawLine(f, y0, (float) (x0 + (getDig() * 0.005d) + (j * getDisi())), yf, getOtroPincel());
        }
        for (int j2 = 1; j2 <= obtenerFilasColumnas()[0] - 1; j2++) {
            if ((x0 + (getDig() * 0.005d) + (j2* getDisi())) < (getHeightRejilla() - x0) - (getDig() * 0.005d)) {
                 canvas.drawLine((float) (x0 + (getDig() * 0.005d)), y0 + (((float) j2) * getDisi()), (float) (xf - ( getDig() * 0.005d)), y0 + (((float) j2) * getDisi()), getOtroPincel());
            }
        }
    }
}