package com.example.BuonAppetito.exceptions;

public class PietanzaNotFoundException extends Exception{

    @Override
    public String getMessage () { return "Pietanza non trovata!"; }

}
