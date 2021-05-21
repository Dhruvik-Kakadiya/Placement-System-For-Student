package com.example.dduplacement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    CompanyAdapter adapter;
    RecyclerView recyclerView;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getSupportActionBar().setTitle("DDUPlacement");


        recyclerView = (RecyclerView)findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.menu_home:
                        return  true;

                    case R.id.menu_profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_registered_company:
                        startActivity(new Intent(getApplicationContext(),Registered_Company.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_logout:
                        firebaseAuth.getInstance().signOut();

                        Intent intent = new Intent(MainActivity.this, Login_form.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


        FirebaseRecyclerOptions<Company_modal_for_home> options =
                new FirebaseRecyclerOptions.Builder<Company_modal_for_home>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Companies"), Company_modal_for_home.class)
                        .build();
        adapter = new CompanyAdapter(options,getApplicationContext());
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