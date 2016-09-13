package com.example.tiararatna.topsis;

import android.util.Log;
import android.widget.ListView;

import com.example.tiararatna.adapter.identitasKos_cariAdapter;
import com.example.tiararatna.modul.fuzzyFasilitas_kos;
import com.example.tiararatna.modul.identitas_kos;
import com.example.tiararatna.modul.tfn_kriteria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiara Ratna
 */
public class topsis {
    static ListView listView;
    static identitasKos_cariAdapter identitasKosAdapter;
    static List<fuzzyFasilitas_kos> prioritas = new ArrayList<fuzzyFasilitas_kos>();
    static List<identitas_kos> data_identitas = new ArrayList<identitas_kos>();
    String[] sql_fuzzy;

    List<tfn_kriteria> jarak_kos = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> biaya_kos = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> jk = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> jmlh_penghuni = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> meja_kursi = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> lemari = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> tmpt_tidur = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> km_luar = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> kipas_angin = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> ac = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> tv = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> kulkas = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> dispenser = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> internet = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> ruang_tamu = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> dapur = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> mesin_cuci = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> pembantu = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> cuci_gosok = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> cctv_security = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> klm_renang = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> parkir_motor = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> parkir_mobil = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> tmpt_makan = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> warnet = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> mall = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> apotek_dokter = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> atm_bank = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> supermarket = new ArrayList<tfn_kriteria>();
    List<tfn_kriteria> kendaraan_umum = new ArrayList<tfn_kriteria>();
    static DecimalFormat df = new DecimalFormat("#.#####");

    public topsis(List<fuzzyFasilitas_kos> prioritas, List<identitas_kos> data_identitas) {
        this.prioritas = prioritas;
        this.data_identitas = data_identitas;
    }

    public static List<tfn_kriteria> langkah_satu_biayaA(List<tfn_kriteria> kriteria) {
        List<tfn_kriteria> hasil = new ArrayList<tfn_kriteria>();
        double minA = kriteria.get(0).getA(), minB = kriteria.get(0).getB(), minC = kriteria.get(0).getC();
        for (int i = 1; i < kriteria.size(); i++) {
            if (kriteria.get(i).getA() < minA) {
                minA = kriteria.get(i).getA();
            }
            if (kriteria.get(i).getB() < minB) {
                minB = kriteria.get(i).getB();
            }
            if (kriteria.get(i).getC() < minC) {
                minC = kriteria.get(i).getC();
            }
        }
        for (int i = 0; i < kriteria.size(); i++) {
            tfn_kriteria tfn= new tfn_kriteria();
            tfn.setA(Double.valueOf(df.format(minA / kriteria.get(i).getC())));
            tfn.setB(Double.valueOf(df.format(minB / kriteria.get(i).getB())));
            tfn.setC(Double.valueOf(df.format(minC / kriteria.get(i).getA())));
            hasil.add(tfn);
            Log.d("langkah_satu_biayaA","tfn A "+ hasil.get(i).getA()+" B "+ hasil.get(i).getB()+" C "+ hasil.get(i).getC());
        }
        return hasil;
    }

    public static List<tfn_kriteria> langkah_satu_keuntunganA(List<tfn_kriteria> kriteria) {
        List<tfn_kriteria> hasil = new ArrayList<tfn_kriteria>();
        double maxA = kriteria.get(0).getA(), maxB = kriteria.get(0).getB(), maxC = kriteria.get(0).getC();
        for (int i = 1; i < kriteria.size(); i++) {
            if (kriteria.get(i).getA() > maxA) {
                maxA = kriteria.get(i).getA();
            }
            if (kriteria.get(i).getB() > maxB) {
                maxB = kriteria.get(i).getB();
            }
            if (kriteria.get(i).getC() > maxC) {
                maxC = kriteria.get(i).getC();
            }
        }
        for (int i = 0; i < kriteria.size(); i++) {
            tfn_kriteria tfn= new tfn_kriteria();
            tfn.setA(Double.valueOf(df.format(kriteria.get(i).getA() / maxC)));
            tfn.setB(Double.valueOf(df.format(kriteria.get(i).getB() / maxB)));
            tfn.setC(Double.valueOf(df.format(kriteria.get(i).getC() / maxA)));
            hasil.add(tfn);
            Log.d("langkah_satu_untungA","tfn A "+ hasil.get(i).getA()+" B "+ hasil.get(i).getB()+" C "+ hasil.get(i).getC());
        }
        return hasil;
    }

    public static List<tfn_kriteria> langkah_satu_keuntunganB(List<tfn_kriteria> kriteria) {
        double maxA = kriteria.get(0).getA();
        for (int i = 1; i < kriteria.size(); i++) {
            if (kriteria.get(i).getA() > maxA) {
                maxA = kriteria.get(i).getA();
            }
        }
        for (int i = 0; i < kriteria.size(); i++) {
            double hitung = Double.valueOf(df.format(kriteria.get(i).getA() / maxA));
            kriteria.get(i).setA(hitung);
            kriteria.get(i).setB(hitung);
            kriteria.get(i).setC(hitung);
            Log.d("langkah_satu_untungA","tfn A "+ kriteria.get(i).getA()+" B "+ kriteria.get(i).getB()+" C "+ kriteria.get(i).getC());
        }
        return kriteria;
    }

