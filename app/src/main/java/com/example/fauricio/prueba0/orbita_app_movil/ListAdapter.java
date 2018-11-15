package com.example.fauricio.prueba0.orbita_app_movil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<Item> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListAdapter(ArrayList<Item> arrayItems, Context context) {
        this.arrayItems = arrayItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vistaItem = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        ImageView iv_imagen = (ImageView) vistaItem.findViewById(R.id.iv_imagen);
        TextView tv_titulo = (TextView) vistaItem.findViewById(R.id.tv_nombre);
        TextView tv_contenido = (TextView) vistaItem.findViewById(R.id.tv_usuario);
        iv_imagen.setImageResource(arrayItems.get(i).getImage());
        tv_titulo.setText(arrayItems.get(i).getTitulo());
        tv_contenido.setText(arrayItems.get(i).getContenido());

        return vistaItem;
    }
}
