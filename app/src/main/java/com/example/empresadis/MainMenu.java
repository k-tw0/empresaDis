package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnLogin = (Button) findViewById(R.id.btnBuy); // OBTENER BOTON
        Button btnCalcule = (Button) findViewById(R.id.btnCalcule);

        btnLogin.setBackgroundColor(Color.parseColor("#4CAF50"));// Cambiar el BackGround del boton.
        btnLogin.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                Intent i = new Intent(MainMenu.this, ListView.class);// Lista de los objetos
                startActivity(i);
            }
        });
        btnCalcule.setBackgroundColor(Color.parseColor("#EAAB4E"));// Cambiar el BackGround del boton.
        btnCalcule.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                Intent i = new Intent(MainMenu.this, MainCalcule.class);// Lista de los objetos
                startActivity(i);
            }
        });
    }
}