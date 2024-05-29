package com.example.BuonAppetito.exceptions;

public class UtenteNotFoundException extends Exception {

    @Override
    public String getMessage () { return ("Utente con questo id non è presente!"); }

}
