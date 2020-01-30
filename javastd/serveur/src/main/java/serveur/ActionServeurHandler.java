package serveur;

import Database.Database;
import Joueur.Joueur;
import Round.GameEngine;
import Round.GameResult;
import Score.AttributionScore;
import Score.AttributionScoreCircle;
import Score.AttributionScoreSegment;
import Score.AttributionScoreSquare;
import Shape.ShapeCheckingCircle;
import Shape.ShapeCheckingSegment;
import Shape.ShapeCheckingSquare;
import donnees.Point;
import donnees.TypeForme;

import java.util.ArrayList;

public class ActionServeurHandler implements ActionServeurHandlerI {


    public ActionServeurHandler(){

    }

    @Override
    public void receiveShapeHandler(ArrayList<Point> shapePointDestination,ArrayList<Point> shapePointSource){

        shapePointDestination.addAll(shapePointSource);
    }

    @Override
    public void resetScoreHandler(AttributionScore attributionScore, ArrayList<Point> listOfpoint) {
        listOfpoint= new ArrayList<Point>();
        attributionScore.resetScore();

    }

    @Override
    public void attributionScoreHandler(int score, ArrayList<Point> listOfpoint, AttributionScore attributionScore, TypeForme myforme) {
        System.out.println("******* Your old Score ******* " + score);
        if(myforme == TypeForme.SEGMENT){
            attributionScore = new AttributionScoreSegment(listOfpoint);

        }else if(myforme == TypeForme.CARRE){
            attributionScore = new AttributionScoreSquare(listOfpoint);

        }
        else if(myforme == TypeForme.CERCLE){
            attributionScore = new AttributionScoreCircle(listOfpoint);
        }

        attributionScore.calculateScore();

        System.out.println("the New Score " + score + attributionScore.getScore());

    }

    @Override
    public boolean gameFinished(String joueur, GameResult gameResult, GameEngine gameEngine) {
        int id = gameResult.getIdByLogin(joueur);
        return gameEngine.getGame(id).getGameFinished();
    }

    @Override
    public String findOpponentName(String joueur, GameEngine gameEngine, GameResult gameResult) {
        int newId = gameResult.getIdByLogin(joueur);
        if(joueur.equals(gameEngine.getGame(newId).getPlayer1().getLogin())){
            return gameEngine.getGame(newId).getPlayer2().getLogin();
        }

        return gameEngine.getGame(newId).getPlayer1().getLogin();

    }



    @Override
    public boolean searchingOpponent(Joueur joueur, Database database, GameEngine gameEngine, GameResult gameResult) {
        Joueur opponent = searchingOtherPlayer(joueur, database);
        if (opponent != null && opponent.isReady() && !opponent.isInGame()){
            gameEngine.initGameEngine(gameResult,joueur,opponent);
            return true;
        }
        else {
            return false;

        }

    }

    @Override
    public TypeForme determineShape(ArrayList<Point> shapePoints){
        if(new ShapeCheckingSegment(shapePoints).check()){
            return TypeForme.SEGMENT;
        }else if(new ShapeCheckingSquare(shapePoints).check()){
            return TypeForme.CARRE;
        }else if(new ShapeCheckingCircle(shapePoints).check()){
            return TypeForme.CERCLE;
        }

        return TypeForme.UNDEFINED;
    }


    @Override
    public String sendWinner(GameEngine gameEngine,int gameId) {
        return gameEngine.getGame(gameId).getWinner();
    }

    @Override
    public void forceDisconnectHandler(String client, Database database) {
        if(database.getTableClient().size() != 0){database.getTableClient().get(client).setReady(false);
        }
        database.setoffline(client);
        System.out.println(client + " sign out Now :(");
        System.out.println("*--------Connected Clients -------* \n");
        database.displayDataBase();

    }



    @Override
    public Joueur searchingOtherPlayer(Joueur client,Database database) {
        for(String loginId : database.getTableClient().keySet()){
            System.out.println("opponent searching..."+loginId);
            if(database.getClient(loginId).isOnline() && database.getClient(loginId).isReady()  && !client.getLogin().equals(database.getClient(loginId).getLogin())){
                return database.getClient(loginId);
            }
        }

        return null;

    }
}
