package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import com.example.myapplication.Rejillas.Rejilla;
import java.util.LinkedList;

public class Jugador {
    private int color;
    private Context contexto;

    /* renamed from: id */
    private int f48id;
    private int puntuacion = 0;
    private Rejilla rejilla;
    private LinkedList<float[]> toques;

    /* renamed from: x0 */
    private float f49x0;

    /* renamed from: xf */
    private float f50xf;

    /* renamed from: y0 */
    private float f51y0;

    /* renamed from: yf */
    private float f52yf;

    public Jugador(int id, int color2, Context contexto2) {
        this.f48id = id;
        this.contexto = contexto2;
        this.color = color2;
        this.toques = new LinkedList<>();
        this.f49x0 = 0.0f;
        this.f50xf = 0.0f;
        this.f51y0 = 0.0f;
        this.f52yf = 0.0f;
    }

    public int getColor() {
        return this.color;
    }

    public int getId() {
        return this.f48id;
    }

    public void aumentarPuntaje() {
        this.puntuacion++;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setToques(float x, float y, float zoom, float[] referenciaAumento) {
        Rejilla rejilla2 = this.rejilla;
        if (rejilla2 != null) {
            this.f49x0 = rejilla2.getMargenes()[0];
            this.f51y0 = this.rejilla.getMargenes()[1];
            this.f50xf = this.rejilla.getMargenes()[2];
            this.f52yf = this.rejilla.getMargenes()[3];
        }
        float[] pos = new float[6];
        pos[0] = x;
        pos[1] = y;
        pos[2] = zoom;
        if (referenciaAumento == null) {
            pos[3] = 0.0f;
            pos[4] = 0.0f;
            pos[5] = 0.0f;
        } else {
            pos[3] = referenciaAumento[0];
            pos[4] = referenciaAumento[1];
            pos[5] = referenciaAumento[2];
        }
        this.toques.add(pos);
        Log.i("Pos", "X: ");
    }

    public LinkedList<float[]> getToques() {
        return this.toques;
    }

    public void setRejilla(Rejilla rejilla2) {
        this.rejilla = rejilla2;
    }

    public void setColor(int color2) {
        this.color = color2;
    }

    /* access modifiers changed from: protected */
    public void setToques(float[] pos) {
        this.toques.add(pos);
    }
}
