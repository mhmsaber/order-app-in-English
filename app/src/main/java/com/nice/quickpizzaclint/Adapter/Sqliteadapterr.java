package com.nice.quickpizzaclint.Adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nice.quickpizzaclint.Model.Modelfood;

import java.util.ArrayList;
import java.util.List;

public class Sqliteadapterr  extends SQLiteOpenHelper {


    public Sqliteadapterr(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("CREATE TABLE " + Utils.TABLE_NAME + " ("
                + Utils.CULOMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.CULOMN_NAME + " TEXT,"
                + Utils.CULOMN_LARGE + " TEXT,"
                + Utils.CULOMN_MEDEAM + " TEXT,"
                + Utils.CULOMN_SMALLE + " TEXT,"
                + Utils.CULOMN_PRICE + " TEXT); ");



        String query3 = ("CREATE TABLE " + Utils.TABLE_NAME + " ("
                + Utils.CULOMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.CULOMN_NAME + " TEXT,"
                + Utils.CULOMN_QUANTITY + " TEXT,"
                + Utils.CULOMN_LARGE + " TEXT,"
                + Utils.CULOMN_MEDEAM + " TEXT,"
                + Utils.CULOMN_SMALLE + " TEXT,"
                + Utils.CULOMN_PRICE + " TEXT); ");

        db.execSQL(query3);









    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + Utils.TABLE_NAME);



    }

    public long insertdata2(String name, String price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.CULOMN_NAME, name);
        cv.put(Utils.CULOMN_PRICE,price);
        long id = db.insert(Utils.TABLE_NAME, null, cv);
        db.close();
        return id;


    }


    public long insertdata3(String name ,String price, String quantity,String larege
            ,String medeam,String smalle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.CULOMN_NAME, name);
        cv.put(Utils.CULOMN_QUANTITY, quantity);
        cv.put(Utils.CULOMN_PRICE,price);
        cv.put(Utils.CULOMN_LARGE,larege);
        cv.put(Utils.CULOMN_MEDEAM,medeam);
        cv.put(Utils.CULOMN_SMALLE,smalle);
        long id = db.insert(Utils.TABLE_NAME, null, cv);
        db.close();
        return id;



    }

    public  long insertdata4(Modelfood modelfood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utils.CULOMN_NAME, modelfood.getNamefood());
        cv.put(Utils.CULOMN_PRICE, modelfood.getLarge());

        long id = db.insert(Utils.TABLE_NAME, null, cv);
        db.close();
        return id;


//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Utils.COLOUMN_NAME, name);
//        cv.put(Utils.COLOUMN_LNAME, lname);
//        cv.put(Utils.COLOUMN_AGE, age);
//        long id = db.insert(Utils.TABLE_NAME, null, cv);
//        db.close();
//        return id;
//
//
//


    }



    @SuppressLint("Range")


    public List<Modelfood> getAllData() {
        List<Modelfood> allData = new ArrayList<>();
        String query = "SELECT * FROM "+ Utils.TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( query ,null);
        if(cursor.moveToFirst())
            do{
                Modelfood data = new Modelfood();
                // i stoped id to not show  id in firebase order
                data.setIdfood(cursor.getString(cursor.getColumnIndex(Utils.CULOMN_ID)));
                data.setNamefood( cursor.getString(cursor.getColumnIndex(Utils.CULOMN_NAME)));

                data.setTotsql( cursor.getString(cursor.getColumnIndex(Utils.CULOMN_PRICE)));
                data.setNummberlarge(cursor.getString(cursor.getColumnIndex(Utils.CULOMN_LARGE)));
                data.setNummbermed(cursor.getString(cursor.getColumnIndex(Utils.CULOMN_MEDEAM)));




                //original code
                // i stoped id to not show  id in firebase order
//                data.setIdM(cursor.getString(cursor.getColumnIndex(Utils.CULOMN_ID)));
//                data.setNameM( cursor.getString(cursor.getColumnIndex(Utils.CULOMN_NAME)));
//                data.setQuantity( cursor.getString(cursor.getColumnIndex(Utils.CULOMN_QUANTITY)));
//                data.setPriceM( cursor.getString(cursor.getColumnIndex(Utils.CULOMN_PRICE)));






                allData.add(data);

            }while (cursor.moveToNext());
        db.close();
        return allData;







    }




    public void deletedata(Modelfood modelfood) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME, Utils.CULOMN_ID + " =?",
                new String[]{String.valueOf(modelfood.getIdfood())});
        db.close();
    }

    public void deletealldata() {


        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM TABLE_NAME");


        db.execSQL(query);


//    public  void deleteData(Data data){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Utils.TABLE_NAME ,  Utils.COLOUMN_ID + " =?",
//                new String[]{String.valueOf(data.getId())});
//        db.close();
    }






}
