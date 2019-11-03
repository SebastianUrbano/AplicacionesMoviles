package com.example.listaslibreta;

import android.widget.ArrayAdapter;

public class LaPlaylist {

    private String nombreDeLista;
    private String nombreDeUsuario, descripcion, urlImagenPlaylist;
    private int numeroDeCancionesEnLaLista;
    //private ArrayList<Cancion> canciones;------------------------------------------------ HACER
    private CancionAdapter canciones;


    public LaPlaylist(String nombreDeLista, String nombreDeUsuario, String descripcion, int numeroDeCancionesEnLaLista) {
        this.nombreDeLista = nombreDeLista;
        this.nombreDeUsuario = nombreDeUsuario;
        this.descripcion = descripcion;
        this.numeroDeCancionesEnLaLista = numeroDeCancionesEnLaLista;
        urlImagenPlaylist = "";
        canciones = new CancionAdapter();
    }

    public String getNombreDeLista() {
        return nombreDeLista;
    }

    public void setNombreDeLista(String nombreDeLista) {
        this.nombreDeLista = nombreDeLista;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroDeCancionesEnLaLista() {
        return numeroDeCancionesEnLaLista;
    }

    public void aumentarNumeroDeCanciones() {
        setNumeroDeCancionesEnLaLista(numeroDeCancionesEnLaLista++);
    }

    public void disminuirNumeroDeCanciones() {
        if (numeroDeCancionesEnLaLista > 0) {
            setNumeroDeCancionesEnLaLista(numeroDeCancionesEnLaLista--);

        }
    }

    public void setNumeroDeCancionesEnLaLista(int numeroDeCancionesEnLaLista) {
        this.numeroDeCancionesEnLaLista = numeroDeCancionesEnLaLista;
    }

    public String getUrlImagenPlaylist() {
        return urlImagenPlaylist;
    }

    public void setUrlImagenPlaylist(String urlImagenPlaylist) {
        this.urlImagenPlaylist = urlImagenPlaylist;
    }

    public CancionAdapter getCanciones() {
        return canciones;
    }
}
