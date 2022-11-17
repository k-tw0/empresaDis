package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainCalcule extends AppCompatActivity {

    private EditText lat1, lat2, lon1, lon2;
    private TextView txt, txtMetros, txtMinutos;
    private double age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calcule);

        Button btnCalcule = (Button) findViewById(R.id.btnLatLong);

        lat1 = (EditText) findViewById(R.id.editTextDecimalLat1);
        lon1 = (EditText) findViewById(R.id.editTextDecimalLong1);
        lat2 = (EditText) findViewById(R.id.editTextDecimalLat2);
        lon2 = (EditText) findViewById(R.id.editTextDecimalLong2);

        txt = (TextView) findViewById(R.id.textView3);
        txtMetros = (TextView) findViewById(R.id.textViewMetros);
        txtMinutos = (TextView) findViewById(R.id.textViewMinutos);

        btnCalcule.setBackgroundColor(Color.parseColor("#4CAF50"));// Cambiar el BackGround del boton.
        btnCalcule.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                String la1, la2, lo1, lo2;
                double resLa1, resLa2, resLo1, resLo2;
                la1 = lat1.getText().toString(); // ALMACENAR VALOR DE INPUT EN TEXTO
                la2 = lat2.getText().toString(); // ALMACENAR VALOR DE INPUT EN TEXTO
                lo1 = lon1.getText().toString(); // ALMACENAR VALOR DE INPUT EN TEXTO
                lo2 = lon2.getText().toString(); // ALMACENAR VALOR DE INPUT EN TEXTO

                // double resLa1 = Integer.parseInt(la1); // TRANSFOMAR VALOR DE TEXTO A DOU
                resLa1 = Double.parseDouble(la1);
                resLa2 = Double.parseDouble(la2);
                resLo1 = Double.parseDouble(lo1);
                resLo2 = Double.parseDouble(lo2);

                distance(resLa1, resLa2, resLo1, resLo2, 0.0, 0.0);
                txt.setText("Total: " + age);
                double i = Math.round(age * Math.PI / 180);
                txtMinutos.setText(""+i);
                txtMetros.setText(Math.round(age) + " Metros distancia linea recta.");
                // txtMetros.setText(age * Math.PI / 180 + " Minutos de distancia");


                Log.i("Administrador", "Lat: " + resLa1 + " Lat2: " + resLa2 + " Long: " + resLo1 + " Long2: " + resLo2);

            }
        });
    }
    public double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        /**
         * Calculate distance between two points in latitude and longitude taking
         * into account height difference. If you are not interested in height
         * difference pass 0.0. Uses Haversine method as its base.
         *
         * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
         * el2 End altitude in meters
         * @returns Distance in Meters
         */

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        this.age = Math.sqrt(distance);

        return Math.sqrt(distance);
    }
}