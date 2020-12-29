package com.example.myapplication.Rejillas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

import com.example.myapplication.Arbitro;
import com.example.myapplication.Juego;
import com.example.myapplication.Jugador;
import com.example.myapplication.Quadrupla;
import com.example.myapplication.IA.IA;
import java.util.LinkedList;

public class Rejilla extends View {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Jugador[] aJugadores;
    private int backgroundColor;
    private final int columnas;
    private final int columnasi;
    private float desdeDonde;
    private float desdeDondeH;
    private double dig;
    private float dis;
    private final float disi;
    private final int filas;
    private float fromX;
    private float fromY;

    /* renamed from: h */
    private int f79h;
    private final int height;
    private float lineaDeEnfoqueH;
    private float lineaDeEnfoqueV;
    private int lineasHorizonatales;
    private final int lineasPorFila;
    private int lineasVerticales;
    private final int nLineas;
    private int ncolumnasZoom;

    private Object o;
    private final Paint otroPincel;
    private Paint pincelBorde;

    /* renamed from: p */
    private LinkedList<int[]> f81p;
    private float paintX0;
    private float paintXf;
    private float paintY0;
    private float paintYf;
    private final Paint pincelRojo;
    private final Paint pintaCajas;
    private final Paint pintaRejilla;
    private LinkedList<int[]> posicionesOrganizadas;
    private LinkedList<float[]> toquesOrganizadosCompartidos = new LinkedList<>();
    private int width;

    /* renamed from: x0 */
    private final float f82x0;

    /* renamed from: xf */
    private final float f83xf;

    /* renamed from: y0 */
    private final float f84y0;

    /* renamed from: yf */
    private final float f85yf;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Rejilla(Context contexto, int columns, Object o) {
        super(contexto);
        int i;
        float f;
        int i2;
        int i3;
        float f2;
        float f3;
        double d;
        float f4;
        this.columnasi = columns;
        this.columnas = columns;
        this.o = o;
        this.lineasPorFila = (obtenerFilasColumnas()[1] * 2) - 1;
        double d2 = 0.005d;
        this.desdeDonde = (float) (this.dig * 0.005d);
        this.backgroundColor = -1;
        this.posicionesOrganizadas = new LinkedList<>();
        this.f81p = new LinkedList<>();
        this.lineaDeEnfoqueV = 0.0f;
        this.lineaDeEnfoqueH = 0.0f;
        @SuppressLint("WrongConstant") WindowManager manager = (WindowManager) contexto.getSystemService("window");
        Display dp = manager.getDefaultDisplay();
        DisplayMetrics dm2 = new DisplayMetrics();
        dp.getRealMetrics(dm2);
        this.height = dm2.heightPixels;
        this.width = dm2.widthPixels;
        this.dig = Math.sqrt(Math.pow( this.height, 2.0d) + Math.pow( this.width, 2.0d));
        Paint paint = new Paint();
        this.pintaRejilla = paint;
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.pintaRejilla.setStrokeWidth((float) (this.dig * 0.01d));
        this.pintaRejilla.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.otroPincel = paint2;
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.otroPincel.setStrokeWidth((float) ((this.dig * 0.01d) / 5.0d));
        this.otroPincel.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.pincelRojo = paint3;
        paint3.setColor(SupportMenu.CATEGORY_MASK);
        this.pincelRojo.setStyle(Paint.Style.STROKE);
        this.pincelRojo.setStrokeWidth((float) ((this.dig * 0.01d) / 4.0d));
        Paint paint4 = new Paint();
        this.pintaCajas = paint4;
        paint4.setColor(-16711936);
        this.pintaCajas.setStyle(Paint.Style.FILL);
        this.pintaCajas.setStrokeWidth((float) ((this.dig * 0.01d) / 4.0d));
        double d3 = this.dig;
        float f5 = (float) (d3 * 0.01d * 2.0d);
        this.f82x0 = f5;
        float f6 = ((float) this.width) - f5;
        this.f83xf = f6;
        double d4 = (f6 - f5);
        double d5 = d4 - (d3 * 0.01d);
        float f7 = (float) (d5 / (double) this.columnas);
        this.disi = f7;
        this.dis = f7;
        this.f79h = 0;
        while (true) {
            float f8 = this.f82x0;
            double d8 = this.dig;
            i = this.f79h;
            f = this.dis;
            double d9 = (((float) i) * f);
            double d10 = (double) f8 + (d8 * d2) + d9;
            i2 = this.height;
            double d11 =  (((float) i2) - f8);
            if (d10 >= d11 - (d8 * 0.005d)) {
                break;
            }
            this.f79h = i + 1;
            d2 = 0.005d;
        }
        this.filas = i;
        int i4 = this.columnas;
        this.nLineas = (((i4 * 2) - 1) * (i - 2)) + (i4 - 1);
        int i5 = i - 1;
        this.f79h = i5;
        double d12 = (((float) i2) - (((float) i5) * f));
        float f9 = (float) (d12 / 2.0d);
        this.f84y0 = f9;
        this.f85yf = ((float) i2) - f9;
        int it = 1;
        while (true) {
            i3 = this.height;
            double d13 = i3;
            double d14 = it;
            f2 = this.f83xf;
            f3 = this.f82x0;
            double d15 = (f2 - f3);
            d = this.dig;
            double d16 = (d13 / 2.0d) - (d14 * ((d15 - (d * 0.01d)) / 8.0d));
            f4 = this.f84y0;
            if (d16 <= ((double) f4)) {
                break;
            }
            it++;
        }
        int it2 = it - 1;
        double d17 =  i3;
        double d18 = (f2 - f3);
        double d19 =  (((float) it2) * ((float) ((d18 - (d * 0.01d)) / 8.0d)));
        double d20 = (d17 / 2.0d) - d19;
        double d21 =  f4;
        this.desdeDondeH = (float) (d20 - d21);
        this.ncolumnasZoom = it2;
        int it3 = 1;
        while (true) {
            float f10 = this.f83xf;
            float f11 = this.f82x0;
            double d23 = (f10 - f11);
            double d24 =  (((float) it3) * ((float) ((d23 - (this.dig * 0.01d)) / 8.0d)));
            float f12 = this.f85yf;
            if (((double) this.height / 2.0d) + d24 < ((double) f12)) {
                it3++;
            } else {
                this.ncolumnasZoom += it3;
                this.paintX0 = f11;
                this.paintXf = f10;
                this.paintY0 = this.f84y0;
                this.paintYf = f12;
                return;
            }
        }
    }

    public void setAjugadores(Jugador[] aJugadores2) {
        this.aJugadores = aJugadores2;
    }

