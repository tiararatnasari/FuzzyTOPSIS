package com.example.tiararatna.mykos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tiara Ratna on 4/14/2016.
 */
public class signup_pemilikActivity extends AppCompatActivity {

    EditText nama, telp, username, password, repassword;
    String Nama, Telp, Username, Password, Repassword, json_string;
    Button login, signup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup_pemilik);

        nama = (EditText) findViewById(R.id.f_spemilik_nama);
        telp = (EditText) findViewById(R.id.f_spemilik_telp);
        username = (EditText) findViewById(R.id.f_spemilik_user);
        password = (EditText) findViewById(R.id.f_spemilik_pass);
        repassword = (EditText) findViewById(R.id.f_spemilik_repass);

        onClickButtonListenerLoginPemilik();
        onClickButtonListenerSignupPemilik();
    }

    public void onClickButtonListenerLoginPemilik() {
        login = (Button) findViewById(R.id.b_spemilik_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup_pemilikActivity.this, login_pemilikActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerSignupPemilik() {
        signup = (Button) findViewById(R.id.b_spemilik_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password = password.getText().toString();
                Log.d("password", Password);
                Repassword = repassword.getText().toString();
                Log.d("repassword", Repassword);

                if(Nama.equalsIgnoreCase("")&&Telp.equalsIgnoreCase("")&&Username.equalsIgnoreCase("")&&Password.equalsIgnoreCase("")&&Repassword.equalsIgnoreCase("")){
                    Toast.makeText(signup_pemilikActivity.this, "Data Kosong", Toast.LENGTH_LONG).show();
                }else if(Nama.equalsIgnoreCase("")||Telp.equalsIgnoreCase("")||Username.equalsIgnoreCase("")||Password.equalsIgnoreCase("")||Repassword.equalsIgnoreCase("")){
                    Toast.makeText(signup_pemilikActivity.this, "Data Belum Lengkap", Toast.LENGTH_LONG).show();
                }else if(!Nama.equalsIgnoreCase("")&&!Telp.equalsIgnoreCase("")&&!Username.equalsIgnoreCase("")&&!Password.equalsIgnoreCase("")&&!Repassword.equalsIgnoreCase("")) {
                    if (Password.equalsIgnoreCase(Repassword)) {
                        Nama = nama.getText().toString();
                        Log.d("nama", Nama);
                        Telp = telp.getText().toString();
                        Log.d("telp", Telp);
                        Username = username.getText().toString();
                        Log.d("username", Username);
                        new Background(signup_pemilikActivity.this).execute(Nama, Telp, Username, Password);
                    } else if (!Password.equals(Repassword)) {
                        Toast.makeText(signup_pemilikActivity.this, "Repassword harus sama dengan Password", Toast.LENGTH_LONG);
                    }
                }
            }
        });
    }

    class Background extends AsyncTask<String, Void, String> {
        String JSON_STRING, url_signup_pemilik;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {url_signup_pemilik = "http://mykos.stander.id/pemilikKos_signup.php";}

        @Override
        protected String doInBackground(String... params) {
            String nama_pemilik = params[0];
            Log.d("nama", nama_pemilik);
            String telp_pemilik = params[2];
            Log.d("telp", telp_pemilik);
            String username_pemilik = params[3];
            Log.d("user", username_pemilik);
            String password_pemilik = params[4];
            Log.d("pass", password_pemilik);
            try {
                URL url = new URL(url_signup_pemilik);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("nama_pemilik","UTF-8")+"="+URLEncoder.encode(nama_pemilik,"UTF-8")+"&"+
                        URLEncoder.encode("telp_pemilik","UTF-8")+"="+URLEncoder.encode(telp_pemilik,"UTF-8")+"&"+
                        URLEncoder.encode("username_pemilik","UTF-8")+"="+URLEncoder.encode(username_pemilik,"UTF-8")+"&"+
                        URLEncoder.encode("password_pemilik","UTF-8")+"="+URLEncoder.encode(password_pemilik,"UTF-8");
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
            return  null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            Log.d("json_login", json_string);
            if (json_string != "FAILURE") {
                Intent intent = new Intent(signup_pemilikActivity.this, utama_pemilikActivity.class);
                intent.putExtra("json_data", json_string);
                startActivityForResult(intent, 0);
                nama.setText("");
                telp.setText("");
                username.setText("");
                password.setText("");
                repassword.setText("");
            } else if (json_string == "FAILURE"){
                Toast.makeText(getApplicationContext(), "Tidak Berhasil Mendaftar", Toast.LENGTH_LONG).show();
            }
        }
    }

}
