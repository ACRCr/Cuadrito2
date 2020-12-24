package com.example.myapplication.Rejillas;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

public class DrawRejillaCompleta extends Rejilla {
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
        double d = (double) x0;
        double dig = (double) getDig();
        Double.isNaN(dig);
        Double.isNaN(d);
        double d2 = (double) y0;
        double dig2 = (double) getDig();
        Double.isNaN(dig2);
        Double.isNaN(d2);
        double d3 = (double) xf;
        double dig3 = (double) getDig();
        Double.isNaN(dig3);
        Double.isNaN(d3);
        double d4 = (double) y0;
        double dig4 = (double) getDig();
        Double.isNaN(dig4);
        Double.isNaN(d4);
        canvas.drawLine((float) (d - (dig * 0.005d)), (float) (d2 - (dig2 * 0.005d)), (float) (d3 + (dig3 * 0.005d)), (float) (d4 - (dig4 * 0.005d)), getPintaRejilla());
        canvas.drawLine(x0, y0, x0, yf, getPintaRejilla());
        double d5 = (double) x0;
        double dig5 = (double) getDig();
        Double.isNaN(dig5);
        Double.isNaN(d5);
        double d6 = (double) yf;
        double dig6 = (double) getDig();
        Double.isNaN(dig6);
        Double.isNaN(d6);
        double d7 = (double) xf;
        double dig7 = (double) getDig();
        Double.isNaN(dig7);
        Double.isNaN(d7);
        double d8 = (double) yf;
        double dig8 = (double) getDig();
        Double.isNaN(dig8);
        Double.isNaN(d8);
        canvas.drawLine((float) (d5 - (dig5 * 0.005d)), (float) (d6 + (dig6 * 0.005d)), (float) (d7 + (dig7 * 0.005d)), (float) (d8 + (dig8 * 0.005d)), getPintaRejilla());
        canvas.drawLine(xf, y0, xf, yf, getPintaRejilla());
        for (int j = 1; j <= obtenerFilasColumnas()[1] - 1; j++) {
            double d9 = (double) x0;
            double dig9 = (double) getDig();
            Double.isNaN(dig9);
            Double.isNaN(d9);
            double d10 = d9 + (dig9 * 0.005d);
            double disi = (double) (((float) j) * getDisi());
            Double.isNaN(disi);
            float f = (float) (d10 + disi);
            double d11 = (double) x0;
            double dig10 = (double) getDig();
            Double.isNaN(dig10);
            Double.isNaN(d11);
            double d12 = d11 + (dig10 * 0.005d);
            double disi2 = (double) (((float) j) * getDisi());
            Double.isNaN(disi2);
            canvas.drawLine(f, y0, (float) (d12 + disi2), yf, getOtroPincel());
        }
        for (int j2 = 1; j2 <= obtenerFilasColumnas()[0] - 1; j2++) {
            double d13 = (double) x0;
            double dig11 = (double) getDig();
            Double.isNaN(dig11);
            Double.isNaN(d13);
            double d14 = d13 + (dig11 * 0.005d);
            double disi3 = (double) (((float) j2) * getDisi());
            Double.isNaN(disi3);
            double d15 = d14 + disi3;
            double heightRejilla = (double) (getHeightRejilla() - x0);
            double dig12 = (double) getDig();
            Double.isNaN(dig12);
            Double.isNaN(heightRejilla);
            if (d15 < heightRejilla - (dig12 * 0.005d)) {
                double d16 = (double) x0;
                double dig13 = (double) getDig();
                Double.isNaN(dig13);
                Double.isNaN(d16);
                float f2 = (float) (d16 + (dig13 * 0.005d));
                float disi4 = y0 + (((float) j2) * getDisi());
                double d17 = (double) xf;
                double dig14 = (double) getDig();
                Double.isNaN(dig14);
                Double.isNaN(d17);
                canvas.drawLine(f2, disi4, (float) (d17 - (dig14 * 0.005d)), y0 + (((float) j2) * getDisi()), getOtroPincel());
            }
        }
    }
}