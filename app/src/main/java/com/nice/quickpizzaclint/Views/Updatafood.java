package com.nice.quickpizzaclint.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nice.quickpizzaclint.Adapter.Sqliteadapterr;
import com.nice.quickpizzaclint.Model.Modelfood;
import com.nice.quickpizzaclint.R;

import java.util.ArrayList;
import java.util.List;

public class Updatafood extends AppCompatActivity {

    TextView namefoodup,largeup,mediumup,smalleup,count1,count2,count3,totalprice;
    ImageView add1,remov1,add2,remov2,add3,remov3,foodimag,imagesend;
    Button send;

    int  c1, c2, c3,tot,tot11,tot22,tot33;

    Sqliteadapterr sqliteadapterr=new Sqliteadapterr(this);
    List<Modelfood> listt=new ArrayList<>();








    //  DatabaseReference ref=  FirebaseDatabase.getInstance().getReference("query4").child("menu").child("xx");
    StorageReference refs =  FirebaseStorage.getInstance().getReference("quickpizza").child("xx");

    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatafood);





        if(!Register.checkinternet(this)){

            Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show();

            // if i want app not connect without net i acticeted return :D
            //        return;


        }





        sqliteadapterr=new Sqliteadapterr(this);



        namefoodup=findViewById(R.id.namefoodup);
        largeup=findViewById(R.id.largeup);
        mediumup=findViewById(R.id.mediumup);
        smalleup=findViewById(R.id.smalleup);

        add1=findViewById(R.id.add1);
        add2=findViewById(R.id.add2);
        add3=findViewById(R.id.add3);
        remov1=findViewById(R.id.remov1);
        remov2=findViewById(R.id.remov2);
        remov3=findViewById(R.id.remov3);
        count1=findViewById(R.id.cont1);
        count2=findViewById(R.id.cont2);
        count3=findViewById(R.id.cont3);
        totalprice=findViewById(R.id.totalprice);
        send =findViewById(R.id.send);
        foodimag=findViewById(R.id.foodimag);









        Intent intent=this.getIntent();
        bundle=intent.getExtras();
        Modelfood modelfood= (Modelfood) bundle.getSerializable("key");

        // to  write data in updatefood Activity  :D



        namefoodup.setText(modelfood.getNamefood());
        largeup.setText("كبير    " +modelfood.getLarge());
        mediumup.setText("وسط    " +modelfood.getMedium());
        smalleup.setText("صغير    " +modelfood.getSmalle());


        Glide.with(getBaseContext()).load(modelfood.getImagefood()).into(foodimag);


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c1 <10)
                {
                    c1++;
                    count1.setText(String.valueOf(c1));
                }

            }
        });
        remov1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c1 >0.5)
                {
                    c1--;
                    count1.setText(String.valueOf(c1));
                }

            }
        });





        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c2 <10)
                {
                    c2++;
                    count2.setText(String.valueOf(c2));
                }

            }
        });
        remov2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c2 >0.5)
                {
                    c2--;
                    count2.setText(String.valueOf(c2));
                }

            }
        });



        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c3 <10)
                {
                    c3++;
                    count3.setText(String.valueOf(c3));
                }

            }
        });
        remov3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c3 >0)
                {
                    c3--;
                    count3.setText(String.valueOf(c3));



                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                tot11 = c1 * Integer.parseInt((modelfood.getLarge()));

                tot22 = c2 * Integer.parseInt((modelfood.getMedium()));

                tot33 = c3 * Integer.parseInt((modelfood.getSmalle()));


                if(c1+c2+c3==0){
                    Toast.makeText(Updatafood.this, "لاتوجد مشتريات", Toast.LENGTH_SHORT).show();
                    return;
                }



                tot = tot11 + tot22 + tot33;
                totalprice.setText(" المجموع "+String.valueOf( tot )+ " جنية");






                String c11=String.valueOf(c1);
                String c22=String.valueOf(c2);
                String c33=String.valueOf(c3);
                String tott = String.valueOf(tot);


                Toast.makeText(Updatafood.this, modelfood.getNamefood() + "  تم اضافتها ", Toast.LENGTH_SHORT).show();


                sqliteadapterr.insertdata3(modelfood.getNamefood(), tott,tott, c11
                        ,c22,c33);






            }


        });









    }
}