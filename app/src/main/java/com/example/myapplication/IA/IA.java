package com.example.myapplication.IA;

import android.content.Context;
import android.util.Log;
import com.example.myapplication.Arbitro;
import com.example.myapplication.Jugador;
import com.example.myapplication.Quadrupla;
import com.example.myapplication.Rejillas.Rejilla;
import com.example.myapplication.Simulacion;
import java.util.LinkedList;
import java.util.Random;

/* renamed from: com.example.myapplication.IA.IA */
public class IA extends Jugador {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Arbitro arbitro;
    private Context contexto;
    private String dificultad;
    private int numeroDeLineas;
    private LinkedList<Quadrupla<Integer>> posicionesAleatorias = new LinkedList<>();
    private LinkedList<Quadrupla<Integer>> posicionesCompletarCuadro = new LinkedList<>();
    private LinkedList<Quadrupla<Integer>> posicionesReducirGanacia = new LinkedList<>();
    private LinkedList<Quadrupla<Integer>> posicionesSemiCuadro = new LinkedList<>();
    private Rejilla rejilla;

    public IA(String dificultad2, Context context, Arbitro arbitro2) {
        super(0, -16776961, context);
        this.arbitro = arbitro2;
        this.contexto = context;
        this.dificultad = dificultad2;
    }

    @Override // com.example.myapplication.Jugador
    public void setRejilla(Rejilla rejilla2) {
        this.rejilla = rejilla2;
    }

    public void muyFacil(LinkedList<int[]> positions) {
        int[] array2 = new int[10];
        array2[0] = getId();
        Quadrupla<Integer> array = obtenerPosicionAleatoria();
        float[] xy = obtenerCoordenadas(array);
        array2[1] = array.getFirst().intValue();
        array2[2] = array.getSecond().intValue();
        array2[3] = array.getThird().intValue();
        array2[4] = array.getFourth().intValue();
        Rejilla rejilla2 = this.rejilla;
        float[] pos = {xy[0], xy[1], rejilla2.setDis((float) rejilla2.obtenerFilasColumnas()[1]), 0.0f, 0.0f, 0.0f};
        positions.add(array2);
        this.arbitro.setPositions(positions, this.contexto);
        this.arbitro.setRejilla(this.rejilla);
        positions.get(positions.size() - 1)[9] = getColor();
        if (positions.size() != this.rejilla.getLineas()[0] + this.rejilla.getLineas()[1]) {
            super.setToques(pos);
            this.rejilla.postInvalidate();
            if (0 != 0) {
                muyFacil(positions);
            }
        }
    }

    public float[] facil(LinkedList<int[]> positions, LinkedList<int[]> positionsRejilla) {
        char c;
        Quadrupla<Integer> array;
        int[] array2 = new int[10];
        array2[0] = getId();
        float[] pos = new float[6];
        int[] criterios = generaCriterios("dificil");
        Log.i("testt6", "goal" + criterios[0] + " " + criterios[2]);
        if (criterios[0] == 1) {
            array = this.posicionesCompletarCuadro.get(0);
            removerPosciones(array);
            Log.i("testt67 ", " " + array.getFirst() + " " + array.getSecond() + " " + array.getThird() + " " + array.getFourth());
            c = 1;
        } else if (criterios[1] == 1) {
            array = obtenerPosicionAleatoria();
            c = 1;
        } else if (criterios[2] == 1) {
            new Quadrupla(0, 0, 0, 0, 0);
            c = 1;
            array = new Simulacion(this, this.arbitro, this.rejilla, positions, positionsRejilla).getPosicionDeMenorCoste();
            this.posicionesSemiCuadro.remove(array);
        } else {
            c = 1;
            array = this.posicionesSemiCuadro.get(0);
            this.posicionesSemiCuadro.remove(0);
        }
        float[] xy = obtenerCoordenadas(array);
        array2[c] = array.getFirst().intValue();
        array2[2] = array.getSecond().intValue();
        array2[3] = array.getThird().intValue();
        array2[4] = array.getFourth().intValue();
        pos[0] = xy[0];
        pos[c] = xy[c];
        Rejilla rejilla2 = this.rejilla;
        pos[2] = rejilla2.setDis((float) rejilla2.obtenerFilasColumnas()[c]);
        pos[3] = 0.0f;
        pos[4] = 0.0f;
        pos[5] = 0.0f;
        positions.add(array2);
        return pos;
    }

    public void setToque(float[] pos) {
        getToques().add(pos);
    }

