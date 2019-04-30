package com.tugbaozaydin.myapplication.Holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugbaozaydin.myapplication.Adapter.MyRecyclerViewAdapter;
import com.tugbaozaydin.myapplication.Model.Country;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

public class GridViewCountryHolder extends RecyclerView.ViewHolder  {
    Context context = null;
    public ImageView imgSehir;
    public TextView txtSehir;
    ArrayList<Country> countries;
    MyRecyclerViewAdapter.MyClickListener myClickListener;

    /* Holder sınıfı, Recyclerview içerisindeki nesnelerin tanımlanması
    ve olayların(tıklanma vs) yazıldığı sınıftır.*/

    public GridViewCountryHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imgSehir = itemView.findViewById(R.id.imageSehir);
        txtSehir = itemView.findViewById(R.id.txtSehir);


    }

}

