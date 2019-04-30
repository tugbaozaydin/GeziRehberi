package com.tugbaozaydin.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tugbaozaydin.myapplication.Activity.NavigationActivity;
import com.tugbaozaydin.myapplication.Model.Country;
import com.tugbaozaydin.myapplication.Holder.GridViewCountryHolder;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

public class GridViewCountryAdapter extends RecyclerView.Adapter<GridViewCountryHolder> {
    private ArrayList<Country> countries;
    private Context context;

    private LayoutInflater layoutInflater;

    public GridViewCountryAdapter(Context context, ArrayList<Country> countries) {
        this.countries = countries;
        this.context = context;
    }

    @Override// satır görüntüsü oluşmaya yarar
    public GridViewCountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View satirGoruntusu = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_row, null);
        GridViewCountryHolder holder = new GridViewCountryHolder(satirGoruntusu);

        return holder;
    }


    @Override // Nesnelerin doldurulma olayları db den dolabilir
    public void onBindViewHolder(GridViewCountryHolder holder, final int position) {
        // Glide.with(context).load(countries.get(position).getImage()).into(holder.imgSehir);


        try {
            int id = context.getResources().getIdentifier(countries.get(position).getImage(), "drawable", context.getPackageName());
            Glide.with(context).load(id).into(holder.imgSehir);
        } catch (Exception e) {
        }
        // Programatik olarak dosya ismini resource içerisinde integer karşılığını döner

        holder.txtSehir.setText(countries.get(position).getName());
        holder.imgSehir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Satıra tıklama olayı
                Intent i = new Intent(context, NavigationActivity.class);
                i.putExtra("country_id", countries.get(position).getId());
                i.putExtra("country_name", countries.get(position).getName());
                i.putExtra("country_image",countries.get(position).getImage());
                context.startActivity(i);
                Log.d("LOG","Tıklanan Id:" +position);

            }
        });

    }

    @Override
    public long getItemId(int position) {

        return countries.get(position).getId();
    }

    @Override
    public int getItemCount() {

        return countries.size();
    }

}
