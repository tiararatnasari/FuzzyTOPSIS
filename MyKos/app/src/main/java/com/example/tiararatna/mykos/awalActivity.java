package com.example.tiararatna.mykos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Tiara Ratna on 4/14/2016.
 */
public class awalActivity extends AppCompatActivity {
    private static Button next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        onClickButtonListenerNext();
    }

    public void onClickButtonListenerNext() {
        next = (Button) findViewById(R.id.b_awal_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), masukActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}

