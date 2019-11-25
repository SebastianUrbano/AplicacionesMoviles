package com.example.telecracksapp.izimath;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterOptionsActivity  extends AppCompatActivity {


    private Button usuario_bt;
    private Button colaborador_bt;
    private Button atras_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_options_activity);
        usuario_bt.findViewById(R.id.userB_bt);
        colaborador_bt.findViewById(R.id.helperB_bt);
        atras_bt.findViewById(R.id.BackOption_bt);
    }


}
