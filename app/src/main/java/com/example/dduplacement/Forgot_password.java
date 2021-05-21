package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {

    private EditText forgot_pass_email;
    private Button reset_pass;
    private ImageButton back;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //getSupportActionBar().setTitle("RESET PASSWORD");

        forgot_pass_email = (EditText)findViewById(R.id.forgot_pass_email);
        reset_pass= (Button)findViewById(R.id.btn_reset_pass);
        back = (ImageButton)findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_form.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        firebaseAuth= FirebaseAuth.getInstance();

        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();

            }
        });
    }
    private void resetPassword()
    {
        String email = forgot_pass_email.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Forgot_password.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(Forgot_password.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(Forgot_password.this, "Check Your Email to Reset Your Password", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Forgot_password.this, "Try Again Something Wrong Happened", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}