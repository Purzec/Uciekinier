package com.example.uciekinier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void start(View view) {

        Intent intent;
        switch (view.getId()) {

            case R.id.tak:
                intent = new Intent(MainActivity.this,PierwszaZagadkaActivity.class);
                startActivity(intent);
                break;
        }
        }


    public void test(View view) {
    }
}
