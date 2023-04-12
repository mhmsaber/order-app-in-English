package com.nice.quickpizzaclint.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nice.quickpizzaclint.Model.Modelfood;
import com.nice.quickpizzaclint.R;
import com.nice.quickpizzaclint.Views.Cart;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapterrcart   extends RecyclerView.Adapter<Adapterrcart.Viewcart> {

    Context context;

    List<Modelfood> list;
    Sqliteadapterr sqliteadapterr;
    Cart cart;

    public Adapterrcart(Context context, List<Modelfood> list, Sqliteadapterr sqliteadapterr) {
        this.context = context;
        this.list = list;
        this.sqliteadapterr = sqliteadapterr;
    }

    @NonNull
    @NotNull
    @Override
    public Adapterrcart.Viewcart onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcart,parent,false);



        return new Viewcart(view);


    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapterrcart.Viewcart holder, int position) {


        Modelfood modelfood= list.get(position);
        holder.name22.setText(modelfood.getNamefood());
        holder.price22.setText(modelfood.getTotsql());


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delet" );
                builder.setMessage("Are you sure you want delet " +modelfood.getNamefood() + "  ? ");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        sqliteadapterr.deletedata(modelfood);
                        list.remove(position);
                        Adapterrcart.this.notifyDataSetChanged();
                        Intent intent=new Intent(context,Cart.class);
                        context.startActivity(intent);





                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                builder.create().show();

            }
        });









    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewcart extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView name22,price22;

        ImageView deleteBtn;


        public Viewcart(@NonNull @NotNull View itemView) {
            super(itemView);

            name22=itemView.findViewById(R.id.itemcartname);
            price22=itemView.findViewById(R.id.itemcartprice);
            deleteBtn=itemView.findViewById(R.id.itemcartdelet);



            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }


    }
}
