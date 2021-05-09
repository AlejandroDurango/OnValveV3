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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Iniciar_sesion extends AppCompatActivity
{

    private EditText txtCorreoElectronico;
    private EditText txtContrasena;
    RequestQueue requestQueue;
    final String URL = "http://192.168.0.16:3000/usuarios/iniciarSesion";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtContrasena = findViewById(R.id.txtContraseña);
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);
    }

    public void stringRequest(final String correo_electronico, final String contrasena) throws JSONException {
        JSONObject parametros = new JSONObject();
        parametros.put("correo", String.format("%s",correo_electronico));
        parametros.put("contraseña", String.format("%s",contrasena));
        requestQueue.start();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, parametros,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onResponse(JSONObject response) {
                            Toast.makeText(Iniciar_sesion.this, response.toString(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), agregar_valvula.class);
                            startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.getMessage());
                    }
                }
        );
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
            try {
                stringRequest(correoElectronico, contraseña);
            }
            catch (JSONException error){
                Log.i("TAG", "Registrarse: "+ error);
            }
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