package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiararatna.modul.pemilik_kos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tiara Ratna on 4/14/2016.
 */
public class ubah_pemilikActivity extends AppCompatActivity {

    EditText nama, telp, username, passlama, passbaru, repassbaru;
    String Id, Nama, Telp, Username, Password, Passlama, Passbaru, Repassbaru, json_string;
    pemilik_kos pemilikKos, pemilikKos_ubah;
    Button ubah_pass, ubah;
    int click = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pemilik);
        pemilikKos = (pemilik_kos) getIntent().getExtras().get("pemilik_kos");

        nama = (EditText) findViewById(R.id.f_upemilik_nama);
        telp = (EditText) findViewById(R.id.f_upemilik_telp);
        username = (EditText) findViewById(R.id.f_upemilik_user);
        passlama = (EditText) findViewById(R.id.f_upemilik_passlama);
        passbaru = (EditText) findViewById(R.id.f_upemilik_passbaru);
        repassbaru = (EditText) findViewById(R.id.f_upemilik_repassbaru);

        Id = pemilikKos.getId_pemilik();
        Log.d("id", Id);
        Nama = pemilikKos.getNama_pemilik();
        Log.d("nama", Nama);
        nama.setText(Nama);
        Telp = pemilikKos.getTelp_pemilik();
        Log.d("telp", Telp);
        telp.setText(Telp);
        Username = pemilikKos.getUsername_pemilik();
        Log.d("username", Username);
        username.setText(Username);
        Password = pemilikKos.getPassword_pemilik();
        Log.d("password", Password);

        onClickButtonListenerUbahPemilik();
        onClickButtonListenerUbahPassPemilik();
    }

    public void onClickButtonListenerUbahPassPemilik() {
        ubah_pass = (Button) findViewById(R.id.b_upemilik_ubahpass);
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

    public void onClickButtonListenerUbahPemilik() {
        ubah = (Button) findViewById(R.id.b_upemilik_ubah);
        pemilikKos_ubah = new pemilik_kos();
        pemilikKos_ubah.setId_pemilik(Id);
        pemilikKos_ubah.setNama_pemilik("");
        pemilikKos_ubah.setTelp_pemilik("");
        pemilikKos_ubah.setUsername_pemilik("");
        pemilikKos_ubah.setPassword_pemilik("");
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaUbah = nama.getText().toString();
                String telpUbah = telp.getText().toString();
                String userUbah = username.getText().toString();
                if(click == 0) {
                    if (namaUbah.equalsIgnoreCase("") && telpUbah.equalsIgnoreCase("") && userUbah.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pemilikActivity.this, "Data Kosong", Toast.LENGTH_LONG).show();
                    } else if (namaUbah.equalsIgnoreCase("") || telpUbah.equalsIgnoreCase("") || userUbah.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pemilikActivity.this, "Data Belum Lengkap", Toast.LENGTH_LONG).show();
                    } else if (!namaUbah.equalsIgnoreCase("") && !telpUbah.equalsIgnoreCase("") && !userUbah.equalsIgnoreCase("")) {
                        if (!pemilikKos.getNama_pemilik().equalsIgnoreCase(namaUbah)) {
                            pemilikKos_ubah.setNama_pemilik(namaUbah);
                        }if (!pemilikKos.getTelp_pemilik().equalsIgnoreCase(telpUbah)) {
                            pemilikKos_ubah.setTelp_pemilik(telpUbah);
                        }if (!pemilikKos.getUsername_pemilik().equalsIgnoreCase(userUbah)) {
                            pemilikKos_ubah.setUsername_pemilik(userUbah);
                        }
                        new Background(ubah_pemilikActivity.this).execute();
                    }
                } else if (click == 1) {
                    Passlama = passlama.getText().toString();
                    Passbaru = passbaru.getText().toString();
                    Repassbaru = repassbaru.getText().toString();
                    if (namaUbah.equalsIgnoreCase("") && telpUbah.equalsIgnoreCase("") && userUbah.equalsIgnoreCase("")&&Passlama.equalsIgnoreCase("")&&Passbaru.equalsIgnoreCase("")&&Repassbaru.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pemilikActivity.this, "Data Kosong", Toast.LENGTH_LONG).show();
                    }else if (namaUbah.equalsIgnoreCase("") || telpUbah.equalsIgnoreCase("") || userUbah.equalsIgnoreCase("")||Passlama.equalsIgnoreCase("")||Passbaru.equalsIgnoreCase("")||Repassbaru.equalsIgnoreCase("")) {
                        Toast.makeText(ubah_pemilikActivity.this, "Data Belum Lengkap", Toast.LENGTH_LONG).show();
                    } else if (!namaUbah.equalsIgnoreCase("") && !telpUbah.equalsIgnoreCase("") && !userUbah.equalsIgnoreCase("")&&Passlama.equalsIgnoreCase("")&&Passbaru.equalsIgnoreCase("")&&Repassbaru.equalsIgnoreCase("")) {
                        if (Passlama.equalsIgnoreCase(pemilikKos.getPassword_pemilik())) {
                            if (Passbaru.equalsIgnoreCase(Repassbaru)) {
                                pemilikKos_ubah.setPassword_pemilik(passbaru.getText().toString());
                                new Background(ubah_pemilikActivity.this).execute();
                            } else if (Repassbaru.equalsIgnoreCase("")) {
                                Toast.makeText(ubah_pemilikActivity.this, "Repassword belum diisi", Toast.LENGTH_LONG);
                            } else if (!Passbaru.equalsIgnoreCase(Repassbaru)) {
                                Toast.makeText(ubah_pemilikActivity.this, "Repassword harus sama dengan Password", Toast.LENGTH_LONG);
                            }
                        } else if (!Passlama.equalsIgnoreCase(pemilikKos.getPassword_pemilik())) {
                            Toast.makeText(ubah_pemilikActivity.this, "Password lama salah", Toast.LENGTH_LONG);
                        }
                    }
                }
            }
        });
    }

    class Background extends AsyncTask<Void, Void, String> {
        String JSON_STRING, url_signup_pemilik;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {url_signup_pemilik = "http://mykos.stander.id/pemilikKos_change.php";}

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(url_signup_pemilik);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("id_pemilik","UTF-8")+"="+URLEncoder.encode(pemilikKos_ubah.getId_pemilik(),"UTF-8")+"&"+
                        URLEncoder.encode("nama_pemilik","UTF-8")+"="+URLEncoder.encode(pemilikKos_ubah.getNama_pemilik(),"UTF-8")+"&"+
                        URLEncoder.encode("telp_pemilik","UTF-8")+"="+URLEncoder.encode(pemilikKos_ubah.getTelp_pemilik(),"UTF-8")+"&"+
                        URLEncoder.encode("username_pemilik","UTF-8")+"="+URLEncoder.encode(pemilikKos_ubah.getUsername_pemilik(),"UTF-8")+"&"+
                        URLEncoder.encode("password_pemilik","UTF-8")+"="+URLEncoder.encode(pemilikKos_ubah.getPassword_pemilik(),"UTF-8");
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
                Intent intent = new Intent(ubah_pemilikActivity.this, utama_pemilikActivity.class);
                intent.putExtra("json_data", json_string);
                startActivityForResult(intent, 0);
                nama.setText("");
                telp.setText("");
                username.setText("");
                passlama.setText("");
                passbaru.setText("");
                repassbaru.setText("");
            } else if (json_string == "FAILURE"){
                Toast.makeText(getApplicationContext(), "Tidak Berhasil Mengubah", Toast.LENGTH_LONG).show();
            }
        }


    }

}
