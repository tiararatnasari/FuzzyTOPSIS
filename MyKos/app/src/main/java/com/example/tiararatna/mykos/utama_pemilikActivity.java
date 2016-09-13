package com.example.tiararatna.mykos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
 * Created by Tiara Ratna on 4/19/2016.
 */
public class utama_pemilikActivity extends AppCompatActivity {
    private static Button ubah,kelola;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;

    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    pemilik_kosAdapter pemilikkosAdapter;
    pemilik_kos pemilikKos;
    String id_pemilik, nama_pemilik, telp_pemilik, username_pemilik, password_pemilik;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_pemilik);

        pemilikkosAdapter = new pemilik_kosAdapter(this, R.layout.activity_utama_pemilik);
        json_string=getIntent().getExtras().getString("json_data");
        Log.d("json_utama",json_string);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            pemilikKos = new pemilik_kos();

            id_pemilik = JO.getString("id_pemilik");
            pemilikKos.setId_pemilik(id_pemilik);
            Log.d("id",pemilikKos.getId_pemilik());

            nama_pemilik = JO.getString("nama_pemilik");
            pemilikKos.setNama_pemilik(nama_pemilik);
            Log.d("nama",pemilikKos.getNama_pemilik());

            telp_pemilik = JO.getString("telp_pemilik");
            pemilikKos.setTelp_pemilik(telp_pemilik);
            Log.d("telp",pemilikKos.getTelp_pemilik());

            username_pemilik = JO.getString("username_pemilik");
            pemilikKos.setUsername_pemilik(username_pemilik);
            Log.d("username",pemilikKos.getUsername_pemilik());

            password_pemilik = JO.getString("password_pemilik");
            pemilikKos.setPassword_pemilik (password_pemilik);
            Log.d("password",pemilikKos.getPassword_pemilik());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        TextView nama = (TextView)findViewById(R.id.f_upemilik_nama);
        TextView telp = (TextView)findViewById(R.id.f_upemilik_telp);
        TextView username = (TextView)findViewById(R.id.f_upemilik_username);
        TextView password = (TextView)findViewById(R.id.f_upemilik_password);

        nama.setText(pemilikKos.getNama_pemilik());
        telp.setText(pemilikKos.getTelp_pemilik());
        username.setText(pemilikKos.getUsername_pemilik());
        password.setText(pemilikKos.getPassword_pemilik());

        mContext = this;
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(4000);
        mViewFlipper.startFlipping();

        onClickButtonListenerUbah();
        onClickButtonListenerKelola();
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
                    mViewFlipper.showPrevious();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public void onClickButtonListenerUbah (){
        ubah = (Button)findViewById(R.id.b_upemilik_ubah);
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ubah_pemilikActivity.class);
                intent.putExtra("pemilik_kos", pemilikKos);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerKelola (){
        kelola = (Button)findViewById(R.id.b_upemilik_kelola);
        kelola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Background().execute();
            }
        });
    }

    class Background extends AsyncTask<Void, Void, String> {
        String JSON_STRING, json_url;
        @Override
        protected void onPreExecute() {json_url = "http://mykos.stander.id/identitasKos_getId.php";}

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("id_pemilik", "UTF-8") + "=" + URLEncoder.encode(pemilikKos.getId_pemilik(), "UTF-8");
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
        protected void onProgressUpdate(Void... values) {super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result) {
            json_string=result;
            Log.d("json_string", json_string);
            if(json_string=="FAILURE"){
                Toast.makeText(utama_pemilikActivity.this, "Tidak Ada Data", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(utama_pemilikActivity.this, kelola_infokosActivity.class);
                intent.putExtra("data", "tidak ada");
                intent.putExtra("id_pemilik", pemilikKos.getId_pemilik());
                Log.d("id_pemilik",pemilikKos.getId_pemilik());
                startActivityForResult(intent, 0);
            } else if(json_string!="FAILURE") {
                Toast.makeText(utama_pemilikActivity.this, "Berhasil Mengambil Data Kos", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(utama_pemilikActivity.this, kelola_infokosActivity.class);
                intent.putExtra("data", "ada");
                intent.putExtra("json_string", json_string);
                Log.d("json_string", json_string);
                intent.putExtra("id_pemilik", pemilikKos.getId_pemilik());
                Log.d("id_pemilik",pemilikKos.getId_pemilik());
                startActivityForResult(intent, 0);
            }
        }
    }
}
