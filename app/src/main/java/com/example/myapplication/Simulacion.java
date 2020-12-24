package com.example.myapplication;

import android.util.Log;
import com.example.myapplication.Rejillas.Rejilla;
import com.example.myapplication.IA.IA;
import java.lang.reflect.Array;
import java.util.LinkedList;

public class Simulacion {
    private Arbitro arbitro;
    private int[][] array;
    private int[][] arrayP;
    private int[][] arrayPosicionesOrganizadas;
    private int[][] arraySC;

    private IA ia;
    private LinkedList<Quadrupla<Integer>> posicionesAleatorias;
    private LinkedList<Quadrupla<Integer>> posicionesCompletarCuadro;
    private LinkedList<Quadrupla<Integer>> posicionesMenorCoste;
    private LinkedList<Quadrupla<Integer>> posicionesSemiCuadro;
    LinkedList<int[]> positionsRejilla;
    private Rejilla rejilla;

    public Simulacion(IA ia, Arbitro arbitro2, Rejilla rejilla2, LinkedList<int[]> linkedList, LinkedList<int[]> positionsRejilla2) {
        this.posicionesCompletarCuadro = ia.getPosicionesCompletarCuadro();
        this.posicionesSemiCuadro = ia.getPosicionesSemiCuadro();
        this.positionsRejilla = positionsRejilla2;
        int size = positionsRejilla2.size();
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = size;
        this.arrayPosicionesOrganizadas = (int[][]) Array.newInstance(int.class, iArr);
        int size2 = this.posicionesCompletarCuadro.size();
        int[] iArr2 = new int[2];
        iArr2[1] = 5;
        iArr2[0] = size2;
        this.array = (int[][]) Array.newInstance(int.class, iArr2);
        int size3 = this.posicionesSemiCuadro.size();
        int[] iArr3 = new int[2];
        iArr3[1] = 5;
        iArr3[0] = size3;
        this.arraySC = (int[][]) Array.newInstance(int.class, iArr3);
        for (int i = 0; i < this.posicionesSemiCuadro.size(); i++) {
            this.arraySC[i][0] = this.posicionesSemiCuadro.get(i).getFirst().intValue();
            this.arraySC[i][1] = this.posicionesSemiCuadro.get(i).getSecond().intValue();
            this.arraySC[i][2] = this.posicionesSemiCuadro.get(i).getThird().intValue();
            this.arraySC[i][3] = this.posicionesSemiCuadro.get(i).getFourth().intValue();
            this.arraySC[i][4] = this.posicionesSemiCuadro.get(i).getPosicion().intValue();
        }
        for (int i2 = 0; i2 < this.posicionesCompletarCuadro.size(); i2++) {
            this.array[i2][0] = this.posicionesCompletarCuadro.get(i2).getFirst().intValue();
            this.array[i2][1] = this.posicionesCompletarCuadro.get(i2).getSecond().intValue();
            this.array[i2][2] = this.posicionesCompletarCuadro.get(i2).getThird().intValue();
            this.array[i2][3] = this.posicionesCompletarCuadro.get(i2).getFourth().intValue();
            this.array[i2][4] = this.posicionesSemiCuadro.get(i2).getPosicion().intValue();
        }
        for (int i3 = 0; i3 < positionsRejilla2.size(); i3++) {
            this.arrayPosicionesOrganizadas[i3][0] = positionsRejilla2.get(i3)[0];
        }
        Log.i("CompletarPosiciones ", " " + this.posicionesCompletarCuadro.size());
        Log.i("CompletarPosicionesSCf ", " " + this.posicionesSemiCuadro.size() + " " + this.arraySC.length);
        this.ia = ia;
        this.arbitro = arbitro2;
        this.rejilla = rejilla2;
    }

