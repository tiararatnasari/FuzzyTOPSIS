package com.example.tiararatna.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.tiararatna.mykos.R;
import com.example.tiararatna.mykos.signup_pencariActivity;

/**
 * Created by Tiara Ratna on 7/24/2016.
 */
public class DropDownSpinner {
    static ArrayAdapter<CharSequence> adapter_univ, adapter_fak, adapter_prodi, adapter_jk;
    public static ArrayAdapter fakultas(Context context, int index_univ){
        if(index_univ == 0){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak01, android.R.layout.simple_spinner_item);
        } else if(index_univ == 1){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak02, android.R.layout.simple_spinner_item);
        } else if(index_univ == 2){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak03, android.R.layout.simple_spinner_item);
        } else if(index_univ == 3){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak04, android.R.layout.simple_spinner_item);
        } else if(index_univ == 4){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak05, android.R.layout.simple_spinner_item);
        } else if(index_univ == 5){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak06, android.R.layout.simple_spinner_item);
        } else if(index_univ == 6){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak07, android.R.layout.simple_spinner_item);
        } else if(index_univ == 7){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak08, android.R.layout.simple_spinner_item);
        } else if(index_univ == 8){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak09, android.R.layout.simple_spinner_item);
        } else if(index_univ == 9){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak10, android.R.layout.simple_spinner_item);
        } else if(index_univ == 10){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak11, android.R.layout.simple_spinner_item);
        } else if(index_univ == 11){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak12, android.R.layout.simple_spinner_item);
        } else if(index_univ == 12){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak13, android.R.layout.simple_spinner_item);
        } else if(index_univ == 13){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak14, android.R.layout.simple_spinner_item);
        } else if(index_univ == 14){
            adapter_fak = ArrayAdapter.createFromResource(context, R.array.fak15, android.R.layout.simple_spinner_item);
        }
        return  adapter_fak;
    }
    public static ArrayAdapter prodi(Context context, int index_univ, int index_fak) {
        if((index_univ==0)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0101, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0102, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0103, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0104, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0105, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0106, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0107, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0108, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 8)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0109, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 9)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0110, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 10)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0111, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 11)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0112, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 12)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0113, android.R.layout.simple_spinner_item);
        } else if((index_univ==0)&&(index_fak == 13)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0114, android.R.layout.simple_spinner_item);
        } else if((index_univ==1)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0201, android.R.layout.simple_spinner_item);
        } else if((index_univ==1)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0202, android.R.layout.simple_spinner_item);
        } else if((index_univ==1)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0203, android.R.layout.simple_spinner_item);
        } else if((index_univ==1)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0204, android.R.layout.simple_spinner_item);
        } else if((index_univ==1)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0205, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0301, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0302, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0303, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0304, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0305, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0306, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0307, android.R.layout.simple_spinner_item);
        } else if((index_univ==2)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0308, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0401, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0402, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0403, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0404, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0405, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0406, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0407, android.R.layout.simple_spinner_item);
        } else if((index_univ==3)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0408, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0501, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0502, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0503, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0504, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0505, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0506, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0507, android.R.layout.simple_spinner_item);
        } else if((index_univ==4)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0508, android.R.layout.simple_spinner_item);
        } else if((index_univ==5)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0601, android.R.layout.simple_spinner_item);
        } else if((index_univ==5)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0602, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0701, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0702, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0703, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0704, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0705, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0706, android.R.layout.simple_spinner_item);
        } else if((index_univ==6)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0707, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0801, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0802, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0803, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0901, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0902, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0903, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0904, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0905, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0906, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0907, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0908, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 8)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0909, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 9)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0910, android.R.layout.simple_spinner_item);
        } else if((index_univ==8)&&(index_fak == 10)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps0911, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1001, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1002, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1003, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1004, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1005, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1006, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1007, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1008, android.R.layout.simple_spinner_item);
        } else if((index_univ==9)&&(index_fak == 8)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1009, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1101, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1102, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1103, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1104, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1105, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1106, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1107, android.R.layout.simple_spinner_item);
        } else if((index_univ==10)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1108, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1201, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1202, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1203, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1204, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1205, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1206, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 6)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1207, android.R.layout.simple_spinner_item);
        } else if((index_univ==11)&&(index_fak == 7)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1208, android.R.layout.simple_spinner_item);
        } else if((index_univ==12)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1301, android.R.layout.simple_spinner_item);
        } else if((index_univ==12)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1302, android.R.layout.simple_spinner_item);
        } else if((index_univ==12)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1303, android.R.layout.simple_spinner_item);
        } else if((index_univ==12)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1304, android.R.layout.simple_spinner_item);
        } else if((index_univ==12)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1305, android.R.layout.simple_spinner_item);
        } else if((index_univ==12)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1306, android.R.layout.simple_spinner_item);
        } else if((index_univ==13)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1401, android.R.layout.simple_spinner_item);
        } else if((index_univ==14)&&(index_fak == 0)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1501, android.R.layout.simple_spinner_item);
        } else if((index_univ==14)&&(index_fak == 1)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1502, android.R.layout.simple_spinner_item);
        } else if((index_univ==14)&&(index_fak == 2)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1503, android.R.layout.simple_spinner_item);
        } else if((index_univ==14)&&(index_fak == 3)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1504, android.R.layout.simple_spinner_item);
        } else if((index_univ==14)&&(index_fak == 4)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1505, android.R.layout.simple_spinner_item);
        } else if((index_univ==14)&&(index_fak == 5)){
            adapter_prodi = ArrayAdapter.createFromResource(context, R.array.ps1506, android.R.layout.simple_spinner_item);
        }
        return adapter_prodi;
    }
    public static String idProdi(int index_univ, int index_fak, int index_prodi){
        String Id_prodi;
        if((index_univ+1) < 10) {
            Id_prodi = "ps0" + (index_univ + 1);
        } else {
            Id_prodi = "ps" + (index_univ + 1);
        }
        if((index_fak+1) < 10) {
            Id_prodi += "0" + (index_fak + 1);
        } else {
            Id_prodi += "" + (index_fak + 1);
        }
        if((index_prodi+1) < 10) {
            Id_prodi += "0" + (index_prodi + 1);
        } else {
            Id_prodi += "" + (index_prodi + 1);
        }
        return Id_prodi;
    }
    public static String jenisKelamin(int index_jk){
        String Jk="";
        if(index_jk==0){
            Jk = "p";
        } else if (index_jk==1){
            Jk = "l";
        }
        return Jk;
    }
    public static String jkPenghuni(int index_jk){
        String Jk="";
        if(index_jk==0){
            Jk = "p";
        } else if (index_jk==1){
            Jk = "l";
        } else if (index_jk==2){
            Jk = "c";
        }
        return Jk;
    }
}
