package com.nice.quickpizzaclint.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nice.quickpizzaclint.Adapter.Adapterx;
import com.nice.quickpizzaclint.Model.Model;
import com.nice.quickpizzaclint.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {
    RecyclerView recy1;
    Adapterx adapterx;

    List<Model> listt=new ArrayList<>();

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("quickpizza").child("menu");
    StorageReference refs = (StorageReference) FirebaseStorage.getInstance().getReference("quickpizza");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);



        recy1=findViewById(R.id.recy1);








        recy1.setLayoutManager(new LinearLayoutManager(this));
        recy1.setItemAnimator(new DefaultItemAnimator());

        recy1.setHasFixedSize(true);

//        adapter=new Adapter(getApplicationContext(),listt);
//        recy1.setLayoutManager(new LinearLayoutManager(this));
//        recy1.setItemAnimator(new DefaultItemAnimator());
//        recy1.setAdapter(adapter);
//        recy1.setHasFixedSize(true);






        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                adapterx=new Adapterx(getApplicationContext(),listt);
                recy1.setAdapter(adapterx);

                for(DataSnapshot snap: snapshot.getChildren()) {
                    Model mod = snap.getValue(Model.class);
                    listt.add(mod);
                }
                adapterx.notifyItemInserted(listt.size() - 1);
                adapterx.getItemCount();
                adapterx.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });




    }
}