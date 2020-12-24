package com.example.myapplication;

public class Quadrupla<T> implements Cloneable {
    private T first;
    private T fourth;
    private T posicion;
    private T second;
    private T third;

    public Quadrupla(T posicion2, T first2, T second2, T third2, T fourth2) {
        this.first = first2;
        this.second = second2;
        this.third = third2;
        this.fourth = fourth2;
        this.posicion = posicion2;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public T getThird() {
        return this.third;
    }

    public T getFourth() {
        return this.fourth;
    }

    public T getPosicion() {
        return this.posicion;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Quadrupla)) {
            return false;
        }
        Quadrupla quadrupla = (Quadrupla) obj;
        if (quadrupla.getFirst().equals(this.first) && quadrupla.getSecond() == this.second && quadrupla.getThird() == this.third && quadrupla.getFourth() == this.fourth) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Object
    public Quadrupla<T> clone() throws CloneNotSupportedException {
        Quadrupla<T> cloned = (Quadrupla) super.clone();
        cloned.first = this.first;
        cloned.second = this.second;
        cloned.third = this.third;
        cloned.fourth = this.fourth;
        cloned.posicion = this.posicion;
        return cloned;
    }
}