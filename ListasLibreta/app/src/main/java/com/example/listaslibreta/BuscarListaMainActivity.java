package com.example.listaslibreta;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Permissions;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.listaslibreta.CRUDApp.WEBUtilDomi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarListaMainActivity extends AppCompatActivity {

    //private EditText nameEt;
    //  private EditText telEt;
    DeezerConnect deezerConnect;

    private EditText textoBusqueda;

    private ImageButton botonBusqueda;
    private FloatingActionButton btnAgregarPlaylist;
    private ListView listviewPlaylists;
    private PlaylistAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buscar_lista);

        Toolbar mToolbar = findViewById(R.id.toolbar2);
        mToolbar.setTitle("         " +
                "NDeezer");

        String applicationID = "com.example.ndeezer";
        deezerConnect = new DeezerConnect(this, applicationID);


        String[] permissions = new String[]{
                Permissions.BASIC_ACCESS,
                Permissions.MANAGE_LIBRARY,
                Permissions.LISTENING_HISTORY};

        botonBusqueda = findViewById(R.id.buscar_button);
        textoBusqueda = findViewById(R.id.buscar_edit_text);


        btnAgregarPlaylist = findViewById(R.id.btn_agregar_playlist);
        listviewPlaylists = findViewById(R.id.listview_playlists);

        adapter = new PlaylistAdapter();
        listviewPlaylists.setAdapter(adapter);


        btnAgregarPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getApplicationContext(), CrearPlaylistActivity.class);
                startActivityForResult(myIntent, 11);

            }
        });

        listviewPlaylists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent myIntent = new Intent(BuscarListaMainActivity.this, VerListaActivity.class);

                CancionAdapter cancion = adapter.getItem(position).getCanciones();
                String req = "";

                RequestListener listener = new JsonRequestListener() {

                    public void onResult(Object result, Object requestId) {


                               ArrayList<Album> albums = (ArrayList<Album>) result;

                           //    req = albums.get(0);

                    }

                    public void onUnparsedResult(String requestResponse, Object requestId) {
                    }

                    public void onException(Exception e, Object requestId) {
                    }
                };

// create the request
                long artistID = 27;
                DeezerRequest request = DeezerRequestFactory.requestArtistAlbums(artistID);

// set a requestId, that will be passed on the listener's callback methods
                request.setId("myRequest");

// launch the request asynchronously
                deezerConnect.requestAsync(request, listener);



                cancion.addCancion(new Cancion("ay " + req, "lewis", "2000"));
                myIntent.putExtra("adapterDeCanciones", cancion);

                startActivity(myIntent);


                Toast.makeText(BuscarListaMainActivity.this, "iniciado" + req, Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK) {

            String nombreLista = data.getExtras().getString("nombreLista");
            String nombreUsuario = data.getExtras().getString("nombreUsuario");
            String descripcion = data.getExtras().getString("descripcion");

            Playlist p = new Playlist(nombreLista, nombreUsuario, descripcion, adapter.getCount());

            adapter.addPlaylist(p);


        }
    }


}
