package com.nice.quickpizzaclint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nice.quickpizzaclint.Model.ModelReg;
import com.nice.quickpizzaclint.Views.Catogrey;
import com.nice.quickpizzaclint.Views.Register;
import com.nice.quickpizzaclint.Views.Signin;

public class MainActivity extends AppCompatActivity {

    Button signin,register;
    ImageView googlebtn2;
    LinearLayout googlebtn;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        signin=findViewById(R.id.signin);
        register=findViewById(R.id.register);

        googlebtn=findViewById(R.id.googlebtn);




        if(!Register.checkinternet(this)){

            Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show();
            // if i want app not connect without net i acticeted return :D
            //    return;


        }






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });




        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Signin.class);
                startActivity(intent);
            }
        });


        ModelReg modelReg=new ModelReg();




        // google test



        googlebtn = findViewById(R.id.googlebtn);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //          Toast.makeText(MainActivity.this, "GOOD WORK", Toast.LENGTH_SHORT).show();
                sigin();
            }

        });

    }



    void sigin() {

        Intent intent=gsc.getSignInIntent();
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode==1000){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try{

                task.getResult(ApiException.class);
                navigateToSecondActivity();

            }
            catch (ApiException e){
                Toast.makeText(this, "تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
             //   Toast.makeText(MainActivity.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    void navigateToSecondActivity(){
        finish();
        Intent intent=new Intent(MainActivity.this, Catogrey.class);

        startActivity(intent);









    }
}