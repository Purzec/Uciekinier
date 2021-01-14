package com.example.uciekinier;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrzeciaZagadka extends AppCompatActivity {
    private Disposable zapelnienie;
    private  ProgressBar progressBar;
    private int liczba;
    private ImageView obrazekczlowieka;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trzecia_zagadka);
        progressBar=findViewById(R.id.progressBar);
obrazekczlowieka = findViewById(R.id.czlowieczek);
liczba=1;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onStart() {

        super.onStart();
       Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(
                        x -> progressBar.setProgress(Math.toIntExact(x)));

    }


    public void bieganie(View view) {
    obrazekczlowieka.setY(obrazekczlowieka.getY()-10);
    if (liczba==1)
    {
        obrazekczlowieka.setImageResource(R.drawable.czlowiek1);
        liczba=0;
    }else
    {
        obrazekczlowieka.setImageResource(R.drawable.czlowiek2);
        liczba=1;

    }

    }
}