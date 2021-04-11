package com.example.OnValve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.OnValve.Modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Iniciar_sesion extends AppCompatActivity
{

    private EditText txtCorreoElectronico;
    private EditText txtContraseña;
    private  FirebaseAuth Auth;


    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtContraseña = findViewById(R.id.txtContraseña);
        Auth = FirebaseAuth.getInstance();
    }

    private void inicializarFirebase()
    {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void validacion()
    {
        if(txtCorreoElectronico.getText().toString().isEmpty())
        {
            txtCorreoElectronico.setError("Requerido");
        }
        if(txtContraseña.getText().toString().isEmpty())
        {
            txtContraseña.setError("Requerido");
        }
    }


    public boolean DatosVacios()
    {
        if(txtCorreoElectronico.getText().toString().isEmpty())
        {
            return true;
        }
        if(txtContraseña.getText().toString().isEmpty())
        {
            return true;
        }
        return false;
    }


    public void IniciarSesion(View view)
    {
        String correoElectronico = txtCorreoElectronico.getText().toString();
        String contraseña = txtContraseña.getText().toString();

        if(DatosVacios())
        {
            validacion();
        }
        else
        {
            Auth.signInWithEmailAndPassword(correoElectronico, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        startActivity(new Intent(Iniciar_sesion.this, perfil_usuario.class));
                    }
                    else
                    {
                        Toast.makeText(Iniciar_sesion.this, "Ocurrió un error, por favor revise sus datos, sino está registrado por favor hágalo", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    public void RecuperarContraseña(View view)
    {
        startActivity(new Intent(this, RecuperarPasswordActivity.class));
    }

    public void IrAtrasInicio(View view)
    {
        Intent Main = new Intent(this, MainActivity.class);
        startActivity(Main);
    }


}