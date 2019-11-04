package com.example.listaslibreta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter {

    private ArrayList<LaPlaylist> laPlaylists;

    public PlaylistAdapter(){
        laPlaylists = new ArrayList<>();

    }




    @Override
    public int getCount() {
        return laPlaylists.size();
    }


    @Override
    public LaPlaylist getItem(int position) {
        return laPlaylists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Se encarga de un objeto visible a la vez
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view = inflater.inflate(R.layout.row_playlists, null);



        TextView textoUsuario =(TextView) view.findViewById(R.id.text_nombre_usuario);
        TextView textoNombre = (TextView) view.findViewById(R.id.text_nombre_lista);
        TextView  textoNumeroItems = (TextView) view.findViewById(R.id.text_numero_items);
        ImageView imagenn= (ImageView) view.findViewById(R.id.imagen_playlist);

        final LaPlaylist data = laPlaylists.get(position);


        textoNombre.setText(data.getNombreDeLista());
        textoUsuario.setText(data.getNombreDeUsuario());
        textoNumeroItems.setText(data.getNumeroDeCancionesEnLaLista() + "");
        Picasso.get().load(data.getUrlImagenPlaylist()).into(imagenn);






        return view;
    }

    public void addPlaylist(LaPlaylist c )
    {
        laPlaylists.add(c);
        notifyDataSetChanged();

    }

}
