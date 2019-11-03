package com.example.listaslibreta.CRUDApp;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.network.request.event.RequestListener;

import java.util.ArrayList;

public class ElRequestListener implements RequestListener {
    public ArrayList<Album> albumsDeezerr;




    @Override
    public void onComplete(String s, Object o) {

    }
    @Override
    public void onException(Exception e, Object o) {

    }


    public ArrayList<Album> getAlbumsDeezerr() {
        return albumsDeezerr;
    }

    public void setAlbumsDeezerr(ArrayList<Album> albumsDeezerr) {
        this.albumsDeezerr = albumsDeezerr;
    }
}
