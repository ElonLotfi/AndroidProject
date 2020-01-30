package serveur;

import Database.Database;
import Joueur.Joueur;
import Round.GameEngine;
import Round.GameResult;
import Score.AttributionScore;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import donnees.Point;
import donnees.Points;
import donnees.TypeForme;

import java.util.ArrayList;

public class ConnexionServeur {

    Configuration config;
    SocketIOServer serveur;
    private AttributionScore attributionScore ;
    private ArrayList<Point> listOfpoint = new ArrayList<Point>();
    private TypeForme myforme ;
    private Database database = new Database();
    private  GameResult gameResult ;
    private  Joueur senderShape;
    private  String winner ;
    private GameEngine gameEngine = new GameEngine() ;
    private  int idOfGAME;
    private ActionServeurHandler actionServeurHandler = new ActionServeurHandler();
    private ArrayList<SocketIOClient> socketIOClients = new ArrayList<SocketIOClient>();
    private  String theWinner;


    final Object attenteConnexion = new Object();

    public ConnexionServeur(final Configuration config){

        // creation du serveur
        this.config=config;
        serveur = new SocketIOServer(config);

        // Objet de synchro
        System.out.println("préparation du listener");

        // on accept une connexion
        serveur.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("connexion de "+socketIOClient.getRemoteAddress());

            }
        });

        serveur.addEventListener("identifiant", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String user, AckRequest ackRequest) throws Exception {
                System.out.println("===> Welcome  ::::" + user);
                socketIOClients.add(socketIOClient);
                socketIOClient.sendEvent("identifiant","welcome  "+user);
            }

        });

        serveur.addEventListener("emitShape", Points.class, new DataListener<Points>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Points arrayList, AckRequest ackRequest) throws Exception {
                System.out.println("Array list verification started ...");
                actionServeurHandler.receiveShapeHandler(listOfpoint,arrayList.getPoints());
                 theWinner = new String();
                 theWinner = updateGame(senderShape);
                 for(SocketIOClient sc : socketIOClients){
                     sc.sendEvent("gameProgress","==> "+senderShape.getLogin()+" played an "+ senderShape.getArm().getName() +" shape");
                 }


            }

        });

        serveur.addEventListener("senderShape", Joueur.class, new DataListener<Joueur>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Joueur player, AckRequest ackRequest) throws Exception {
                System.out.println("client who send the shape... ::::" +player.getLogin());
                senderShape=player;
            }

        });

        serveur.addEventListener("emitCarre", Points.class, new DataListener<Points>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Points arrayList, AckRequest ackRequest) throws Exception {
                System.out.println("===>Array list verification started ...");
                actionServeurHandler.receiveShapeHandler(listOfpoint,arrayList.getPoints());
                socketIOClient.sendEvent("emitCarre","le dessin a ete recu  "  );


            }

        });
        serveur.addEventListener("emitCircle", Points.class, new DataListener<Points>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Points arrayList, AckRequest ackRequest) throws Exception {
                System.out.println("===>Array list verification started ...");
                actionServeurHandler.receiveShapeHandler(listOfpoint,arrayList.getPoints());
                socketIOClient.sendEvent("emitCircle","le dessin a ete recu  "  );

            }

        });

        serveur.addEventListener("clientScore", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Integer score, AckRequest ackRequest) throws Exception {
                actionServeurHandler.attributionScoreHandler(score,listOfpoint,attributionScore,myforme);
                socketIOClient.sendEvent("clientScore" ,score + attributionScore.getScore());
                actionServeurHandler.resetScoreHandler(attributionScore,listOfpoint);
            }

        });

        serveur.addEventListener("typeForme", TypeForme.class, new DataListener<TypeForme>() {
            @Override
            public void onData(SocketIOClient socketIOClient, TypeForme forme, AckRequest ackRequest) throws Exception {
                System.out.println("===> la forme est   ::::" + forme);
                setMyforme(forme);
            }

        });

        serveur.addEventListener("client", Joueur.class, new DataListener<Joueur>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Joueur player, AckRequest ackRequest) throws Exception {
                System.out.println("client added to database ::::" +player.getLogin());
                database.addClient(player);
                System.out.println(database.toString());

            }

        });

        serveur.addEventListener("searchOpponent", Joueur.class, new DataListener<Joueur>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Joueur player, AckRequest ackRequest) throws Exception {
                gameResult = new GameResult();
                if(actionServeurHandler.searchingOpponent(player,database,gameEngine,gameResult)){
                    socketIOClient.sendEvent("searchOpponent" ,true);
                }else{

                    socketIOClient.sendEvent("searchOpponent" ,false);
                }
                System.out.println(database.toString());

            }

        });



        serveur.addEventListener("updateClient", Joueur.class, new DataListener<Joueur>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Joueur player, AckRequest ackRequest) throws Exception {
                database.updateClient(player);
                System.out.println("client update... ::::" +player.getLogin());


            }

        });

        serveur.addEventListener("OpponentName", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String joueur, AckRequest ackRequest) throws Exception {
                String opponentName = actionServeurHandler.findOpponentName(joueur,gameEngine,gameResult);
                socketIOClient.sendEvent("OpponentName" ,opponentName);
            }

        });


        serveur.addEventListener("forceDisconnect", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String user, AckRequest ackRequest) throws Exception {
                actionServeurHandler.forceDisconnectHandler(user,database);
            }

        });

        serveur.addEventListener("winner", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String client, AckRequest ackRequest) throws Exception {
                if(actionServeurHandler.gameFinished(client,gameResult,gameEngine)){
                    for(SocketIOClient sc: socketIOClients){
                        sc.sendEvent("winner" ,gameEngine.getGame(gameResult.getIdByLogin(client)).getWinner());
                    }
                }

            }

        });



    }


    public void setServeur(SocketIOServer serveur) {
        this.serveur = serveur;
    }

    public SocketIOServer getServeur() {
        return serveur;
    }

    public void demarrer() {
        serveur.start();
        System.out.println("en attente de connexion");
        synchronized (attenteConnexion) {
            try {
                attenteConnexion.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("erreur dans l'attente");
            }
        }

        System.out.println("Une connexion est arrivée, on arrête");
        serveur.stop();

    }


    public void setMyforme(TypeForme myforme) {
        this.myforme = myforme;
    }

    public String updateGame(Joueur sender) {

        int gameId = gameResult.getIdByLogin(sender.getLogin());
        System.out.println("New game: " + gameEngine.getGame(gameId).getNameOfGame());
        System.out.println("Game id : [" + gameId + " ]  " + gameEngine.getGame(gameId).getNameOfGame() + " reply received from : " + sender.getLogin());

        if (sender.getLogin().equals(gameEngine.getGame(gameId).getPlayer1().getLogin())) {
            senderShape=gameEngine.getGame(gameId).getPlayer1();
            gameEngine.getGame(gameId).setFormePlayer1(actionServeurHandler.determineShape(listOfpoint));
            System.out.println(gameEngine.getGame(gameId).getPlayer1().getLogin() + " =>" + gameEngine.getGame(gameId).getForme().toString());

        } else if (sender.getLogin().equals(gameEngine.getGame(gameId).getPlayer2().getLogin())) {
            senderShape=gameEngine.getGame(gameId).getPlayer2();
            gameEngine.getGame(gameId).setFormePlayer2(actionServeurHandler.determineShape(listOfpoint));
            System.out.println(gameEngine.getGame(gameId).getPlayer2().getLogin() + " =>" + gameEngine.getGame(gameId).getFormeBis().toString());

        }
        if (sender.getLogin().equals(gameEngine.getGame(gameId).getPlayer1().getLogin())) {
            senderShape=gameEngine.getGame(gameId).getPlayer1();
            gameEngine.getGame(gameId).updateArmPlayer1();
            System.out.println(gameEngine.getGame(gameId).getPlayer1().getLogin() + " =>" + gameEngine.getGame(gameId).getPlayer1().getArm());

        } else if (sender.getLogin().equals(gameEngine.getGame(gameId).getPlayer2().getLogin())) {
            senderShape=gameEngine.getGame(gameId).getPlayer2();
            gameEngine.getGame(gameId).updateArmPlayer2();
            System.out.println(gameEngine.getGame(gameId).getPlayer2().getLogin() + " =>" + gameEngine.getGame(gameId).getPlayer2().getArm());


        }
        if (gameEngine.getGame(gameId).getPlayer1().getArm() != null && gameEngine.getGame(gameId).getPlayer2().getArm() != null) {
            gameEngine.getGame(gameId).setGameFinished(true);
            gameEngine.updateWinner(gameId);
            winner = gameEngine.getGame(gameId).getWinner();
            System.out.println("End of the Game , The winner is : " + gameEngine.getGame(gameId).getWinner());


        } else {
            System.out.println(" *___________Game in progress____________*");

        }
        return null;
    }






}