    public static List<tfn_kriteria> langkah_dua(List<tfn_kriteria> kriteria, tfn_kriteria bobot) {
        for (int i = 0; i < kriteria.size(); i++) {
            kriteria.get(i).setA(Double.valueOf(df.format(kriteria.get(i).getA() * bobot.getA())));
            kriteria.get(i).setB(Double.valueOf(df.format(kriteria.get(i).getB() * bobot.getB())));
            kriteria.get(i).setC(Double.valueOf(df.format(kriteria.get(i).getC() * bobot.getC())));
            Log.d("langkah_dua","tfn A "+ kriteria.get(i).getA()+" B "+ kriteria.get(i).getB()+" C "+ kriteria.get(i).getC());
        }
        return kriteria;
    }

    public static int[] langkah_tiga(List<tfn_kriteria> kriteria) {
        double[] mean = new double[kriteria.size()];
        int[] data_ke= new int[2];
        double max=0, min=0;
        for (int i = 0; i < kriteria.size(); i++) {
            mean[i] = Double.valueOf(df.format((-Math.pow(kriteria.get(i).getA(), 2.0) - (Math.pow(kriteria.get(i).getB(), 2.0))
                    + (Math.pow(kriteria.get(i).getB(), 2.0)) + (Math.pow(kriteria.get(i).getC(), 2.0))
                    - (kriteria.get(i).getA() * kriteria.get(i).getB()) + (kriteria.get(i).getB() * kriteria.get(i).getC()))
                    / (3 * (-kriteria.get(i).getA() - kriteria.get(i).getB() + kriteria.get(i).getB() + kriteria.get(i).getC()))));
            Log.d("mean", ""+mean[i]);
        }
        max = mean[0]; min = mean[0];
        for (int i = 1; i < kriteria.size(); i++) {
            if (max < mean[i]) {
                max = mean[i];
                data_ke[0]=i;
            } else if (min > mean[i]) {
                min = mean[i];
                data_ke[1]=i;
            }
        }
        Log.d("langkah_tiga_untung","data max "+data_ke[0]+" min "+data_ke[1]);
        return data_ke;
    }

    public static double[] langkah_empat_max(List<tfn_kriteria> kriteria, int max) {
        double[] D_max = new double[kriteria.size()];
        for (int i = 0; i < kriteria.size(); i++) {
            if (i == max) {
                D_max[i] = 0;
            }
            D_max[i] = Double.valueOf(df.format(1 - ((kriteria.get(max).getA() - kriteria.get(i).getC()) / ((-(kriteria.get(i).getC() - kriteria.get(i).getB())) - (kriteria.get(max).getB()-kriteria.get(max).getA())))));
            Log.d("langkah_empat_max","D max "+ D_max[i]);
        }
        return D_max;
    }

    public static double[] langkah_empat_min(List<tfn_kriteria> kriteria, int min) {
        double[] D_min = new double[kriteria.size()];
        for (int i = 0; i < kriteria.size(); i++) {
            //if (i == min) {
            //    D_min[i] = 0;
            //}
            D_min[i] = Double.valueOf(df.format(1 - ((kriteria.get(min).getC() - kriteria.get(min).getA()) / ((kriteria.get(i).getB() - kriteria.get(i).getA()) + (kriteria.get(min).getC() - kriteria.get(min).getB())))));
            Log.d("langkah_empat_min","D min "+ D_min[i]);
        }
        return D_min;
    }

    public static List<identitas_kos> langkah_lima(double[] S_plus, double[] S_min) {
        double[] C = new double[S_plus.length];;
        List<identitas_kos> data_identitas_urut = data_identitas;
        for (int i = 0; i < S_plus.length; i++) {
            C[i] = (S_min[i] / (S_min[i] + S_plus[i]));
            Log.d("c", ""+C[i]);
        }
        for (int j = 1; j < C.length - 1; j++) {
            for (int i = 0; i < C.length - 1; i++) {
                if (C[i] > C[i + 1]) {
                    double x = C[i];
                    identitas_kos move = data_identitas_urut.get(i);
                    C[i] = C[i + 1];
                    data_identitas_urut.set(i, data_identitas_urut.get(i+1));
                    C[i + 1] = x;
                    data_identitas_urut.set(i+1, move);
                    //for (int k = 0; k < C.length; k++) {
                    //    Log.d("urut", ""+C[k]);
                    //}
                }
            }
        }
        //for (int i = 0; i < C.length; i++) {
        //    double min = C[i];
        //    identitas_kos data_min = data_identitas_urut.get(i);
        //    int j = i;
        //    while ((j > 0) && (min < C[j - 1])) {
        //        C[j] = C[j - 1];
        //        data_identitas_urut.get(j).equals(data_identitas_urut.get(j-1));
        //        j--;
        //    }
        //    C[j] = min;
        //}

        for (int i = 0; i < data_identitas_urut.size(); i++) {
            Log.d("langkah_lima","id langkah lima "+data_identitas_urut.get(i).getId_tfn());
        }
        //listView = (ListView) listView.findViewById(R.id.daftarkos_listview);
        //identitasKosAdapter = new identitasKos_cariAdapter(this, R.layout.activity_cari_daftarkos_row);
        //listView.setAdapter(identitasKosAdapter);
        //for (int i = 0; i < data_identitas_urut.size(); i++) {
        //    identitasKosAdapter.add(data_identitas_urut.get(i));
        //    Log.d("hasil",data_identitas_urut.get(i).getId_kos());
        //}
        return data_identitas_urut;
    }

    public static void delete() {
        prioritas.clear();
        data_identitas.clear();
    }
}
