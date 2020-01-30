package com.plfgb.app.Client;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import donnees.TypeForme;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Connexion {

    private String adresseHost;
    private int port;
    private Socket socketClient;
    private Controleur controleur;
    public Connexion(String adresseHost, int port, final Controleur controleur){
        this.adresseHost=adresseHost;
        this.port=port;
        this.controleur=controleur;
        this.controleur.setConnexion(this);

        try {
            socketClient = IO.socket("http://"+this.adresseHost+":"+port);

            System.out.println("on s'abonne à la connection / déconnection ");

            socketClient.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" on est connecté ! et on s'identifie ");
                    controleur.getClient().setOnline(true);



                }
            });

            socketClient.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" !! on est déconnecté !! ");
                    socketClient.disconnect();
                    socketClient.close();
                    try {
                        controleur.receivingDisconnection();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            socketClient.on("identifiant", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String message = (String) objects[0];
                    controleur.recievingToastMessage(message);
                }
            });

            socketClient.on("emitShape", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String message = (String) objects[0];
                    controleur.recievingToastMessage(message);

                }
            });

            socketClient.on("emitCarre", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String message = (String) objects[0];
                    controleur.recievingToastMessage(message);

                }
            });
            socketClient.on("emitCircle", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String message = (String) objects[0];
                    controleur.recievingToastMessage(message);

                }
            });

            socketClient.on("clientScore", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    Integer newScore = (Integer) objects[0];
                    controleur.recievingScore(newScore);


                }
            });


            socketClient.on("OpponentName", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String string = (String) objects[0];
                    if(string != null){controleur.getClient().setOpponentName(string);
                    }
                    else{controleur.getClient().setOpponentName("not found");}
                }
            });


            socketClient.on("searchOpponent", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    boolean gameStart = (boolean) objects[0];
                    if(gameStart){
                        try {
                            controleur.receivingOpponent();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }
            });

            socketClient.on("gameProgress", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String messageGameProgress = (String) objects[0];
                    controleur.recievingToastMessage(messageGameProgress);


                }
            });

            socketClient.on("winner", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String winner = (String) objects[0];
                    if(controleur.getClient().getLogin().equals(winner)){
                        controleur.recievingToastMessage("You You win the game, you can PRESS BACK to QUIT the game...");
                    }
                    else if(winner.equals("Draw !!!")){
                        controleur.recievingToastMessage("Draw, you can PRESS BACK to QUIT the game...");

                    }
                    else {
                        controleur.recievingToastMessage("You lose the game, , you can PRESS BACK to QUIT the game...");
                    }

                }
            });


            socketClient.on("clientLeftGame", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    String message = (String) objects[0];
                    controleur.recievingToastMessage(message);


                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public void seConnecter() {

        System.out.print("CONNECT_SERVER Trying to connect...");
        socketClient.connect();
    }


    public void emitShape(JSONObject jsonPoints) {
        getSocketClient().emit("emitShape",jsonPoints);

    }

    public void emitScore(int score) {
        getSocketClient().emit("clientScore",score);

    }

    public void emitForme(TypeForme forme) {
       getSocketClient().emit("typeForme",forme);

    }

    public void emitSenderShape(JSONObject client){
        getSocketClient().emit("senderShape",client);
    }

    public void emitSearchOpponent(JSONObject client){
        getSocketClient().emit("searchOpponent",client);
    }

    public void emitClient(JSONObject client){
        getSocketClient().emit("client",client);
    }

    public void emitUpdateClient(JSONObject client){
        getSocketClient().emit("updateClient",client);
    }
    public void emitLeftGame(JSONObject client){
        getSocketClient().emit("clientLeftGame",client);
    }
    public void emitSearchOpponentName(String login){
        getSocketClient().emit("OpponentName",login);
    }

    public void emitGameFinished(String login){
        getSocketClient().emit("winner",login);
    }
    public void emitDisconnect(String login){
        getSocketClient().emit("forceDisconnect",login);
    }

    public void emitReady(String login){getSocketClient().emit("updateReady",login);}

    public void emitOpponentName(String login){getSocketClient().emit("OpponentName",login);}

}
