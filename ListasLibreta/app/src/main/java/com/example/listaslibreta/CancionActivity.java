package com.example.listaslibreta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CancionActivity extends AppCompatActivity {

    private TextView uno, dos, tres, cuatro;
    private ImageView cinco;
    private Button botonEscuchar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancion);



        uno = findViewById(R.id.nombre_cancion_txt);
        dos = findViewById(R.id.artista_cancion_txt);
        tres = findViewById(R.id.album_cancion_txt);
        cuatro = findViewById(R.id.duracion_cancion_txt);
        cinco = findViewById(R.id.imageview_cancion);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
          String   songName = bundle.getString("songname");
          String   artistName = bundle.getString("artistname");
          String   albumname = bundle.getString("albumname");
          String   duration = bundle.getString("duration");
          final String   songlink = bundle.getString("songlink");
          String   urlimage = bundle.getString("urlimage");

            uno.setText(songName);
            dos.setText(artistName);
            tres.setText(albumname);
            cuatro.setText(duration + " segundos");

            Picasso.get().load(urlimage).into(cinco);

            botonEscuchar = findViewById(R.id.boton_escuchar_cancion);
            botonEscuchar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(songlink);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(webIntent);


                    //accion
                }
            });
        }







    }
}
