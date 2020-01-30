package com.plfgb.app.Client;
import donnees.TypeArms;
import donnees.TypeForme;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {
    private static final int count = 0 ;
    private Controleur controleur;
    private Connexion connexion;
    private String login ;
    private int score;
    private static int clientId=0 ;
    private boolean online;
    private boolean opponentFound;
    private boolean ready;
    private TypeArms arm;
    private String opponentName;
    private TypeForme forme;
    private boolean isInGame ;


    public Client() {
    }

    public Client(Connexion connexion,Controleur ctrl) {

        this.controleur = ctrl;
        this.controleur.setClient(this);
        this.connexion=connexion;
        this.score= 0;
        this.clientId += 1 ;
        this.online = false ;
        this.ready = false;
        this.isInGame=false;
        this.opponentFound=false;
        this.arm = arm;
    }

    public JSONObject convertToJson() throws JSONException {

        JSONObject joueur = new JSONObject();
        joueur.put("login", this.login);
        joueur.put("opponentFound",this.opponentFound);
        joueur.put("ready", this.ready);
        joueur.put("score", this.score);
        joueur.put("online",this.online);
        return joueur;

    }


    public boolean isOnline() {
        return online;
    }

    public void logInGame(String login) {
        this.connexion.getSocketClient().emit("identifiant",login);

    }

    public void setLogin(String login) throws Exception {
        if(login=="") throw new Exception("Vous devez rentrez un login valide");
        this.login = login;

    }
    public String getLogin() {
        return this.login;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public int getClientId() {
        return clientId;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }


    public boolean isOpponentFound() {
        return opponentFound;
    }

    public void setOpponentFound(boolean opponentFound) {
        this.opponentFound = opponentFound;
    }

    public void setArm(TypeArms arm) {
        this.arm = arm;
    }

    public TypeArms getArm() {
        return arm;
    }
    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
    public TypeForme getForme() {
        return forme;
    }

    public void setForme(TypeForme forme) {
        this.forme = forme;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        this.isInGame = inGame;
    }


}
