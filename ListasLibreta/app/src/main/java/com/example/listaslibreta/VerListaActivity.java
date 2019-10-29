package com.example.listaslibreta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VerListaActivity extends AppCompatActivity {

    private ListView listviewCanciones;
    private CancionAdapter adapter;

    //sera rellenado con Playlist.darCancionAdapter----------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista);



        listviewCanciones = findViewById(R.id.listview_canciones);

        adapter = new CancionAdapter();

        Intent data = getIntent();
        CancionAdapter  c = data.getParcelableExtra("adapterDeCanciones");


        adapter = c;


        if (adapter  != null){
            Toast.makeText(this, "OIGA" + adapter.toString(), Toast.LENGTH_SHORT).show();
        }


        listviewCanciones.setAdapter(adapter);

        listviewCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Toast.makeText(VerListaActivity.this, "" +adapter.getItem(position).getNombreDeCancion(), Toast.LENGTH_SHORT).show();

            }
        });

        //btnAgregarPlaylist.setOnClickListener(new View.OnClickListener() {
        // @Override
        //  public void onClick(View view) {


        //     Intent myIntent = new Intent(getApplicationContext(), CrearPlaylistActivity.class);
        //     startActivityForResult(myIntent, 11);


        //  }
        // });


        // adapter = new PlaylistAdapter();
        //  listviewPlaylists.setAdapter(adapter);


        //  btnAgregarPlaylist.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //      public void onClick(View view) {


        //      Intent myIntent = new Intent(getApplicationContext(), CrearPlaylistActivity.class);
        //        startActivityForResult(myIntent, 11);


        //     }
        //  });


    }



    public void setAdapterCancion(CancionAdapter cancionadapter) {

    }
}
