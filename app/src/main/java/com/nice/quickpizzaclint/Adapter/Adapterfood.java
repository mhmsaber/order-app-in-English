package com.nice.quickpizzaclint.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.nice.quickpizzaclint.Model.Modelfood;
import com.nice.quickpizzaclint.R;
import com.nice.quickpizzaclint.Views.Updatafood;

import java.util.List;

public class Adapterfood extends RecyclerView.Adapter<Adapterfood.Myview>{

    List<Modelfood> listfood;
    Context context;

    public Adapterfood(List<Modelfood> listfood, Context context) {
        this.listfood = listfood;
        this.context = context;
    }
    @NonNull
    @NotNull
    @Override
    public Adapterfood.Myview onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemfood,parent,false);
        return  new Myview(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapterfood.Myview holder, int position) {

        Modelfood modelfood= listfood.get(position);
        holder.namefood2.setText(modelfood.getNamefood());
        Glide.with(context).load(modelfood.getImagefood()).into(holder.imagefood2);


    }

    @Override
    public int getItemCount() {
        return listfood.size();
    }

    public class Myview extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView namefood2;
        ImageView imagefood2;

        public Myview(@NonNull @NotNull View itemView) {
            super(itemView);
            namefood2=itemView.findViewById(R.id.itemfoodname);
            imagefood2=itemView.findViewById(R.id.itemfoodimage);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {


            int position=getAdapterPosition();
            Modelfood mm=listfood.get(position);
            //          Toast.makeText(context,mm.getNamefood(),Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(v.getContext(), Updatafood.class);

            Bundle bundle=new Bundle();
            bundle.putSerializable("key",  mm);
            bundle.putSerializable("catname",mm.getSeefood());
            bundle.putSerializable("catnum",mm.getNumcat());
            bundle.putSerializable("idfood",mm.getIdfood());
            bundle.putSerializable("image",mm.getImagefood());
            //product_model.getImageResource()



            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        }

    }
}
