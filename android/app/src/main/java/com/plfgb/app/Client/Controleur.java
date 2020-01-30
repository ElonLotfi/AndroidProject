package com.plfgb.app.Client;

import org.json.JSONException;

import donnees.TypeForme;
import donnees.*;


public class Controleur  {

    private Vue vue ;
    private Client client;
    private Connexion connexion;
    public Controleur(){
    }
    public Controleur (Vue vue){
        this.vue =  vue;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
    public int getId(){return client.getClientId();}

    public void sendResponseToGame(Points shapePoints) throws JSONException {
        connexion.emitSenderShape(getClient().convertToJson());
        connexion.emitShape(shapePoints.convertToJson());
    }

    public void sendSegment(Points shapePoints) throws JSONException {
        connexion.emitShape(shapePoints.convertToJson());
        connexion.emitForme(TypeForme.SEGMENT)
        ;}
    public void sendCarre(Points shapePoints) throws JSONException {
        connexion.emitShape(shapePoints.convertToJson());
        connexion.emitForme(TypeForme.CARRE);
    }
    public void sendCircle(Points shapePoints) throws JSONException {
        connexion.emitShape(shapePoints.convertToJson());
        connexion.emitForme(TypeForme.CERCLE);
    }
    public void sendScore(){
        connexion.emitScore(getClient().getScore());
    }
    public void sendClient() throws JSONException {connexion.emitClient(getClient().convertToJson());}
    public void sendUpdateClient() throws JSONException {
        connexion.emitUpdateClient(getClient().convertToJson());}
    public void searchClientOpponent() throws JSONException {
        connexion.emitSearchOpponent(getClient().convertToJson());
        connexion.emitUpdateClient(getClient().convertToJson());
    };

    public void receivingDisconnection() throws JSONException {
        getClient().setScore(0);
        getClient().setOnline(false);
        sendUpdateClient();

    }

    public void sendFinishedGame(String login){
        connexion.emitGameFinished(login);
    }

    public void sendLeftGame(){
        try {
            connexion.emitLeftGame(getClient().convertToJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendDisconnection(){
        connexion.emitDisconnect(getClient().getLogin());
    }

    public void receivingOpponent() throws JSONException {
        getClient().setOpponentFound(true);
        sendUpdateClient();
    }

    public Vue getVue() {
        return vue;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public void recievingScore(Integer newScore) {

        getClient().setScore(newScore);
        getVue().displayToastMessage("Your score" + newScore);
    }

    public void recievingToastMessage(String message) {
        getVue().displayToastMessage(message);

    }

    public void recievingMessage(String message) {
        getVue().displayMessage(message);

    }

    public void resetScore(int reset) {
        getClient().setScore(reset);
    }
    public void searchClientOpponentName(String login){
        connexion.emitSearchOpponentName(login);
    }
    public void forceDisconnect(String login){connexion.emitDisconnect(login);}
    public void emitState(String login){
        connexion.emitReady(login);}



}

