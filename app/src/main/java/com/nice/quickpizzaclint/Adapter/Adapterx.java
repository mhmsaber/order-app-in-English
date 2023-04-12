package com.nice.quickpizzaclint.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.nice.quickpizzaclint.Model.Model;
import com.nice.quickpizzaclint.R;
import com.nice.quickpizzaclint.Views.Food;

import java.util.List;

public class Adapterx extends RecyclerView.Adapter<Adapterx.Myvh>{

    Context context;
    List<Model> list;

    public Adapterx(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public Adapterx.Myvh onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new Myvh(view);


    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapterx.Myvh holder, int position) {

        Model model= list.get(position);


        holder.name2.setText(model.getNamemodle());
        Glide.with(context).load(list.get(position).getImagemodel()).into(holder.image2);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myvh extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name2;
        ImageView image2;

        public Myvh(@NonNull @NotNull View itemView) {
            super(itemView);

            name2=itemView.findViewById(R.id.nameitem);

            image2=itemView.findViewById(R.id.imageitem);

            itemView.setOnClickListener(this);


        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {


            //         Intent intent=new Intent(context,Food.class);
//            Bundle bundle2=new Bundle();
//     //       bundle2.putSerializable("nummber",nummber.getText().toString());
//            //          bundle2.putSerializable("nummberr",model.getNumbermodel());
//            bundle2.putSerializable("name cat",name2.getText().toString());
//            intent.putExtras(bundle2);






            int position=getAdapterPosition();
            Model mm=list.get(position);
            //           Toast.makeText(context,mm.getNamemodle(),Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(v.getContext(), Food.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("key",  mm);
            bundle.putSerializable("name",mm.getNamemodle());
            bundle.putSerializable("nummber",mm.getNumbermodel());
            bundle.putSerializable("image",  mm.getImagemodel());


            intent.putExtras(bundle);
            v.getContext().startActivity(intent);






        }

    }



}
