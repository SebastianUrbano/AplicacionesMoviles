package com.example.listaslibreta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter {

    private ArrayList<Playlist> playlists;

    public PlaylistAdapter(){
        playlists = new ArrayList<>();

    }




    @Override
    public int getCount() {
        return playlists.size();
    }


    @Override
    public Playlist getItem(int position) {
        return playlists.get(position);
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

        final Playlist data = playlists.get(position);


        //rowCall TAREA: ACTION_CALL --------------------------------------------------------------------------------------------------

      //  rowDelete.setOnClickListener(new View.OnClickListener() {
     //       @Override
      //      public void onClick(View view) {
//
       //         playlists.remove(data);
      //          notifyDataSetChanged();
      //      }
      //  });

        textoNombre.setText(data.getNombreDeLista());
        textoUsuario.setText(data.getNombreDeUsuario());
        textoNumeroItems.setText("30");




        return view;
    }

    public void addPlaylist(Playlist c )
    {
        playlists.add(c);
        notifyDataSetChanged();

    }

}
