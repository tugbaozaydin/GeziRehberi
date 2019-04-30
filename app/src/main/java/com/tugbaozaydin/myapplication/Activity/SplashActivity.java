package com.tugbaozaydin.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.tugbaozaydin.myapplication.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    KenBurnsView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_splash);

       // this.getSupportActionBar().hide();

        image = findViewById(R.id.imageAna);
        String [] appBackgrounds = {"turkiye_gez1","turkiye_gez2","turkiye_gez3"};
        int randomId = new Random().nextInt(appBackgrounds.length);
        String rastgeleDonenDeger = (appBackgrounds[randomId]);

        int resourceId = getResources().getIdentifier(rastgeleDonenDeger,
                "drawable",getPackageName());
        image.setImageResource(resourceId);
        new SayfaGecisi().start();
    }
    private class SayfaGecisi extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(5000);

            }catch (Exception e){ }
            finally {
                {
                    Intent i = new Intent(SplashActivity.this,WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }
    }
}
