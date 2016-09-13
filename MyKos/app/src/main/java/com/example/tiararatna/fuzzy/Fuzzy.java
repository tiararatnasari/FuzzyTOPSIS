package com.example.tiararatna.fuzzy;

import android.util.Log;

import java.util.Locale;

/**
 *
 * @author Tiara Ratna
 */
public class Fuzzy {

    public static String jarak(double jarak){
        double [] jarak_kos= {0.0,0.03,3.5,2.6,4.6,6.6,5.1,9.9,10.0};
        String result = fuzzy1(jarak,jarak_kos);

        String tfn = "";
        if(result.equalsIgnoreCase("dekat")){tfn="0.003,0.003,0.35";}
        else if(result.equalsIgnoreCase("menengah")){tfn="0.26,0.46,0.66";}
        else if(result.equalsIgnoreCase("jauh")){tfn="0.51,0.99,1.00";}
        return tfn;
    }
    public static String biaya(int biaya){
        double [] biaya_kos= {100000,180000,470000,400000,600000,800000,760000,1100000,1200000};
        String result = fuzzy2(biaya,biaya_kos);

        String tfn = "";
        if(result.equalsIgnoreCase("dekat")){tfn="0.083,0.15,0.392";}
        else if(result.equalsIgnoreCase("menengah")){tfn="0.333,0.5,0.667";}
        else if(result.equalsIgnoreCase("jauh")){tfn="0.633,0.917,1";}

        return tfn;
    }
    public static String jk(String jk){
        String tfn = "";
        if(jk.equalsIgnoreCase("c")){tfn="1.0";}
        else if((jk.equalsIgnoreCase("p"))||(jk.equalsIgnoreCase("l"))){tfn="0.0";}
        Log.d("jk", tfn);
        return tfn;
    }
    public static String penghuni(int data){
        double [] batas= {1.0,1.0,2.0,1.0,2.0,3.0,2.0,4.0,4.0};
        String result = fuzzy1(data, batas);

        String tfn = "";
        if(result.equalsIgnoreCase("dekat")){tfn="0.25,0.25,0.5";}
        else if(result.equalsIgnoreCase("menengah")){tfn="0.25,0.5,0.75";}
        else if(result.equalsIgnoreCase("jauh")){tfn="0.5,1.0,1.0";}
        Log.d("penghuni", tfn);

        return result;
    }
    public static String fasilitas(String fas){
        String tfn = "";
        if(fas.equalsIgnoreCase("y")){tfn="1.0";}
        else if(fas.equalsIgnoreCase("t")){tfn="0.0";}
        Log.d("fasilitas", tfn);
        return tfn;
    }
    public static String parkir_motor(int motor){
        double [] parkir_motor= {1.0,2.0,7.0,6.0,9.5,13.0,12.0,19.0,20.0};
        String result = fuzzy2(motor, parkir_motor);

        String tfn = "";
        if(result.equalsIgnoreCase("dekat")){tfn="0.05,0.1,0.35";}
        else if(result.equalsIgnoreCase("menengah")){tfn="0.3,0.475,0.65";}
        else if(result.equalsIgnoreCase("jauh")){tfn="0.6,0.95,1.0";}

        return tfn;
    }
    public static String parkir_mobil(int mobil){
        double [] parkir_mobil= {1.0,2.0,4.0,3.0,5.0,7.0,6.0,9.0,10.0};
        String result = fuzzy2(mobil, parkir_mobil);

        String tfn = "";
        if(result.equalsIgnoreCase("dekat")){tfn="0.1,0.2,0.4";}
        else if(result.equalsIgnoreCase("menengah")){tfn="0.3,0.5,0.7";}
        else if(result.equalsIgnoreCase("jauh")){tfn="0.6,0.9,1.0";}

        return tfn;
    }
    public static String fasum(int fasum){
        double [] fasum_kos= {0.0,0.03,3.5,2.6,4.6,6.6,5.1,9.9,10.0};
        String result = fuzzy1(fasum,fasum_kos);

        String tfn = "";
        if(result.equalsIgnoreCase("dekat")){tfn="0.003,0.003,0.35";}
        else if(result.equalsIgnoreCase("menengah")){tfn="0.26,0.46,0.66";}
        else if(result.equalsIgnoreCase("jauh")){tfn="0.51,0.99,1.00";}

        return tfn;
    }
    public static String fuzzy1(double data, double [] batas){
        double dekat=0, menengah=0, jauh=0;
        
        if(data >= batas[2]){dekat = 0.0;}
        else if((data >= batas[1])&&(data <= batas[2])){dekat = (batas[2]-data)/(batas[2]-batas[1]);}
        
        if((data <= batas[3])||(data >= batas[5])){menengah = 0.0;}
        else if((data >= batas[3])&&(data <= batas[4])){menengah = (data-batas[3])/(batas[4]-batas[3]);}
        else if((data >= batas[4])&&(data <= batas[5])){menengah = (batas[5]-data)/(batas[5]-batas[4]);}
        
        if(data <= batas[6]){jauh = 0.0;}
        else if((data >= batas[6])&&(data <= batas[7])){jauh = (data-batas[6])/(batas[7]-batas[6]);}
        else if(data >= batas[7]){jauh = 1.0;}
        
        double max=dekat;
        String result="dekat";
        if(max<menengah){max=menengah; result="menengah";}
        else if(max<jauh){max=jauh; result="jauh";}
        
        return result;
    }
    public static String fuzzy2(double data, double [] batas){
        double dekat=0, menengah=0, jauh=0;

        if(data >= batas[2]){dekat = 0.0;}
        else if((data >= batas[1])&&(data <= batas[2])){dekat = (batas[2]-data)/(batas[2]-batas[1]);}
        else if(data >= batas[1]){jauh = 1.0;}

        if((data <= batas[3])||(data >= batas[5])){menengah = 0.0;}
        else if((data >= batas[3])&&(data <= batas[4])){menengah = (data-batas[3])/(batas[4]-batas[3]);}
        else if((data >= batas[4])&&(data <= batas[5])){menengah = (batas[5]-data)/(batas[5]-batas[4]);}

        if(data <= batas[6]){jauh = 0.0;}
        else if((data >= batas[6])&&(data <= batas[7])){jauh = (data-batas[6])/(batas[7]-batas[6]);}
        else if(data >= batas[7]){jauh = 1.0;}

        double max=dekat;
        String result="dekat";
        if(max<menengah){max=menengah; result="menengah";}
        else if(max<jauh){max=jauh; result="jauh";}

        return result;
    }
}
