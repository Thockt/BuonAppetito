package com.example.BuonAppetito.exceptions;

public class PrenotazioneNotFoundException extends Exception{

    @Override
    public String getMessage () { return ("Questa prenotazione non esiste"); }
}
