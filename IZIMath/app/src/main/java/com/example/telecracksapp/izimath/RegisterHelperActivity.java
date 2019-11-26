package com.example.telecracksapp.izimath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telecracksapp.izimath.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegisterHelperActivity extends AppCompatActivity {


    private Button atrasBtn;
    private Button registrarseBtn;
    private EditText userEt;
    private EditText emailEt;
    private EditText passwordEt;
    private EditText confirmPasswordEt;
    private CheckBox checkBoxConditions;
    private Spinner spinner;

    FirebaseAuth auth;
    FirebaseDatabase db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_helper_activity);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        checkBoxConditions = findViewById(R.id.checkBoxHR_cbx);
        //dictamina si se estan aceptando las condiciones
        boolean chuleado = checkBoxConditions.isChecked();

        userEt = findViewById(R.id.userHR_et);
        emailEt = findViewById(R.id.emailHR_et);
        passwordEt = findViewById(R.id.passHR_et);
        confirmPasswordEt = findViewById(R.id.repassHR_et);

        spinner = findViewById(R.id.spinnerHR_sp);

        String[] especialidades = {"Álgebra", "Cálculo", "Cálculo Multivariable", "Estadística", "Ecuaciones Diferenciales", "Geometría"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, especialidades);
        spinner.setAdapter(adapter);


        atrasBtn = findViewById(R.id.backHR_btn);
        registrarseBtn = findViewById(R.id.registerHR_btn);

        //FUNCIONES

        registrarseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //REGISTRARSE terminar llevando datos----------------------------------------------------------------------------------

                if (emailEt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterHelperActivity.this, "El campo de email esta vacio", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!passwordEt.getText().toString()
                        .equals(confirmPasswordEt.getText().toString())) {
                    Toast.makeText(RegisterHelperActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }

                if (passwordEt.getText().toString().trim().length() < 10) {
                    Toast.makeText(RegisterHelperActivity.this, "Las contraseñas debe tener mínimo 10 carácteres", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(
                        emailEt.getText().toString().trim(),
                        passwordEt.getText().toString().trim()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                //Ya se está logeado

                               String especialidad = spinner.getSelectedItem().toString();



                               String userType = "" + getIntent().getExtras().getSerializable("userType");;

                                User user = new User(
                                        auth.getCurrentUser().getUid(),
                                        userEt.getText().toString(),
                                        emailEt.getText().toString(),
                                       userType,
                                        passwordEt.getText().toString(),
                                        especialidad
                                );

                                db.getReference().child("users").child(user.getUid())
                                        .setValue(user);

                               // Friend friend = new Friend(

                                      //  user.getUid(),
                                   //     user.getName(),
                                  //      user.getUsername(),
                                     //   user.getPhone(),
                                    //    user.getEmail()

                            //    );
                              //  db.getReference().child("friends")
                                      //  .child(friend.getUid())
                                      //  .setValue(friend);


                                Intent i = new Intent(RegisterHelperActivity.this, PrincipalActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(RegisterHelperActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


              //  Intent i = new Intent(RegisterHelperActivity.this, PrincipalActivity.class);
             //   startActivity(i);
            }
        });

        atrasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
