package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IniciarApp(View view)
    {
        Intent Main = new Intent(this, registro_usuario.class);
        startActivity(Main);
    }

    public void IrInicioSesion(View view)
    {
        Intent Main = new Intent(this, Iniciar_sesion.class);
        startActivity(Main);
    }
}