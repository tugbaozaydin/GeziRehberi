package com.tugbaozaydin.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tugbaozaydin.myapplication.Model.Country;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

public class BlogActivity extends AppCompatActivity {
    TextView txtSub;
    EditText edtSub;
    Spinner spin;
    Button btnKayit;
    ArrayList<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        txtSub = (TextView) findViewById(R.id.txtSub);
        edtSub = (EditText) findViewById(R.id.edtSub);
        spin = (Spinner) findViewById(R.id.spinCountry);
        btnKayit = (Button) findViewById(R.id.btnKayit);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,countries);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),countries.get(position) ,Toast.LENGTH_LONG).show();
            }
        });
    }
}
