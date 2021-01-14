package com.example.uciekinier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class drugaZagadka extends AppCompatActivity {

    private AppCompatImageView imgv1;
    private Disposable imageLoader;
    private Random random = new Random();
    private int zagadkaDruga=random.nextInt(2);
    EditText poczatkowa;
    EditText koncowa;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga_zagadka);

        // Example loading a bitmap
        imgv1 = findViewById(R.id.image1);

        poczatkowa = findViewById(R.id.pozycjaPText);
        koncowa = findViewById(R.id.pozycjaKText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        switch (zagadkaDruga)
        {
            case 1:
                imageLoader = Observable.create(emitter -> {
                            emitter.onNext(R.drawable.poczatekszachy);
                            Thread.sleep(2000); // Symulacja pobierania danych
                            emitter.onNext(R.drawable.zagadka1);
                            emitter.onComplete();
                        }
                ).subscribeOn(Schedulers.io()) // Operacje powyzej wykonaja sie w tle
                        .observeOn(AndroidSchedulers.mainThread()) // Zmiana zdjecia wykona sie w watku glownym
                        .subscribe(img -> imgv1.setImageResource((Integer) img));
                break;
            case 0:
                imageLoader = Observable.create(emitter -> {
                            emitter.onNext(R.drawable.poczatekszachy);
                             Thread.sleep(2000); // Symulacja pobierania danych
                            emitter.onNext(R.drawable.zagadka2i1);
                             Thread.sleep(1000); // Symulacja pobierania danych
                            emitter.onNext(R.drawable.zagadka2i2);
                             Thread.sleep(1000); // Symulacja pobierania danych
                            emitter.onNext(R.drawable.zagadka2i3);
                               Thread.sleep(1000); // Symulacja pobierania danych
                            emitter.onNext(R.drawable.zagadka2i4);
                    emitter.onComplete();
                        }
                ).subscribeOn(Schedulers.io()) // Operacje powyzej wykonaja sie w tle
                        .observeOn(AndroidSchedulers.mainThread()) // Zmiana zdjecia wykona sie w watku glownym
                        .subscribe(img -> imgv1.setImageResource((Integer) img));
                break;

        }
    }

    public void sprawdz(View view) throws InterruptedException {
        if (poczatkowa.getText().toString().equalsIgnoreCase("e1") && koncowa.getText().toString().equalsIgnoreCase("e8"))
        {
            imageLoader = Observable.create(emitter -> {
                        emitter.onNext(R.drawable.mat);
                        emitter.onComplete();
                    }
            ).subscribeOn(Schedulers.io()) // Operacje powyzej wykonaja sie w tle
                    .observeOn(AndroidSchedulers.mainThread()) // Zmiana zdjecia wykona sie w watku glownym
                    .subscribe(img -> imgv1.setImageResource((Integer) img));
            Thread.sleep(2000);
            intent = new Intent(drugaZagadka.this,TrzeciaZagadka.class);
            startActivity(intent);

        }else
        {
            imageLoader = Observable.create(emitter -> {
                        emitter.onNext(R.drawable.zle);
                        emitter.onComplete();
                    }
            ).subscribeOn(Schedulers.io()) // Operacje powyzej wykonaja sie w tle
                    .observeOn(AndroidSchedulers.mainThread()) // Zmiana zdjecia wykona sie w watku glownym
                    .subscribe(img -> imgv1.setImageResource((Integer) img));
            Thread.sleep(2000);
            intent = new Intent(drugaZagadka.this,Porazka.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        imageLoader.dispose();
        System.out.println("konczy");// Konczy emisje i zamyka watek
    }
}


