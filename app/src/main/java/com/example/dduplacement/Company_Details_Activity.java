package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company_Details_Activity extends AppCompatActivity {

    private TextView lastDate, comeDate, company_name, type, role, pac, bond, company_name_d, tech, cgpi;
    private Button btn_regi;
    private ImageButton back;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    private double stdCPI,coCPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__details_);
      //  getSupportActionBar().setTitle("Company Details");


        lastDate = (TextView)findViewById(R.id.d_last_date);
        comeDate = (TextView)findViewById(R.id.d_come_date);
        company_name = (TextView)findViewById(R.id.d_company_name);
        company_name_d = (TextView)findViewById(R.id.d_company_name_d);
        type = (TextView)findViewById(R.id.d_type);
        role = (TextView)findViewById(R.id.d_role);
        pac = (TextView)findViewById(R.id.d_package);
        bond = (TextView)findViewById(R.id.d_bond);
        tech = (TextView)findViewById(R.id.d_tech);
        cgpi = (TextView)findViewById(R.id.d_cgpi);
        btn_regi = (Button)findViewById(R.id.btn_regi);
        back = (ImageButton) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        final String CompanyId = getIntent().getStringExtra("CompanyId");

        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               firebaseDatabase.getInstance().getReference("Users")
                       .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists())
                       {

                           String clgIDUser = snapshot.child("collageID").getValue().toString();

                           firebaseDatabase.getInstance().getReference("2021 batch").child(clgIDUser).addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   if(snapshot.exists())
                                   {
                                       String studentCPI = snapshot.child("CPI").getValue().toString();

                                       stdCPI = Double.parseDouble(studentCPI);

                                       firebaseDatabase.getInstance().getReference("Companies").child(CompanyId).addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {

                                               String companyCPI = snapshot.child("cgpi_above").getValue().toString();

                                               coCPI = Double.parseDouble(companyCPI);

                                               if(stdCPI >= coCPI)
                                               {

                                                   firebaseDatabase.getInstance().getReference("Companies")
                                                           .child(CompanyId).addValueEventListener(new ValueEventListener() {
                                                       @Override
                                                       public void onDataChange(@NonNull DataSnapshot snapshot) {


                                                           String name = snapshot.child("name").getValue(String.class);
                                                           String b = snapshot.child("bond").getValue(String.class);
                                                           String cgpi = snapshot.child("cgpi_above").getValue(String.class);
                                                           String come_date = snapshot.child("comeDate").getValue(String.class);
                                                           String co_pac = snapshot.child("company_package").getValue(String.class);
                                                           String last_date = snapshot.child("lastDate").getValue(String.class);
                                                           String role = snapshot.child("role").getValue(String.class);
                                                           String tech = snapshot.child("tech").getValue(String.class);
                                                           String type = snapshot.child("type").getValue(String.class);


                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("name").setValue(name);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("bond").setValue(b);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("cgpi_above").setValue(cgpi);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("comeDate").setValue(come_date);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("company_package").setValue(co_pac);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("lastDate").setValue(last_date);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("role").setValue(role);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("tech").setValue(tech);

                                                           firebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                   .child("Registered Company").child(CompanyId).child("type").setValue(type);


                                                           Toast.makeText(Company_Details_Activity.this, "Company Register Successfully", Toast.LENGTH_SHORT).show();




                                                       }
                                                       @Override
                                                       public void onCancelled(@NonNull DatabaseError error) {
                                                           Toast.makeText(Company_Details_Activity.this, "Company not Registerd", Toast.LENGTH_SHORT).show();
                                                       }
                                                   });


                                                   firebaseDatabase.getInstance().getReference("Users")
                                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                                       @Override
                                                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                           String cid = snapshot.child("collageID").getValue(String.class);
                                                           String email = snapshot.child("email").getValue(String.class);

                                                           firebaseDatabase.getInstance().getReference("Companies").child(CompanyId).child("Registerd Student").child(cid).child("CollageID").setValue(cid);
                                                           firebaseDatabase.getInstance().getReference("Companies").child(CompanyId).child("Registerd Student").child(cid).child("Email").setValue(email);
                                                       }
                                                       @Override
                                                       public void onCancelled(@NonNull DatabaseError error) {
                                                           Toast.makeText(Company_Details_Activity.this, "Company not Registerd", Toast.LENGTH_SHORT).show();
                                                       }
                                                   });

                                               }
                                               else
                                               {
                                                   Toast.makeText(Company_Details_Activity.this, "You Are Not Eligible to Register In This Company", Toast.LENGTH_SHORT).show();

                                               }

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError error) {
                                               Toast.makeText(Company_Details_Activity.this, "Server Error... Please Try Again", Toast.LENGTH_SHORT).show();

                                           }
                                       });

                                   }
                                   else
                                   {
                                       Toast.makeText(Company_Details_Activity.this, "You Are Not This Batch Student", Toast.LENGTH_SHORT).show();

                                   }



                               }

                               @Override
                               public void onCancelled(@NonNull DatabaseError error) {
                                   Toast.makeText(Company_Details_Activity.this, "You Are Not This Batch Student", Toast.LENGTH_SHORT).show();

                               }
                           });

                       }
                       else
                       {
                           Toast.makeText(Company_Details_Activity.this, "Server Error... Please Try Again", Toast.LENGTH_SHORT).show();
                       }


                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       Toast.makeText(Company_Details_Activity.this, "Server Error... Please Try Again", Toast.LENGTH_SHORT).show();

                   }
               });


            }
        });



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
                    company_name_d.setText(companyName);
                    Company_Details_Activity.this.lastDate.setText(lastDate);
                    Company_Details_Activity.this.comeDate.setText(comeDate);
                    type.setText(Type);
                    role.setText(Role);
                    pac.setText(Pac);
                    bond.setText(Bond);
                    tech.setText(Tech);
                    cgpi.setText(cgpi_above);
                }
                else
                {
                    Toast.makeText(Company_Details_Activity.this, "Company Details Not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Company_Details_Activity.this, "Something Went Wrong Server Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}