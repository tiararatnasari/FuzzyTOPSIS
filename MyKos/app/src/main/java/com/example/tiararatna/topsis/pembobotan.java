package com.example.tiararatna.topsis;

import android.util.Log;

import com.example.tiararatna.modul.tfn_kriteria;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 8/3/2016.
 */
public class pembobotan {
    static List<tfn_kriteria> tfn = new ArrayList<>();
    static tfn_kriteria sangatRendah = new tfn_kriteria();
    static tfn_kriteria rendah = new tfn_kriteria();
    static tfn_kriteria menengahRendah = new tfn_kriteria();
    static tfn_kriteria menengah = new tfn_kriteria();
    static tfn_kriteria menengahTinggi = new tfn_kriteria();
    static tfn_kriteria tinggi = new tfn_kriteria();
    static tfn_kriteria sangatTinggi = new tfn_kriteria();

    public static tfn_kriteria SR() {
        sangatRendah.setA(0.0);
        sangatRendah.setB(0.0);
        sangatRendah.setC(0.1);
        return sangatRendah;
    }
    public static tfn_kriteria R() {
        rendah.setA(0.0);
        rendah.setB(0.1);
        rendah.setC(0.3);
        return rendah;
    }
    public static tfn_kriteria MR() {
        menengahRendah.setA(0.1);
        menengahRendah.setB(0.3);
        menengahRendah.setC(0.5);
        return menengahRendah;
    }
    public static tfn_kriteria M() {
        menengah.setA(0.3);
        menengah.setB(0.5);
        menengah.setC(0.7);
        return menengah;
    }
    public static tfn_kriteria MT() {
        menengahTinggi.setA(0.5);
        menengahTinggi.setB(0.7);
        menengahTinggi.setC(0.9);
        return menengahTinggi;
    }
    public static tfn_kriteria T() {
        tinggi.setA(0.7);
        tinggi.setB(0.9);
        tinggi.setC(1.0);
        return tinggi;
    }
    public static tfn_kriteria ST() {
        sangatTinggi.setA(0.9);
        sangatTinggi.setB(1.0);
        sangatTinggi.setC(1.0);
        return sangatTinggi;
    }
    public static List<tfn_kriteria> bobot(int urutan) {
        tfn.clear();
        int x, a=0,b=0, index = 0;

        x = urutan / 6;

        if (x == 0){a = urutan; b = 1; index = urutan;}
        else if (x == 1){a = 6; b = 1; index = 6 - (urutan%6);}
        else if (x > 1){a = 6; b = x; index = 6 - (urutan%56);}

        int i = 1;

        for (int j = 1; j < a+1; j++) {
            if (i <= index){
                for (int k = 0; k < b; k++) {
                    hit_bobot(i);
                }
                i++;
            }
            else if (i > index){
                for (int k = 0; k < x+1; k++) {
                    hit_bobot(i);
                }
                i++;
            }
        }
        for (int j = 0; j < tfn.size(); j++) {
            Log.d("j+1", ""+tfn.get(j).getA()+" "+tfn.get(j).getB()+" "+tfn.get(j).getC());
        }
        return tfn;
    }
    public static void hit_bobot(int i){
        if (i == 1){
            tfn.add(ST());
        } else if (i == 2){
            tfn.add(T());
        } else if (i == 3){
            tfn.add(MT());
        } else if (i == 4){
            tfn.add(M());
        } else if (i == 5){
            tfn.add(MR());
        } else if (i == 6){
            tfn.add(R());
        } else if (i == 7){
            tfn.add(SR());
        }
    }
}
