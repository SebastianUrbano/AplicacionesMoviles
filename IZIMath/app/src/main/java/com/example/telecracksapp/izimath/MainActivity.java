package com.example.telecracksapp.izimath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        olvida_tv.findViewById(R.id.olvida_tv);
        registra_tv.findViewById(R.id.registrar_tv);
        usuario_et.findViewById(R.id.user_et);
        password_et.findViewById(R.id.password_et);
        inicia_bt.findViewById(R.id.inicia_bt);
    }
}
