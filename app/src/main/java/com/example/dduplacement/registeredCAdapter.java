package com.example.dduplacement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class registeredCAdapter extends FirebaseRecyclerAdapter<modal_class_for_registered_company,registeredCAdapter.myviewholder> {

    Context context;
    public registeredCAdapter(@NonNull FirebaseRecyclerOptions<modal_class_for_registered_company> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, final int position, @NonNull modal_class_for_registered_company model) {
        holder.tname.setText(model.getName());
        holder.trole.setText(model.getRole());
        holder.ttech.setText(model.getTech());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Registered_company_Details.class);
                intent.putExtra("Company",getRef(position).getKey());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_registered_company,parent,false);
        return new myviewholder(view);
    }



    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView tname,trole,ttech;
        View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tname = (TextView)itemView.findViewById(R.id.company_name_Rcard);
            trole = (TextView)itemView.findViewById(R.id.role_Rcard);
            ttech = (TextView)itemView.findViewById(R.id.tech_Rcard);
            v=itemView;


        }
    }

}