    public void setBackgroundColor(int backgroundColor2) {
        this.backgroundColor = backgroundColor2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {

        int i2;
        Jugador[] jugadorArr;
        int lineaH;
        int lineaV;
        int i3;
        int j;
        int i4;
        super.onDraw(canvas);


        int j2 = 1;
        while (true) {
            if (j2 > this.columnas - 1) {
                break;
            }
            canvas.drawLine((float) (this.f82x0 + (this.dig * 0.005d) + ( j2 * this.dis)), this.f84y0, (float) (this.f82x0 + (this.dig * 0.005d) + (j2 * this.dis)), this.f85yf, this.otroPincel);
            j2++;
        }
        if (this.dis == this.disi) {
            this.desdeDondeH = 0.0f;
            for (int j3 = 1; j3 <= this.f79h - 1; j3++) {
                if (this.f82x0 + (this.dig * 0.005d) +  (j3 *  this.dis) < ((this.height) - this.f82x0) - (this.dig * 0.005d)) {
                    canvas.drawLine((float) ( this.f82x0 + (this.dig * 0.005d)),  this.f84y0 + (((float) j3) *  this.dis), (float) (this.f83xf - (this.dig * 0.005d)),  this.f84y0 + (((float) j3) *  this.dis), this.otroPincel);
                }
            }
        } else {

            if (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d)))) {
                this.desdeDondeH = 0.0f;
                int j4 = 1;
                while (true) {
                    if ((((float) j4) * this.dis) + this.f84y0 >= this.f85yf) {
                        break;
                    }
                    canvas.drawLine((float) (this.f82x0 + ( this.dig * 0.005d)), this.f84y0 + (((float) j4) * this.dis), (float) (this.f83xf - ( this.dig * 0.005d)), this.f84y0 + (((float) j4) * this.dis), this.otroPincel);
                    j4++;
                }
                this.ncolumnasZoom = j4 - 1;
            } else {
                if (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) ( this.ncolumnasZoom / 2.0d))))) {
                    int j5 = 1;
                    while (true) {
                        if (this.f85yf - (((float) j5) * this.dis) <=  this.f84y0) {
                            break;
                        }
                        canvas.drawLine((float) (this.f82x0 + (this.dig * 0.005d)), this.f85yf - (((float) j5) * this.dis), (float) (this.f83xf - (this.dig * 0.005d)), this.f85yf - (((float) j5) * this.dis), this.otroPincel);
                        j5++;
                    }
                    int j6 = j5 - 1;
                    this.desdeDondeH = (this.f85yf - (((float) (j6 + 1)) * this.dis)) -  this.f84y0;
                    this.ncolumnasZoom = j6;
                } else {
                    if (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d)))) {
                        centrado(canvas);
                    }
                }
            }
        }
        if (this.o instanceof Juego) {
            Jugador[] jugadorArr2 = this.aJugadores;
            int i6 = 0;
            while (true) {
                if (i6 >=  jugadorArr2.length) {
                    break;
                }
                Jugador jugador = jugadorArr2[i6];
                if (this.dis == this.disi) {
                    i2 = i6;
                    if (jugador.getToques().size() > 0) {
                        this.pincelRojo.setColor(jugador.getColor());
                        int i7 = 0;
                        while (i7 < jugador.getToques().size()) {
                            float[] array = obtenerLineas(jugador.getToques().get(i7)[0], jugador.getToques().get(i7)[1], jugador.getToques().get(i7)[2], jugador.getToques().get(i7)[3], jugador.getToques().get(i7)[4], jugador.getToques().get(i7)[5], false);
                            int lineaV2 = (int) array[0];
                            int lineaH2 = (int) array[1];
                            float distanciaV = array[2];
                            float distanciaH = array[3];
                            if (this.dis == this.disi || (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                jugadorArr = jugadorArr2;
                                metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2, lineaH2, jugador.getId(), false);
                            } else {
                                jugadorArr = jugadorArr2;
                                if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                    metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2, lineaH2 - ((this.filas - 2) - this.ncolumnasZoom), jugador.getId(), false);
                                } else {
                                    if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                        metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2, (int) (((float) lineaH2) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2)))), jugador.getId(), false);
                                    } else {
                                        if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                            metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV2 - (this.columnasi - 8), lineaH2 - ((this.filas - 2) - this.ncolumnasZoom), jugador.getId(), false);
                                        } else {
                                            lineaV = lineaV2;
                                            if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV - (this.columnasi - 8), (int) (((float) lineaH2) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2)))), jugador.getId(), false);
                                            } else {
                                                if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && ( this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                    metodo(null, null, this, canvas, distanciaH, distanciaV, lineaV - (this.columnasi - 8), lineaH2, jugador.getId(), false);
                                                } else {
                                                    lineaH = lineaH2;
                                                    if (this.lineaDeEnfoqueV > 4.0f && ( this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) ( this.ncolumnasZoom / 2.0d)))))) {
                                                        metodo(null, null, this, canvas, distanciaH, distanciaV, (int) (((float) lineaV) - (this.lineaDeEnfoqueV - 4.0f)), lineaH - ((this.filas - 2) - this.ncolumnasZoom), jugador.getId(), false);
                                                    } else {
                                                        if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                            metodo(null, null, this, canvas, distanciaH, distanciaV, (int) (((float) lineaV) - (this.lineaDeEnfoqueV - 4.0f)), (int) (((float) lineaH) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2)))), jugador.getId(), false);
                                                        } else {
                                                            if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH <= ((float) ((int) ( this.ncolumnasZoom / 2.0d))))) {
                                                                metodo(null, null, this, canvas, distanciaH, distanciaV, (int) (((float) lineaV) - (this.lineaDeEnfoqueV - 4.0f)), lineaH, jugador.getId(), false);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                i7++;
                            }
                            i7++;
                            jugadorArr2 = jugadorArr;
                        }
                    }
                } else if (jugador.getToques().size() > 0) {
                    int i8 = (int) calcularFilaDeInicioYfinal()[0];
                    while (true) {
                        if (i8 > ((int) calcularFilaDeInicioYfinal()[1])) {
                            break;
                        }
                        int j7 = (int) calcularCloumnaDeInicioYfinal()[0];
                        while (j7 < ((int) calcularCloumnaDeInicioYfinal()[1])) {
                            if (!(((this.lineasPorFila * (i8 + -1)) + j7) - 1 < this.toquesOrganizadosCompartidos.size()) || !(((this.lineasPorFila * (i8 + -1)) + j7) - 1 >= 0)) {
                                j = j7;
                                i3 = i8;
                                i4 = i6;
                            } else if (this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[1] != 0.0f) {
                                this.pincelRojo.setColor(this.aJugadores[(int) this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[0]].getColor());
                                j = j7;
                                i3 = i8;
                                float[] array2 = obtenerLineas(this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[1], this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[2], this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[3], this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[4], this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[5], this.toquesOrganizadosCompartidos.get(((this.lineasPorFila * (i8 - 1)) + j7) - 1)[6], false);
                                int lineaV3 = (int) array2[0];
                                int lineaH3 = (int) array2[1];
                                float distanciaV2 = array2[2];
                                float distanciaH2 = array2[3];
                                if (this.dis == this.disi || (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                    i4 = i6;
                                     metodo(null, null, this, canvas, distanciaH2, distanciaV2, lineaV3, lineaH3, jugador.getId(), false);
                                } else {
                                    i4 = i6;
                                    if (this.lineaDeEnfoqueV <= 4.0f && ( this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                        metodo(null, null, this, canvas, distanciaH2, distanciaV2, lineaV3, lineaH3 - ((this.filas - 2) - this.ncolumnasZoom), jugador.getId(), false);
                                    } else {
                                        if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                            metodo(null, null, this, canvas, distanciaH2, distanciaV2, lineaV3, (int) (((float) lineaH3) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2)))), jugador.getId(), false);
                                        } else {
                                            if ( this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) ( this.ncolumnasZoom / 2.0d)))))) {
                                                metodo(null, null, this, canvas, distanciaH2, distanciaV2, lineaV3 - (this.columnasi - 8), lineaH3 - ((this.filas - 2) - this.ncolumnasZoom), jugador.getId(), false);
                                            } else {
                                                if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                    metodo(null, null, this, canvas, distanciaH2, distanciaV2, lineaV3 - (this.columnasi - 8), (int) (((float) lineaH3) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2)))), jugador.getId(), false);
                                                } else {
                                                    if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                        metodo(null, null, this, canvas, distanciaH2, distanciaV2, lineaV3 - (this.columnasi - 8), lineaH3, jugador.getId(), false);
                                                    } else {
                                                        if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                                            metodo(null, null, this, canvas, distanciaH2, distanciaV2, (int) (((float) lineaV3) - (this.lineaDeEnfoqueV - 4.0f)), lineaH3 - ((this.filas - 2) - this.ncolumnasZoom), jugador.getId(), false);
                                                        } else {
                                                            if (this.lineaDeEnfoqueV > 4.0f && ( this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                                metodo(null, null, this, canvas, distanciaH2, distanciaV2, (int) (((float) lineaV3) - (this.lineaDeEnfoqueV - 4.0f)), (int) (((float) lineaH3) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2)))), jugador.getId(), false);
                                                            } else {
                                                                if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                                    metodo(null, null, this, canvas, distanciaH2, distanciaV2, (int) (((float) lineaV3) - (this.lineaDeEnfoqueV - 4.0f)), lineaH3, jugador.getId(), false);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                j = j7;
                                i3 = i8;
                                i4 = i6;
                            }
                            j7 = j + 1;
                            i6 = i4;
                            i8 = i3;
                        }
                        i8++;
                    }
                    i2 = i6;
                } else {
                    i2 = i6;
                }
                i6 = i2 + 1;
            }
            if (this.aJugadores[1].getToques().size() <= 0) {
                return;
            }
            if (this.dis == this.disi) {
                for (int i = 0; i < this.f81p.size(); i++) {
                    try {
                        float cambio2 = (float) this.f81p.get(i)[2];
                        float cambio3 = (float) this.f81p.get(i)[3];
                        float cambio4 = (float) this.f81p.get(i)[4];
                        if (this.disi != this.dis) {
                            if ((this.f81p.get(i)[5] == 1) || (this.f81p.get(i)[7] == 3)) {
                                if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH >= ((float) (this.filas - 1 - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                    this.f81p.get(i)[3] = this.f81p.get(i)[3] - ((this.filas - 2) - this.ncolumnasZoom);
                                    this.f81p.get(i)[4] = this.f81p.get(i)[4] - ((this.filas - 2) - this.ncolumnasZoom);
                                } else {
                                    if (this.lineaDeEnfoqueV <= 4.0f && ( this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                        this.f81p.get(i)[3] = (int) (((float) this.f81p.get(i)[3]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                        this.f81p.get(i)[4] = (int) (((float)  this.f81p.get(i)[4]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                    } else {
                                        if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                            this.f81p.get(i)[2] = this.f81p.get(i)[2] - (this.columnasi - 8);
                                            this.f81p.get(i)[3] = this.f81p.get(i)[3] - ((this.filas - 2) - this.ncolumnasZoom);
                                            this.f81p.get(i)[4] = this.f81p.get(i)[4] - ((this.filas - 2) - this.ncolumnasZoom);
                                        } else {
                                            if ( this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && ( this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                this.f81p.get(i)[2] = this.f81p.get(i)[2] - (this.columnasi - 8);
                                                this.f81p.get(i)[3] = (int) (((float) this.f81p.get(i)[3]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                this.f81p.get(i)[4] = (int) (((float) this.f81p.get(i)[4]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                            } else {
                                                if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                    this.f81p.get(i)[2] = this.f81p.get(i)[2] - (this.columnasi - 8);
                                                } else {
                                                    if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                                        this.f81p.get(i)[2] = (int) (((float)  this.f81p.get(i)[2]) - (this.lineaDeEnfoqueV - 4.0f));
                                                        this.f81p.get(i)[3] = this.f81p.get(i)[3] - ((this.filas - 2) - this.ncolumnasZoom);
                                                        this.f81p.get(i)[4] = this.f81p.get(i)[4] - ((this.filas - 2) - this.ncolumnasZoom);
                                                    } else {
                                                        if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH > ((float) ((int) ( this.ncolumnasZoom / 2.0d))))) {
                                                            this.f81p.get(i)[2] = (int) (((float) this.f81p.get(i)[2]) - (this.lineaDeEnfoqueV - 4.0f));
                                                            this.f81p.get(i)[3] = (int) (((float) this.f81p.get(i)[3]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                            this.f81p.get(i)[4] = (int) (((float) this.f81p.get(i)[4]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                        } else {
                                                            if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                                this.f81p.get(i)[2] = (int) (((float) this.f81p.get(i)[2]) - (this.lineaDeEnfoqueV - 4.0f));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if ((this.f81p.get(i)[6] == 2) || (this.f81p.get(i)[8] == 4)) {

                                if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) ( this.ncolumnasZoom / 2.0d)))))) {
                                    this.f81p.get(i)[2] = this.f81p.get(i)[2] - ((this.filas - 2) - this.ncolumnasZoom);
                                } else {
                                    if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                        this.f81p.get(i)[2] = (int) (((float)  this.f81p.get(i)[2]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                    } else {
                                        if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) ( this.ncolumnasZoom / 2.0d)))))) {
                                            this.f81p.get(i)[4] = this.f81p.get(i)[4] - (this.columnasi - 8);
                                            this.f81p.get(i)[3] = this.f81p.get(i)[3] - (this.columnasi - 8);
                                            this.f81p.get(i)[2] = this.f81p.get(i)[2] - ((this.filas - 2) - this.ncolumnasZoom);
                                        } else {
                                            if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH > ((float) ((int) ( this.ncolumnasZoom / 2.0d))))) {
                                                this.f81p.get(i)[4] = this.f81p.get(i)[4] - (this.columnasi - 8);
                                                this.f81p.get(i)[3] =  this.f81p.get(i)[3] - (this.columnasi - 8);
                                                this.f81p.get(i)[2] = (int) (((float)  this.f81p.get(i)[2]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                            } else {
                                                if (this.lineaDeEnfoqueV > ((float) (this.columnasi - 4)) && (this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                    this.f81p.get(i)[3] = this.f81p.get(i)[3] - (this.columnasi - 8);
                                                    this.f81p.get(i)[4] = this.f81p.get(i)[4] - (this.columnasi - 8);
                                                } else {
                                                    if (this.lineaDeEnfoqueV > 4.0f && ( this.lineaDeEnfoqueH >= ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                                        this.f81p.get(i)[3] = (int) (((float) this.f81p.get(i)[3]) - (this.lineaDeEnfoqueV - 4.0f));
                                                        this.f81p.get(i)[4] = (int) (((float) this.f81p.get(i)[4]) - (this.lineaDeEnfoqueV - 4.0f));
                                                        this.f81p.get(i)[2] = this.f81p.get(i)[2] - ((this.filas - 2) - this.ncolumnasZoom);
                                                    } else {
                                                        if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                                            this.f81p.get(i)[4] = (int) (((float) this.f81p.get(i)[4]) - (this.lineaDeEnfoqueV - 4.0f));
                                                            this.f81p.get(i)[3] = (int) (((float) this.f81p.get(i)[3]) - (this.lineaDeEnfoqueV - 4.0f));
                                                            this.f81p.get(i)[2] = (int) (((float) this.f81p.get(i)[2]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                        } else {
                                                            if (this.lineaDeEnfoqueV > 4.0f && (this.lineaDeEnfoqueH <= ((float) ((int) ( this.ncolumnasZoom / 2.0d))))) {
                                                                this.f81p.get(i)[4] = (int) (((float) this.f81p.get(i)[4]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                this.f81p.get(i)[3] = (int) (((float) this.f81p.get(i)[3]) - (this.lineaDeEnfoqueV - 4.0f));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (this.f81p.get(i)[9] != 0) {
                            this.pintaCajas.setColor(this.f81p.get(i)[9]);
                            if (this.f81p.get(i)[5] == 1) {
                                if (this.f81p.get(i)[3] > this.f81p.get(i)[4]) {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) (this.f81p.get(i)[2] - 1)) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[2]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) (this.f81p.get(i)[2] - 1)) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[2]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                }
                            }
                            if (this.f81p.get(i)[6] == 2) {
                                if (this.f81p.get(i)[3] > this.f81p.get(i)[4]) {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[4]) * this.dis), this.f84y0 + (((float) (this.f81p.get(i)[2] - 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[3]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[3]) * this.dis), this.f84y0 + (((float) (this.f81p.get(i)[2] - 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[4]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                }
                            }
                            if (this.f81p.get(i)[7] == 3) {
                                if (this.f81p.get(i)[3] > this.f81p.get(i)[4]) {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[2]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) (this.f81p.get(i)[2] + 1)) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[2]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) (this.f81p.get(i)[2] + 1)) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                }
                            }
                            if (this.f81p.get(i)[8] == 4) {
                                if (this.f81p.get(i)[3] > this.f81p.get(i)[4]) {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[4]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[3]) * this.dis), this.f84y0 + (((float) (this.f81p.get(i)[2] + 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                } else {
                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[3]) * this.dis), this.f84y0 + (((float) this.f81p.get(i)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.f81p.get(i)[4]) * this.dis), this.f84y0 + (((float) (this.f81p.get(i)[2] + 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                }
                            }
                        }
                        this.f81p.get(i)[2] = (int) cambio2;
                        this.f81p.get(i)[3] = (int) cambio3;
                        this.f81p.get(i)[4] = (int) cambio4;
                    } catch (Exception e) {
                    }
                }
                return;
            }
            int i12 = (int) (calcularFilaDeInicioYfinal()[0] - 1.0f);
            while (true) {
                if (((float) i12) <= calcularFilaDeInicioYfinal()[1]) {
                    int j8 = (int) calcularCloumnaDeInicioYfinal()[0];
                    for (int i13 = 1; ((float) j8) <= calcularCloumnaDeInicioYfinal()[i13]; i13 = 1) {
                        int posicion = ((this.lineasPorFila * (i12 - 1)) + j8) - i13;
                        if ((posicion < this.posicionesOrganizadas.size()) && (posicion >= 0)) {
                            if (this.posicionesOrganizadas.get(posicion)[2] != 0) {
                                try {
                                    float cambio22 = (float) this.posicionesOrganizadas.get(posicion)[2];
                                    float cambio32 = (float) this.posicionesOrganizadas.get(posicion)[3];
                                    float cambio42 = (float) this.posicionesOrganizadas.get(posicion)[4];
                                    if (this.disi != this.dis) {
                                        try {
                                            if ((this.posicionesOrganizadas.get(posicion)[5] == 1) || (this.posicionesOrganizadas.get(posicion)[7] == 3)) {
                                                boolean z37 = this.lineaDeEnfoqueV <= 4.0f;
                                                float f57 = this.lineaDeEnfoqueH;
                                                double d68 = (double) this.ncolumnasZoom;
                                                Double.isNaN(d68);
                                                if (z37 && (f57 >= ((float) ((this.filas - 1) - ((int) (d68 / 2.0d)))))) {
                                                    int[] iArr37 = this.posicionesOrganizadas.get(posicion);
                                                    iArr37[3] = iArr37[3] - ((this.filas - 2) - this.ncolumnasZoom);
                                                    int[] iArr38 = this.posicionesOrganizadas.get(posicion);
                                                    iArr38[4] = iArr38[4] - ((this.filas - 2) - this.ncolumnasZoom);
                                                } else {
                                                    boolean z38 = this.lineaDeEnfoqueV <= 4.0f;
                                                    float f58 = this.lineaDeEnfoqueH;
                                                    double d69 = (double) this.ncolumnasZoom;
                                                    Double.isNaN(d69);
                                                    if (z38 && (f58 > ((float) ((int) (d69 / 2.0d))))) {
                                                        int[] iArr39 = this.posicionesOrganizadas.get(posicion);
                                                        iArr39[3] = (int) (((float) iArr39[3]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                        int[] iArr40 = this.posicionesOrganizadas.get(posicion);
                                                        iArr40[4] = (int) (((float) iArr40[4]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                    } else {
                                                        boolean z39 = this.lineaDeEnfoqueV > ((float) (this.columnasi - 4));
                                                        float f59 = this.lineaDeEnfoqueH;
                                                        double d70 = (double) this.ncolumnasZoom;
                                                        Double.isNaN(d70);
                                                        if (z39 && (f59 >= ((float) ((this.filas - 1) - ((int) (d70 / 2.0d)))))) {
                                                            int[] iArr41 = this.posicionesOrganizadas.get(posicion);
                                                            iArr41[2] = iArr41[2] - (this.columnasi - 8);
                                                            int[] iArr42 = this.posicionesOrganizadas.get(posicion);
                                                            iArr42[3] = iArr42[3] - ((this.filas - 2) - this.ncolumnasZoom);
                                                            int[] iArr43 = this.posicionesOrganizadas.get(posicion);
                                                            iArr43[4] = iArr43[4] - ((this.filas - 2) - this.ncolumnasZoom);
                                                        } else {
                                                            boolean z40 = this.lineaDeEnfoqueV > ((float) (this.columnasi - 4));
                                                            float f60 = this.lineaDeEnfoqueH;
                                                            double d71 = (double) this.ncolumnasZoom;
                                                            Double.isNaN(d71);
                                                            if (z40 && (f60 > ((float) ((int) (d71 / 2.0d))))) {
                                                                int[] iArr44 = this.posicionesOrganizadas.get(posicion);
                                                                iArr44[2] = iArr44[2] - (this.columnasi - 8);
                                                                int[] iArr45 = this.posicionesOrganizadas.get(posicion);
                                                                iArr45[3] = (int) (((float) iArr45[3]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                                int[] iArr46 = this.posicionesOrganizadas.get(posicion);
                                                                iArr46[4] = (int) (((float) iArr46[4]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                            } else {
                                                                boolean z41 = this.lineaDeEnfoqueV > ((float) (this.columnasi - 4));
                                                                float f61 = this.lineaDeEnfoqueH;
                                                                double d72 = (double) this.ncolumnasZoom;
                                                                Double.isNaN(d72);
                                                                if (z41 && (f61 <= ((float) ((int) (d72 / 2.0d))))) {
                                                                    int[] iArr47 = this.posicionesOrganizadas.get(posicion);
                                                                    iArr47[2] = iArr47[2] - (this.columnasi - 8);
                                                                } else {
                                                                    boolean z42 = this.lineaDeEnfoqueV > 4.0f;
                                                                    float f62 = this.lineaDeEnfoqueH;
                                                                    double d73 = (double) this.ncolumnasZoom;
                                                                    Double.isNaN(d73);
                                                                    if (z42 && (f62 >= ((float) ((this.filas - 1) - ((int) (d73 / 2.0d)))))) {
                                                                        int[] iArr48 = this.posicionesOrganizadas.get(posicion);
                                                                        iArr48[2] = (int) (((float) iArr48[2]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                        int[] iArr49 = this.posicionesOrganizadas.get(posicion);
                                                                        iArr49[3] = iArr49[3] - ((this.filas - 2) - this.ncolumnasZoom);
                                                                        int[] iArr50 = this.posicionesOrganizadas.get(posicion);
                                                                        iArr50[4] = iArr50[4] - ((this.filas - 2) - this.ncolumnasZoom);
                                                                    } else {
                                                                        boolean z43 = this.lineaDeEnfoqueV > 4.0f;
                                                                        float f63 = this.lineaDeEnfoqueH;
                                                                        double d74 = (double) this.ncolumnasZoom;
                                                                        Double.isNaN(d74);
                                                                        if (z43 && (f63 > ((float) ((int) (d74 / 2.0d))))) {
                                                                            int[] iArr51 = this.posicionesOrganizadas.get(posicion);
                                                                            iArr51[2] = (int) (((float) iArr51[2]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                            int[] iArr52 = this.posicionesOrganizadas.get(posicion);
                                                                            iArr52[3] = (int) (((float) iArr52[3]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                                            int[] iArr53 = this.posicionesOrganizadas.get(posicion);
                                                                            iArr53[4] = (int) (((float) iArr53[4]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                                        } else {
                                                                            boolean z44 = this.lineaDeEnfoqueV > 4.0f;
                                                                            float f64 = this.lineaDeEnfoqueH;
                                                                            double d75 = (double) this.ncolumnasZoom;
                                                                            Double.isNaN(d75);
                                                                            if (z44 && (f64 <= ((float) ((int) (d75 / 2.0d))))) {
                                                                                int[] iArr54 = this.posicionesOrganizadas.get(posicion);
                                                                                iArr54[2] = (int) (((float) iArr54[2]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if ((this.posicionesOrganizadas.get(posicion)[6] == 2) || (this.posicionesOrganizadas.get(posicion)[8] == 4)) {
                                                boolean z45 = this.lineaDeEnfoqueV <= 4.0f;
                                                float f65 = this.lineaDeEnfoqueH;
                                                double d76 = (double) this.ncolumnasZoom;
                                                Double.isNaN(d76);
                                                if (z45 && (f65 >= ((float) ((this.filas - 1) - ((int) (d76 / 2.0d)))))) {
                                                    int[] iArr55 = this.posicionesOrganizadas.get(posicion);
                                                    iArr55[2] = iArr55[2] - ((this.filas - 2) - this.ncolumnasZoom);
                                                } else {
                                                    boolean z46 = this.lineaDeEnfoqueV <= 4.0f;
                                                    float f66 = this.lineaDeEnfoqueH;
                                                    double d77 = (double) this.ncolumnasZoom;
                                                    Double.isNaN(d77);
                                                    if (z46 && (f66 > ((float) ((int) (d77 / 2.0d))))) {
                                                        int[] iArr56 = this.posicionesOrganizadas.get(posicion);
                                                        iArr56[2] = (int) (((float) iArr56[2]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                    } else {
                                                        boolean z47 = this.lineaDeEnfoqueV > ((float) (this.columnasi - 4));
                                                        float f67 = this.lineaDeEnfoqueH;
                                                        double d78 = (double) this.ncolumnasZoom;
                                                        Double.isNaN(d78);
                                                        if (z47 && (f67 >= ((float) ((this.filas - 1) - ((int) (d78 / 2.0d)))))) {
                                                            int[] iArr57 = this.posicionesOrganizadas.get(posicion);
                                                            iArr57[4] = iArr57[4] - (this.columnasi - 8);
                                                            int[] iArr58 = this.posicionesOrganizadas.get(posicion);
                                                            iArr58[3] = iArr58[3] - (this.columnasi - 8);
                                                            int[] iArr59 = this.posicionesOrganizadas.get(posicion);
                                                            iArr59[2] = iArr59[2] - ((this.filas - 2) - this.ncolumnasZoom);
                                                        } else {
                                                            boolean z48 = this.lineaDeEnfoqueV > ((float) (this.columnasi - 4));
                                                            float f68 = this.lineaDeEnfoqueH;
                                                            double d79 = (double) this.ncolumnasZoom;
                                                            Double.isNaN(d79);
                                                            if (z48 && (f68 > ((float) ((int) (d79 / 2.0d))))) {
                                                                int[] iArr60 = this.posicionesOrganizadas.get(posicion);
                                                                iArr60[4] = iArr60[4] - (this.columnasi - 8);
                                                                int[] iArr61 = this.posicionesOrganizadas.get(posicion);
                                                                iArr61[3] = iArr61[3] - (this.columnasi - 8);
                                                                int[] iArr62 = this.posicionesOrganizadas.get(posicion);
                                                                iArr62[2] = (int) (((float) iArr62[2]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                            } else {
                                                                boolean z49 = this.lineaDeEnfoqueV > ((float) (this.columnasi - 4));
                                                                float f69 = this.lineaDeEnfoqueH;
                                                                double d80 = (double) this.ncolumnasZoom;
                                                                Double.isNaN(d80);
                                                                if (z49 && (f69 <= ((float) ((int) (d80 / 2.0d))))) {
                                                                    int[] iArr63 = this.posicionesOrganizadas.get(posicion);
                                                                    iArr63[3] = iArr63[3] - (this.columnasi - 8);
                                                                    int[] iArr64 = this.posicionesOrganizadas.get(posicion);
                                                                    iArr64[4] = iArr64[4] - (this.columnasi - 8);
                                                                } else {
                                                                    boolean z50 = this.lineaDeEnfoqueV > 4.0f;
                                                                    float f70 = this.lineaDeEnfoqueH;
                                                                    double d81 = (double) this.ncolumnasZoom;
                                                                    Double.isNaN(d81);
                                                                    if (z50 && (f70 >= ((float) ((this.filas - 1) - ((int) (d81 / 2.0d)))))) {
                                                                        int[] iArr65 = this.posicionesOrganizadas.get(posicion);
                                                                        iArr65[3] = (int) (((float) iArr65[3]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                        int[] iArr66 = this.posicionesOrganizadas.get(posicion);
                                                                        iArr66[4] = (int) (((float) iArr66[4]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                        int[] iArr67 = this.posicionesOrganizadas.get(posicion);
                                                                        iArr67[2] = iArr67[2] - ((this.filas - 2) - this.ncolumnasZoom);
                                                                    } else {
                                                                        boolean z51 = this.lineaDeEnfoqueV > 4.0f;
                                                                        float f71 = this.lineaDeEnfoqueH;
                                                                        double d82 = (double) this.ncolumnasZoom;
                                                                        Double.isNaN(d82);
                                                                        if (z51 && (f71 > ((float) ((int) (d82 / 2.0d))))) {
                                                                            int[] iArr68 = this.posicionesOrganizadas.get(posicion);
                                                                            iArr68[4] = (int) (((float) iArr68[4]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                            int[] iArr69 = this.posicionesOrganizadas.get(posicion);
                                                                            iArr69[3] = (int) (((float) iArr69[3]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                            int[] iArr70 = this.posicionesOrganizadas.get(posicion);
                                                                            iArr70[2] = (int) (((float) iArr70[2]) - (this.lineaDeEnfoqueH - ((float) (this.ncolumnasZoom / 2))));
                                                                        } else {
                                                                            boolean z52 = this.lineaDeEnfoqueV > 4.0f;
                                                                            float f72 = this.lineaDeEnfoqueH;
                                                                            double d83 = (double) this.ncolumnasZoom;
                                                                            Double.isNaN(d83);
                                                                            if (z52 && (f72 <= ((float) ((int) (d83 / 2.0d))))) {
                                                                                try {
                                                                                    int[] iArr71 = this.posicionesOrganizadas.get(posicion);
                                                                                    iArr71[4] = (int) (((float) iArr71[4]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                                    int[] iArr72 = this.posicionesOrganizadas.get(posicion);
                                                                                    iArr72[3] = (int) (((float) iArr72[3]) - (this.lineaDeEnfoqueV - 4.0f));
                                                                                } catch (Exception e2) {
                                                                                    j8++;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (Exception e3) {
                                            j8++;
                                        }
                                    }
                                    if (this.posicionesOrganizadas.get(posicion)[9] != 0) {
                                        this.pintaCajas.setColor(this.posicionesOrganizadas.get(posicion)[9]);
                                        if (this.posicionesOrganizadas.get(posicion)[5] == 1) {
                                            if (this.posicionesOrganizadas.get(posicion)[3] > this.posicionesOrganizadas.get(posicion)[4]) {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) (this.posicionesOrganizadas.get(posicion)[2] - 1)) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            } else {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) (this.posicionesOrganizadas.get(posicion)[2] - 1)) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            }
                                        }
                                        if (this.posicionesOrganizadas.get(posicion)[6] == 2) {
                                            if (this.posicionesOrganizadas.get(posicion)[3] > this.posicionesOrganizadas.get(posicion)[4]) {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis), this.f84y0 + (((float) (this.posicionesOrganizadas.get(posicion)[2] - 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            } else {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis), this.f84y0 + (((float) (this.posicionesOrganizadas.get(posicion)[2] - 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            }
                                        }
                                        if (this.posicionesOrganizadas.get(posicion)[7] == 3) {
                                            if (this.posicionesOrganizadas.get(posicion)[3] > this.posicionesOrganizadas.get(posicion)[4]) {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) (this.posicionesOrganizadas.get(posicion)[2] + 1)) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            } else {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) (this.posicionesOrganizadas.get(posicion)[2] + 1)) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            }
                                        }
                                        if (this.posicionesOrganizadas.get(posicion)[8] == 4) {
                                            if (this.posicionesOrganizadas.get(posicion)[3] > this.posicionesOrganizadas.get(posicion)[4]) {
                                                canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis), this.f84y0 + (((float) (this.posicionesOrganizadas.get(posicion)[2] + 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                            } else {
                                                try {
                                                    canvas.drawRect(this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[3]) * this.dis), this.f84y0 + (((float) this.posicionesOrganizadas.get(posicion)[2]) * this.dis) + (this.desdeDondeH * 1.0f), this.f82x0 + this.desdeDonde + (((float) this.posicionesOrganizadas.get(posicion)[4]) * this.dis), this.f84y0 + (((float) (this.posicionesOrganizadas.get(posicion)[2] + 1)) * this.dis) + (this.desdeDondeH * 1.0f), this.pintaCajas);
                                                } catch (Exception e4) {
                                                    j8++;
                                                }
                                            }
                                        }
                                    }
                                    try {
                                        this.posicionesOrganizadas.get(posicion)[2] = (int) cambio22;
                                        try {
                                            this.posicionesOrganizadas.get(posicion)[3] = (int) cambio32;
                                            try {
                                                this.posicionesOrganizadas.get(posicion)[4] = (int) cambio42;
                                            } catch (Exception e5) {
                                            }
                                        } catch (Exception e6) {
                                            j8++;
                                        }
                                    } catch (Exception e7) {
                                        j8++;
                                    }
                                } catch (Exception e8) {
                                    j8++;
                                }
                            }
                        }
                        j8++;
                    }
                    i12++;
                } else {
                    return;
                }
            }
        }
    }

    public int getnLineas() {
        return this.nLineas;
    }

    public void setP(LinkedList<int[]> p) {
        this.f81p = p;
    }

    public Object[] metodo(LinkedList<int[]> posicionesRejilla, LinkedList<int[]> positions, Object o, Canvas canvas, float distanciaH, float distanciaV, int lineaV, int lineaH, int id, boolean esIntermedio) {
        Object[] objects;
        int[] pos = new int[10];
        int posicion = 0;
        pos[5] = 0;
        pos[6] = 0;
        pos[7] = 0;
        pos[8] = 0;
        pos[9] = 0;
        Object[] objects2 = new Object[2];
        objects2[0] = false;
        if (Math.abs(distanciaH) >= Math.abs(distanciaV)) {
            objects = objects2;
            if (lineaH == obtenerFilasColumnas()[0] - 1) {
                objects[0] = true;
                return objects;
            } else if (distanciaH < 0.0f) {
                if ((((double) (this.paintX0 + this.desdeDonde + (((float) (lineaV - 1)) * this.dis))) <= this.dig * 0.01d || lineaV - 1 < 0) && this.paintX0 != 0.0f) {
                    objects[0] = true;
                    return objects;
                } else if (o instanceof View) {
                    float f = this.paintX0;
                    float f2 = this.desdeDonde;
                    float f3 = this.dis;
                    float f4 = this.paintY0;
                    float f5 = this.desdeDondeH;
                    canvas.drawLine(f + f2 + (((float) lineaV) * f3), (((float) lineaH) * f3) + f4 + (f5 * 1.0f), f + f2 + (((float) (lineaV - 1)) * f3), f4 + (((float) lineaH) * f3) + (f5 * 1.0f), this.pincelRojo);
                } else {
                    pos[0] = id;
                    pos[1] = 1;
                    pos[2] = lineaH;
                    pos[3] = lineaV - 1;
                    pos[4] = lineaV;
                    posicion = Arbitro.calcularPosicion(pos[1], pos[2], pos[4], this);
                    if (!Arbitro.esta(posicionesRejilla, posicion)) {
                        posicionesRejilla.get(posicion)[0] = 1;
                        positions.add(pos);
                        ((IA) this.aJugadores[0]).removerPosciones(new Quadrupla<>(0, 1, Integer.valueOf(lineaH), Integer.valueOf(lineaV - 1), Integer.valueOf(lineaV)));
                    } else {
                        objects[0] = true;
                    }
                }
            } else if (o instanceof View) {
                float f6 = this.paintX0;
                float f7 = this.desdeDonde;
                float f8 = this.dis;
                float f9 = this.paintY0;
                float f10 = this.desdeDondeH;
                canvas.drawLine(f6 + f7 + (((float) lineaV) * f8), (((float) lineaH) * f8) + f9 + (f10 * 1.0f), f6 + f7 + (((float) (lineaV + 1)) * f8), f9 + (((float) lineaH) * f8) + (f10 * 1.0f), this.pincelRojo);
            } else {
                pos[0] = id;
                pos[1] = 1;
                pos[2] = lineaH;
                pos[3] = lineaV;
                pos[4] = lineaV + 1;
                posicion = Arbitro.calcularPosicion(pos[1], pos[2], pos[4], this);
                if (!Arbitro.esta(posicionesRejilla, posicion)) {
                    posicionesRejilla.get(posicion)[0] = 1;
                    positions.add(pos);
                    ((IA) this.aJugadores[0]).removerPosciones(new Quadrupla<>(0, 1, Integer.valueOf(lineaH), Integer.valueOf(lineaV), Integer.valueOf(lineaV + 1)));
                } else {
                    objects[0] = true;
                }
            }
        } else if (lineaV == 0 && this.paintX0 != 0.0f) {
            objects2[0] = true;
            return objects2;
        } else if (distanciaV >= 0.0f) {
            objects = objects2;
            if (o instanceof View) {
                float f11 = this.paintX0;
                float f12 = this.desdeDonde;
                float f13 = this.dis;
                float f14 = this.paintY0;
                float f15 = this.desdeDondeH;
                canvas.drawLine(f11 + f12 + (((float) lineaV) * f13), (((float) lineaH) * f13) + f14 + (f15 * 1.0f), f11 + f12 + (((float) lineaV) * f13), f14 + (((float) (lineaH + 1)) * f13) + (f15 * 1.0f), this.pincelRojo);
            } else {
                pos[0] = id;
                pos  [1] = 0;
                pos[2] = lineaV;
                pos[3] = lineaH;
                pos[4] = lineaH + 1;
                posicion = Arbitro.calcularPosicion(pos[1], pos[2], pos[4], this);
                if (!Arbitro.esta(posicionesRejilla, posicion)) {
                    posicionesRejilla.get(posicion)[0] = 1;
                    positions.add(pos);
                    ((IA) this.aJugadores[0]).removerPosciones(new Quadrupla<>(0, 0,  Integer.valueOf(lineaV), Integer.valueOf(lineaH), Integer.valueOf(lineaH + 1)));
                } else {
                    objects[0] = true;
                }
            }
        } else if (o instanceof View) {
            float f16 = this.paintX0;
            float f17 = this.desdeDonde;
            float f18 = this.dis;
            float f19 = this.paintY0;
            float f20 = this.desdeDondeH;
            canvas.drawLine(f16 + f17 + (((float) lineaV) * f18), (((float) lineaH) * f18) + f19 + f20, f16 + f17 + (((float) lineaV) * f18), f19 + (((float) (lineaH - 1)) * f18) + f20, this.pincelRojo);
            objects = objects2;
        } else {
            pos[0] = id;
            pos[1] = 0;
            pos[2] = lineaV;
            pos[3] = lineaH - 1;
            pos[4] = lineaH;
            posicion = Arbitro.calcularPosicion(pos[1], pos[2], pos[4], this);
            if (!Arbitro.esta(posicionesRejilla, posicion)) {
                posicionesRejilla.get(posicion)[0] = 1;
                positions.add(pos);
                objects = objects2;
                ((IA) this.aJugadores[0]).removerPosciones(new Quadrupla<>(0, 0, Integer.valueOf(lineaV), Integer.valueOf(lineaH - 1), Integer.valueOf(lineaH)));
            } else {
                objects = objects2;
                objects[0] = true;
            }
        }
        objects[1] = Integer.valueOf(posicion);
        return objects;
    }

    public int[] getLineas() {
        return new int[]{this.lineasVerticales, this.lineasHorizonatales};
    }

    public boolean esta(LinkedList<int[]> lista, int pos0, int pos1, int pos2, int pos3) {
        if (lista.size() <= 0) {
            return false;
        }
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[1] == pos0 && lista.get(i)[2] == pos1 && ((lista.get(i)[3] == pos2 && lista.get(i)[4] == pos3) || (lista.get(i)[3] == pos3 && lista.get(i)[4] == pos2))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void centrado(Canvas canvas) {
        canvas.drawLine( (float) (this.f82x0 + (this.dig * 0.005d)), (float) (this.height / 2.0d), (float) (this.f83xf - (this.dig * 0.005d)), (float) (this.height / 2.0d), this.otroPincel);
        int it = 1;
        while (true) {
            if ((this.height / 2.0d) - (it *  this.dis) <= ((double) this.f84y0)) {
                break;
            }
            canvas.drawLine((float) (this.f82x0 + (this.dig * 0.005d)), (float)((this.height / 2.0d) - (it *  this.dis)), (float) (this.f83xf - (this.dig * 0.005d)), (float) ((this.height / 2.0d) - (it *  this.dis)), this.otroPincel);
            it++;
        }
        int it2 = it - 1;
        this.desdeDondeH = (float) (((this.height / 2.0d) - (it2 *  this.dis)) - this.f84y0);
        this.ncolumnasZoom = it2;
        int it3 = 1;
        while (true) {
            if ((this.height / 2.0d) + (it3 * this.dis) < ((double) this.f85yf)) {
                canvas.drawLine((float) (this.f82x0 + (this.dig * 0.005d)), (float) (( this.height / 2.0d) + (it3 * this.dis)), (float) (this.f83xf - ( this.dig * 0.005d)), (float) ((this.height / 2.0d) +  (it3 * this.dis)), this.otroPincel);
                it3++;
            } else {
                this.ncolumnasZoom += it3;
                return;
            }
        }
    }
    public float[] obtenerLineas(float x, float y, float zoom, float lineaDeEnfoqueV2, float lineaDeEnfoqueH2, float ncolumnasZoom2, boolean band) {
        float lineaH=0;
        if ((((x < this.f82x0) | (x > this.f83xf)) || (y < this.f84y0)) || (y > this.f85yf)) {
            return null;
        }
        float lineaV = 0.0f;
        float distanciaH = (float) this.width;
        float distanciaV2 = (float) this.height;
        float[] array = new float[4];
        for (int j = 0; j <= this.columnasi - 1; j++) {
            if (Math.abs(x - (f82x0 + (this.dig * 0.005d) + (j*zoom))) < ((double) distanciaH)) {
                lineaV = (float) j;
                distanciaH = (float) (x - (f82x0 + (this.dig * 0.005d) + (j*zoom)));
            }
        }
        float lineaH4 = 0;
        String str3 = "zooom123 ";
        if (((lineaDeEnfoqueH2 == 0.0f) && (lineaDeEnfoqueV2 == 0.0f)) && (!band)) {
            for (int j = 1; j <= this.filas - 2; j++) {
                if (Math.abs(y - (this.f84y0 + (((float) j) * zoom))) < distanciaV2) {
                    lineaH4 = (float) j;
                    distanciaV2 = y - (this.f84y0 + (((float) j) * zoom));
                }
            }
            lineaH = lineaH4;
        } else {
            if ((lineaDeEnfoqueH2 != 0.0f) || (lineaDeEnfoqueV2 != 0.0f)) {
                if (lineaDeEnfoqueH2 <= ((float) ((int) (ncolumnasZoom2 / 2.0d)))) {
                    lineaH = 0;
                    for (int j = 1; j <= this.filas - 2; j++) {
                        if (Math.abs(y - (this.f84y0 + (((float) j) * zoom))) < distanciaV2) {
                            lineaH = (float) j;
                            distanciaV2 = y - (this.f84y0 + (((float) j) * zoom));
                        }
                    }
                }else {
                    if (lineaDeEnfoqueH2 > ((float) (this.filas - 1 - ((int) (ncolumnasZoom2 / 2.0d))))) {
                        float lineaH5 = 0;
                        for (int j = (int) ncolumnasZoom2; j >= 1; j--) {
                            if (Math.abs(y - (this.f85yf - (((float) j) * zoom))) < distanciaV2) {
                                lineaH5 = (float) j;
                                distanciaV2 = y - (this.f85yf - (((float) j) * zoom));
                            }
                        }
                        lineaH = (ncolumnasZoom2 - lineaH5) + 1.0f;
                        Log.i("zooom4 ", " " + lineaH);
                    } else if(lineaDeEnfoqueH2 == ((float) (this.filas - 1 - ((int) (ncolumnasZoom2 / 2.0d))))){
                        if (lineaDeEnfoqueH2 > ((float) ((int) (ncolumnasZoom2 / 2.0d)))) {

                            float distanciaV1 = (float)  this.height;
                            float distanciaV22 = (float)  this.height;
                            int it = 0;
                            while (true) {
                                if ((this.height / 2.0d) - (it* zoom) <= ((double) this.f84y0)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) - (it * zoom))) < ((double) Math.abs(distanciaV1))) {
                                    distanciaV1 = y - ((float) ((this.height / 2.0d) - ( it * zoom)));
                                    lineaH4 = (float) it;
                                }
                                it++;
                            }
                            it--;
                            float lineaH6 = ((float) it) - lineaH4;
                            int it1 = 1;
                            float linea = 0.0f;
                            while (true) {
                                if ((this.height / 2.0d) + ( it1 * zoom) >= ((double) this.f85yf)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) + (it1 * zoom))) < ((double) Math.abs(distanciaV1))) {
                                    distanciaV1 = y - ((float) ((this.height / 2.0d) + (it1 * zoom)));
                                    linea = (float) it1;
                                }
                                it1++;
                            }
                            lineaH = lineaH6 + linea;
                            if (Math.abs(distanciaV1) < Math.abs(distanciaV22)) {
                                distanciaV2 = distanciaV1;
                            } else {
                                distanciaV2 = distanciaV22;
                            }
                        }
                    } else {

                        if (lineaDeEnfoqueH2 > ((float) ((int) (ncolumnasZoom2 / 2.0d)))) {

                            float distanciaV1 = (float)  this.height;
                            float distanciaV22 = (float)  this.height;
                            int it = 0;
                            while (true) {
                                if ((this.height / 2.0d) - (it* zoom) <= ((double) this.f84y0)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) - (it * zoom))) < ((double) Math.abs(distanciaV1))) {
                                    distanciaV1 = y - ((float) ((this.height / 2.0d) - ( it * zoom)));
                                    lineaH4 = (float) it;
                                }
                                it++;
                            }
                            float lineaH6 = ((float) it) - lineaH4;
                            int it1 = 1;
                            float linea = 0.0f;
                            while (true) {
                                if ((this.height / 2.0d) + ( it1 * zoom) >= ((double) this.f85yf)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) + (it1 * zoom))) < ((double) Math.abs(distanciaV1))) {
                                    distanciaV1 = y - ((float) ((this.height / 2.0d) + (it1 * zoom)));
                                    linea = (float) it1;
                                }
                                it1++;
                            }
                            lineaH = lineaH6 + linea;
                            if (Math.abs(distanciaV1) < Math.abs(distanciaV22)) {
                                distanciaV2 = distanciaV1;
                            } else {
                                distanciaV2 = distanciaV22;
                            }
                        }
                    }
                }
            } else if (band) {
                if ( this.lineaDeEnfoqueH <= ((float) ((int) (this.ncolumnasZoom / 2.0d)))) {
                    lineaH = 0;
                    for (int j = 1; j <= this.filas - 2; j++) {
                        if (Math.abs(y - (this.f84y0 + (((float) j) * zoom))) < distanciaV2) {
                            distanciaV2 = y - (this.f84y0 + (((float) j) * zoom));
                            lineaH = (float) j;
                        }
                    }
                } else{
                    if ( this.lineaDeEnfoqueH > ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d))))) {
                        float lineaH7 = 0;
                        for (int j6 = this.ncolumnasZoom; j6 >= 1; j6--) {
                            if (Math.abs(y - (this.f85yf - (((float) j6) * zoom))) < distanciaV2) {
                                distanciaV2 = y - (this.f85yf - (((float) j6) * zoom));
                                lineaH7 = (float) j6;
                            }
                        }
                        lineaH = (((float) this.ncolumnasZoom) - lineaH7) + 1.0f;
                    } else if(this.lineaDeEnfoqueH == ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d))))){
                        if ( this.lineaDeEnfoqueH > ((float) ((int) ( this.ncolumnasZoom / 2.0d)))) {
                            float distanciaV12 = (float) this.height;
                            float distanciaV23 = (float) this.height;
                            int it2 = 0;
                            float lineaH8 = lineaH4;
                            while (true) {
                                if ((this.height / 2.0d) - ( it2 * zoom) <= ((double) this.f84y0)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) - (it2 * zoom))) < ((double) Math.abs(distanciaV12))) {
                                    distanciaV12 = y - ((float) ((this.height / 2.0d) - (it2 * zoom)));
                                    lineaH8 = (float) it2;
                                }
                                it2++;
                            }
                            it2--;
                            float lineaH9 = ((float) it2) - lineaH8;
                            int it12 = 1;
                            float linea2 = 0.0f;
                            while (true) {;
                                if ((this.height/ 2.0d) + (it12 * zoom) >= ((double) this.f85yf)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) + (it12 * zoom))) < ((double) Math.abs(distanciaV12))) {
                                    distanciaV12 = y - ((float) ((this.height/ 2.0d) +  (it12 * zoom)));
                                    linea2 = (float) it12;
                                }
                                it12++;
                            }
                            float lineaH10 = lineaH9 + linea2;
                            if (Math.abs(distanciaV12) < Math.abs(distanciaV23)) {
                                distanciaV2 = distanciaV12;
                            } else {
                                distanciaV2 = distanciaV23;
                            }
                            lineaH = lineaH10;
                        }
                    } else {

                        if ( this.lineaDeEnfoqueH > ((float) ((int) ( this.ncolumnasZoom / 2.0d)))) {
                            float distanciaV12 = (float) this.height;
                            float distanciaV23 = (float) this.height;
                            int it2 = 0;
                            float lineaH8 = lineaH4;
                            while (true) {
                                if ((this.height / 2.0d) - ( it2 * zoom) <= ((double) this.f84y0)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) - (it2 * zoom))) < ((double) Math.abs(distanciaV12))) {
                                    distanciaV12 = y - ((float) ((this.height / 2.0d) - (it2 * zoom)));
                                    lineaH8 = (float) it2;
                                }
                                it2++;
                            }
                            float lineaH9 = ((float) it2) - lineaH8;
                            int it12 = 1;
                            float linea2 = 0.0f;
                            while (true) {;
                            if ((this.height/ 2.0d) + (it12 * zoom) >= ((double) this.f85yf)) {
                                    break;
                                }
                                if (Math.abs(y - ((this.height / 2.0d) + (it12 * zoom))) < ((double) Math.abs(distanciaV12))) {
                                    distanciaV12 = y - ((float) ((this.height/ 2.0d) +  (it12 * zoom)));
                                    linea2 = (float) it12;
                                }
                                it12++;
                            }
                            float lineaH10 = lineaH9 + linea2;
                            if (Math.abs(distanciaV12) < Math.abs(distanciaV23)) {
                                distanciaV2 = distanciaV12;
                            } else {
                                distanciaV2 = distanciaV23;
                            }
                            lineaH = lineaH10;
                        }
                    }
                }
            }
         }
        if ((lineaDeEnfoqueV2 != 0.0f) || (lineaDeEnfoqueH2 != 0.0f)) {
            if ((lineaDeEnfoqueV2 <= 4.0f) && (lineaDeEnfoqueH2 > ((float) ((this.filas - 1) - ((int) (ncolumnasZoom2 / 2.0d)))))) {
                lineaH += ((float) (this.filas - 2)) - ncolumnasZoom2;
            } else {
                if (lineaDeEnfoqueV2 <= 4.0f && (lineaDeEnfoqueH2 > ((float) ((int) (ncolumnasZoom2 / 2.0d))))) {
                    lineaH += lineaDeEnfoqueH2 - ((float) ((int) ((ncolumnasZoom2 + 1.0f) / 2.0d)));
                } else {

                    if ((lineaDeEnfoqueV2 > ((float) (this.columnasi - 4))) && (lineaDeEnfoqueH2 > ((float) ((this.filas - 1) - ((int) (ncolumnasZoom2 / 2.0d)))))) {
                        lineaV += (float) (this.columnasi - 8);
                        lineaH += ((float) (this.filas - 2)) - ncolumnasZoom2;
                    } else {
                        if ((lineaDeEnfoqueV2 > ((float) (this.columnasi - 4))) && (lineaDeEnfoqueH2 > ((float) ((int) ( ncolumnasZoom2 / 2.0d))))) {
                            lineaV += (float) (this.columnasi - 8);
                            lineaH += lineaDeEnfoqueH2 - ((float) ((int) ((ncolumnasZoom2 + 1.0f)/ 2.0d)));
                        } else {
                            if ((lineaDeEnfoqueV2 > ((this.columnasi - 4))) && (lineaDeEnfoqueH2 <= ((float) ((int) (ncolumnasZoom2 / 2.0d))))) {
                                lineaV += (float) (this.columnasi - 8);
                            } else {
                                if ((lineaDeEnfoqueV2 > 4.0f) && (lineaDeEnfoqueH2 > ((float) ((this.filas - 1) - ((int) (ncolumnasZoom2 / 2.0d)))))) {
                                    lineaV += lineaDeEnfoqueV2 - 4.0f;
                                    lineaH += ((float) (this.filas - 2)) - ncolumnasZoom2;
                                } else {
                                    if ((lineaDeEnfoqueV2 > 4.0f) && (lineaDeEnfoqueH2 > ((float) ((int) (ncolumnasZoom2 / 2.0d))))) {
                                        lineaV += lineaDeEnfoqueV2 - 4.0f;
                                        lineaH += lineaDeEnfoqueH2 - ((float) ((int) ((ncolumnasZoom2 + 1.0f) / 2.0d)));
                                    } else {
                                        if (lineaDeEnfoqueV2 > 4.0f && (lineaDeEnfoqueH2 <= ((float) ((int) (ncolumnasZoom2 / 2.0d))))) {
                                            lineaV += lineaDeEnfoqueV2 - 4.0f;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (band) {
            if (this.lineaDeEnfoqueV <= 4.0f && (this.lineaDeEnfoqueH> ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                lineaH += (float) ((this.filas - 2) - this.ncolumnasZoom);
            } else {
                if ((this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d)))) && (this.lineaDeEnfoqueV <= 4.0f)) {
                    lineaH += this.lineaDeEnfoqueH - ((float) ((int) ((this.ncolumnasZoom + 1) / 2.0d)));
                } else {

                    if ((this.lineaDeEnfoqueV > ((float) (this.columnasi - 4))) && (this.lineaDeEnfoqueH > ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                        lineaV += (float) (this.columnasi - 8);
                        lineaH += (float) ((this.filas - 2) - this.ncolumnasZoom);
                    } else {
                        if ((this.lineaDeEnfoqueV > ((float) (this.columnasi - 4))) && ((this.lineaDeEnfoqueH) > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                            lineaV += (float) (this.columnasi - 8);
                            lineaH += this.lineaDeEnfoqueH - ((float) ((int) ((this.ncolumnasZoom + 1) / 2.0d)));
                        } else {
                            if ((this.lineaDeEnfoqueV > ((float) (this.columnasi - 4))) && ((this.lineaDeEnfoqueH) <= ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                lineaV += (float) (this.columnasi - 8);
                            } else {
                                if ((this.lineaDeEnfoqueV > 4.0f) && ((this.lineaDeEnfoqueH) > ((float) ((this.filas - 1) - ((int) (this.ncolumnasZoom / 2.0d)))))) {
                                    lineaV += this.lineaDeEnfoqueV - 4.0f;
                                     lineaH += (float) ((this.filas - 2) - this.ncolumnasZoom);
                                } else {
                                    if ((this.lineaDeEnfoqueV > 4.0f) && (this.lineaDeEnfoqueH > ((float) ((int) (this.ncolumnasZoom / 2.0d))))) {
                                        lineaV += this.lineaDeEnfoqueV - 4.0f;
                                        lineaH += this.lineaDeEnfoqueH - ((float) ((int) ((this.ncolumnasZoom + 1) / 2.0d)));
                                    } else {
                                        if ((this.lineaDeEnfoqueV > 4.0f) && ((this.lineaDeEnfoqueH) <= ((float) ((int) ((this.ncolumnasZoom) / 2.0d))))) {
                                            lineaV += this.lineaDeEnfoqueV - 4.0f;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((Math.abs(distanciaH) < Math.abs(distanciaV2)) && (lineaV == ((float) this.columnasi))) {
            lineaV -= 1.0f;
        }
        array[0] = lineaV;
        array[1] = lineaH;
        array[2] = distanciaV2;
        array[3] = distanciaH;
        return array;
    }


    public float setDis(float columnas2) {
        double d = (double) (this.f83xf - this.f82x0);
        double d2 = d - (this.dig * 0.01d);
        float f = (float) (d2 / columnas2);
        this.dis = f;
        return f;
    }

    public int[] obtenerFilasColumnas() {
        return new int[]{this.filas, this.columnasi};
    }

    public float[] getMargenes() {
        return new float[]{this.f82x0, this.f84y0, this.f83xf, this.f85yf};
    }

    public Paint getPintaRejilla() {
        return this.pintaRejilla;
    }

    public float getEspaciado() {
        return this.disi;
    }

    public void setPuntoDeZoom(float v, float v1) {
        float[] array = obtenerLineas(v, v1, setDis((float) this.columnasi), 0.0f, 0.0f, 0.0f, false);
        this.lineaDeEnfoqueV = array[0];
        this.lineaDeEnfoqueH = array[1];
    }

    public float[] getReferenciasDeAumento() {
        return new float[]{this.lineaDeEnfoqueV, this.lineaDeEnfoqueH, (float) this.ncolumnasZoom};
    }

    public float getDis() {
        return this.dis;
    }

    public float getDig() {
        return (float) this.dig;
    }

    public float getHeightRejilla() {
        return (float) this.height;
    }

    public float getWidthRejilla() {
        return (float) this.width;
    }

    public Paint getOtroPincel() {
        return this.otroPincel;
    }

    public Object getO() {
        return this.o;
    }

    public Jugador[] getaJugadores() {
        return this.aJugadores;
    }

    public Paint getPincelRojo() {
        return this.pincelRojo;
    }

    public float getDisi() {
        return this.disi;
    }

    public void setDesdeDondeH(float desdeDondeH2) {
        this.desdeDondeH = desdeDondeH2;
    }

    public void setDesdeDonde(float desdeDonde2) {
        this.desdeDonde = desdeDonde2;
    }

    public void setPaintX0(float paintX02) {
        this.paintX0 = paintX02;
    }

    public void setPaintY0(float paintY02) {
        this.paintY0 = paintY02;
    }

    public float getFromY() {
        int j = 1;
        while (true) {
            double heightRejilla = (double) getHeightRejilla();
            Double.isNaN(heightRejilla);
            if (((double) (this.f84y0 + (((float) j) * getDisi()))) >= heightRejilla / 2.0d) {
                break;
            }
            j++;
        }
        double disi2 = (double) (this.f84y0 + (((float) j) * getDisi()));
        double heightRejilla2 = (double) getHeightRejilla();
        Double.isNaN(heightRejilla2);
        Double.isNaN(disi2);
        float f = (float) (disi2 - (heightRejilla2 / 2.0d));
        this.fromY = f;
        if (((int) f) == ((int) this.disi)) {
            return 0.0f;
        }
        return f;
    }

    public float getFromX() {
        int i = 1;
        while (true) {
            double d = (double) this.f82x0;
            double dig2 = (double) getDig();
            Double.isNaN(dig2);
            Double.isNaN(d);
            double d2 = d + (dig2 * 0.005d);
            double disi2 = (double) (((float) i) * getDisi());
            Double.isNaN(disi2);
            double d3 = d2 + disi2;
            double widthRejilla = (double) getWidthRejilla();
            Double.isNaN(widthRejilla);
            if (d3 >= widthRejilla / 2.0d) {
                break;
            }
            i++;
        }
        double d4 = (double) this.f82x0;
        double dig3 = (double) getDig();
        Double.isNaN(dig3);
        Double.isNaN(d4);
        double d5 = d4 + (dig3 * 0.005d);
        double disi3 = (double) (((float) i) * getDisi());
        Double.isNaN(disi3);
        double d6 = d5 + disi3;
        double widthRejilla2 = (double) getWidthRejilla();
        Double.isNaN(widthRejilla2);
        float f = (float) (d6 - (widthRejilla2 / 2.0d));
        this.fromX = f;
        if (((int) f) == ((int) this.dis)) {
            return 0.0f;
        }
        return f;
    }

    private float[] calcularFilaDeInicioYfinal() {
        float[] linasIF = {0.0f, 0.0f};
        if ((this.lineaDeEnfoqueH > 8.0f) && (this.lineaDeEnfoqueH < ((float) (obtenerFilasColumnas()[0] + -9)))) {
            float f = this.lineaDeEnfoqueH;
            linasIF[0] = f - 8.0f;
            linasIF[1] = f + 10.0f;
        } else if (this.lineaDeEnfoqueH >= ((float) (obtenerFilasColumnas()[0] - 9))) {
            linasIF[0] = (float) ((obtenerFilasColumnas()[0] - 1) - 18);
            linasIF[1] = (float) (obtenerFilasColumnas()[0] - 1);
        } else {
            linasIF[0] = 1.0f;
            linasIF[1] = 18.0f;
        }
        return linasIF;
    }

    private float[] calcularCloumnaDeInicioYfinal() {
        float[] linasIF = {0.0f, 0.0f};
        if ((this.lineaDeEnfoqueV > 4.0f) && (this.lineaDeEnfoqueV < ((float) (this.columnasi + -4)))) {
            float f = this.lineaDeEnfoqueV;
            linasIF[0] = (f * 2.0f) - 8.0f;
            linasIF[1] = (f * 2.0f) + 8.0f;
        } else {
            float f2 = this.lineaDeEnfoqueV;
            int i = this.columnasi;
            if (f2 >= ((float) (i - 4))) {
                linasIF[0] = (float) ((i * 2) - 16);
                linasIF[1] = (float) ((i * 2) + 2);
            } else {
                linasIF[0] = 1.0f;
                linasIF[1] = 16.0f;
            }
        }
        return linasIF;
    }

    public Paint getPincelBorde() {
        return pincelBorde;
    }

    public Paint getPintaCajas() {
        return this.pintaCajas;
    }

    public void setToquesOrganizadosCompartidos(LinkedList<float[]> toquesOrganizadosCompartidos2) {
        this.toquesOrganizadosCompartidos = toquesOrganizadosCompartidos2;
    }

    public void setPosicionesOrganizadas(LinkedList<int[]> posicionesOrganizadas2) {
        this.posicionesOrganizadas = posicionesOrganizadas2;
    }
    public void setColorPintaBorde(int color){
        pincelBorde.setColor(color);
    }
    public int getLineasPorFila() {
        return this.lineasPorFila;
    }
}
