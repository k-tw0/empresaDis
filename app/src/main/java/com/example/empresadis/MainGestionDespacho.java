package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainGestionDespacho extends AppCompatActivity {

    private Button btnCalc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gestion_despacho);

        btnCalc = findViewById(R.id.btnCalculeTemp);

        btnCalc.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                activityGest(v);
            }
        });
    }
    public void activityGest(View v){
        Intent i = new Intent(MainGestionDespacho.this, MainCalculeDespacho.class);// Lista de los objetos
        startActivity(i);
    }
}