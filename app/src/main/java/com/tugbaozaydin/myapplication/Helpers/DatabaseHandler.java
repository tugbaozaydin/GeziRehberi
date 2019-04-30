package com.tugbaozaydin.myapplication.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TugbaOzaydin on 3.03.2019.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;
    private static String DB_NAME = "gezisql.db";
    private static String DB_PATH = "";
    public static int DATABASE_VERSION = 1;
    public SQLiteDatabase sqLiteDatabase;


    public DatabaseHandler(Context context) throws IOException {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.context = context;
        boolean dbExists = checkDatabase();
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        if (dbExists) {
            Log.d("DB_LOG", "Database bulundu !");
        } else {
            try {
                if (createDatabase() == true) {
                    Log.d("DB_LOG", "Database oluşturuldu !");
                } else {
                    Log.d("DB_LOG", "Database oluşturulamadı !");
                }
            } catch (Exception e) {
                Log.d("DB_LOG", "Database oluşturulamadı !");
            }
        }

    }
    public Cursor getRows(String query){
        sqLiteDatabase=this.getReadableDatabase();
        String Query=query;
        Cursor cr =sqLiteDatabase.rawQuery(Query,null);
        return cr;
    }
    private  int getCount(String tableName){
        sqLiteDatabase=this.getReadableDatabase();
        String  Query="select * from" +tableName;
        Cursor cr=sqLiteDatabase.rawQuery(Query,null);
        return cr.getCount();
    }
    public String getCountryName(int id){
        String str = "";
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c=db.rawQuery("select * from COUNTRY where id ="+id,null);
            while (c.moveToNext()){
                str=c.getString(1);
            }
        }catch (Exception e){}
        return  str;
    }


/*
    public ArrayList<Country> getCountry() {

        ArrayList<Country> countries = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();
        String sorgu1 = "select * from COUNTRY";
        Cursor c1 = sqLiteDatabase.rawQuery(sorgu1, null);
        while (c1.moveToNext()) {
            countries.add(new Country(c1.getInt(c1.getColumnIndex("id")),c1.getString(c1.getColumnIndex("name")), c1.getString(c1.getColumnIndex("image")), c1.getString(c1.getColumnIndex("text"))
            ));

        }
        return countries;
    }
    */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i > i1){
            Log.v("Database Upgrade","Database version higher then old");
            deleteDatabase();
        }

    }

    public boolean createDatabase() throws IOException {
        boolean dbExists = checkDatabase();
        // checkDatabase metodu ile database varmı/yokmu kontrolü yap
        if (dbExists) { //database varsa
            return true;
        } else { // database yoksa
            this.getReadableDatabase();
            try {
                this.close();
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Database kopyalanma hatası");
            }
            return false;
        }
    }

    public boolean checkDatabase() {
        boolean checkdb = false;

        try {
            String dosyaKonumu = DB_PATH + DB_NAME;
            File dbFile = new File(dosyaKonumu);
            checkdb = dbFile.exists();
        } catch (SQLiteException e) {
            Log.d("DB_LOG", "Database bulunamadı");
        }

        return checkdb;
    }

    private void copyDatabase() throws IOException {
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = context.getAssets().open(DB_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    public void openDatabase() {
        String myPath = DB_PATH + DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void deleteDatabase() {
        File file = new File((DB_PATH+DB_NAME));
        if (file.exists()){
            file.delete();
            if (file.delete()==true){
                Log.d("DB_LOG","Database file deleted on apk in database file");
            }else{
                Log.d("DB_LOG","Databse file do not deleted");
            }
        }
    }

    public synchronized void close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        super.close();
    }


}
