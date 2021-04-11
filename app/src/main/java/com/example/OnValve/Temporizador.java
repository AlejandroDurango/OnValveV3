package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Temporizador extends AppCompatActivity {
    private TextView temporizadorTxt;
    private Button temporizadorBtt;
    private EditText tiempoTemporizadorTxt;

    private CountDownTimer cuentaRegresiva;
    private long tiempoRestante = 60000;
    private boolean tiempoCorriendo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        temporizadorTxt = findViewById(R.id.txtVistaTiempo);
        temporizadorBtt =  findViewById(R.id.bttnEmpezarTemporizador);
        tiempoTemporizadorTxt = findViewById(R.id.txtTiempoTemporizar);

        temporizadorBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starStop();
            }
        });
    }

    public void starStop(){
        if (tiempoCorriendo){
            pararTiempo();
        }else{
            empezarTiempo();
        }
    }

    public void empezarTiempo(){

        cuentaRegresiva = new CountDownTimer(tiempoRestante, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoRestante = millisUntilFinished;
                actualizarTiempo();
            }

            @Override
            public void onFinish() {

            }

        };
        cuentaRegresiva.start();
        tiempoCorriendo = true;
    }
    public void  pararTiempo(){
        cuentaRegresiva.cancel();
        tiempoCorriendo = false;

    }

    public void actualizarTiempo(){
        int minutos = (int) tiempoRestante/60000;
        int segundos = (int) tiempoRestante%60000/1000;

        String tiempoRestanteTxt;

        tiempoRestanteTxt = "" + minutos;
        tiempoRestanteTxt = ":";
        if (segundos<10) tiempoRestanteTxt += "0";
        tiempoRestanteTxt += segundos;

        temporizadorTxt.setText(tiempoRestanteTxt);
    }
}