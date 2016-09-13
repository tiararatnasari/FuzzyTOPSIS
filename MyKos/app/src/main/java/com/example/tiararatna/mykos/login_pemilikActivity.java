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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiararatna.adapter.pemilik_kosAdapter;
import com.example.tiararatna.modul.pemilik_kos;

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

/**
 * Created by Tiara Ratna on 4/14/2016.
 */
public class login_pemilikActivity extends AppCompatActivity {
    Button login, signup;
    EditText username, password;
    String Username, Password;
    String json_string;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pemilik);

        username = (EditText) findViewById(R.id.f_lpemilik_username);
        password = (EditText) findViewById(R.id.f_lpemilik_password);

        onClickButtonListenerLoginPemilik();
        onClickButtonListenerSignupPemilik();
    }

    public void onClickButtonListenerSignupPemilik() {
        signup = (Button) findViewById(R.id.b_lpemilik_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_pemilikActivity.this, signup_pemilikActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerLoginPemilik() {
        login = (Button) findViewById(R.id.b_lpemilik_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Log.d("username", Username);
                Password = password.getText().toString();
                Log.d("password", Password);
                if(Username.equalsIgnoreCase("")&&Password.equalsIgnoreCase("")){
                    Toast.makeText(login_pemilikActivity.this, "Data Kosong", Toast.LENGTH_LONG).show();
                } else if(Username.equalsIgnoreCase("")||Password.equalsIgnoreCase("")){
                    Toast.makeText(login_pemilikActivity.this, "Data Belum Lengkap", Toast.LENGTH_LONG).show();
                } else if(!Username.equalsIgnoreCase("")&&!Password.equalsIgnoreCase("")){
                    new Background(login_pemilikActivity.this).execute(Username, Password);
                }
            }
        });
    }

    class Background extends AsyncTask<String, Void, String> {
        String JSON_STRING, url_login_pemilik;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {url_login_pemilik = "http://mykos.stander.id/pemilikKos_login.php";}

        @Override
        protected String doInBackground(String... params) {
            String username_pemilik = params[0];
            Log.d("user", username_pemilik);
            String password_pemilik = params[1];
            Log.d("pass", password_pemilik);
            try {
                URL url = new URL(url_login_pemilik);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username_pemilik", "UTF-8") + "=" + URLEncoder.encode(username_pemilik, "UTF-8") + "&" +
                        URLEncoder.encode("password_pemilik", "UTF-8") + "=" + URLEncoder.encode(password_pemilik, "UTF-8");
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
                Intent intent = new Intent(login_pemilikActivity.this, utama_pemilikActivity.class);
                intent.putExtra("json_data", json_string);
                startActivityForResult(intent, 0);
                username.setText("");
                password.setText("");
            } else if (json_string == "FAILURE"){
                Toast.makeText(getApplicationContext(), "Data tidak ditemukan", Toast.LENGTH_LONG).show();
            }

        }
    }
}
