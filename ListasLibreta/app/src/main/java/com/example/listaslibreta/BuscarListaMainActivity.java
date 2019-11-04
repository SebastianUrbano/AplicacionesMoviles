package com.example.listaslibreta;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class BuscarListaMainActivity extends AppCompatActivity {

    //private EditText nameEt;
    //  private EditText telEt;

    private EditText textoBusqueda;

    private ImageButton botonBusqueda;
    private FloatingActionButton btnAgregarPlaylist;
    private ListView listviewPlaylists;
    private PlaylistAdapter adapter;
    private ArrayList<Playlist> listaPlaylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buscar_lista);

        Toolbar mToolbar = findViewById(R.id.toolbar2);
        mToolbar.setTitle("         " +
                "NDeezer");

        String applicationID = "377884";
        final DeezerConnect deezerConnect = new DeezerConnect(this, applicationID);


        botonBusqueda = findViewById(R.id.buscar_button);
        textoBusqueda = findViewById(R.id.buscar_edit_text);


        btnAgregarPlaylist = findViewById(R.id.btn_agregar_playlist);
        listviewPlaylists = findViewById(R.id.listview_playlists);

        adapter = new PlaylistAdapter();
        listviewPlaylists.setAdapter(adapter);


        botonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!textoBusqueda.getText().toString().equals("")) {

                    RequestListener listener = new JsonRequestListener() {
                        public void onResult(Object result, Object requestId) {
                            listaPlaylist = (ArrayList<Playlist>) result;

                            for (int i = 0; i < listaPlaylist.size(); i++) {

                                String nombreLista = listaPlaylist.get(i).getTitle();
                                String nombreUsuario = "" + listaPlaylist.get(i).getCreator().getName();
                                String descripcion = listaPlaylist.get(i).getDescription();


                                //CAMBIAR NUMERO DE CANCIONES
                                CancionAdapter cancion = new CancionAdapter();

                                List<Track> tracks = listaPlaylist.get(i).getTracks();


                                for (int j = 0; j < tracks.size(); j++) {


                                    String nombreDeCancion = listaPlaylist.get(i).getTracks().get(j).getTitle() + j;
                                    String nombreArtista = listaPlaylist.get(i).getTracks().get(j).getArtist().getName() + j;
                                    String anhoLanza = "uy" + j;


                                    cancion.addCancion(new Cancion(nombreDeCancion, nombreArtista, anhoLanza,
                                            listaPlaylist.get(i).getTracks().get(j).getAlbum().getImageUrl()));


                                }


                                LaPlaylist p = new LaPlaylist(nombreLista, nombreUsuario, descripcion, listaPlaylist.get(i).getTracks().size()
                                        , cancion);

                                Toast.makeText(BuscarListaMainActivity.this, listaPlaylist.get(i).getTracks().size() + "", Toast.LENGTH_LONG);
                                p.setUrlImagenPlaylist(listaPlaylist.get(i).getMediumImageUrl());
                                p.setNumeroDeCancionesEnLaLista(listaPlaylist.get(i).getTracks().size());


                                adapter.addPlaylist(p);
                            }


                        }

                        public void onUnparsedResult(String requestResponse, Object requestId) {
                        }

                        public void onException(Exception e, Object requestId) {
                        }
                    };


                    DeezerRequest request = DeezerRequestFactory.requestSearchPlaylists(textoBusqueda.getText().toString());

                    deezerConnect.requestAsync(request, listener);


                } else {
                    Toast.makeText(BuscarListaMainActivity.this, "Campo vacío, por favor escriba una Playlist", Toast.LENGTH_SHORT).show();
                }

            }
        });


        listviewPlaylists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent myIntent = new Intent(BuscarListaMainActivity.this, VerListaActivity.class);

                long ID = listaPlaylist.get(position).getId();
                myIntent.putExtra("id", ID);
                startActivity(myIntent);


                // Toast.makeText(BuscarListaMainActivity.this, "" +adapter.getItem(position).getNombreDeLista(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK) {

            //  String nombreLista = data.getExtras().getString("nombreLista");
            // String nombreUsuario = data.getExtras().getString("nombreUsuario");
            // String descripcion = data.getExtras().getString("descripcion");

            //  LaPlaylist p = new LaPlaylist(nombreLista, nombreUsuario, descripcion, adapter.getCount());

            //  adapter.addPlaylist(p);


        }
    }


}
