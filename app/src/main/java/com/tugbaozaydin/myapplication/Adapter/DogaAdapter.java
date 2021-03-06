package com.tugbaozaydin.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tugbaozaydin.myapplication.Model.Doga;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

public class DogaAdapter extends BaseAdapter {
    ArrayList<Doga> doga;
    Context context;
    LayoutInflater layoutInflater;

    public DogaAdapter( Context context,ArrayList<Doga> list) {
        this.doga = list;
        this.context = context;
        this.layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return doga.size();
    }

    @Override
    public Object getItem(int position) {
        return doga.get(position);
    }

    @Override
    public long getItemId(int position) {
        return doga.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =  layoutInflater.inflate(R.layout.ozel_satir,null);
        //İçerisindeki nesneleri tanımlamamız gereliyor
        ImageView ımageView = v.findViewById(R.id.imageView);
        int id = context.getResources().getIdentifier(
                doga.get(position).getImage(), "drawable",context.getPackageName()
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
