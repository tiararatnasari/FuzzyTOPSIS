package com.example.tiararatna.mykos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
public class masukActivity extends Activity {
    private static ImageButton masuk_pemilik,masuk_pencari;
    String json_string;
    String json_univ, json_fak, json_prodi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        onClickButtonListenerMasukPemilik();
        onClickButtonListenerMasukPencari();
    }

    public void onClickButtonListenerMasukPemilik() {
        masuk_pemilik = (ImageButton) findViewById(R.id.b_masuk_pemilik);
        masuk_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(masukActivity.this, login_pemilikActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerMasukPencari (){
        masuk_pencari = (ImageButton)findViewById(R.id.b_masuk_pencari);
        masuk_pencari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(masukActivity.this, login_pencariActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
