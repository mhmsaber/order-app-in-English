package com.nice.quickpizzaclint.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.nice.quickpizzaclint.R;

import org.jetbrains.annotations.NotNull;

public class Signin extends AppCompatActivity {


    EditText passwordsin,phonesin;
    Button sigin;

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("quickpizza").child("Users");
    StorageReference refs= FirebaseStorage.getInstance().getReference("quickpizza");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);




        phonesin=findViewById(R.id.phonesin);
        passwordsin=findViewById(R.id.passwordsin);
        sigin=findViewById(R.id.signin);



        if(!Register.checkinternet(this)){

            Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show();
            // if i want app not connect without net i acticeted return :D
            //      return;


        }

//
//        Intent intent=new Intent(Signin.this,Catogrey.class);
//        startActivity(intent);
//
//




        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(phonesin.getText().toString().isEmpty() ) {

                    Toast.makeText(Signin.this, "please inter your phone ", Toast.LENGTH_SHORT).show();
                    return;

                }



                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        // to chick if the phone number is exists

                        if(snapshot.child(phonesin.getText().toString()).exists()){

                            //Toast.makeText(Signin.this,"Good man",Toast.LENGTH_SHORT).show();





                            String ps1=passwordsin.getText().toString();
                            String ps2=snapshot.child(phonesin.getText().toString()).
                                    child("password").getValue(String.class);

                            if(ps1.equals(ps2)){
                                //                               Toast.makeText(Signin.this,"GOOD PASSWORD",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Signin.this,Catogrey.class);
                                startActivity(intent);





                            }else{
                                Toast.makeText(Signin.this,"RONG PASSWORD",Toast.LENGTH_SHORT).show();
                            }
                        }else{

                            Toast.makeText(Signin.this,"Please  register ",Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });



            }
        });





    }
}