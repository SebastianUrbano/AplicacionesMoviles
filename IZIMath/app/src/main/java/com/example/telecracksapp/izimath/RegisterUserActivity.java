package com.example.telecracksapp.izimath;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterUserActivity extends AppCompatActivity {

    private Button atrasBtn;
    private Button registrarseBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user_activity);

        atrasBtn = findViewById(R.id.backUR_btn);
        registrarseBtn = findViewById(R.id.registerUR_btn);

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

                Intent i = new Intent(RegisterUserActivity.this, PrincipalActivity.class);
                startActivity(i);
            }
        });


    }
}
