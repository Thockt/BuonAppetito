package com.example.BuonAppetito.exceptions;

public class PostoNotFoundException extends Exception{

    @Override
    public String getMessage () { return "Menù non trovato!"; }

}
