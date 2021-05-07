package com.example.OnValve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class RecuperarPasswordActivity extends AppCompatActivity
{
    private EditText Correo_recuperar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_password);
        Correo_recuperar = findViewById(R.id.txt_correo_recuperar);
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
    }
}