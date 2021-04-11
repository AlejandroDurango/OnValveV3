package com.example.OnValve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RecuperarPasswordActivity extends AppCompatActivity
{
    private EditText Correo_recuperar;
    private FirebaseAuth Auth;
    private FirebaseUser User;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_password);
        Correo_recuperar = findViewById(R.id.txt_correo_recuperar);
        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
    }

    public void validacion()
    {
        if(Correo_recuperar.getText().toString().isEmpty())
        {
            Correo_recuperar.setError("Requerido");
        }
    }

    public boolean DatosVacios()
    {
        if(Correo_recuperar.getText().toString().isEmpty())
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    public void RecuperarContraseña(View view)
    {
        if(DatosVacios())
        {
            validacion();
        }
        else
        {
            String correoElectronico = Correo_recuperar.getText().toString();
            Auth.sendPasswordResetEmail(correoElectronico).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RecuperarPasswordActivity.this, "Contraseña enviada a su correo", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(RecuperarPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}