    private Quadrupla<Integer> obtenerPosicionAleatoria() {
        boolean fallo = false;
        Quadrupla<Integer> array = new Quadrupla<>(0, 0, 0, 0, 0);
        do {
            double size = (double) (this.posicionesAleatorias.size() - 1);
            double nextDouble = new Random().nextDouble();
            Double.isNaN(size);
            try {
                array = this.posicionesAleatorias.get((int) (size * nextDouble));
                continue;
            } catch (Exception e) {
                fallo = true;
                continue;
            }
        } while (fallo);
        removerPosciones(array);
        return array;
    }

    private float[] obtenerCoordenadas(Quadrupla<Integer> array2) {
        float x;
        float y;
        if (array2.getFirst().intValue() == 0) {
            y = (((float) (array2.getFourth().intValue() - 1)) * this.rejilla.getEspaciado()) + (this.rejilla.getEspaciado() / 2.0f) + this.rejilla.getMargenes()[1];
            float intValue = (((float) array2.getSecond().intValue()) * this.rejilla.getEspaciado()) + this.rejilla.getMargenes()[0];
            double dig = (double) this.rejilla.getDig();
            Double.isNaN(dig);
            x = intValue + ((float) (dig * 0.005d));
        } else {
            y = (((float) array2.getSecond().intValue()) * this.rejilla.getEspaciado()) + this.rejilla.getMargenes()[1];
            float intValue2 = (((float) (array2.getFourth().intValue() - 1)) * this.rejilla.getEspaciado()) + (this.rejilla.getEspaciado() / 2.0f) + this.rejilla.getMargenes()[0];
            double dig2 = (double) this.rejilla.getDig();
            Double.isNaN(dig2);
            x = intValue2 + ((float) (dig2 * 0.005d));
        }
        return new float[]{x, y};
    }

    private int[] generaCriterios(String dificulatad) {
        int[] criterios = {0, 0, 0};
        boolean z = false;
        if (dificulatad.equalsIgnoreCase("facil")) {
            int completarCuadro = new Random().nextInt(9);
            int evitarSemiCuadro = new Random().nextInt(9);
            if ((completarCuadro <= 2) && (!this.posicionesCompletarCuadro.isEmpty())) {
                criterios[0] = 1;
            }
            if (evitarSemiCuadro > 2) {
                z = true;
            }
            if ((!this.posicionesAleatorias.isEmpty()) && z) {
                criterios[1] = 1;
            }
        } else if (dificulatad.equalsIgnoreCase("normal")) {
            int completarCuadro2 = new Random().nextInt(24);
            int menorGanancia = new Random().nextInt(9);
            if ((completarCuadro2 > 2) && (!this.posicionesCompletarCuadro.isEmpty())) {
                criterios[0] = 1;
            }
            if (!this.posicionesAleatorias.isEmpty()) {
                criterios[1] = 1;
            }
            if (menorGanancia <= 5) {
                criterios[2] = 1;
            }
        } else if (dificulatad.equalsIgnoreCase("dificil")) {
            if (this.posicionesCompletarCuadro.size() > 0) {
                criterios[0] = 1;
            }
            if (this.posicionesAleatorias.size() > 0) {
                criterios[1] = 1;
            }
            if (this.posicionesAleatorias.size() == 0) {
                criterios[2] = 1;
            }
        }
        return criterios;
    }

    public void removerPosciones(Quadrupla<Integer> posicioneARemover) {
        this.posicionesAleatorias.remove(posicioneARemover);
        this.posicionesSemiCuadro.remove(posicioneARemover);
        this.posicionesCompletarCuadro.remove(posicioneARemover);
    }

