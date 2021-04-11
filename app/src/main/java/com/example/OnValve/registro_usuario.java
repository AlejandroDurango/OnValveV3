package com.example.OnValve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OnValve.Modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.UUID;

public class registro_usuario extends AppCompatActivity
{
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtCiudad;
    private EditText txtCorreoElectronico;
    private EditText txtContraseña;
    private EditText txtRepetirContraseña;
    private  FirebaseAuth Auth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


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
        inicializarFirebase();
        Auth = FirebaseAuth.getInstance();
    }

    private void inicializarFirebase()
    {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
        else
        {
            return false;
        }
    }
    public void Registrarse(View view)
    {
        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String ciudad = txtCiudad.getText().toString();
        String correoElectronico = txtCorreoElectronico.getText().toString();
        String contraseña = txtContraseña.getText().toString();
        String repetirContraseña = txtRepetirContraseña.getText().toString();
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

            Auth.createUserWithEmailAndPassword(correoElectronico, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        final String id = Auth.getCurrentUser().getUid();
                        databaseReference.child("Usuarios").child(id).setValue(NuevoUsuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2)
                            {
                                if(task2.isSuccessful())
                                {
                                    startActivity(new Intent(registro_usuario.this, perfil_usuario.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(registro_usuario.this, "no se registraron los datos correctamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });


            txtNombres.setText("");
            txtApellidos.setText("");
            txtCiudad.setText("");
            txtCorreoElectronico.setText("");
            txtContraseña.setText("");
            txtRepetirContraseña.setText("");

            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void IrAtrasInicio(View view)
    {
        Intent Registro = new Intent(this, MainActivity.class);
        startActivity(Registro);
    }


}