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
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_form extends AppCompatActivity {

    EditText txt_firstName, txt_lastName, txt_email, txt_password, txt_collageId,txt_phone;
    Button btn_register;
    FirebaseAuth firebaseAuth;
    private ImageButton back;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String roll="student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        //getSupportActionBar().setTitle("Sign Up");

        txt_firstName = (EditText) findViewById(R.id.txt_firstName);
        txt_lastName = (EditText) findViewById(R.id.txt_lastName);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_collageId = (EditText) findViewById(R.id.txt_collageID);
        btn_register = (Button) findViewById(R.id.btn_register);
        back = (ImageButton) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_form.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        databaseReference = firebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String firstName = txt_firstName.getText().toString().trim();
                 final String phone = txt_phone.getText().toString().trim();
                 final String lastName = txt_lastName.getText().toString().trim();
                 final String email = txt_email.getText().toString().trim();
                 String password = txt_password.getText().toString().trim();
                 final String collageID = txt_collageId.getText().toString().trim();





                if (TextUtils.isEmpty(firstName)) {
                    Toast.makeText(Signup_form.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    Toast.makeText(Signup_form.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup_form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(Signup_form.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(collageID)) {
                    Toast.makeText(Signup_form.this, "Please Enter Collage ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(Signup_form.this, "Please Enter Phone No.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(Signup_form.this, "Password is too Short..", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Student info = new Student(

                                            firstName,
                                            lastName,
                                            collageID,
                                            email,
                                            roll,
                                            phone

                                    );

                                    firebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(Signup_form.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                        }
                                    });


                                } else {
                                    Toast.makeText(Signup_form.this, "Registration Failed, Please Try Again.....", Toast.LENGTH_SHORT).show();


                                }


                            }


                        });

            }
        });
    }
}