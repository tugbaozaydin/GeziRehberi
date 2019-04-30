package com.tugbaozaydin.myapplication.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tugbaozaydin.myapplication.Adapter.GridViewCountryAdapter;
import com.tugbaozaydin.myapplication.Model.Country;
import com.tugbaozaydin.myapplication.Helpers.DatabaseHandler;
import com.tugbaozaydin.myapplication.R;

import java.io.IOException;
import java.util.ArrayList;


public class CountryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridViewCountryAdapter adapter;
    DatabaseHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        recyclerView = findViewById(R.id.recyclerCountry);
        adapter = new GridViewCountryAdapter(this, sehirGetir());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public ArrayList<Country> sehirGetir() {
        ArrayList<Country> sehir = new ArrayList<>();
        //sehir.add(new Country(1,"Adana","adana","Adanadır."));
        // veriabanı sorgusu bu alanda olacak
        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            //Cursor veritabanı select sorgusundan geri dönen tüm satırları yakalayan nesne
            Cursor c = db.rawQuery("select * from COUNTRY", null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String image = c.getString(c.getColumnIndex("image"));
                String name = c.getString(c.getColumnIndex("name"));
                String text = c.getString(c.getColumnIndex("text"));
                sehir.add(new Country(id, name, image, text));
            }
            c.close();
            db.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sehir;
    }

}