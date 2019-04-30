package com.tugbaozaydin.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tugbaozaydin.myapplication.Model.Kamp;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

public class KampAdapter extends BaseAdapter {
    ArrayList<Kamp> kamp;
    Context context;
    LayoutInflater layoutInflater;

    public KampAdapter( Context context,ArrayList<Kamp> list) {
        this.kamp = list;
        this.context = context;
        this.layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return kamp.size();
    }

    @Override
    public Object getItem(int position) {
        return kamp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return kamp.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =  layoutInflater.inflate(R.layout.ozel_satir,null);
        //İçerisindeki nesneleri tanımlamamız gereliyor
        ImageView ımageView = v.findViewById(R.id.imageView);
        int id = context.getResources().getIdentifier(
                kamp.get(position).getImage(), "drawable",context.getPackageName()
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
