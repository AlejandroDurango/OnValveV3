package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class perfil_usuario extends AppCompatActivity
{
    private HorizontalScrollView ContainerHorizontal;
    private LinearLayout LyoutGaraje;
    private TextView viewe;
    private static Button valvula1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuario);
        ContainerHorizontal = findViewById(R.id.ContainerHorizontal);
        LyoutGaraje = findViewById(R.id.LyoutGaraje);
        valvula1=findViewById(R.id.bttValcula1);
    }

    public void IrRegistrarValvula(View view)
    {
        Intent main = new Intent(this, agregar_valvula.class);
        startActivity(main);
    }

    public void IrEstadoValvula(View view){
        Intent main = new Intent(this, EstadoValvula.class);
        startActivity(main);
    }

    public static void ActivarValvula(){
        valvula1.setVisibility(View.VISIBLE);
        valvula1.setText(agregar_valvula.txtnombre_valvula.getText().toString());
    }



}