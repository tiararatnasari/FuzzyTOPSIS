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

import com.example.tiararatna.adapter.pencari_kosAdapter;
import com.example.tiararatna.modul.pencari_kos;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tiara Ratna on 4/19/2016.
 */
public class utama_pencariActivity extends AppCompatActivity {
    private static Button ubah,cari,riwayat;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;

    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    String json_string,json_riwayat, json_univ, json_fak, json_prodi;;
    JSONObject jsonObject;
    JSONArray jsonArray;
    pencari_kosAdapter pencarikosAdapter;
    pencari_kos pencariKos;
    String id_pencari, id_prodi, nama_prodi, nama_fakultas, latitude, longitude, nama_universitas, nama_pencari, jk_pencari, username_pencari, password_pencari;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_pencari);

        pencarikosAdapter = new pencari_kosAdapter(this, R.layout.activity_utama_pencari);
        json_string=getIntent().getExtras().getString("json_data");
        Log.d("json_utama",json_string);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            pencariKos = new pencari_kos();

            id_pencari = JO.getString("id_pencari");
            pencariKos.setId_pencari(id_pencari);
            Log.d("id",pencariKos.getId_pencari());

            id_prodi = JO.getString("id_prodi");
            pencariKos.setId_prodi(id_prodi);
            Log.d("prodi",pencariKos.getId_prodi());

            nama_prodi = JO.getString("nama_prodi");
            pencariKos.setNama_prodi(nama_prodi);
            Log.d("id_univ",pencariKos.getNama_prodi());

            nama_fakultas = JO.getString("nama_fakultas");
            pencariKos.setNama_fakultas(nama_fakultas);
            Log.d("univ",pencariKos.getNama_fakultas());

            nama_universitas = JO.getString("nama_universitas");
            pencariKos.setNama_universitas(nama_universitas);
            Log.d("univ",pencariKos.getNama_universitas());

            latitude = JO.getString("latitude");
            pencariKos.setLatitude(latitude);
            Log.d("latitude",""+pencariKos.getLatitude());

            longitude = JO.getString("longitude");
            pencariKos.setLongitude(longitude);
            Log.d("longitude",""+pencariKos.getLongitude());

            nama_pencari = JO.getString("nama_pencari");
            pencariKos.setNama_pencari(nama_pencari);
            Log.d("nama",pencariKos.getNama_pencari());

            jk_pencari = JO.getString("jk_pencari");
            pencariKos.setJk_pencari(jk_pencari);
            Log.d("jk",pencariKos.getJk_pencari());

            username_pencari = JO.getString("username_pencari");
            pencariKos.setUsername_pencari(username_pencari);
            Log.d("username",pencariKos.getUsername_pencari());

            password_pencari = JO.getString("password_pencari");
            pencariKos.setPassword_pencari (password_pencari);
            Log.d("password",pencariKos.getPassword_pencari());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        TextView univ = (TextView)findViewById(R.id.f_upencari_univ);
        TextView fak = (TextView)findViewById(R.id.f_upencari_fak);
        TextView prodi = (TextView)findViewById(R.id.f_upencari_prodi);
        TextView nama = (TextView)findViewById(R.id.f_upencari_nama);
        TextView jk = (TextView)findViewById(R.id.f_upencari_jk);
        TextView username = (TextView)findViewById(R.id.f_upencari_username);
        TextView password = (TextView)findViewById(R.id.f_upencari_password);

        univ.setText(pencariKos.getNama_universitas());
        fak.setText(pencariKos.getNama_fakultas());
        prodi.setText(pencariKos.getNama_prodi());
        nama.setText(pencariKos.getNama_pencari());
        jk.setText(pencariKos.getJk_pencari());
        username.setText(pencariKos.getUsername_pencari());
        password.setText(pencariKos.getPassword_pencari());

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
        onClickButtonListenerCari();
        onClickButtonListenerRiwayat();
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
        ubah = (Button)findViewById(R.id.b_upencari_ubah);
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Background(utama_pencariActivity.this).execute("univ");
                new Background(utama_pencariActivity.this).execute("fak");
                new Background(utama_pencariActivity.this).execute("prodi");
            }
        });
    }

    public void onClickButtonListenerCari (){
        cari = (Button)findViewById(R.id.b_upencari_cari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(utama_pencariActivity.this, cari_input2Activity.class);
                intent.putExtra("pencari", pencariKos);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void onClickButtonListenerRiwayat (){
        riwayat = (Button)findViewById(R.id.b_upencari_riwayat);
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Background(utama_pencariActivity.this).execute("riwayat");
            }
        });
    }

    class Background extends AsyncTask<String, Void, String> {
        String JSON_STRING, url_riwayatGet, url_univ, url_fak, url_prodi, method;
        Context ctx;

        public Background(Context ctx) {this.ctx = ctx;}

        @Override
        protected void onPreExecute() {
            url_riwayatGet = "http://mykos.stander.id/riwayatPencarian_get.php";
            url_univ = "http://mykos.stander.id/universitas.php";
            url_fak = "http://mykos.stander.id/fakultas.php";
            url_prodi = "http://mykos.stander.id/program_studi.php";
        }

        @Override
        protected String doInBackground(String... params) {
            method = params[0];
            if (method.equalsIgnoreCase("riwayat")) {
                try {
                    URL url = new URL(url_riwayatGet);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("id_pencari", "UTF-8") + "=" + URLEncoder.encode(id_pencari, "UTF-8");
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
            } else if(method.equalsIgnoreCase("univ")){
                try {
                    URL url = new URL(url_univ);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
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
            } else if(method.equalsIgnoreCase("fak")){
                try {
                    URL url = new URL(url_fak);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
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
            } else if(method.equalsIgnoreCase("prodi")){
                try {
                    URL url = new URL(url_prodi);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
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
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result) {
            if (method.equalsIgnoreCase("riwayat")) {
                json_riwayat = result;
                Log.d("json_riwayat", json_riwayat);
                if (json_riwayat.equalsIgnoreCase("FAILURE")) {
                    Toast.makeText(utama_pencariActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(utama_pencariActivity.this, riwayat_daftarkosActivity.class);
                    intent.putExtra("data", "tidak ada");
                    intent.putExtra("pencari", pencariKos);
                    startActivityForResult(intent, 0);
                    Log.d("data", "tidak ada");
                } else if (!json_riwayat.equalsIgnoreCase("FAILURE")) {
                    Intent intent = new Intent(utama_pencariActivity.this, riwayat_daftarkosActivity.class);
                    intent.putExtra("data", "ada");
                    intent.putExtra("riwayat_kos", json_riwayat);
                    intent.putExtra("pencari", pencariKos);
                    Log.d("la", pencariKos.getLatitude());
                    Log.d("lo", pencariKos.getLongitude());
                    startActivityForResult(intent, 0);
                    Log.d("data", "ada");
                }
            } else if (method.equalsIgnoreCase("univ")) {
                json_univ = result;
                Log.d("json_univ", json_univ);
            } else if (method.equalsIgnoreCase("fak")) {
                json_fak = result;
                Log.d("json_fak", json_fak);
            } else if (method.equalsIgnoreCase("prodi")) {
                json_prodi = result;
                Log.d("json_prodi", json_prodi);
                if (json_prodi != null) {
                    Intent intent = new Intent(utama_pencariActivity.this, ubah_pencariActivity.class);
                    intent.putExtra("pencari_kos", pencariKos);
                    intent.putExtra("json_univ", json_univ);
                    intent.putExtra("json_fak", json_fak);
                    intent.putExtra("json_prodi", json_prodi);
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "First Get JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
