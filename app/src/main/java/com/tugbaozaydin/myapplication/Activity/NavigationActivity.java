package com.tugbaozaydin.myapplication.Activity;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tugbaozaydin.myapplication.Adapter.ActivityAdapter;
import com.tugbaozaydin.myapplication.Adapter.DogaAdapter;
import com.tugbaozaydin.myapplication.Adapter.KampAdapter;
import com.tugbaozaydin.myapplication.Adapter.ListViewAdapter;
import com.tugbaozaydin.myapplication.Adapter.MuzeAdapter;
import com.tugbaozaydin.myapplication.Adapter.TarihiYerlerAdapter;
import com.tugbaozaydin.myapplication.Helpers.DatabaseHandler;
import com.tugbaozaydin.myapplication.Fragments.ContentDefault;
import com.tugbaozaydin.myapplication.Helpers.HttpHandler;
import com.tugbaozaydin.myapplication.Model.Aktivite;
import com.tugbaozaydin.myapplication.Model.Camiiler;
import com.tugbaozaydin.myapplication.Model.CardViewObject;
import com.tugbaozaydin.myapplication.Model.Doga;
import com.tugbaozaydin.myapplication.Model.Kamp;
import com.tugbaozaydin.myapplication.Model.Muzeler;
import com.tugbaozaydin.myapplication.Model.TarihiYerler;
import com.tugbaozaydin.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ContentDefault cd;
    RecyclerView myRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;


    ListView listView;
    private ArrayList<CardViewObject> cardViewObjects;
    ArrayList<Camiiler> camiiler;
    ArrayList<TarihiYerler> tarihiYerler;
    ArrayList<Muzeler> muzeler;
    ArrayList<Doga> doga;
    ArrayList<Kamp> kamplar;
    KampAdapter kampAdapter;
    ArrayList<Aktivite> aktiviteler;
    ActivityAdapter activityAdapter;
    DogaAdapter dogaAdapter;
    MuzeAdapter muzeAdapter;
    TarihiYerlerAdapter tarihiYerlerAdapter;
    DatabaseHandler dbHelper;
    SQLiteDatabase db;
    ListViewAdapter listViewAdapter;
    ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        int country_id = getIntent().getIntExtra("country_id", 0);
        String sehirBaslik = getIntent().getStringExtra("country_name");
        String sehirResim = getIntent().getStringExtra("country_image");
        this.setTitle(sehirBaslik);

        myRecyclerView = findViewById(R.id.myRecyclerView);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(NavigationActivity.this, BlogActivity.class);
                startActivity(i);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ArrayList<CardViewObject> cardViewObjects = new ArrayList<>();
