package Joueur;

import donnees.TypeArms;
import donnees.TypeForme;

public class Joueur {

    private String login;
    private TypeArms arm;
    private boolean online;
    private boolean ready;
    private TypeForme forme;
    private boolean isInGame;
    private boolean opponentFound;
    private int score;

    public Joueur(){

    }

    public int getScore() {
        return score;
    }

    public boolean isOpponentFound() {
        return opponentFound;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TypeArms getArm() {
        return arm;
    }

    public void setArm(TypeArms arm) {
        this.arm = arm;
    }

    public boolean isOnline() {
        return online;
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
        isInGame = inGame;
    }

    public void setOpponentFound(boolean opponentFound) {
        this.opponentFound = opponentFound;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