    public Quadrupla<Integer> getPosicionDeMenorCoste() {
        Log.i("menorCoste1 ", " hola");
        int costeM = 1000000;
        int desdeDondeCC = this.array.length;
        Quadrupla<Integer> posicionMenorCoste = new Quadrupla<>(0, 0, 0, 0, 0);
        for (int i = 0; i < this.posicionesSemiCuadro.size(); i++) {
            Log.i("menorCoste2 ", " " + this.posicionesSemiCuadro.get(i).getFirst() + " " + this.posicionesSemiCuadro.get(i).getSecond() + " " + this.posicionesSemiCuadro.get(i).getThird() + " " + this.posicionesSemiCuadro.get(i).getFourth());
            Quadrupla<Integer> posicionMenorCoste2 = this.posicionesSemiCuadro.get(i);
            this.positionsRejilla.get(this.posicionesSemiCuadro.get(i).getPosicion().intValue())[0] = 1;
            int i2 = 5;
            this.arbitro.llenarArraysParaLaDificulatasdIA(new int[]{0, this.posicionesSemiCuadro.get(i).getFirst().intValue(), this.posicionesSemiCuadro.get(i).getSecond().intValue(), this.posicionesSemiCuadro.get(i).getThird().intValue(), this.posicionesSemiCuadro.get(i).getFourth().intValue()}, this.ia, this.positionsRejilla, this.posicionesSemiCuadro.get(i).getPosicion().intValue());
            int j = desdeDondeCC;
            while (j < this.posicionesCompletarCuadro.size()) {
                Log.i("arrayPosicion10", " " + this.posicionesCompletarCuadro.get(j).getFirst() + " " + this.posicionesCompletarCuadro.get(j).getSecond() + " " + this.posicionesCompletarCuadro.get(j).getThird() + " " + this.posicionesCompletarCuadro.get(j).getFourth() + " " + this.posicionesCompletarCuadro.get(j).getPosicion());
                if (!Arbitro.esta(this.positionsRejilla, this.posicionesCompletarCuadro.get(j).getPosicion().intValue())) {
                    this.posicionesSemiCuadro.remove(this.posicionesCompletarCuadro.get(j));
                    this.positionsRejilla.get(this.posicionesCompletarCuadro.get(j).getPosicion().intValue())[0] = 1;
                    Arbitro arbitro2 = this.arbitro;
                    int[] iArr = new int[i2];
                    iArr[0] = 0;
                    iArr[1] = this.posicionesCompletarCuadro.get(j).getFirst().intValue();
                    iArr[2] = this.posicionesCompletarCuadro.get(j).getSecond().intValue();
                    iArr[3] = this.posicionesCompletarCuadro.get(j).getThird().intValue();
                    iArr[4] = this.posicionesCompletarCuadro.get(j).getFourth().intValue();
                    arbitro2.llenarArraysParaLaDificulatasdIA(iArr, this.ia, this.positionsRejilla, this.posicionesCompletarCuadro.get(j).getPosicion().intValue());
                }
                j++;
                i2 = 5;
            }
            if (this.posicionesCompletarCuadro.size() - this.array.length < costeM) {
                posicionMenorCoste = posicionMenorCoste2;
                costeM = this.posicionesCompletarCuadro.size() - this.array.length;
            }
            this.posicionesCompletarCuadro.clear();
            int[][] iArr2 = this.array;
            for (int[] q : iArr2) {
                this.posicionesCompletarCuadro.add(new Quadrupla<>(Integer.valueOf(q[4]), Integer.valueOf(q[0]), Integer.valueOf(q[1]), Integer.valueOf(q[2]), Integer.valueOf(q[3])));
            }
            this.ia.setPosicionesCompletarCuadro(this.posicionesCompletarCuadro);
            for (int k = 0; k < this.positionsRejilla.size(); k++) {
                this.positionsRejilla.get(k)[0] = this.arrayPosicionesOrganizadas[k][0];
            }
        }
        Log.i("CompletarPosiciones1.5 ", " " + this.posicionesCompletarCuadro.size() + " " + this.array.length);
        this.posicionesCompletarCuadro.clear();
        int[][] iArr3 = this.array;
        for (int[] q2 : iArr3) {
            this.posicionesCompletarCuadro.add(new Quadrupla<>(Integer.valueOf(q2[4]), Integer.valueOf(q2[0]), Integer.valueOf(q2[1]), Integer.valueOf(q2[2]), Integer.valueOf(q2[3])));
        }
        this.posicionesSemiCuadro.clear();
        int[][] iArr4 = this.arraySC;
        for (int[] q3 : iArr4) {
            this.posicionesSemiCuadro.add(new Quadrupla<>(Integer.valueOf(q3[4]), Integer.valueOf(q3[0]), Integer.valueOf(q3[1]), Integer.valueOf(q3[2]), Integer.valueOf(q3[3])));
        }
        for (int k2 = 0; k2 < this.positionsRejilla.size(); k2++) {
            this.positionsRejilla.get(k2)[0] = this.arrayPosicionesOrganizadas[k2][0];
        }
        Log.i("CompletarPosicionesf ", " " + this.posicionesCompletarCuadro.size() + " " + this.array.length);
        Log.i("CompletarPosicionesSCf ", " " + this.posicionesSemiCuadro.size() + " " + this.arraySC.length);
        Log.i("MenorCoste4 ", "" + costeM + "  " + posicionMenorCoste.getFirst() + " " + posicionMenorCoste.getSecond() + " " + posicionMenorCoste.getThird() + " " + posicionMenorCoste.getFourth());
        this.ia.setPosicionesSemiCuadrado(this.posicionesSemiCuadro);
        this.ia.setPosicionesCompletarCuadro(this.posicionesCompletarCuadro);
        return posicionMenorCoste;
    }
}