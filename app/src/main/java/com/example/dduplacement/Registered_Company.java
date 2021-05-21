package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Registered_Company extends AppCompatActivity {
    registeredCAdapter adapter;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered__company);
       // getSupportActionBar().setTitle("Registered Company");

        recyclerView = (RecyclerView)findViewById(R.id.registered_company_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_registered_company);
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
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_registered_company:
                        return true;

                    case R.id.menu_logout:
                        firebaseAuth.getInstance().signOut();

                        Intent intent = new Intent(Registered_Company.this, Login_form.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

       /* Query query= FirebaseDatabase.getInstance().getReference().child("Companies")
                .child(String.valueOf(FirebaseDatabase.getInstance().getReference().child("Users")
                       .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Registered Company")));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Registered Company");

        final String[] cid = new String[1];
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 cid[0] = snapshot.getValue().toString();
                Log.d("Dhruvik",snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


       Query query= FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Registered Company");




        FirebaseRecyclerOptions<modal_class_for_registered_company> options =
                new FirebaseRecyclerOptions.Builder<modal_class_for_registered_company>()
                        .setQuery(query, modal_class_for_registered_company.class)
                        .build();

      adapter = new registeredCAdapter(options,getApplicationContext());
      recyclerView.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}