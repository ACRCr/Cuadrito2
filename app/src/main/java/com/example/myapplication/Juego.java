package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;

import com.example.myapplication.Rejillas.DrawRejillaCompleta;
import com.example.myapplication.Rejillas.DrawToquesYCuadritos;
import com.example.myapplication.Rejillas.Rejilla;
import com.example.myapplication.IA.IA;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Juego extends AppCompatActivity {
    private Arbitro arbitro;
    private boolean band2;
    private int columnas;
    private Thread cuentaRegresiva;
    public float[][] dondeToque;
    private DrawToquesYCuadritos drawToquesYCuadritos;
    private boolean iaRepiteTurno;
    private Jugador[] jugador;
    private int jugadores;
    private ReentrantLock lock = new ReentrantLock();
    CountDownLatch miCount;
    private int posicion;
    private LinkedList<int[]> posicionesOrganizadas;
    private LinkedList<int[]> posicionesTemporales;
    private LinkedList<int[]> positions;
    private LinkedList<int[]> positionsRejilla;
    private int quienJuega = 1;
    private Rejilla rejilla;
    private DrawRejillaCompleta rejillaCompleta;
    private Thread tIA;
    private Thread tZoom;
    public int tiempoEspera = 200;
    private Thread tjugador;
    private float toqueaX;
    private float toqueaY;
    private LinkedList<float[]> toques;
    private LinkedList<float[]> toquesOrganizados;

    /* renamed from: x */
    private float f46x;

    /* renamed from: y */
    private float f47y;
    private boolean zoom;

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(4102);
    }

    /* access modifiers changed from: protected */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.ComponentActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.positions = new LinkedList<>();
        this.posicionesTemporales = new LinkedList<>();
        this.positionsRejilla = new LinkedList<>();
        this.posicionesOrganizadas = new LinkedList<>();
        this.toques = new LinkedList<>();
        this.toquesOrganizados = new LinkedList<>();
        Intent intent = getIntent();
        this.columnas = intent.getIntExtra("posicion", 5);
        this.jugadores = intent.getIntExtra("jugadores", 1);
        this.rejilla = new Rejilla(this, this.columnas, this);
        this.rejillaCompleta = new DrawRejillaCompleta(this, this.columnas, this);
        this.drawToquesYCuadritos = new DrawToquesYCuadritos(this, this.columnas, this);
        this.rejillaCompleta = new DrawRejillaCompleta(this, this.columnas, this);
        this.arbitro = new Arbitro();
        this.dondeToque = (float[][]) Array.newInstance(float.class, 2, 2);
        Jugador[] jugadorArr = new Jugador[(this.jugadores + 1)];
        this.jugador = jugadorArr;
        jugadorArr[1] = new Jugador(1, SupportMenu.CATEGORY_MASK, this);
        this.jugador[0] = new IA("normal", this, this.arbitro);
        if (this.jugadores > 1) {
            this.jugador[2] = new Jugador(2, -16711936, this);
        }
        this.rejilla.setAjugadores(this.jugador);
        this.drawToquesYCuadritos.setAjugadores(this.jugador);
        this.jugador[0].setRejilla(this.rejilla);
        this.jugador[1].setRejilla(this.rejilla);
        if (this.jugadores > 1) {
            this.jugador[2].setRejilla(this.rejilla);
        }
        this.rejilla.setBackgroundColor(-1);
        this.arbitro.setRejilla(this.rejilla);
        View v = this.rejilla;
        v.setMinimumHeight((int) this.rejilla.getHeightRejilla());
        v.setMinimumWidth((int) this.rejilla.getWidthRejilla());
        v.setDrawingCacheEnabled(true);
        this.drawToquesYCuadritos.setRejillaCompleta(ImageUtil.getBitmapFromView(v));
        this.drawToquesYCuadritos.setPositions(this.posicionesTemporales);
        this.drawToquesYCuadritos.setToques(this.toques);
        ((IA) this.jugador[0]).llenarArrayPoscionesAleatorias(this.positionsRejilla, this.toquesOrganizados, this.posicionesOrganizadas);
        setContentView(this.drawToquesYCuadritos);
    }

    /* JADX INFO: Multiple debug info for r7v1 com.example.myapplication.Rejillas.DrawToquesYCuadritos: [D('v' android.view.View), D('e' java.lang.InterruptedException)] */
    public boolean onTouchEvent(MotionEvent event) {
        this.band2 = false;
        Runnable r3 = new Runnable() {
            /* class com.example.myapplication.$$Lambda$Juego$nUoNDJUmytnj4hTCmjqELg8VTj4 */

            public final void run() {
                Juego.this.lambda$onTouchEvent$0$Juego();
            }
        };
        Runnable r = new Runnable() {
            /* class com.example.myapplication.$$Lambda$Juego$21Uyw2TsU9JQMyeTFFo4r8SvH5M */

            public final void run() {
                Juego.this.lambda$onTouchEvent$1$Juego();
            }
        };
        Runnable r2 = new Runnable() {
            /* class com.example.myapplication.$$Lambda$Juego$HDNZf5QE0w6WUR5CAyV9I37Aoc */

            public final void run() {
                Juego.this.lambda$onTouchEvent$2$Juego();
            }
        };
        Runnable r4 = new Runnable() {
            /* class com.example.myapplication.$$Lambda$Juego$lQhY4Wvtm2JI4Wf9Z_Sl8LxfQEw */

            public final void run() {
                Juego.this.lambda$onTouchEvent$3$Juego();
            }
        };
        lambda r5 = lambda.INSTANCE;
        this.tjugador = new Thread(r);
        this.tIA = new Thread(r2);
        this.tZoom = new Thread(r4);
        Thread thread = new Thread(r3);
        this.cuentaRegresiva = thread;
        thread.interrupt();
        try {
            this.tIA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (event.getAction() == 1) {
            this.toques.clear();
            this.posicionesTemporales.clear();
            this.f46x = 0.0f;
            this.f47y = 0.0f;
            this.f46x = event.getX();
            this.f47y = event.getY();
            if (((this.f46x < this.rejilla.getMargenes()[0]) | (this.f47y < this.rejilla.getMargenes()[1]) | (this.f46x > this.rejilla.getMargenes()[2])) || (this.f47y > this.rejilla.getMargenes()[3])) {
                return true;
            }
            this.rejilla.setDis((float) this.columnas);
            Rejilla rejilla2 = this.rejilla;
            double dig = (double) rejilla2.getDig();
            Double.isNaN(dig);
            rejilla2.setDesdeDonde((float) (dig * 0.005d));
            setContentView(this.drawToquesYCuadritos);
            this.band2 = false;
            if ((this.tiempoEspera < 200) && (this.columnas > 8)) {
                this.zoom = true;
                this.band2 = true;
                this.cuentaRegresiva.interrupt();
                this.tZoom.start();
                try {
                    this.tZoom.join();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                return true;
            } else if (this.toqueaX == this.f46x && this.toqueaY == this.f47y) {
                return false;
            } else {
                float[][] fArr = this.dondeToque;
                float[] fArr2 = fArr[0];
                float f = this.f46x;
                fArr2[0] = f;
                float[] fArr3 = fArr[0];
                float f2 = this.f47y;
                fArr3[1] = f2;
                this.toqueaY = f2;
                this.toqueaX = f;
                this.cuentaRegresiva.start();
                if (this.columnas <= 8) {
                    try {
                        this.cuentaRegresiva.join();
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        View v = this.drawToquesYCuadritos;
        v.setMinimumHeight((int) this.drawToquesYCuadritos.getHeightRejilla());
        v.setMinimumWidth((int) this.drawToquesYCuadritos.getWidthRejilla());
        this.drawToquesYCuadritos.setRejillaCompleta(ImageUtil.getBitmapFromView(v));
        return false;
    }

    public /* synthetic */ void lambda$onTouchEvent$0$Juego() {
        this.lock.lock();
        boolean z = false;
        try {
            this.tiempoEspera = 0;
            while (this.tiempoEspera <= 200) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
                Log.i("Tiempo ", " " + this.tiempoEspera);
                this.tiempoEspera = this.tiempoEspera + 1;
            }
            try {
                this.tjugador.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            try {
                if ((!this.band2) && (!this.tjugador.isAlive())) {
                    this.tjugador.start();
                }
            } catch (IllegalThreadStateException e3) {
            }
            try {
                this.tjugador.join();
            } catch (InterruptedException e4) {
                e4.printStackTrace();
            }
            try {
                this.tIA.join();
            } catch (InterruptedException e5) {
                e5.printStackTrace();
            }
            try {
                if (!this.band2) {
                    z = true;
                }
                if (z && (!this.tIA.isAlive())) {
                    this.tIA.start();
                }
            } catch (IllegalThreadStateException e6) {
            }
            try {
                this.tIA.join();
            } catch (InterruptedException e7) {
                e7.printStackTrace();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public /* synthetic */ void lambda$onTouchEvent$1$Juego() {
        int id;
        float[] array;
        int i = this.quienJuega;
        int i2 = this.jugadores;
        if (i % i2 == 1 || i / i2 == i) {
            id = this.jugador[1].getId();
        } else {
            id = this.jugador[2].getId();
        }

        float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f};
        if (this.zoom) {
            Rejilla rejilla2 = this.rejilla;
            array = rejilla2.obtenerLineas(this.f46x, this.f47y, rejilla2.setDis(8.0f), 0.0f, 0.0f, 0.0f, true);
        } else {
            Rejilla rejilla3 = this.rejilla;
            array = rejilla3.obtenerLineas(this.f46x, this.f47y, rejilla3.setDis((float) this.columnas), 0.0f, 0.0f, 0.0f, false);
        }
        if (array != null) {
            Rejilla rejilla4 = this.rejilla;
            LinkedList<int[]> linkedList = this.positionsRejilla;
            LinkedList<int[]> linkedList2 = this.positions;
            Jugador[] jugadorArr = this.jugador;
            Object[] objects = rejilla4.metodo(linkedList, linkedList2, jugadorArr[id], null, array[3], array[2], (int) array[0], (int) array[1], jugadorArr[id].getId(), false);
            if (!((Boolean) objects[0]).booleanValue()) {
                this.posicion = ((Integer) objects[1]).intValue();
                this.quienJuega++;
                if (this.zoom) {
                    this.jugador[id].setToques(this.f46x, this.f47y, this.rejilla.setDis(8.0f), this.rejilla.getReferenciasDeAumento());
                    llenarToquesOrganizadosCompartidos(this.jugador[id].getToques().getLast(), 1);
                    float[] toque = {(float) id, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                    for (int i3 = 0; i3 < this.jugador[id].getToques().getLast().length; i3++) {
                        toque[i3 + 1] = this.jugador[id].getToques().getLast()[i3];
                    }
                    this.toques.add(toque);
                } else {
                    this.jugador[id].setToques(this.f46x, this.f47y, this.rejilla.setDis((float) this.columnas), null);
                    llenarToquesOrganizadosCompartidos(this.jugador[id].getToques().getLast(), 1);
                    float[] toque2 = {(float) id, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                    for (int i4 = 0; i4 < this.jugador[id].getToques().getLast().length; i4++) {
                        toque2[i4 + 1] = this.jugador[id].getToques().getLast()[i4];
                    }
                    this.toques.add(toque2);
                }
                this.drawToquesYCuadritos.setToques(this.toques);
                this.arbitro.setPositions(this.positions, getApplicationContext());
                this.arbitro.llenarArraysParaLaDificulatasdIA(this.positions.getLast(), (IA) this.jugador[0], this.positionsRejilla, this.posicion);
                if (this.arbitro.obtienePunto(this.jugador[1], this.rejilla.obtenerFilasColumnas(), this.posicion, this.positionsRejilla)) {
                    this.quienJuega--;
                }
                if (this.positions.size() > 0) {
                    LinkedList<int[]> linkedList3 = this.positions;
                    linkedList3.get(linkedList3.size() - 1)[9] = this.jugador[id].getColor();
                }
                this.posicionesTemporales.add(this.positions.getLast());
                llenarPosicionesOrganizados();
                this.drawToquesYCuadritos.setPositions(this.posicionesTemporales);
                this.rejilla.setDis((float) this.columnas);
                this.drawToquesYCuadritos.postInvalidate();
            }
        }
    }

    public /* synthetic */ void lambda$onTouchEvent$2$Juego() {
        int i = this.jugadores;
        if (i != 2 && i == 1 && this.quienJuega % (i + 1) == 0) {
            do {
                this.iaRepiteTurno = false;
                if (this.positions.size() < this.rejilla.getnLineas()) {
                    float[] pos = ((IA) this.jugador[0]).facil(this.positions, this.positionsRejilla);
                    this.positions.getLast()[9] = this.jugador[0].getColor();
                    int calcularPosicion = Arbitro.calcularPosicion(this.positions.getLast()[1], this.positions.getLast()[2], this.positions.getLast()[4], this.rejilla);
                    this.posicion = calcularPosicion;
                    this.positionsRejilla.get(calcularPosicion)[0] = 1;
                    this.arbitro.setPositions(this.positions, getApplicationContext());
                    this.iaRepiteTurno = this.arbitro.obtienePunto(this.jugador[0], this.rejilla.obtenerFilasColumnas(), this.posicion, this.positionsRejilla);
                    this.arbitro.llenarArraysParaLaDificulatasdIA(this.positions.getLast(), (IA) this.jugador[0], this.positionsRejilla, this.posicion);
                    ((IA) this.jugador[0]).setToque(pos);
                    llenarToquesOrganizadosCompartidos(pos, 0);
                    llenarPosicionesOrganizados();
                    float[] toque = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                    for (int i2 = 0; i2 < this.jugador[0].getToques().getLast().length; i2++) {
                        toque[i2 + 1] = this.jugador[0].getToques().getLast()[i2];
                    }
                    this.toques.add(toque);
                    this.drawToquesYCuadritos.setToques(this.toques);
                    this.posicionesTemporales.add(this.positions.getLast());
                }
            } while (this.iaRepiteTurno & (this.positions.size() < this.rejilla.getnLineas()));
            this.quienJuega++;
        }
        this.zoom = false;
        this.drawToquesYCuadritos.setPositions(this.posicionesTemporales);
        this.drawToquesYCuadritos.postInvalidate();
    }

    public /* synthetic */ void lambda$onTouchEvent$3$Juego() {
        float[][] fArr = this.dondeToque;
        fArr[1][0] = this.f46x;
        fArr[1][1] = this.f47y;
        Rejilla rejilla2 = this.rejilla;
        double d = (double) (fArr[0][0] + fArr[1][0]);
        Double.isNaN(d);
        double d2 = (double) (fArr[0][1] + fArr[1][1]);
        Double.isNaN(d2);
        rejilla2.setPuntoDeZoom((float) (d / 2.0d), (float) (d2 / 2.0d));
        this.rejilla.setDis(8.0f);
        this.band2 = true;
        Rejilla rejilla3 = this.rejilla;
        double dig = (double) rejilla3.getDig();
        Double.isNaN(dig);
        rejilla3.setDesdeDonde((float) (dig * 0.005d));
        this.rejilla.setToquesOrganizadosCompartidos(this.toquesOrganizados);
        this.rejilla.setPosicionesOrganizadas(this.posicionesOrganizadas);
        this.rejilla.postInvalidate();
        setContentView(this.rejilla);
    }

    static /* synthetic */ void lambda$onTouchEvent$4() {
    }

    public void llenarToquesOrganizadosCompartidos(float[] array, int id) {
        this.toquesOrganizados.get(this.posicion)[0] = (float) id;
        System.arraycopy(array, 0, this.toquesOrganizados.get(this.posicion), 1, array.length);
    }

    public void llenarPosicionesOrganizados() {
        this.posicionesOrganizadas.get(this.posicion)[0] = this.positions.getLast()[0];
        for (int i = 1; i < this.positions.getLast().length; i++) {
            this.posicionesOrganizadas.get(this.posicion)[i] = this.positions.getLast()[i];
        }
    }
}