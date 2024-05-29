package com.example.BuonAppetito.exceptions;

public class RistoranteNotFoundException extends Exception{

    @Override
    public String getMessage () { return ("Questo ristorante non esiste"); }
}
