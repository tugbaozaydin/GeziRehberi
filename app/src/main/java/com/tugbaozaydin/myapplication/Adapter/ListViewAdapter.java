package com.tugbaozaydin.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tugbaozaydin.myapplication.Model.Camiiler;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

/**
 * Created by TugbaOzaydin on 7.03.2019.
 */
public class ListViewAdapter extends BaseAdapter {
    ArrayList<Camiiler> camiiler;
    Context context;
    LayoutInflater layoutInflater;

    public ListViewAdapter( Context context,ArrayList<Camiiler> list) {
        this.camiiler = list;
        this.context = context;
        this.layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return camiiler.size();
    }

    @Override
    public Object getItem(int i) {
        return camiiler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return camiiler.get(i).getId();
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        View v =  layoutInflater.inflate(R.layout.ozel_satir,null);
        //İçerisindeki nesneleri tanımlamamız gereliyor
        ImageView ımageView = v.findViewById(R.id.imageView);
        int id = context.getResources().getIdentifier(
                camiiler.get(i).getImage(), "drawable",context.getPackageName()
        );
        Glide.with(context).load(id).into(ımageView);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Haritaya bağlan
            }
        });
        return  v;
    }
}

/*
public class ListViewAdapter extends BaseAdapter {
    Context context;
    String[] sehir_adi;
    int[] sehir_icon_int;
    LayoutInflater inflater;
    ArrayList<Country> countries;
    int indis;

    public ListViewAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
        this.sehir_adi = sehir_adi;
        this.sehir_icon_int = sehir_icon_int;

    }


    @Override
    public int getCount() {
        return sehir_adi.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.list_item_row, parent, false);
        }
        Country p = (Country) getItem(position);
        if (p != null) {
            TextView sehiradi = v.findViewById(R.id.txtSehir);
            ImageView image = (ImageView) v.findViewById(R.id.image);

            if (sehiradi != null) {
                sehiradi.setText(p.getName());
            }
            if (image != null) {


                int id = image.getResources().getIdentifier(p.getImage(), "drawable", BuildConfig.APPLICATION_ID);
                image.setImageResource(id);
            }
        }
        return v;
    }

}

/*
        // Declare Variables
        TextView sehir_adi_textview;
        ImageView sehir_icon_imageView;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item_row, parent, false);//list_item_row dan yeni bir view oluşturuyoruz

        // oluşan itemviewin içindeki alanları Anasayfadan gelen değerler ile set ediyoruz
        sehir_adi_textview = (TextView) itemView.findViewById(R.id.sehir_adi);
        sehir_icon_imageView = (ImageView) itemView.findViewById(R.id.sehir_icon);

        sehir_adi_textview.setText(sehir_adi[position]);
        sehir_icon_imageView.setImageResource(sehir_icon_int[position]);


        return itemView;
        */


