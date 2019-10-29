package com.example.listaslibreta;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CancionAdapter extends BaseAdapter implements Parcelable {

    private ArrayList<Cancion> canciones;

    public CancionAdapter(){
        canciones = new ArrayList<>();

    }




    @Override
    public int getCount() {
        return canciones.size();
    }


    @Override
    public Cancion getItem(int position) {
        return canciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Se encarga de un objeto visible a la vez
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_canciones, null);

        //  PONER LO DE LA IMAGEN DE CADA UNO CON SU URL


        TextView textoCancion =(TextView) view.findViewById(R.id.text_nombre_cancion);
        TextView textoArtista = (TextView) view.findViewById(R.id.text_nombre_artista);
        TextView  textoAnhoLanzamiento = (TextView) view.findViewById(R.id.text_anho_lanzamiento);

        final Cancion data = canciones.get(position);


        //rowCall TAREA: ACTION_CALL --------------------------------------------------------------------------------------------------

        //  rowDelete.setOnClickListener(new View.OnClickListener() {
        //       @Override
        //      public void onClick(View view) {
//
        //         playlists.remove(data);
        //          notifyDataSetChanged();
        //      }
        //  });

        textoArtista.setText(data.getNombreDelArtista());
        textoCancion.setText(data.getNombreDeCancion());
        //CAMBIAR--------------------------------------------------------------------------------
        textoAnhoLanzamiento.setText("1998");




        return view;
    }

    public void addCancion(Cancion c )
    {
        canciones.add(c);
        notifyDataSetChanged();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.canciones);
    }

    protected CancionAdapter(Parcel in) {
        this.canciones = new ArrayList<Cancion>();
        in.readList(this.canciones, Cancion.class.getClassLoader());
    }

    public static final Parcelable.Creator<CancionAdapter> CREATOR = new Parcelable.Creator<CancionAdapter>() {
        @Override
        public CancionAdapter createFromParcel(Parcel source) {
            return new CancionAdapter(source);
        }

        @Override
        public CancionAdapter[] newArray(int size) {
            return new CancionAdapter[size];
        }
    };
}
