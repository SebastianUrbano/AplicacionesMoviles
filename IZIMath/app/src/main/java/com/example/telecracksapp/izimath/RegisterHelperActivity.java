package com.example.telecracksapp.izimath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterHelperActivity extends AppCompatActivity {


    private Button atrasBtn;
    private Button registrarseBtn;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        atrasBtn = findViewById(R.id.backHR_btn);
        registrarseBtn = findViewById(R.id.registerHR_btn);

        atrasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registrarseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //REGISTRARSE terminar llevando datos----------------------------------------------------------------------------------

                Intent i = new Intent(RegisterHelperActivity.this, PrincipalActivity.class);
                startActivity(i);
            }
        });


    }
}
