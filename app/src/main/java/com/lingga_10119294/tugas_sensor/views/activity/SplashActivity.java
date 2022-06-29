package com.lingga_10119294.tugas_sensor.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.lingga_10119294.tugas_sensor.MainActivity;
/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}