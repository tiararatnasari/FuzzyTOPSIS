package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tiararatna.adapter.DropDownSpinner;
import com.example.tiararatna.modul.fakultas;
import com.example.tiararatna.modul.pencari_kos;
import com.example.tiararatna.modul.program_studi;
import com.example.tiararatna.modul.universitas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara Ratna on 4/14/2016.
 */
public class ubah_pencariActivity extends AppCompatActivity {

    EditText nama, username, passlama, passbaru, repassbaru;
    String Id_prodi, Id, Id_univ, Nama, Jk, Username, Password, Passlama, Passbaru, Repassbaru, json_string, data_univ, data_fak, data_prodi;
    pencari_kos pencariKos, pencariKos_ubah;
    Button ubah_pass, ubah;
    int index, click = 0;
    JSONObject jsonObject;
    JSONArray jsonArray;
    List<universitas> Universitas;
    List<fakultas> Fakultas;
    List<program_studi> Program_studi;
    Spinner univ,fak,prodi,jk;
    static ArrayAdapter<CharSequence> adapter_univ, adapter_fak, adapter_prodi, adapter_jk;
    int index_univ, index_fak, index_prodi, index_jk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pencari);
        pencariKos = (pencari_kos) getIntent().getExtras().get("pencari_kos");

        univ = (Spinner) findViewById(R.id.s_upencari_univ);
        fak = (Spinner) findViewById(R.id.s_upencari_fak);
        prodi = (Spinner) findViewById(R.id.s_upencari_prodi);
        nama = (EditText) findViewById(R.id.f_upencari_nama);
        jk = (Spinner) findViewById(R.id.s_upencari_jk);
        username = (EditText) findViewById(R.id.f_upencari_user);
        passlama = (EditText) findViewById(R.id.f_upencari_passlama);
        passbaru = (EditText) findViewById(R.id.f_upencari_passbaru);
        repassbaru = (EditText) findViewById(R.id.f_upencari_repassbaru);

        Id = pencariKos.getId_pencari();
        Nama = pencariKos.getNama_pencari();
        Username = pencariKos.getUsername_pencari();
        Password = pencariKos.getPassword_pencari();
        final int pgl_univ = Integer.parseInt(pencariKos.getId_prodi().substring(2,4))-1;
        Log.d("penggal univ", ""+pgl_univ);
        final int pgl_fak = Integer.parseInt(pencariKos.getId_prodi().substring(4,6))-1;
        Log.d("penggal fak", ""+pgl_fak);
        final int pgl_prodi = Integer.parseInt(pencariKos.getId_prodi().substring(6,8))-1;
        Log.d("penggal prodi", ""+pgl_prodi);
        univ.post(new Runnable() {
            @Override
            public void run() {
                univ.setSelection(pgl_univ);
                fak.post(new Runnable() {
                    @Override
                    public void run() {
                        fak.setSelection(pgl_fak);
                        prodi.post(new Runnable() {
                            @Override
                            public void run() {
                                prodi.setSelection(pgl_prodi);
                            }
                        });
                    }
                });
            }
        });
        if(pencariKos.getJk_pencari().equalsIgnoreCase("p")) {
            index_jk = 0;
        } else if(pencariKos.getJk_pencari().equalsIgnoreCase("l")) {
            index_jk = 1;
        }
        jk.post(new Runnable() {
            @Override
            public void run() {
                jk.setSelection(index_jk);
            }
        });
        nama.setText(Nama);
        username.setText(Username);

        adapter_univ = ArrayAdapter.createFromResource(this, R.array.univ, android.R.layout.simple_spinner_item);
        adapter_univ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        univ.setAdapter(adapter_univ);
        univ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index_univ = univ.getSelectedItemPosition();
                adapter_fak = DropDownSpinner.fakultas(ubah_pencariActivity.this, index_univ);
                adapter_fak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fak.setAdapter(adapter_fak);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        fak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index_fak = fak.getSelectedItemPosition();
                adapter_prodi = DropDownSpinner.prodi(ubah_pencariActivity.this, index_univ, index_fak);
                adapter_prodi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                prodi.setAdapter(adapter_prodi);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        prodi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_prodi = prodi.getSelectedItemPosition();
                Id_prodi = DropDownSpinner.idProdi(index_univ,index_fak,index_prodi);
                Toast.makeText(getBaseContext(), "You have selected item : "+Id_prodi, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        adapter_jk = ArrayAdapter.createFromResource(ubah_pencariActivity.this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item);
        adapter_jk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jk.setAdapter(adapter_jk);
        jk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_jk = jk.getSelectedItemPosition();
                Jk = DropDownSpinner.jenisKelamin(index_jk);
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        onClickButtonListenerUbahPencari();
        onClickButtonListenerUbahPassPencari();
    }

    public void onClickButtonListenerUbahPassPencari() {
        ubah_pass = (Button) findViewById(R.id.b_upencari_ubahpass);
        ubah_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passlama.setEnabled(true);
                passlama.setBackgroundColor(R.color.colorPutih);
                passbaru.setEnabled(true);
                passbaru.setBackgroundColor(R.color.colorPutih);
                repassbaru.setEnabled(true);
                repassbaru.setBackgroundColor(R.color.colorPutih);
                click = 1;
            }
        });
    }

    public void onClickButtonListenerUbahPencari() {
        ubah = (Button) findViewById(R.id.b_upencari_ubah);
        pencariKos_ubah = new pencari_kos();
        pencariKos_ubah.setId_pencari(Id);
        pencariKos_ubah.setId_prodi("");
        pencariKos_ubah.setNama_pencari("");
        pencariKos_ubah.setJk_pencari("");
        pencariKos_ubah.setUsername_pencari("");
        pencariKos_ubah.setPassword_pencari("");
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaUbah = nama.getText().toString();
                String userUbah = username.getText().toString();
                if(click == 0) {
                    if (namaUbah.equalsIgnoreCase("") && userUbah.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pencariActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }else if (namaUbah.equalsIgnoreCase("") || userUbah.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pencariActivity.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                    } else if (!namaUbah.equalsIgnoreCase("") && !userUbah.equalsIgnoreCase("")) {
                        if (!pencariKos.getId_prodi().equalsIgnoreCase(Id_prodi)) {
                            pencariKos_ubah.setId_prodi(Id_prodi);
                        }if (!pencariKos.getNama_pencari().equalsIgnoreCase(namaUbah)) {
                            pencariKos_ubah.setNama_pencari(namaUbah);
                        }if (!pencariKos.getUsername_pencari().equalsIgnoreCase(userUbah)) {
                            pencariKos_ubah.setUsername_pencari(userUbah);
                        }if (!pencariKos.getJk_pencari().equalsIgnoreCase(Jk)) {
                            pencariKos_ubah.setJk_pencari(Jk);
                        }
                        new Background(ubah_pencariActivity.this).execute();
                    }
                } else if (click == 1) {
                    Passlama = passlama.getText().toString();
                    Passbaru = passbaru.getText().toString();
                    Repassbaru = repassbaru.getText().toString();
                    if (namaUbah.equalsIgnoreCase("") && userUbah.equalsIgnoreCase("")&&Passlama.equalsIgnoreCase("")&&Passbaru.equalsIgnoreCase("")&&Repassbaru.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pencariActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    } else if (namaUbah.equalsIgnoreCase("") || userUbah.equalsIgnoreCase("")||Passlama.equalsIgnoreCase("")||Passbaru.equalsIgnoreCase("")||Repassbaru.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pencariActivity.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                    } else if (!namaUbah.equalsIgnoreCase("") && !userUbah.equalsIgnoreCase("")&&Passlama.equalsIgnoreCase("")&&Passbaru.equalsIgnoreCase("")&&Repassbaru.equalsIgnoreCase("")) {
                        if (Passlama.equalsIgnoreCase(pencariKos.getPassword_pencari())) {
                            if (Passbaru.equalsIgnoreCase(Repassbaru)) {
                                pencariKos_ubah.setPassword_pencari(passbaru.getText().toString());
                                new Background(ubah_pencariActivity.this).execute();
                            } else if (Repassbaru.equalsIgnoreCase("")) {
                                Toast.makeText(ubah_pencariActivity.this, "Repassword belum diisi", Toast.LENGTH_SHORT);
                            } else if (!Passbaru.equalsIgnoreCase(Repassbaru)) {
                                Toast.makeText(ubah_pencariActivity.this, "Repassword harus sama dengan Password", Toast.LENGTH_SHORT);
                            }
                        } else if (!Passlama.equalsIgnoreCase(pencariKos.getPassword_pencari())) {
                            Toast.makeText(ubah_pencariActivity.this, "Password lama salah", Toast.LENGTH_SHORT);
                        }
                    }
                }
            }
        });
    }

    class Background extends AsyncTask<Void, Void, String> {
            String JSON_STRING, url_signup_pencari;
            Context ctx;

            public Background(Context ctx) {this.ctx = ctx;}

            @Override
            protected void onPreExecute() {url_signup_pencari = "http://mykos.stander.id/pencariKos_change.php";}

            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL(url_signup_pencari);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos_ubah.getId_pencari(), "UTF-8") + "&" +
                            URLEncoder.encode("id_prodi", "UTF-8") + "=" + URLEncoder.encode(pencariKos_ubah.getId_prodi(), "UTF-8") + "&" +
                            URLEncoder.encode("nama_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos_ubah.getNama_pencari(), "UTF-8") + "&" +
                            URLEncoder.encode("jk_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos_ubah.getJk_pencari(), "UTF-8") + "&" +
                            URLEncoder.encode("username_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos_ubah.getUsername_pencari(), "UTF-8") + "&" +
                            URLEncoder.encode("password_pencari", "UTF-8") + "=" + URLEncoder.encode(pencariKos_ubah.getPassword_pencari(), "UTF-8");
                    Log.d("data", data);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRING + "\n");
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();

                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Error";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String result) {
                json_string = result;
                Log.d("json_login", json_string);
                if (json_string != "FAILURE") {
                    Intent intent = new Intent(ubah_pencariActivity.this, utama_pencariActivity.class);
                    intent.putExtra("json_data", json_string);
                    startActivityForResult(intent, 0);
                    univ.getItemAtPosition(0);
                    fak.getItemAtPosition(0);
                    prodi.getItemAtPosition(0);
                    nama.setText("");
                    jk.getItemAtPosition(0);
                    username.setText("");
                    passlama.setText("");
                    passbaru.setText("");
                    repassbaru.setText("");
                } else if (json_string == "FAILURE"){
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Megubah", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }