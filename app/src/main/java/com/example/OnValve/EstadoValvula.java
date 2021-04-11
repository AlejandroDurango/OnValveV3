package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.OnValve.Modelo.Usuario;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class EstadoValvula extends AppCompatActivity
{
    private TextView EstadoV;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_valvula);
        EstadoV = findViewById(R.id.txtEstado);
        FirebaseDatabase firebaseDatabase = Iniciar_sesion.firebaseDatabase;
        DatabaseReference databaseReference = Iniciar_sesion.databaseReference;
        auth = FirebaseAuth.getInstance();
    }

    public void IrTemporizador(View view)
    {
        startActivity(new Intent(this, Temporizador.class));
    }

    public void CerrarValvula(View view)
    {
        FirebaseUser usuario = auth.getCurrentUser();
    }
}