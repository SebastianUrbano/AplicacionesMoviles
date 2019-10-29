package com.example.listaslibreta;

import java.io.Serializable;

public class Cancion implements Serializable {

    private String nombreDeCancion, nombreDelArtista, anhoDeLanzamiento, urlImagenCancion;


    public Cancion(String nombreDeCancion, String nombreDelArtista, String anhoDeLanzamiento) {
        this.nombreDeCancion = nombreDeCancion;
        this.nombreDelArtista = nombreDelArtista;
        this.anhoDeLanzamiento = anhoDeLanzamiento;
        urlImagenCancion = "";
    }

    public String getNombreDeCancion() {
        return nombreDeCancion;
    }

    public void setNombreDeCancion(String nombreDeCancion) {
        this.nombreDeCancion = nombreDeCancion;
    }

    public String getNombreDelArtista() {
        return nombreDelArtista;
    }

    public void setNombreDelArtista(String nombreDelArtista) {
        this.nombreDelArtista = nombreDelArtista;
    }

    public String getAnhoDeLanzamiento() {
        return anhoDeLanzamiento;
    }

    public void setAnhoDeLanzamiento(String anhoDeLanzamiento) {
        this.anhoDeLanzamiento = anhoDeLanzamiento;
    }

    public String getUrlImagenCancion() {
        return urlImagenCancion;
    }

    public void setUrlImagenCancion(String urlImagenCancion) {
        this.urlImagenCancion = urlImagenCancion;
    }
}
