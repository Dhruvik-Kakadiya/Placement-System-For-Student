package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registered_company_Details extends AppCompatActivity {


    private TextView lastDate, comeDate, company_name, type, role, pac, bond, company_name_r, tech, cgpi;
    private ImageButton back;


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_company__details);

        lastDate = (TextView)findViewById(R.id.r_last_date);
        comeDate = (TextView)findViewById(R.id.r_come_date);
        company_name = (TextView)findViewById(R.id.r_company_name);
        company_name_r = (TextView)findViewById(R.id.r_company_name_r);
        type = (TextView)findViewById(R.id.r_type);
        role = (TextView)findViewById(R.id.r_role);
        pac = (TextView)findViewById(R.id.r_package);
        bond = (TextView)findViewById(R.id.r_bond);
        tech = (TextView)findViewById(R.id.r_tech);
        cgpi = (TextView)findViewById(R.id.r_cgpi);
        back = (ImageButton) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registered_Company.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        final String CompanyId = getIntent().getStringExtra("Company");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Companies");
        databaseReference.child(CompanyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String companyName = snapshot.child("name").getValue().toString();
                    String lastDate = snapshot.child("lastDate").getValue().toString();
                    String comeDate = snapshot.child("comeDate").getValue().toString();
                    String Type = snapshot.child("type").getValue().toString();
                    String Role = snapshot.child("role").getValue().toString();
                    String Pac = snapshot.child("company_package").getValue().toString();
                    String Bond = snapshot.child("bond").getValue().toString();
                    String Tech = snapshot.child("tech").getValue().toString();
                    String cgpi_above = snapshot.child("cgpi_above").getValue().toString();

                    company_name.setText(companyName);
                    company_name_r.setText(companyName);
                    Registered_company_Details.this.lastDate.setText(lastDate);
                    Registered_company_Details.this.comeDate.setText(comeDate);
                    type.setText(Type);
                    role.setText(Role);
                    pac.setText(Pac);
                    bond.setText(Bond);
                    tech.setText(Tech);
                    cgpi.setText(cgpi_above);

                }
                else
                {
                    Toast.makeText(Registered_company_Details.this, "Company Details Not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Registered_company_Details.this, "Something Went Wrong Server Error", Toast.LENGTH_SHORT).show();

            }
        });




    }
}