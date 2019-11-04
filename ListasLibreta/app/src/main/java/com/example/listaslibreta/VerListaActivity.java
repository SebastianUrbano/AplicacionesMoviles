package com.example.listaslibreta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;

import java.util.List;

public class VerListaActivity extends AppCompatActivity {

    private ListView listviewCanciones;
    private CancionAdapter adapter;
    private Playlist playlist;
    private List<Track> theTracks;

    //sera rellenado con LaPlaylist.darCancionAdapter----------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista);

        String applicationID = "377884";
        final DeezerConnect deezerConnect = new DeezerConnect(this, applicationID);

        long id = 0;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getLong("id");
        }
        adapter = new CancionAdapter();

        RequestListener listener = new JsonRequestListener() {

            public void onResult(Object result, Object requestId) {
                playlist = (Playlist) result;
                theTracks = playlist.getTracks();

             //   txt_Descripcion.setText(playlist.getDescription());
              //  txt_Nombre.setText(playlist.getTitle());
              //  txt_NumCanciones.setText(canciones.size() + " Canciones"  + "  --  " + playlist.getFans() + " Fans");

               // Picasso.get().load(playlist.getMediumImageUrl()).into(imageView);

                for(int i = 0; i< theTracks.size(); i++){

                    Cancion cancioncita = new Cancion(theTracks.get(i).getTitle(), theTracks.get(i).getArtist().getName(), "2011",
                            theTracks.get(i).getAlbum().getBigImageUrl()  );
                    adapter.addCancion(cancioncita );


                }



            }

            public void onUnparsedResult(String requestResponse, Object requestId) {
            }

            public void onException(Exception e, Object requestId) {
            }
        };
        DeezerRequest request = DeezerRequestFactory.requestPlaylist(id);
        deezerConnect.requestAsync(request, listener);
        listviewCanciones = findViewById(R.id.listview_canciones);

        listviewCanciones.setAdapter(adapter);

        listviewCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(VerListaActivity.this, CancionActivity.class);



                myIntent.putExtra("songname", theTracks.get(position).getTitle());
                myIntent.putExtra("artistname", theTracks.get(position).getArtist().getName());
                myIntent.putExtra("albumname", theTracks.get(position).getAlbum().getTitle());
                myIntent.putExtra("duration", theTracks.get(position).getDuration() + "");
                myIntent.putExtra("songlink", theTracks.get(position).getPreviewUrl());
                myIntent.putExtra("urlimage", theTracks.get(position).getAlbum().getBigImageUrl());

                startActivity(myIntent);

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
