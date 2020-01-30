package serveur;

import Database.Database;
import Joueur.Joueur;
import Round.GameEngine;
import Round.GameResult;
import Score.AttributionScore;
import donnees.Point;
import donnees.TypeForme;

import java.util.ArrayList;

public interface ActionServeurHandlerI {


    void receiveShapeHandler(ArrayList<Point> shapePointDestination,ArrayList<Point> shapePointSource);
    void resetScoreHandler(AttributionScore attributionScore, ArrayList<Point> listOfpoint);
    Joueur searchingOtherPlayer(Joueur client,Database database);
    void attributionScoreHandler(int score, ArrayList<Point> listOfpoint, AttributionScore attributionScore, TypeForme myforme);
    boolean gameFinished (String joueur, GameResult gameResult, GameEngine gameEngine);
    String findOpponentName(String joueur, GameEngine gameEngine, GameResult gameResult);
    boolean searchingOpponent(Joueur joueur, Database database, GameEngine gameEngine, GameResult gameResult);

    TypeForme determineShape(ArrayList<Point> shapePoint);
    String sendWinner(GameEngine gameEngine, int gameId);

    void forceDisconnectHandler(String client, Database database);
}
