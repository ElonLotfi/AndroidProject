package com.plfgb.app;

import android.app.Application;

import com.plfgb.app.Client.Client;
import com.plfgb.app.Client.Controleur;

public class GlobalState extends Application {

    private Client client ;
    private Controleur controleur;


    public Client getClient(){
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}
