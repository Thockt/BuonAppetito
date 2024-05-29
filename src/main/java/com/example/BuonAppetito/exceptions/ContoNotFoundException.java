package com.example.BuonAppetito.exceptions;

public class ContoNotFoundException extends Exception{

    @Override
    public String getMessage () { return ("Questo conto non esiste"); }
}
