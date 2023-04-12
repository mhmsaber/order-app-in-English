package com.nice.quickpizzaclint.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nice.quickpizzaclint.Adapter.Adapterrcart;
import com.nice.quickpizzaclint.Adapter.Sqliteadapterr;
import com.nice.quickpizzaclint.Model.Modelfood;
import com.nice.quickpizzaclint.Model.Request;
import com.nice.quickpizzaclint.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {


    RecyclerView recycart;
    Button send;
    TextView totprice;

    List<Modelfood> listcart =new ArrayList<>();
    Sqliteadapterr sqliteadapterr;
    Adapterrcart adapterrs;


    // to see data from query4 not query1
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("quickpizza");
    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("quickpizza");

    DatabaseReference reff = FirebaseDatabase.getInstance().getReference("quickpizza").
            child("Users").child("Notificat");



    int totall = 0;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);



        recycart=findViewById(R.id.recycart);
        send=findViewById(R.id.send);
        totprice=findViewById(R.id.totpricce);


        if(!Register.checkinternet(this)){

            Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show();


            //        return;


        }




        listcart =new Sqliteadapterr(this).getAllData();
        sqliteadapterr = new Sqliteadapterr(this);
        adapterrs = new Adapterrcart(this, sqliteadapterr.getAllData(), sqliteadapterr);
        recycart.setLayoutManager(new LinearLayoutManager(this));
        recycart.setItemAnimator(new DefaultItemAnimator());
        recycart.setHasFixedSize(true);
        recycart.setAdapter(adapterrs);





        for(Modelfood modelfood: listcart) {
            totall += Integer.valueOf(modelfood.getTotsql());

            Locale locale = new Locale("en", "eg");
            NumberFormat ftm = NumberFormat.getCurrencyInstance(locale);
            totprice.setText(ftm.format( totall));

        }




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog;
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(Cart.this);
                alertdialog.setTitle("            ادخل بياناتك");
                View view=getLayoutInflater().inflate(R.layout.itemdialog,null);
                alertdialog.setIcon(R.drawable.cart2);

                EditText namedialog2,telephone2, editaddress;
                Button senddailog;
                namedialog2=view.findViewById(R.id.namedialog);
                telephone2=view.findViewById(R.id.telphonedialog);
                editaddress=view.findViewById(R.id.addressdialog);
                senddailog=view.findViewById(R.id.senddialog);
                alertdialog.setView(view);


                senddailog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        String m=telephone2.getText().toString();
                        String adressx=editaddress.getText().toString();
                        String namex=namedialog2.getText().toString();
                        Calendar calendar=Calendar.getInstance();
                        //     SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy  hh:mm:ss");
                        SimpleDateFormat sdf=new SimpleDateFormat(" hh:mm dd MM ");
                        String time=sdf.format(calendar.getTime());
                        String status=" order";


//                        String namereq, String totalreq, String address, String timereq,
//                                String statusreq, String telephonereq, List<Modelfood> listreq
//


                        Request request = new Request(namex, String.valueOf(totall),adressx,time,status,
                                m,listcart);
                        //send data to Firebase by milisecand system
                        //ref.child("Orders").child(String.valueOf(System.currentTimeMillis())).setValue(request);
                        ref.child("Orders").child(m).setValue(request);






                        finish();
                    }
                });



                // this code for show dialog
                dialog=alertdialog.create();
                dialog.setIcon(R.drawable.cart_24);
                dialog.show();

            }
        });






//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alertdialog = new AlertDialog.Builder(Cart.this);
//                alertdialog.setTitle("            ادخل بياناتك");
// //               alertdialog.setMessage("Enter your address :");
//
//                EditText editaddress = new EditText(Cart.this);
//                LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.MATCH_PARENT);
//                editaddress.setLayoutParams(ip);
//                alertdialog.setView(editaddress);
//                alertdialog.setIcon(R.drawable.cart2);
//
////
////
////                alertdialog.setView(view);
//
//
//
//
//
//
//
//
//  //              this code for massege for yes and no
//                alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String  m = editaddress.getText().toString();
//                        // if i want more data in list i put it from Sqliteadapterr
//                        // class in part getAllData()
//
///////////
//                        Calendar calendar=Calendar.getInstance();
//                        //     SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy  hh:mm:ss");
//                        SimpleDateFormat sdf=new SimpleDateFormat(" hh:mm dd MM ");
//                        String time=sdf.format(calendar.getTime());
//                        String status=" order";
//
//
//
////                        Toast.makeText(Cart.this, "time now "+ time, Toast.LENGTH_SHORT).show();
//                        Request request = new Request(m, String.valueOf(totall), listcart, time,status);
//
//
//
//
// //                        Toast.makeText(Cart.this, "thanks for help you", Toast.LENGTH_SHORT).show();                        //send data to Firebase by milisecand system
//                        //ref.child("Orders").child(String.valueOf(System.currentTimeMillis())).setValue(request);
//                        ref.child("Orders").child(m).setValue(request);
//                         ref2.child("Users").child("Notificat").child(m).setValue(request).addOnSuccessListener
//                                 (new OnSuccessListener<Void>() {
//                             @Override
//                             public void onSuccess(Void unused) {
//                                 Toast.makeText(Cart.this, "طلبك قيد التنفيذ" +
//                                         "", Toast.LENGTH_SHORT).show();
//
//                             }
//                         }).addOnFailureListener(new OnFailureListener() {
//                             @Override
//                             public void onFailure(@NonNull @NotNull Exception e) {
//
//                                 Toast.makeText(Cart.this, "برجاء اعادة الطلب ", Toast.LENGTH_SHORT).show();
//                             }
//                         });
//
//
//
//                        //delete Cartdata :D
//                        //                       sqliteadapterr.deletealldata();
//                        //                       finish();
//                        // to refrash totalizer
//                        Intent intent = new Intent(Cart.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//
//                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//
//                        dialogInterface.dismiss();
//                    }
//                });
//                alertdialog.show();
//
//
//            }
//
//        });
//
//
//
//
//
//
//
//
//
//
////        reff.child(m).addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
////                Iterable<DataSnapshot> children=datasnapshot.getChildren();
////                for(DataSnapshot child: children) {
////                    Request request = child.getValue(Request.class);
////                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
////                        NotificationChannel channel=new NotificationChannel("My noti","my secand ", NotificationManager.IMPORTANCE_DEFAULT);
////                        NotificationManager manager=getSystemService(NotificationManager.class);
////                        manager.createNotificationChannel(channel);
////                    }
////                    NotificationCompat.Builder
////                            builder=new NotificationCompat.Builder(getApplicationContext(),"My noti");
////                    Intent intent=new Intent(getApplicationContext(),Register.class);
////                    PendingIntent pendingIntent=PendingIntent.getActivities(getApplicationContext(),1
////                            , new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
////                    builder.setContentTitle("حالة الطلب");
////                    builder.setContentText( request.getStatusreq());
////                    builder.setSmallIcon(R.drawable.mass);
////                    builder.setAutoCancel(true);
////                    builder.setContentIntent(pendingIntent);
////                    NotificationManagerCompat managerCompat=NotificationManagerCompat.from(Cart.this);
////                    managerCompat.notify(1,builder.build());
////                }
////            }
////            @Override
////            public void onCancelled(@NonNull @NotNull DatabaseError error) {
////            }
////        });
//








    }
}