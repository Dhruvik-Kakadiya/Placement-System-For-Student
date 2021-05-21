package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_form extends AppCompatActivity {

    TextView txt_Signin_link,txt_Forgot_pass_link;
    EditText txt_email,txt_password;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
     //   getSupportActionBar().setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            startActivity(new Intent(Login_form.this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }



        txt_Signin_link=(TextView)findViewById(R.id.txt_link);
        txt_Signin_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_form.this,Signup_form.class);
                startActivity(in);
            }
        });


        txt_Forgot_pass_link=(TextView)findViewById(R.id.forgot_pass_link);
        txt_Forgot_pass_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_form.this,Forgot_password.class);
                startActivity(in);
            }
        });






        txt_email = (EditText) findViewById(R.id.email);
        txt_password = (EditText) findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txt_email.getText().toString().trim();
                String password = txt_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(Login_form.this, "Password is too Short..", Toast.LENGTH_SHORT).show();

                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Login_form.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                } else {

                                    Toast.makeText(Login_form.this, "Wrong Username and Password", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });






            }

        });
    }
}