package com.example.OnValve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.OnValve.Modelo.Usuario;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class registro_usuario extends AppCompatActivity
{
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtCiudad;
    private EditText txtCorreoElectronico;
    private EditText txtContraseña;
    private EditText txtRepetirContraseña;
    private EditText txtDocumentoIndentidad;
    RequestQueue requestQueue;
    final String URL = "http://localhost:3000/usuarios/crearUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);

        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCiudad = findViewById(R.id.txtCiudad);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtContraseña = findViewById(R.id.txtContraseña);
        txtRepetirContraseña = findViewById(R.id.txtRepetirContraseña);
        txtDocumentoIndentidad = findViewById(R.id.txt_documentoIdentidad);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void stringRequest(final String documentoIdentidad, final String nombre, final String apellidos, final String correo, final String contraseña, final String id_ciudad)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(registro_usuario.this,"Response: " + response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error instanceof ServerError){
                            Log.i("TAG", "Server error ");
                        }
                        if(error instanceof NoConnectionError){
                            Log.i("TAG", "No hay conexion a internet");
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("documento_identidad", documentoIdentidad);
                parametros.put("nombre", nombre);
                parametros.put("apellidos", apellidos);
                parametros.put("id_ciudad", id_ciudad);
                parametros.put("correo", correo);
                parametros.put("contraseña", contraseña);
                return parametros;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void validacion()
    {
        if(txtNombres.getText().toString().equals(""))
        {
            txtNombres.setError("Requerido");
        }
        if(txtApellidos.getText().toString().equals(""))
        {
            txtApellidos.setError("Requerido");
        }
        if(txtCiudad.getText().toString().equals(""))
        {
            txtCiudad.setError("Requerido");
        }
        if(txtCorreoElectronico.getText().toString().equals(""))
        {
            txtCorreoElectronico.setError("Requerido");
        }
        if(txtContraseña.getText().toString().equals(""))
        {
            txtContraseña.setError("Requerido");
        }
        if(txtRepetirContraseña.getText().toString().equals(""))
        {
            txtRepetirContraseña.setError("Requerido");
        }
        if(txtDocumentoIndentidad.getText().toString().equals(""))
        {
            txtDocumentoIndentidad.setError("Requerido");
        }
    }

    public boolean DatosVacios()
    {
        if(txtNombres.getText().toString().equals(""))
        {
            return true;
        }
        if(txtApellidos.getText().toString().equals(""))
        {
            return true;
        }
        if(txtCiudad.getText().toString().equals(""))
        {
            return true;
        }
        if(txtCorreoElectronico.getText().toString().equals(""))
        {
            return true;
        }
        if(txtContraseña.getText().toString().equals(""))
        {
            return true;
        }
        if(txtRepetirContraseña.getText().toString().equals(""))
        {
            return true;
        }
        if(txtDocumentoIndentidad.getText().toString().equals(""))
        {
            return true;
        }
        return false;
    }
    public void Registrarse(View view)
    {
        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String ciudad = txtCiudad.getText().toString();
        String correoElectronico = txtCorreoElectronico.getText().toString();
        String contraseña = txtContraseña.getText().toString();
        String repetirContraseña = txtRepetirContraseña.getText().toString();
        String documentoIdentidad = txtDocumentoIndentidad.getText().toString();
        String UserId = UUID.randomUUID().toString();

        if(!contraseña.equals(repetirContraseña))
        {
            txtRepetirContraseña.setError("Las contraseñas no son iguales");
        }
        else if (this.DatosVacios())
        {
            validacion();
        }
        else if(contraseña.length() < 7)
        {
            txtContraseña.setError("La contraseña debe tener mínimo 7 caracteres");
        }
        else
        {
            final Usuario NuevoUsuario = new Usuario(nombres, apellidos, ciudad, correoElectronico, contraseña);

            stringRequest(documentoIdentidad, nombres, apellidos,correoElectronico,contraseña,ciudad);

            txtNombres.setText("");
            txtApellidos.setText("");
            txtCiudad.setText("");
            txtCorreoElectronico.setText("");
            txtContraseña.setText("");
            txtRepetirContraseña.setText("");
            txtDocumentoIndentidad.setText("");

            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void IrAtrasInicio(View view)
    {
        Intent Registro = new Intent(this, MainActivity.class);
        startActivity(Registro);
    }


}