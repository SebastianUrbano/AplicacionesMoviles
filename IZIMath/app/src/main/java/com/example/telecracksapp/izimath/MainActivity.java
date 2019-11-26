package com.example.telecracksapp.izimath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView olvida_tv;
    private TextView registra_tv;
    private EditText usuario_et;
    private EditText password_et;
    private Button inicia_bt;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        olvida_tv = findViewById(R.id.olvida_tv);
        registra_tv = findViewById(R.id.registrar_tv);
        usuario_et = findViewById(R.id.user_et);
        password_et = findViewById(R.id.password_et);
        inicia_bt = findViewById(R.id.inicia_bt);

        inicia_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HACER INICIO AUTENTICANDO----------------------------------------------------------------------------------

                auth.signInWithEmailAndPassword(
                        usuario_et.getText().toString().trim(),
                        password_et.getText().toString().trim()
                ).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                );

            }
        });

        registra_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterOptionsActivity.class);
                startActivity(i);
            }
        });

        olvida_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "HACER 'olvidaste tu contraseña?", Toast.LENGTH_SHORT).show();
                //Pendiente hacer activity "OLVIDASTE TU CONTRASENHA"-----------------------------------------------------------------------------

            }
        });


    }
}
