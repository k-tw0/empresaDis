package com.example.empresadis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainRegisiter extends AppCompatActivity {
    private EditText regName, regPhone, regEmail, regPass;
    private Button btnReg;
    private TextView txtLoogin;

    ProgressDialog progressDialog;

    // Accedera a los objetos.
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regisiter);
        // Conexion a firebase
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        progressDialog=new ProgressDialog(this);
        // identificadores necesarios
        regName = (EditText)findViewById(R.id.nameFull);
        regPhone = (EditText)findViewById(R.id.Phone);
        regEmail = (EditText)findViewById(R.id.direcEmail);
        regPass = (EditText)findViewById(R.id.EmailPass);
        txtLoogin = (TextView)findViewById(R.id.loginTxt);

        btnReg = (Button)findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                validaReg(v);
            }
        });

        txtLoogin.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                validaReg(v);
            }
        });
    }
    public void validaReg (View view) {
        String e, p, n, ph;
        n = regName.getText().toString();
        ph = regPhone.getText().toString();
        e = regEmail.getText().toString();
        p = regPass.getText().toString();
        //Acceder al metodo de crear usuario
        firebaseAuth.createUserWithEmailAndPassword(e, p)
                //SI LOS DATOS SON REGISTRADOS
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override//CASO EXITO
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(MainRegisiter.this,MainActivity.class));
                        progressDialog.cancel();
                        //GUARDAR DATOS EN UNA COLECCION USER
                        firebaseFirestore.collection("User")
                                .document(FirebaseAuth.getInstance().getUid())//OBTENER ID y los datos
                                .set(new UserModel(n,ph,e));//CREAR CLASE
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainRegisiter.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                });
    }

}