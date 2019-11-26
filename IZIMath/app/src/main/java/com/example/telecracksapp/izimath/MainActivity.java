package com.example.telecracksapp.izimath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView olvida_tv;
    private TextView registra_tv;
    private EditText usuario_et;
    private EditText password_et;
    private Button inicia_bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        olvida_tv = findViewById(R.id.olvida_tv);
        registra_tv = findViewById(R.id.registrar_tv);
        usuario_et = findViewById(R.id.user_et);
        password_et = findViewById(R.id.password_et);
        inicia_bt = findViewById(R.id.inicia_bt);

        inicia_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HACER INICIO AUTENTICANDO----------------------------------------------------------------------------------
                Intent i = new Intent(MainActivity.this, PrincipalActivity.class);
//
                startActivity(i);
            }
        });

        registra_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterOptionsActivity.class);
                startActivity(i);
            }
        });

        //Pendiente hacer activity "OLVIDASTE TU CONTRASENHA"-----------------------------------------------------------------------------


    }
}
