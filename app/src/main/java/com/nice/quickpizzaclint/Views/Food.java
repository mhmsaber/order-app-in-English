package com.nice.quickpizzaclint.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nice.quickpizzaclint.Adapter.Adapterfood;
import com.nice.quickpizzaclint.Model.Modelfood;
import com.nice.quickpizzaclint.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Food extends AppCompatActivity {


    TextView namecatf;
    RecyclerView recyfood;

    Bundle bundle5;
    List<Modelfood> listfood=new ArrayList<>();
    Adapterfood adapterfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);



        namecatf =findViewById(R.id.namecatf);
        recyfood=findViewById(R.id.recyfood);


        Intent intent5=this.getIntent();
        bundle5=intent5.getExtras();

        //      Toast.makeText(this, bundle5.getString("nummber"), Toast.LENGTH_SHORT).show();


        //      namecatf.setText(bundle5.getString("name cat"));








        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("quickpizza").child("menu")
                .child(bundle5.getString("nummber")).child("xx");


        //      Toast.makeText(Food.this, "my cat unmmber   " + bundle5.getString("nummber"), Toast.LENGTH_SHORT).show();
















        adapterfood=new Adapterfood(listfood, getApplicationContext());
        recyfood.setHasFixedSize(true);
        recyfood.setLayoutManager(new LinearLayoutManager(this));
        recyfood.setItemAnimator(new DefaultItemAnimator());
        recyfood.setAdapter(adapterfood);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()) {
                    Modelfood modfood = snap.getValue(Modelfood.class);
                    listfood.add(modfood);
                }
                adapterfood.notifyItemInserted(listfood.size() - 1);
                adapterfood.getItemCount();


                adapterfood.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
































    }
}