//cardview ekleme


        cardViewObjects.add(new CardViewObject(
                country_id, sehirBaslik, sehirResim, "Adana, Türkiye'nin bir ili ", 1));


        cd = new ContentDefault();
        cd.fillContent("main", "Anasayfa", R.layout.fragment_main, cardViewObjects, myRecyclerView);
        Fragment frg = cd;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, frg);
        ft.commit();


    }

    public void CamiListele(int countryID) {

        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("select * from mosque where countryId=" + countryID, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String image = c.getString(c.getColumnIndex("image"));
                String location = c.getString(c.getColumnIndex("text"));
                int countryId = c.getInt(c.getColumnIndex("countryId"));
                cardViewObjects.add(new CardViewObject(id, name, image, location, countryId));
            }
            c.close();
            db.close();
        } catch (IOException e) {

        }

    }


    public void TarihiYerlerListele(int countryID) {

        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("select * from hıstorıcal where countryId=" + countryID, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String image = c.getString(c.getColumnIndex("image"));
                String location = c.getString(c.getColumnIndex("text"));
                int countryId = c.getInt(c.getColumnIndex("countryId"));
                tarihiYerler.add(new TarihiYerler(id, name, image, location, countryId));
            }
            c.close();
            db.close();
        } catch (IOException e) {

        }

    }

    public void MuzelerListele(int countryID) {

        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("select * from museum where countryId=" + countryID, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String image = c.getString(c.getColumnIndex("image"));
                String location = c.getString(c.getColumnIndex("text"));
                int countryId = c.getInt(c.getColumnIndex("countryId"));
                muzeler.add(new Muzeler(id, name, image, location, countryId));
            }
            c.close();
            db.close();
        } catch (IOException e) {

        }

    }

    public void DogaListele(int countryID) {

        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("select * from mosque where countryId=" + countryID, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String image = c.getString(c.getColumnIndex("image"));
                String location = c.getString(c.getColumnIndex("text"));
                int countryId = c.getInt(c.getColumnIndex("countryId"));
                doga.add(new Doga(id, name, image, location, countryId));
            }
            c.close();
            db.close();
        } catch (IOException e) {

        }

    }

    public void KampListele(int countryID) {

        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("select * from mosque where countryId=" + countryID, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String image = c.getString(c.getColumnIndex("image"));
                String location = c.getString(c.getColumnIndex("text"));
                int countryId = c.getInt(c.getColumnIndex("countryId"));
                kamplar.add(new Kamp(id, name, image, location, countryId));
            }
            c.close();
            db.close();
        } catch (IOException e) {

        }

    }

    public void AktiviteListele(int countryID) {

        try {
            dbHelper = new DatabaseHandler(this);
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("select * from activity where countryId=" + countryID, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String image = c.getString(c.getColumnIndex("image"));
                String location = c.getString(c.getColumnIndex("text"));
                int countryId = c.getInt(c.getColumnIndex("countryId"));
                aktiviteler.add(new Aktivite(id, name, image, location, countryId));
            }
            c.close();
            db.close();
        } catch (IOException e) {

        }

    }

    protected void onStart() {
        if (currentUser == null) {
            Intent welcomeIntent = new Intent(NavigationActivity.this, WelcomeActivity.class);
            startActivity(welcomeIntent); //Aktif kullanıcı varsa direk ana sayfadan ekran açılır yeni kullanıcı ise welcome sayfasından başlar.
            finish();
        }
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.mainLogout) {
            auth.signOut();
            Intent loginIntent = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        } else if (id == R.id.mainCountry) {
            Intent countryIntent = new Intent(NavigationActivity.this, CountryActivity.class);
            startActivity(countryIntent);
            finish();

        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        cd = new ContentDefault();
        cardViewObjects = new ArrayList<>();
/*
        cardViewObjects.add(new CardViewObject(
                1, "Adana Ulu Cami", "adanaulucami", "Açıklama", 1));

*/
        GetJson getJson = new GetJson();
        getJson.setCardViewObjects(cardViewObjects);

        if (itemId == R.id.nav_mosque) {
            getJson.setURL("https://raw.githubusercontent.com/tugbaozaydin/deneme/master/camiiler.json");
            getJson.execute();

            cd.fillContent("cami", "Camiiler", R.layout.fragment_main, cardViewObjects, myRecyclerView);
            fragment = cd;


        } else if (itemId == R.id.nav_historial) {
            cd.fillContent("tarihi", "Tarihi Yerler", R.layout.fragment_main, cardViewObjects, myRecyclerView);
            fragment = cd;

        } else if (itemId == R.id.nav_museum) {
            cd.fillContent("müze", "Müzeler", R.layout.fragment_main, cardViewObjects, myRecyclerView);
            fragment = cd;

        } else if (itemId == R.id.nav_nature) {
            cd.fillContent("doğa", "Doğal Alan", R.layout.fragment_main, cardViewObjects, myRecyclerView);
            fragment = cd;

        } else if (itemId == R.id.nav_camp) {
            cd.fillContent("kamp", "Kamp Yerleri", R.layout.fragment_main, cardViewObjects, myRecyclerView);
            fragment = cd;

        } else if (itemId == R.id.nav_activity) {
            cd.fillContent("aktivite", "Aktiviteler", R.layout.fragment_main, cardViewObjects, myRecyclerView);
            fragment = cd;

        }
        if (fragment != null) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout, fragment);
            ft.commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());
        Log.d("LOG", "" + item.getTitle());

        return false;
    }

    public class GetJson extends AsyncTask<Void, Void, Void> {


        ArrayList<CardViewObject> cardViewObjects = new ArrayList<>();

        public void setCardViewObjects(ArrayList<CardViewObject> cardViewObjects) {
            this.cardViewObjects = cardViewObjects;
        }

        private String URL = "";


        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler httpHandler = new HttpHandler();
            String jsonString = httpHandler.makeServiceCall(URL);
            Log.d("RESPONSE", jsonString);
            if (jsonString != "") {

                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray mekanlar = jsonObject.getJSONArray("mekanlar");
                    //Json dosyasındaki isim
                    for (int i = 0; i < mekanlar.length(); i++) {
                        JSONObject j = mekanlar.getJSONObject(i);
                        cardViewObjects.add(new CardViewObject(
                                j.getInt("id"),
                                j.getString("name"),
                                j.getString("image"),
                                j.getString("location"),
                                j.getInt("countryId")

                        ));

                    }
                } catch (JSONException e) {

                }

            } else {
                Log.d("RESPONSE", "Sayfa kaynağı boş");
            }

            return null;
        }
/*
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressDialog progressDialog = new ProgressDialog(NavigationActivity.this);
            //progressDialog.setMessage("Lütfen Bekleyiniz...");
            //progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog != null) {
                progressDialog.dismiss();


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }
        */
    }

}
