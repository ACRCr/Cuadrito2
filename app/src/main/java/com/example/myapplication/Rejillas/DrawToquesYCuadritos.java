package com.example.myapplication.Rejillas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myapplication.Juego;
import java.util.LinkedList;

public class DrawToquesYCuadritos extends Rejilla {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private float desdeDonde;
    private int desdeDondeH = 0;
    private Paint pincelRojo = getPincelRojo();
    private Paint pinta = new Paint();
    private Paint pintaCajas = getPintaCajas();
    private LinkedList<int[]> positions;
    private Bitmap rejillaCompleta;
    private LinkedList<float[]> toques = new LinkedList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public DrawToquesYCuadritos(Context contexto, int columns, Object o) {
        super(contexto, columns, o);
        double dig = (double) getDig();
        Double.isNaN(dig);
        this.desdeDonde = (float) (dig * 0.005d);
    }

    /* access modifiers changed from: protected */
    @Override // com.example.myapplication.Rejillas.Rejilla
    public void onDraw(Canvas canvas) {
        int i = 0;
        int i2 =0;
        int i3 =0;
        int lineaH;
        int lineaH2;
        int lineaV;
        boolean z = false;
        float x0 = getMargenes()[0];
        float y0 = getMargenes()[1];
        canvas.drawBitmap(this.rejillaCompleta, 0.0f, 0.0f, (Paint) null);
        setDesdeDondeH(0.0f);
        double dig = (double) getDig();
        Double.isNaN(dig);
        setDesdeDonde((float) (dig * 0.005d));
        if (getO() instanceof Juego) {
            char c = 3;
            char c2 = 4;
            char c3 = 2;
            if (this.toques.size() > 0) {
                getWidthRejilla();
                getHeightRejilla();
                int i4 = 0;
                while (i4 < this.toques.size()) {
                    this.pincelRojo.setColor(getaJugadores()[(int) this.toques.get(i4)[0]].getColor());
                    float[] array = obtenerLineas(this.toques.get(i4)[1], this.toques.get(i4)[c3], this.toques.get(i4)[c], this.toques.get(i4)[c2], this.toques.get(i4)[5], this.toques.get(i4)[6], false);
                    int lineaV2 = (int) array[0];
                    int lineaH3 = (int) array[1];
                    float distanciaV = array[2];
                    float distanciaH = array[3];
                    float f = getReferenciasDeAumento()[1];
                    double d = (double) getReferenciasDeAumento()[2];
                    Double.isNaN(d);
                    if ( getDis() == getDisi() || (getReferenciasDeAumento()[0] <= 4.0f && (f <= ((float) ((int) (d / 2.0d)))))) {
                        lineaH2 = lineaH3;
                        metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2, lineaH2, (int) this.toques.get(i4)[0], false);
                        lineaH = i4;
                    } else {
                        boolean z4 = getReferenciasDeAumento()[0] <= 4.0f;
                        float f2 = getReferenciasDeAumento()[1];
                        double d2 = (double) getReferenciasDeAumento()[2];
                        Double.isNaN(d2);
                        if (z4 && (f2 > ((float) ((obtenerFilasColumnas()[0] - 1) - ((int) (d2 / 2.0d)))))) {
                            metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2, (int) (((float) lineaH3) - (((float) (obtenerFilasColumnas()[0] - 2)) - getReferenciasDeAumento()[2])), (int) this.toques.get(i4)[0], false);
                            lineaH = i4;
                        } else {
                            boolean z5 = getReferenciasDeAumento()[0] <= 4.0f;
                            float f3 = getReferenciasDeAumento()[1];
                            double d3 = (double) getReferenciasDeAumento()[2];
                            Double.isNaN(d3);
                            if (z5 && (f3 > ((float) ((int) (d3 / 2.0d))))) {
                                metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2, (int) (((float) lineaH3) - (getReferenciasDeAumento()[1] - (getReferenciasDeAumento()[2] / 2.0f))), (int) this.toques.get(i4)[0], false);
                                lineaH = i4;
                            } else {
                                boolean z6 = getReferenciasDeAumento()[0] > ((float) (obtenerFilasColumnas()[1] - 4));
                                float f4 = getReferenciasDeAumento()[1];
                                double d4 = (double) getReferenciasDeAumento()[2];
                                Double.isNaN(d4);
                                if (z6 && (f4 > ((float) ((obtenerFilasColumnas()[0] - 1) - ((int) (d4 / 2.0d)))))) {
                                    metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2 - (obtenerFilasColumnas()[1] - 8), (int) (((float) lineaH3) - (((float) (obtenerFilasColumnas()[0] - 2)) - getReferenciasDeAumento()[2])), (int) this.toques.get(i4)[0], false);
                                    lineaH = i4;
                                } else {
                                    lineaV = lineaV2;
                                    boolean z7 = getReferenciasDeAumento()[0] > ((float) (obtenerFilasColumnas()[1] - 4));
                                    float f5 = getReferenciasDeAumento()[1];
                                    double d5 = (double) getReferenciasDeAumento()[2];
                                    Double.isNaN(d5);
                                    if (z7 && (f5 > ((float) ((int) (d5 / 2.0d))))) {
                                        metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV - (obtenerFilasColumnas()[1] - 8), (int) (((float) lineaH3) - (getReferenciasDeAumento()[1] - (getReferenciasDeAumento()[2] / 2.0f))), (int) this.toques.get(i4)[0], false);
                                        lineaH = i4;
                                    } else {
                                        boolean z8 = getReferenciasDeAumento()[0] > ((float) (obtenerFilasColumnas()[1] - 4));
                                        float f6 = getReferenciasDeAumento()[1];
                                        double d6 = (double) getReferenciasDeAumento()[2];
                                        Double.isNaN(d6);
                                        if (z8 && (f6 <= ((float) ((int) (d6 / 2.0d))))) {
                                            metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV - (obtenerFilasColumnas()[1] - 8), lineaH3, (int) this.toques.get(i4)[0], false);
                                            lineaH = i4;
                                        } else {
                                            boolean z9 = getReferenciasDeAumento()[0] > 4.0f;
                                            float f7 = getReferenciasDeAumento()[1];
                                            double d7 = (double) getReferenciasDeAumento()[2];
                                            Double.isNaN(d7);
                                            if (z9 && (f7 > ((float) ((obtenerFilasColumnas()[0] - 1) - ((int) (d7 / 2.0d)))))) {
                                                metodo(null, null, this, canvas, distanciaH, distanciaV, (int) (((float) lineaV) - (getReferenciasDeAumento()[0] - 4.0f)), (int) (((float) lineaH3) - (((float) (obtenerFilasColumnas()[0] - 2)) - getReferenciasDeAumento()[2])), (int) this.toques.get(i4)[0], false);
                                                lineaH = i4;
                                            } else {
                                                boolean z10 = getReferenciasDeAumento()[0] > 4.0f;
                                                float f8 = getReferenciasDeAumento()[1];
                                                double d8 = (double) getReferenciasDeAumento()[2];
                                                Double.isNaN(d8);
                                                if (z10 && (f8 > ((float) ((int) (d8 / 2.0d))))) {
                                                    metodo(null, null, this, canvas, distanciaH, distanciaV, (int) (((float) lineaV) - (getReferenciasDeAumento()[0] - 4.0f)), (int) (((float) lineaH3) - (getReferenciasDeAumento()[1] - (getReferenciasDeAumento()[2] / 2.0f))), (int) this.toques.get(i4)[0], false);
                                                    lineaH = i4;
                                                } else {
                                                    boolean z11 = getReferenciasDeAumento()[0] > 4.0f;
                                                    float f9 = getReferenciasDeAumento()[1];
                                                    double d9 = (double) getReferenciasDeAumento()[2];
                                                    Double.isNaN(d9);
                                                    if (z11 && (f9 <= ((float) ((int) (d9 / 2.0d))))) {
                                                        lineaH = i4;
                                                        metodo(null, null, this, canvas, distanciaH, distanciaV, (int) (((float) lineaV) - (getReferenciasDeAumento()[0] - 4.0f)), lineaH3, (int) this.toques.get(i4)[0], false);
                                                    } else {
                                                        lineaH2 = lineaH3;
                                                        lineaH = i4;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i4 = lineaH + 1;
                        c3 = 2;
                        c2 = 4;
                        c = 3;
                    }
                    i4 = lineaH + 1;
                    c3 = 2;
                    c2 = 4;
                    c = 3;
                }
            }
            boolean z12 = getaJugadores()[1].getToques().size() > 0;
            if (getaJugadores()[0].getToques().size() > 0) {
                z = true;
            }
            if (z12 || z) {
                for (int i5 = 0; i5 < this.positions.size(); i5++) {
                    try {
                        if (this.positions.get(i5)[9] != 0) {
                            this.pintaCajas.setColor(this.positions.get(i5)[9]);
                            if (this.positions.get(i5)[5] == 1) {
                                i3 = 3;
                                try {
                                    i2 = 4;
                                    try {
                                        if (this.positions.get(i5)[3] > this.positions.get(i5)[4]) {
                                            i = 2;
                                            try {
                                                canvas.drawRect(this.desdeDonde + x0 + (((float) (this.positions.get(i5)[2] - 1)) * getDis()), y0 + (((float) this.positions.get(i5)[4]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) this.positions.get(i5)[2]) * getDis()), y0 + (((float) this.positions.get(i5)[3]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                            } catch (Exception e) {
                                            }
                                        } else {
                                            i = 2;
                                            canvas.drawRect(this.desdeDonde + x0 + (((float) (this.positions.get(i5)[2] - 1)) * getDis()), y0 + (((float) this.positions.get(i5)[3]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) this.positions.get(i5)[2]) * getDis()), y0 + (((float) this.positions.get(i5)[4]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                        }
                                    } catch (Exception e2) {
                                    }
                                } catch (Exception e3) {
                                }
                            } else {
                                i3 = 3;
                                i2 = 4;
                                i = 2;
                            }
                            if (this.positions.get(i5)[6] == i) {
                                if (this.positions.get(i5)[i3] > this.positions.get(i5)[i2]) {
                                    canvas.drawRect(this.desdeDonde + x0 + (((float) this.positions.get(i5)[i2]) * getDis()), y0 + (((float) (this.positions.get(i5)[i] - 1)) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) this.positions.get(i5)[i3]) * getDis()), y0 + (((float) this.positions.get(i5)[i]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.desdeDonde + x0 + (((float) this.positions.get(i5)[i3]) * getDis()), y0 + (((float) (this.positions.get(i5)[i] - 1)) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) this.positions.get(i5)[i2]) * getDis()), y0 + (((float) this.positions.get(i5)[i]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                }
                            }
                            if (this.positions.get(i5)[7] == i3) {
                                if (this.positions.get(i5)[i3] > this.positions.get(i5)[i2]) {
                                    canvas.drawRect(this.desdeDonde + x0 + (((float) this.positions.get(i5)[i]) * getDis()), y0 + (((float) this.positions.get(i5)[i2]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) (this.positions.get(i5)[i] + 1)) * getDis()), y0 + (((float) this.positions.get(i5)[i3]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.desdeDonde + x0 + (((float) this.positions.get(i5)[i]) * getDis()), y0 + (((float) this.positions.get(i5)[i3]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) (this.positions.get(i5)[i] + 1)) * getDis()), y0 + (((float) this.positions.get(i5)[i2]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                }
                            }
                            if (this.positions.get(i5)[8] == i2) {
                                if (this.positions.get(i5)[i3] > this.positions.get(i5)[i2]) {
                                    canvas.drawRect(this.desdeDonde + x0 + (((float) this.positions.get(i5)[i2]) * getDis()), y0 + (((float) this.positions.get(i5)[i]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) this.positions.get(i5)[i3]) * getDis()), y0 + (((float) (this.positions.get(i5)[i] + 1)) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.desdeDonde + x0 + (((float) this.positions.get(i5)[i3]) * getDis()), y0 + (((float) this.positions.get(i5)[i]) * getDis()) + ((float) (this.desdeDondeH * 1)), this.desdeDonde + x0 + (((float) this.positions.get(i5)[i2]) * getDis()), y0 + (((float) (this.positions.get(i5)[i] + 1)) * getDis()) + ((float) (this.desdeDondeH * 1)), this.pintaCajas);
                                }
                            }
                        }
                    } catch (Exception e4) {
                    }
                }
            }
        }
    }

    public void setRejillaCompleta(Bitmap rejillaCompleta2) {
        this.rejillaCompleta = rejillaCompleta2;
    }

    public void setToques(LinkedList<float[]> toques2) {
        this.toques = toques2;
    }

    public void setPositions(LinkedList<int[]> positions2) {
        this.positions = positions2;
    }
}
