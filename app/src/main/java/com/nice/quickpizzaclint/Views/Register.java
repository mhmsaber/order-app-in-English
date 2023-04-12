package com.nice.quickpizzaclint.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nice.quickpizzaclint.Model.ModelReg;
import com.nice.quickpizzaclint.R;

import org.jetbrains.annotations.NotNull;

public class Register extends AppCompatActivity {

    EditText phone, name, password,password2;
    Button save;

    ModelReg modelReg;


    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("quickpizza").child("Users");
    StorageReference refs= FirebaseStorage.getInstance().getReference("quickpizza").child("Users");


//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);




        phone=findViewById(R.id.phone);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        password2=findViewById(R.id.password2);
        save = findViewById(R.id.save);



        if(!checkinternet(this)){

            Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show();

            // if i want app not connect without net i acticeted return :D
            //   return;

        }





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkfunction();

                         writedata();
            }
        });


    }

    public void writedata() {



        modelReg = new ModelReg(phone.getText().toString(), password.getText().toString(),
                name.getText().toString());


        ref.setValue(modelReg);

    }



    public void checkfunction(){


        if(phone.getText().toString().isEmpty()){
            Toast.makeText(this, "please inter your phone ", Toast.LENGTH_SHORT).show();

            return;
        }
        if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "please inter your password ", Toast.LENGTH_SHORT).show();

            return;
        }


        //  if i want password not less than 6 charachter
//        if(password.getText().toString().length() < 6 ){
//            Toast.makeText(this, " password اقل من ستة ارقام", Toast.LENGTH_SHORT).show();
//
//            return;
//        }



        if(name.getText().toString().isEmpty()){
            Toast.makeText(this, "please inter name", Toast.LENGTH_SHORT).show();

            return;
        }





        //  checking the two password are the same :D
        if(!password.getText().toString().equals(password2.getText().toString())){
            Toast.makeText(Register.this, "PASSWORD NOT MATCHING",Toast.LENGTH_SHORT).show();
            return;
        }




        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String ph = phone.getText().toString();

                if (snapshot.hasChild(ph)) {
                    Toast.makeText(Register.this, "this nummber aready Register"
                            , Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    modelReg = new ModelReg(phone.getText().toString(), password.getText().toString(),
                            name.getText().toString());
                    ref.child(ph).setValue(modelReg);
                    Toast.makeText(Register.this, "Good register", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }





            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {


            }
        });

    }


//    public void Noti(){
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
//                Iterable<DataSnapshot> children=datasnapshot.getChildren();
//                for(DataSnapshot child: children) {
//                    Request request = child.getValue(Request.class);
//                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//                        NotificationChannel channel=new NotificationChannel("My noti","my secand ", NotificationManager.IMPORTANCE_DEFAULT);
//                        NotificationManager manager=getSystemService(NotificationManager.class);
//                        manager.createNotificationChannel(channel);
//                    }
//                    NotificationCompat.Builder
//                            builder=new NotificationCompat.Builder(getApplicationContext(),"My noti");
//                    Intent intent=new Intent(getApplicationContext(),Register.class);
//                    PendingIntent pendingIntent=PendingIntent.getActivities(getApplicationContext(),1
//                            , new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
//                    builder.setContentTitle("حالة الطلب");
//                    builder.setContentText( request.getStatusreq());
//                    builder.setSmallIcon(R.drawable.mass);
//                    builder.setAutoCancel(true);
//                    builder.setContentIntent(pendingIntent);
//                    NotificationManagerCompat managerCompat=NotificationManagerCompat.from(Register.this);
//                    managerCompat.notify(1,builder.build());
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//            }
//        });
//    }


    public  static boolean checkinternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;



    }


}