    public void llenarArrayPoscionesAleatorias(LinkedList<int[]> positionRejilla, LinkedList<float[]> toquesOrganizados, LinkedList<int[]> posicionesOrganizadas) {
        for (int j = 1; j <= this.rejilla.obtenerFilasColumnas()[0] - 1; j++) {
            for (int i = 1; i <= this.rejilla.obtenerFilasColumnas()[1] - 1; i++) {
                this.posicionesAleatorias.add(new Quadrupla<>(0, 0, Integer.valueOf(i), Integer.valueOf(j - 1), Integer.valueOf(j)));
                positionRejilla.add(new int[]{0});
                toquesOrganizados.add(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
                posicionesOrganizadas.add(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
        }
        for (int i2 = 1; i2 <= this.rejilla.obtenerFilasColumnas()[1]; i2++) {
            for (int j2 = 1; j2 <= this.rejilla.obtenerFilasColumnas()[0] - 2; j2++) {
                this.posicionesAleatorias.add(new Quadrupla<>(0, 1, Integer.valueOf(j2), Integer.valueOf(i2 - 1), Integer.valueOf(i2)));
                positionRejilla.add(new int[]{0});
                toquesOrganizados.add(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
                posicionesOrganizadas.add(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
        }
        for (int i3 = 0; i3 <= this.rejilla.obtenerFilasColumnas()[1]; i3++) {
            positionRejilla.add(new int[]{0});
            toquesOrganizados.add(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
            posicionesOrganizadas.add(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(0, 1, 1, this.rejilla)), 0, 1, 0, 1));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(1, 1, 1, this.rejilla)), 1, 1, 0, 1));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(0, this.rejilla.obtenerFilasColumnas()[1] - 1, 1, this.rejilla)), 0, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), 0, 1));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(1, 1, this.rejilla.obtenerFilasColumnas()[1], this.rejilla)), 1, 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1])));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(0, 1, this.rejilla.obtenerFilasColumnas()[0] - 1, this.rejilla)), 0, 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 1)));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(1, this.rejilla.obtenerFilasColumnas()[0] - 2, 1, this.rejilla)), 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), 0, 1));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(0, this.rejilla.obtenerFilasColumnas()[1] - 1, this.rejilla.obtenerFilasColumnas()[0] - 1, this.rejilla)), 0, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 1)));
        this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(Arbitro.calcularPosicion(1, this.rejilla.obtenerFilasColumnas()[0] - 2, this.rejilla.obtenerFilasColumnas()[1], this.rejilla)), 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1])));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(0, 1, 1, this.rejilla)), 0, 1, 0, 1));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(1, 1, 1, this.rejilla)), 1, 1, 0, 1));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(0, this.rejilla.obtenerFilasColumnas()[1] - 1, 1, this.rejilla)), 0, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), 0, 1));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(1, 1, this.rejilla.obtenerFilasColumnas()[1], this.rejilla)), 1, 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1])));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(0, 1, this.rejilla.obtenerFilasColumnas()[0] - 1, this.rejilla)), 0, 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 1)));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(1, this.rejilla.obtenerFilasColumnas()[0] - 2, 1, this.rejilla)), 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), 0, 1));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(0, this.rejilla.obtenerFilasColumnas()[1] - 1, this.rejilla.obtenerFilasColumnas()[0] - 1, this.rejilla)), 0, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 1)));
        this.posicionesAleatorias.remove(new Quadrupla(Integer.valueOf(Arbitro.calcularPosicion(1, this.rejilla.obtenerFilasColumnas()[0] - 2, this.rejilla.obtenerFilasColumnas()[1], this.rejilla)), 1, Integer.valueOf(this.rejilla.obtenerFilasColumnas()[0] - 2), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1] - 1), Integer.valueOf(this.rejilla.obtenerFilasColumnas()[1])));
        this.numeroDeLineas = this.posicionesAleatorias.size();
    }

    public void llenarArrayPoscionesCompletarCuadro(Quadrupla<Integer> array) {
        if (!this.posicionesCompletarCuadro.contains(array)) {
            this.posicionesCompletarCuadro.add(array);
        }
        this.posicionesAleatorias.remove(array);
    }

    public void llenarArrayPoscionesSemiCuadro(Quadrupla<Integer> array) {
        if (!this.posicionesSemiCuadro.contains(array)) {
            this.posicionesSemiCuadro.add(array);
        }
        this.posicionesAleatorias.remove(array);
    }

    public LinkedList<Quadrupla<Integer>> getPosicionesCompletarCuadro() {
        return this.posicionesCompletarCuadro;
    }

    public LinkedList<Quadrupla<Integer>> getposicionesAleatorias() {
        return this.posicionesAleatorias;
    }

    public LinkedList<Quadrupla<Integer>> getPosicionesMenorCoste() {
        return this.posicionesReducirGanacia;
    }

    public LinkedList<Quadrupla<Integer>> getPosicionesSemiCuadro() {
        return this.posicionesSemiCuadro;
    }

    public void setPosicionesSemiCuadrado(LinkedList<Quadrupla<Integer>> posicionesSemiCuadro2) {
        this.posicionesSemiCuadro = posicionesSemiCuadro2;
    }

    public void setPosicionesCompletarCuadro(LinkedList<Quadrupla<Integer>> posicionesCompletarCuadro2) {
        this.posicionesCompletarCuadro = posicionesCompletarCuadro2;
    }

    public void setPosicionesMenorGanancia(LinkedList<Quadrupla<Integer>> posicionesMenorCoste) {
        this.posicionesReducirGanacia = posicionesMenorCoste;
    }

    public void setPosicionesAleatorias(LinkedList<Quadrupla<Integer>> posicionesAleatorias2) {
        this.posicionesAleatorias = posicionesAleatorias2;
    }

    public Rejilla getRejilla() {
        return this.rejilla;
    }
}