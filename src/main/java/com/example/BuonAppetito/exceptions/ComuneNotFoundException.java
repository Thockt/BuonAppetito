package com.example.BuonAppetito.exceptions;

public class ComuneNotFoundException extends Exception{

    @Override
    public String getMessage () { return "Comune non trovato!"; }
}
