package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.util.AsyncListUtil;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiararatna.adapter.DropDownSpinner;
import com.example.tiararatna.modul.fakultas;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Tiara Ratna on 4/14/2016.
 */
public class signup_pencariActivity extends AppCompatActivity {

    EditText nama, username, password, repassword;
    String Id_prodi, Nama, Jk, Username, Password, Repassword, json_string, data_univ, data_fak, data_prodi;
    Button login, signup;
    Spinner univ,fak,prodi,jk;
    static ArrayAdapter<CharSequence> adapter_univ, adapter_fak, adapter_prodi, adapter_jk;
    int index_univ, index_fak, index_prodi, index_jk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup_pencari);

        univ = (Spinner) findViewById(R.id.s_spencari_univ);
        fak = (Spinner) findViewById(R.id.s_spencari_fak);
        prodi = (Spinner) findViewById(R.id.s_spencari_prodi);
        jk = (Spinner) findViewById(R.id.s_spencari_jk);

        adapter_univ = ArrayAdapter.createFromResource(this, R.array.univ, android.R.layout.simple_spinner_item);
        adapter_univ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        univ.setAdapter(adapter_univ);
        univ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index_univ = univ.getSelectedItemPosition();
                adapter_fak = DropDownSpinner.fakultas(signup_pencariActivity.this, index_univ);
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
                adapter_prodi = DropDownSpinner.prodi(signup_pencariActivity.this, index_univ, index_fak);
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

        adapter_jk = ArrayAdapter.createFromResource(signup_pencariActivity.this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item);
        adapter_jk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jk.setAdapter(adapter_jk);
        jk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index_jk = jk.getSelectedItemPosition();
                Jk = DropDownSpinner.jenisKelamin(index_jk);
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        nama = (EditText) findViewById(R.id.f_spencari_nama);
        username = (EditText) findViewById(R.id.f_spencari_user);
        password = (EditText) findViewById(R.id.f_spencari_pass);
        repassword = (EditText) findViewById(R.id.f_spencari_repass);

        onClickButtonListenerLoginPencari();
        onClickButtonListenerSignupPencari();
    }

    public void onClickButtonListenerLoginPencari() {
        login = (Button) findViewById(R.id.b_spencari_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup_pencariActivity.this, login_pencariActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerSignupPencari() {
        signup = (Button) findViewById(R.id.b_spencari_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nama = nama.getText().toString();
                Username = username.getText().toString();
                Password = password.getText().toString();
                Repassword = repassword.getText().toString();

                if(Nama.equalsIgnoreCase("")&&Username.equalsIgnoreCase("")&&Password.equalsIgnoreCase("")&&Repassword.equalsIgnoreCase("")){
                    Toast.makeText(signup_pencariActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }else if(Nama.equalsIgnoreCase("")||Username.equalsIgnoreCase("")||Password.equalsIgnoreCase("")||Repassword.equalsIgnoreCase("")){
                    Toast.makeText(signup_pencariActivity.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                }else if(!Nama.equalsIgnoreCase("")&&!Username.equalsIgnoreCase("")&&!Password.equalsIgnoreCase("")&&!Repassword.equalsIgnoreCase("")) {
                    if (Password.equalsIgnoreCase(Repassword)) {
                        Log.d("id_univ", Id_prodi);
                        Nama = nama.getText().toString();
                        Log.d("nama", Nama);
                        Username = username.getText().toString();
                        Log.d("username", Username);

                        new Background(signup_pencariActivity.this).execute( Id_prodi, Nama, Jk, Username, Password);
                    } else if (Repassword.equals(null)) {
                        Toast.makeText(signup_pencariActivity.this, "Repassword belum diisi", Toast.LENGTH_SHORT);
                    }
                }
            }
        });
    }

    class Background extends AsyncTask<String, Void, String> {
        String JSON_STRING, url_signup_pencari;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {url_signup_pencari = "http://mykos.stander.id/pencariKos_signup.php";}

        @Override
        protected String doInBackground(String... params) {
            String id_prodi = params[0];
            Log.d("id_prodi", id_prodi);
            String nama_pencari = params[1];
            Log.d("nama", nama_pencari);
            String jk_pencari = params[2];
            Log.d("jk", jk_pencari);
            String username_pencari = params[3];
            Log.d("user", username_pencari);
            String password_pencari = params[4];
            Log.d("pass", password_pencari);
            try {
                URL url = new URL(url_signup_pencari);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("id_prodi","UTF-8")+"="+URLEncoder.encode(id_prodi,"UTF-8")+"&"+
                        URLEncoder.encode("nama_pencari","UTF-8")+"="+URLEncoder.encode(nama_pencari,"UTF-8")+"&"+
                        URLEncoder.encode("jk_pencari","UTF-8")+"="+URLEncoder.encode(jk_pencari,"UTF-8")+"&"+
                        URLEncoder.encode("username_pencari","UTF-8")+"="+URLEncoder.encode(username_pencari,"UTF-8")+"&"+
                        URLEncoder.encode("password_pencari","UTF-8")+"="+URLEncoder.encode(password_pencari,"UTF-8");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            Log.d("json_data", json_string);
            if (json_string != null) {
                Intent intent = new Intent(signup_pencariActivity.this, utama_pencariActivity.class);
                intent.putExtra("json_data", json_string);
                startActivityForResult(intent, 0);
                univ.getItemAtPosition(0);
                nama.setText("");
                jk.getItemAtPosition(0);
                username.setText("");
                password.setText("");
                repassword.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Tidak Berhasil Mendaftar", Toast.LENGTH_LONG).show();
            }
        }
    }

}