package com.example.OnValve;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Iniciar_sesion extends AppCompatActivity
{

    private EditText txtCorreoElectronico;
    private EditText txtContrasena;
    RequestQueue requestQueue;
    final String URL = "http://localhost:3000/usuarios/iniciarSesion";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtContrasena = findViewById(R.id.txtContraseña);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void stringRequest(final String correo_electronico, final String contrasena)
    {
        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()) {
                            Intent intent = new Intent(getApplicationContext(), perfil_usuario.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Iniciar_sesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error instanceof ServerError){
                            Log.i("TAG", "Server error ");
                        }
                        if(error instanceof NoConnectionError){
                            Log.i("TAG", "No hay conexion a internet");
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", correo_electronico);
                parametros.put("contraseña", contrasena);
                return parametros;
            }
        };

        this.requestQueue.add(request);
    }

    public void validacion()
    {
        if(txtCorreoElectronico.getText().toString().isEmpty())
        {
            txtCorreoElectronico.setError("Requerido");
        }
        if(txtContrasena.getText().toString().isEmpty())
        {
            txtContrasena.setError("Requerido");
        }
    }


    public boolean DatosVacios()
    {
        if(txtCorreoElectronico.getText().toString().isEmpty())
        {
            return true;
        }
        if(txtContrasena.getText().toString().isEmpty())
        {
            return true;
        }
        return false;
    }


    @SuppressLint("ShowToast")
    public void IniciarSesion(View view)
    {
        String correoElectronico = txtCorreoElectronico.getText().toString();
        String contraseña = txtContrasena.getText().toString();

        if(DatosVacios())
        {
            validacion();
        }
        else
        {
            Toast.makeText(Iniciar_sesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
            stringRequest(correoElectronico, contraseña);
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