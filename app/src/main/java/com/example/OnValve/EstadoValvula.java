package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EstadoValvula extends AppCompatActivity
{
    private TextView EstadoV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_valvula);
        EstadoV = findViewById(R.id.txtEstado);
        //FirebaseDatabase firebaseDatabase = Iniciar_sesion.firebaseDatabase;
        //DatabaseReference databaseReference = Iniciar_sesion.databaseReference;
    }

    public void IrTemporizador(View view)
    {
        startActivity(new Intent(this, Temporizador.class));
    }

    public void CerrarValvula(View view)
    {

    }
}