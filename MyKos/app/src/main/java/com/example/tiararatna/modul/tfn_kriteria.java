package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 *
 * @author Tiara Ratna
 */
public class tfn_kriteria  implements Serializable {

    private double a ;
    private double b;
    private double c;

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
    
}
