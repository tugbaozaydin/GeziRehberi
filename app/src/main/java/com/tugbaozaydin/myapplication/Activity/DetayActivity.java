package com.tugbaozaydin.myapplication.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tugbaozaydin.myapplication.Model.CardViewObject;
import com.tugbaozaydin.myapplication.R;

public class DetayActivity extends AppCompatActivity {
    TextView detay ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       CardViewObject cardViewObject = (CardViewObject) getIntent().getSerializableExtra("mekan");
        detay = (TextView) findViewById(R.id.txtDetayIcerik);
        //detay.setText(cardViewObject.getText());
    }
}
