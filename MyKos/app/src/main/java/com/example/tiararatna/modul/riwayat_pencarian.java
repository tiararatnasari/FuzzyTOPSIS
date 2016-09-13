package com.example.tiararatna.modul;

import java.io.Serializable;

/**
 *
 * @author Tiara Ratna
 */
public class riwayat_pencarian implements Serializable {

    private String id_riwayat;
    private String id_pencari;
    private String id_kos;
    private String nama_kos;
    private String alamat_kos;
    private String latitude_kos;
    private String longitude_kos;
    private String id_pemilik;
    private String nama_pemilik;
    private String telp_pemilik;
    private String id_kelurahan;
    private String kelurahan;
    private String kecamatan;
    private String id_tfn;
    private double jarak_kos ;
    private int biaya_kos;
    private String jk;
    private int jmlh_penghuni;
    private String meja_kursi;
    private String lemari;
    private String tmpt_tidur;
    private String km_luar;
    private String kipas_angin;
    private String ac;
    private String tv;
    private String kulkas;
    private String dispenser;
    private String internet;
    private String ruang_tamu;
    private String dapur;
    private String mesin_cuci;
    private String pembantu;
    private String cuci_gosok;
    private String cctv_security;
    private String klm_renang;
    private int parkir_motor;
    private int parkir_mobil;
    private int tmpt_makan;
    private int warnet;
    private int mall;
    private int apotek_dokter;
    private int atm_bank;
    private int supermarket;
    private int kendaraan_umum;

    public String getId_riwayat() {return id_riwayat;}
    public String getId_pencari() {return id_pencari;}
    public String getId_kos() {return id_kos;}
    public String getNama_kos() {return nama_kos;}
    public String getAlamat_kos() {return alamat_kos;}
    public String getLatitude_kos() {   return latitude_kos;}
    public String getLongitude_kos() {  return longitude_kos;}
    public String getId_pemilik() {return id_pemilik;}
    public String getNama_pemilik() {return nama_pemilik;}
    public String getTelp_pemilik() {return telp_pemilik;}
    public String getId_kelurahan() {return id_kelurahan;}
    public String getKelurahan() {return kelurahan;}
    public String getKecamatan() {return kecamatan;}
    public String getId_tfn() {return id_tfn;}
    public double getJarak_kos() {return jarak_kos;}
    public int getBiaya_kos() {return biaya_kos;}
    public String getJk() {return jk;}
    public int getJmlh_penghuni() {return jmlh_penghuni;}
    public String getMeja_kursi() {return meja_kursi;}
    public String getLemari() {return lemari;}
    public String getTmpt_tidur() {return tmpt_tidur;}
    public String getKm_luar() {return km_luar;}
    public String getKipas_angin() {return kipas_angin;}
    public String getAc() {return ac;}
    public String getTv() {return tv;}
    public String getKulkas() {return kulkas;}
    public String getDispenser() {return dispenser;}
    public String getInternet() {return internet;}
    public String getRuang_tamu() {return ruang_tamu;}
    public String getDapur() {return dapur;}
    public String getMesin_cuci() {return mesin_cuci;}
    public String getPembantu() {return pembantu;}
    public String getCuci_gosok() {return cuci_gosok;}
    public String getCctv_security() {return cctv_security;}
    public String getKlm_renang() {return klm_renang;}
    public int getParkir_motor() {return parkir_motor;}
    public int getParkir_mobil() {return parkir_mobil;}
    public int getTmpt_makan() {return tmpt_makan;}
    public int getWarnet() {return warnet;}
    public int getMall() {return mall;}
    public int getApotek_dokter() {return apotek_dokter;}
    public int getAtm_bank() {return atm_bank;}
    public int getSupermarket() {return supermarket;}
    public int getKendaraan_umum() {return kendaraan_umum;}

    public void setId_Riwayat(String id_riwayat) {this.id_riwayat = id_riwayat;}
    public void setId_Pencari(String id_pencari) {this.id_pencari = id_pencari;}
    public void setId_kos(String id_kos) {this.id_kos = id_kos;}
    public void setNama_kos(String nama_kos) {this.nama_kos = nama_kos;}
    public void setAlamat_kos(String alamat_kos) {this.alamat_kos = alamat_kos;}
    public void setLatitude_kos(String latitude_kos) {  this.latitude_kos = latitude_kos;}
    public void setLongitude_kos(String longitude_kos) {this.longitude_kos = longitude_kos;}
    public void setId_pemilik(String id_pemilik) {this.id_pemilik = id_pemilik;}
    public void setNama_pemilik(String nama_pemilik) {this.nama_pemilik = nama_pemilik;}
    public void setTelp_pemilik(String telp_pemilik) {this.telp_pemilik = telp_pemilik;}
    public void setId_kelurahan(String id_kelurahan) {this.id_kelurahan = id_kelurahan;}
    public void setKelurahan(String kelurahan) {this.kelurahan = kelurahan;}
    public void setKecamatan(String kecamatan) {this.kecamatan = kecamatan;}
    public void setId_tfn(String id_tfn) {this.id_tfn = id_tfn;}
    public void setJarak_kos(double jarak_kos) {this.jarak_kos = jarak_kos;}
    public void setBiaya_kos(int biaya_kos) {this.biaya_kos = biaya_kos;}
    public void setJk(String jk) {this.jk = jk;}
    public void setJmlh_penghuni(int jmlh_penghuni) {this.jmlh_penghuni = jmlh_penghuni;}
    public void setMeja_kursi(String meja_kursi) {this.meja_kursi = meja_kursi;}
    public void setLemari(String lemari) {this.lemari = lemari;}
    public void setTmpt_tidur(String tmpt_tidur) {this.tmpt_tidur = tmpt_tidur;}
    public void setKm_luar(String km_luar) {this.km_luar = km_luar;}
    public void setKipas_angin(String kipas_angin) {this.kipas_angin = kipas_angin;}
    public void setAc(String ac) {this.ac = ac;}
    public void setTv(String tv) {this.tv = tv;}
    public void setKulkas(String kulkas) {this.kulkas = kulkas;}
    public void setDispenser(String dispenser) {this.dispenser = dispenser;}
    public void setInternet(String internet) {this.internet = internet;}
    public void setRuang_tamu(String ruang_tamu) {this.ruang_tamu = ruang_tamu;}
    public void setDapur(String dapur) {this.dapur = dapur;}
    public void setMesin_cuci(String mesin_cuci) {this.mesin_cuci = mesin_cuci;}
    public void setPembantu(String pembantu) {this.pembantu = pembantu;}
    public void setCuci_gosok(String cuci_gosok) {this.cuci_gosok = cuci_gosok;}
    public void setCctv_security(String cctv_security) {this.cctv_security = cctv_security;}
    public void setKlm_renang(String klm_renang) {this.klm_renang = klm_renang;}
    public void setParkir_motor(int parkir_motor) {this.parkir_motor = parkir_motor;}
    public void setParkir_mobil(int parkir_mobil) {this.parkir_mobil = parkir_mobil;}
    public void setTmpt_makan(int tmpt_makan) {this.tmpt_makan = tmpt_makan;}
    public void setWarnet(int warnet) {this.warnet = warnet;}
    public void setMall(int mall) {this.mall = mall;}
    public void setApotek_dokter(int apotek_dokter) {this.apotek_dokter = apotek_dokter;}
    public void setAtm_bank(int atm_bank) {this.atm_bank = atm_bank;}
    public void setSupermarket(int supermarket) {this.supermarket = supermarket;}
    public void setKendaraan_umum(int kendaraan_umum) {this.kendaraan_umum = kendaraan_umum;}

}
