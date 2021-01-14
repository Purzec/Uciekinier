package com.example.uciekinier;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;


import android.view.View;

import android.widget.ImageView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PierwszaZagadkaActivity extends AppCompatActivity {

    private Disposable textWatcher;

    private Random random = new Random();
    private int numerZagadki=random.nextInt(5);;
    @Override
    protected void onPause() {
        super.onPause();
        textWatcher.dispose();
        System.out.println("konczy");// Konczy emisje i zamyka watek
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pierwsza_zagadka);
        AppCompatEditText input = findViewById(R.id.password);
        AppCompatTextView message = findViewById(R.id.message);
String[] pytania = new String[5];
pytania[0] = "jaki jest numer alarmowy na telefonach komórkowych?";
pytania[4] = "Pora roku w jakiej pada śnieg";
pytania[3] = "Przeciwieństwo dodawania";
pytania[2] = "Rok kanonizacji Grzegorza IX";
pytania[1] = "Przykładowe silne hasło";
        Intent intent;
        intent = new Intent(PierwszaZagadkaActivity.this,drugaZagadka.class);

ImageView button = findViewById(R.id.accesImage);
        textWatcher = RxTextView.textChanges(input) // Tworzy strumien na podstawie zmian EditText
                .debounce(300, TimeUnit.MILLISECONDS) // Ogranicza emisje na odstepy co 300 milisekund
                .map(this::losowanieZagadkiSlownej) // Zamienia wprowadzony tekst na rezultat logiczny
                .subscribeOn(Schedulers.io()) // Powyzsze operacje wykonuje w tle na watku I/O
                .observeOn(AndroidSchedulers.mainThread()) // Rezultat jest zwracany do UI
                .subscribe( // Subskrybcja, ktora przechwytuje emisje czyli (true/false)
                        valid -> {
                            if (valid) { //Haslo zgodne ze schematem
                                message.setText(pytania[numerZagadki]);
                                button.setImageResource(R.drawable.buttonshape);
                                button.setClickable(true);
                                Thread.sleep(200);
                                startActivity(intent);
                            } else {
                                message.setText(pytania[numerZagadki]);
                                button.setImageResource(R.drawable.buttonred);
                                button.setClickable(false);
                            }
                        });
    }

    
    private boolean losowanieZagadkiSlownej(CharSequence pass) {
switch (numerZagadki)
{
    case 1:
        return pass.toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

     case 2:
         return pass.toString().matches("^1234$");

     case 3:
         return pass.toString().matches("^odejmowanie$");

     case 4:
         return pass.toString().matches("^zima$");

     case 0:
         return pass.toString().matches("^112$");

    default:
        return false;

}



    }
}