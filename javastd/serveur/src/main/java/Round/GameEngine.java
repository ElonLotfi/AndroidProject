package Round;

import Joueur.Joueur;
import donnees.TypeForme;

import java.util.HashMap;

public class GameEngine {

    private HashMap<Integer,GameResult> listGame ;

    public GameEngine(){
        this.listGame = new HashMap<Integer, GameResult>();
    }

    public void addGame(GameResult gameResult){
        listGame.put(gameResult.getGameId(),gameResult);
    }

    public GameResult getGame(int idGame){
        return listGame.get(idGame);
    }

    public void initGameEngine(GameResult gameResult,Joueur firstPlayer, Joueur secondPlayer) {

        gameResult.setPlayer1(firstPlayer);
        gameResult.setPlayer2(secondPlayer);
        gameResult.getPlayer1().setReady(false);
        gameResult.getPlayer2().setReady(false);
        gameResult.getPlayer1().setInGame(true);
        gameResult.getPlayer2().setInGame(true);
        gameResult.setNameOfGame(firstPlayer.getLogin(),secondPlayer.getLogin());
        gameResult.setGameFinished(false);
        this.addGame(gameResult);
        System.out.println("Opponent found : "+secondPlayer.getLogin());
    }

    public void checkAndUpdate(int id,TypeForme forme){
        if (listGame.get(id).getPlayer1().getForme() == null){
            listGame.get(id).getPlayer1().setForme(forme);
        }else if(listGame.get(id).getPlayer1().getForme() != null) {
            listGame.get(id).getPlayer2().setForme(forme);
        }
    }


    public void updateArms(int challengeId,String login){
        if(listGame.get(challengeId).getPlayer1().getLogin().equals(login)){
            listGame.get(challengeId).updateArmPlayer1();
        }else if(listGame.get(challengeId).getPlayer2().getLogin().equals(login)){
            listGame.get(challengeId).updateArmPlayer2();
        }
    }


    public void updateWinner(int gameId){
        listGame.get(gameId).updateWinner();
        }

    public String  getWinner(int gameId){
        return listGame.get(gameId).getWinner();

    }

    @Override
    public String toString() {
        String display = "";
        for (int id : listGame.keySet()) {
            display += "id game" + listGame.get(id).getGameId() + "@@@";

        }
        return display;
    }


}
