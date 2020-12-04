package com.java.eval.web.model;

public class Album {

    private Long albumId;
    private String name;
    private Long artistId;

    public Album(String name, Long artistId){
        this.name = name;
        this.artistId = artistId;
    }
}
