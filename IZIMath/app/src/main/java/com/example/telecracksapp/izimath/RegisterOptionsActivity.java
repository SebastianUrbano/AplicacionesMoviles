package com.example.telecracksapp.izimath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telecracksapp.izimath.model.User;

public class RegisterOptionsActivity  extends AppCompatActivity {


    private Button usuario_bt;
    private Button colaborador_bt;
    private Button atras_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_options_activity);
        usuario_bt = findViewById(R.id.userB_bt);
        colaborador_bt = findViewById(R.id.helperB_bt);
        atras_bt = findViewById(R.id.backOption_bt);


        atras_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        usuario_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegisterOptionsActivity.this, RegisterUserActivity.class);
                i.putExtra("userType", User.USER);
                startActivity(i);
                finish();
            }
        });

        colaborador_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterOptionsActivity.this, RegisterHelperActivity.class);
                i.putExtra("userType", User.HELPER);
                startActivity(i);
                finish();
            }
        });


    }


}
