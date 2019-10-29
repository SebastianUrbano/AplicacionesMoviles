package com.example.listaslibreta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearPlaylistActivity extends AppCompatActivity {

    private EditText etNombre, etUsuario, etDescripcion;
    private Button btnCrear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_playlist);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Crear una playlist");

        mToolbar.setNavigationIcon(R.drawable.backarrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        etNombre = findViewById(R.id.et_nombre_playlist);
        etUsuario = findViewById(R.id.et_nombre_usuario);
        etDescripcion = findViewById(R.id.et_descripcion_playlist);
        btnCrear = findViewById(R.id.btn_crear_la_playlist);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //INTENT DEVOLVER DATOS
                verificar();

            }
        });



    }


    private void verificar() {
        String nombre = etNombre.getText().toString().trim();
        String usuario = etUsuario.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();

        if (nombre.isEmpty() || usuario.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Uno de los campos esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }


        //Enviar el CallBack
        Intent intent = new Intent();

        intent.putExtra("nombreLista", etNombre.getText().toString());
        intent.putExtra("nombreUsuario", etUsuario.getText().toString());
        intent.putExtra("descripcion", etDescripcion.getText().toString());

        setResult(RESULT_OK, intent);

        finish();

    }
}
