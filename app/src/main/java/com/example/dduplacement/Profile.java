package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    TextView p_name, p_email, p_fname, p_lname, p_collageid, p_phone;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       // getSupportActionBar().setTitle("Profile");

        p_name = (TextView)findViewById(R.id.txt_p_name);
        p_email = (TextView)findViewById(R.id.txt_p_email);
        p_fname = (TextView)findViewById(R.id.txt_p_fname);
        p_lname = (TextView)findViewById(R.id.txt_p_lname);
        p_collageid = (TextView)findViewById(R.id.txt_p_collageid);
        p_phone = (TextView)findViewById(R.id.txt_p_phone);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.menu_profile:

                        return true;

                    case R.id.menu_registered_company:
                        startActivity(new Intent(getApplicationContext(),Registered_Company.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_logout:
                        firebaseAuth.getInstance().signOut();

                        Intent intent = new Intent(Profile.this, Login_form.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = firebaseUser.getUid();


        firebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String fname = snapshot.child("firstName").getValue(String.class);
                String lname = snapshot.child("lastName").getValue(String.class);
                String email = snapshot.child("email").getValue(String.class);
                String phone  = snapshot.child("phone").getValue(String.class);
                String cid  = snapshot.child("collageID").getValue(String.class);


                p_name.setText(fname+" "+lname);
                p_fname.setText(fname);
                p_lname.setText(lname);
                p_email.setText(email);
                p_collageid.setText(cid);
                p_phone.setText(phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Profile.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();

            }
        });

    }


}