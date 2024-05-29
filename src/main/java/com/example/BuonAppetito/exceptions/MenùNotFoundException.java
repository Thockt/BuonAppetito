package com.example.BuonAppetito.exceptions;

public class MenùNotFoundException extends Exception{

    @Override
    public String getMessage () { return "Menù non trovato!"; }
}
