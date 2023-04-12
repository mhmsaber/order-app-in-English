package com.nice.quickpizzaclint.Views.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.nice.quickpizzaclint.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    RecyclerView recyfrag;
    Adapterx adapterx;

    List<Model> listt=new ArrayList<>();

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("quickpizza").child("menu");
    StorageReference refs = (StorageReference) FirebaseStorage.getInstance().getReference("quickpizza");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        recyfrag=root.findViewById(R.id.recyfrag);
        recyfrag.setLayoutManager(new LinearLayoutManager(getContext()));
        recyfrag.setItemAnimator(new DefaultItemAnimator());
        recyfrag.setHasFixedSize(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                adapterx=new Adapterx(getContext(),listt);
                recyfrag.setAdapter(adapterx);

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


















//       final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}