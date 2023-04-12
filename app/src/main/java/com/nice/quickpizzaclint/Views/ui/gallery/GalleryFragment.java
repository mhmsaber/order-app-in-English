package com.nice.quickpizzaclint.Views.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nice.quickpizzaclint.Adapter.Adapterrcart;
import com.nice.quickpizzaclint.Adapter.Sqliteadapterr;
import com.nice.quickpizzaclint.MainActivity;
import com.nice.quickpizzaclint.Model.Modelfood;
import com.nice.quickpizzaclint.Model.Request;
import com.nice.quickpizzaclint.R;
//import com.nice.quickpizzaclint.Views.R;
import com.nice.quickpizzaclint.Views.Cart;
import com.nice.quickpizzaclint.Views.Catogrey;
import com.nice.quickpizzaclint.Views.Register;
 // import com.nice.quickpizzaclint.Views.databinding.FragmentGalleryBinding;
import com.nice.quickpizzaclint.databinding.FragmentGalleryBinding;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;


    RecyclerView recycart;
    Button send;
    TextView totprice;

    List<Modelfood> listcart =new ArrayList<>();
    Sqliteadapterr sqliteadapterr;
    Adapterrcart adapterrs;

    // to see data from query4 not query1
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("quickpizza");
    int totall = 0;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        /////////////

        recycart = root.findViewById(R.id.recycart);
        send = root.findViewById(R.id.send);
        totprice = root.findViewById(R.id.totpricce);
        if (!Register.checkinternet(getContext())) {
            Toast.makeText(getContext(), "check internet connection", Toast.LENGTH_SHORT).show();
            //        return;
        }


        listcart = new Sqliteadapterr(getContext()).getAllData();
        sqliteadapterr = new Sqliteadapterr(getContext());
        adapterrs = new Adapterrcart(getContext(), sqliteadapterr.getAllData(), sqliteadapterr);


 
       recycart.setLayoutManager(new LinearLayoutManager(getContext()));
        recycart.setItemAnimator(new DefaultItemAnimator());
        recycart.setHasFixedSize(true);
        recycart.setAdapter(adapterrs);
        for (Modelfood modelfood : listcart) {
            totall += Integer.valueOf(modelfood.getTotsql());
            Locale locale = new Locale("en", "eg");
            NumberFormat ftm = NumberFormat.getCurrencyInstance(locale);
            totprice.setText(ftm.format(totall));
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog;
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());
                alertdialog.setTitle("            ادخل بياناتك");
                View view=getLayoutInflater().inflate(R.layout.itemdialog,null);
                alertdialog.setIcon(R.drawable.cart2);

                EditText  namedialog2,telephone2, editaddress;
                Button senddailog;
                namedialog2=view.findViewById(R.id.namedialog);
                telephone2=view.findViewById(R.id.telphonedialog);
                editaddress=view.findViewById(R.id.addressdialog);
                senddailog=view.findViewById(R.id.senddialog);
                alertdialog.setView(view);


                senddailog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                        String m = telephone2.getText().toString();
                String adressx = editaddress.getText().toString();
                String namex = namedialog2.getText().toString();
                Calendar calendar = Calendar.getInstance();
                //     SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy  hh:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat(" hh:mm dd MM ");
                String time = sdf.format(calendar.getTime());
                String status = " order";


//                        String namereq, String totalreq, String address, String timereq,
//                                String statusreq, String telephonereq, List<Modelfood> listreq
//


                Request request = new Request(namex, String.valueOf(totall), adressx, time, status,
                        m, listcart);
                //send data to Firebase by milisecand system
                //ref.child("Orders").child(String.valueOf(System.currentTimeMillis())).setValue(request);
                ref.child("Orders").child(m).setValue(request);

//                        delete Cartdata :D
                     sqliteadapterr.deletealldata();

//                        to refrash totalizer
                     Intent intent = new Intent(getContext(), Catogrey.class);
                     startActivity(intent);




            }
        });


        // this code for show dialog
        dialog = alertdialog.create();
        dialog.setIcon(R.drawable.cart_24);
        dialog.show();








        /////////
//                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());
//                alertdialog.setTitle("one more step");
//                alertdialog.setMessage("Enter your address :");
//
//                EditText editaddress = new EditText(getContext());
//                LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.MATCH_PARENT);
//                editaddress.setLayoutParams(ip);
//                alertdialog.setView(editaddress);
//                alertdialog.setIcon(R.drawable.cart_24);
//                alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String m = editaddress.getText().toString();
//                        // if i want more data in list i put it from Sqliteadapterr
//                        // class in part getAllData()
//                        Calendar calendar=Calendar.getInstance();
//                        //     SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy  hh:mm:ss");
//                        SimpleDateFormat sdf=new SimpleDateFormat(" hh:mm dd MM ");
//                        String time=sdf.format(calendar.getTime());
//                        String status=" order";
//                        //this not final code
//                        //                the final in cart Activity
////                        Toast.makeText(Cart.this, "time now "+ time, Toast.LENGTH_SHORT).show();
////                        Request request = new Request(m, String.valueOf(totall), listcart, time,status
////                        );
//                        //                       Toast.makeText(Cart.this, "thanks for help you", Toast.LENGTH_SHORT).show();                        //send data to Firebase by milisecand system
//                        //               ref.child("Orders").child(String.valueOf(System.currentTimeMillis())).setValue(request);
//
////                        ref.child("Orders").child(m).setValue(request);
////
//                        //delete Cartdata :D
//                        //                       sqliteadapterr.deletealldata();
//                        //                       finish();
//                        // to refrash totalizer
//                        Intent intent = new Intent(getContext(), MainActivity.class);
//                        startActivity(intent);
//                    }
//
//                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        dialogInterface.dismiss();                    }
//                });
//                alertdialog.show();


    }
});



/////////////////////////////////


























//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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