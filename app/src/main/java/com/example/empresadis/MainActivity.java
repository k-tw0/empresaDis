package com.example.empresadis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //LOCALICZACION
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE=100;

    //Menu principal(ATRIBUTOS)
    private EditText mail, contr;
    private TextView txtReg, txtReset;

    private DatabaseReference mDatabase;//realtime database

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //localizacion
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);

        firebaseAuth=FirebaseAuth.getInstance();//Auth Firebase
        progressDialog=new ProgressDialog(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();//REALTIME DATABASE

        mail = (EditText)findViewById(R.id.editTextTextEmailAddress);
        contr = (EditText)findViewById(R.id.editTextTextPassword);
        txtReg = (TextView)findViewById(R.id.irHaRegistroActividad);
        txtReset = (TextView)findViewById(R.id.resetearContra);

        Button btnLogin =  (Button) findViewById(R.id.btnLogin); // OBTENER BOTON

        btnLogin.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                validar(v);
            }
        });
        txtReset.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                reset(v);
            }
        });
        txtReg.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                register(v);
            }
        });
    }
    public void validar (View view) {
        String em,pw;
        em=mail.getText().toString().trim();
        pw=contr.getText().toString().trim();
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(em,pw)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        realTIme();
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this, "Login succesful", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void reset(View view) {
        String em=mail.getText().toString();
        progressDialog.setTitle("Enviando Email");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(em)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this, "Email enviado", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void register (View view){
        startActivity(new Intent(MainActivity.this,MainRegisiter.class));
    }

    public void realTIme() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location !=null){
                                Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);

                                    Double lon, lat;
                                    String dire, city, country;

                                    lon =addresses.get(0).getLatitude();
                                    lat=addresses.get(0).getLongitude();
                                    dire=addresses.get(0).getAddressLine(0);
                                    city=addresses.get(0).getLocality();
                                    country=addresses.get(0).getCountryName();

                                    Log.i("Tunito", "lat: "+ lat + " lon: "+lon + " Dire: " + dire + " Ciudad: " + city + "Pais: " + country);
                                    //Guardar
                                    Localization loc = new Localization(""+dire, ""+city, "" + country);
                                    mDatabase.child("Localizaciones").child(loc.getCity()).setValue(loc);
                                    startActivity(new Intent(MainActivity.this,MainMenu.class));

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        }else{
            askPermission();
        }



    }
    private void askPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                realTIme();
            }
            else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}