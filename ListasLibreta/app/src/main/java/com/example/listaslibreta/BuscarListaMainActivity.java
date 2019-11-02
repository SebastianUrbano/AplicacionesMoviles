package com.example.listaslibreta;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BuscarListaMainActivity extends AppCompatActivity {

    //private EditText nameEt;
    //  private EditText telEt;

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

        botonBusqueda = findViewById(R.id.buscar_button);
        textoBusqueda = findViewById(R.id.buscar_edit_text);


        btnAgregarPlaylist = findViewById(R.id.btn_agregar_playlist);
        listviewPlaylists = findViewById(R.id.listview_playlists);

        adapter = new PlaylistAdapter();
        listviewPlaylists.setAdapter(adapter);
//xddd

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
                cancion.addCancion(new Cancion("ay ombre", "lewis", "2000"));

                myIntent.putExtra("adapterDeCanciones", cancion);

                startActivity(myIntent);



                Toast.makeText(BuscarListaMainActivity.this, "" +adapter.getItem(position).getNombreDeLista(), Toast.LENGTH_SHORT).show();




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
