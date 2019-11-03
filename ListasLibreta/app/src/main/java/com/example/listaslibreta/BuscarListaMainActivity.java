package com.example.listaslibreta;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Permissions;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.connect.SessionStore;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class BuscarListaMainActivity extends AppCompatActivity {

    //private EditText nameEt;
    //  private EditText telEt;
    DeezerConnect deezerConnect;

    private EditText textoBusqueda;

    private ImageButton botonBusqueda;
    private FloatingActionButton btnAgregarPlaylist;
    private ListView listviewPlaylists;
    private PlaylistAdapter adapter;
    private ArrayList<Album> albumsDeezer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buscar_lista);

        Toolbar mToolbar = findViewById(R.id.toolbar2);
        mToolbar.setTitle("         " +
                "NDeezer");


        // Restaurar sesion

        albumsDeezer = new ArrayList<>();

        String applicationID = "com.example.ndeezer";
        deezerConnect = new DeezerConnect(this, applicationID);

        SessionStore sessionStore = new SessionStore();
        if (sessionStore.restore(deezerConnect, BuscarListaMainActivity.this)) {

        }
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


                //Toast.makeText(BuscarListaMainActivity.this, "xddddd", Toast.LENGTH_SHORT).show();
                //   Intent myIntent = new Intent(getApplicationContext(), CrearPlaylistActivity.class);
                // startActivityForResult(myIntent, 11);

            }
        });

        listviewPlaylists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent myIntent = new Intent(BuscarListaMainActivity.this, VerListaActivity.class);

                CancionAdapter cancion = adapter.getItem(position).getCanciones();

                String req = "";


                //PARA CREAR LOS REQUEST DE DEEZER

                RequestListener listenerRequest = new JsonRequestListener() {


                    public void onResult(Object result, Object requestId) {

                        albumsDeezer = (ArrayList<Album>) result;


//adapter.addPlaylist();

                    }


                    public void onUnparsedResult(String requestResponse, Object requestId) {
                    }

                    public void onException(Exception e, Object requestId) {
                    }
                };

                long artistID = 27;
                DeezerRequest request = DeezerRequestFactory.requestArtistAlbums(artistID);

                // Callback methods
                request.setId("myRequest");

                // Request asynchronously
                deezerConnect.requestAsync(request, listenerRequest);
                req = "xd" + albumsDeezer.get(0).getTitle();

                Toast.makeText(BuscarListaMainActivity.this, "" + req, Toast.LENGTH_SHORT).show();
                //cancion.addCancion(new Cancion("ay " + req, "lewis", "2000"));
              //  myIntent.putExtra("adapterDeCanciones", cancion);


                //Guardar info actual de autenticacion
                SessionStore sessionStore = new SessionStore();
                sessionStore.save(deezerConnect, BuscarListaMainActivity.this);

               // startActivity(myIntent);


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK) {

            String nombreLista = data.getExtras().getString("nombreLista");
            String nombreUsuario = data.getExtras().getString("nombreUsuario");
            String descripcion = data.getExtras().getString("descripcion");

            LaPlaylist p = new LaPlaylist(nombreLista, nombreUsuario, descripcion, adapter.getCount());

            adapter.addPlaylist(p);


        }
    }


}
