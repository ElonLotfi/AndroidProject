package Round;
import Joueur.Joueur;
import donnees.TypeArms;
import donnees.TypeForme;


public class GameResult {

    private Joueur player1;
    private Joueur player2;
    private TypeForme forme;
    private TypeForme formeBis;
    private int gameId  ;
    private String winner ;
    private static int id = 100 ;
    private String nameOfGame;
    private Boolean gameFinished;



    public GameResult(){
        this.gameId = id++;;
    }
    public GameResult(Joueur client,Joueur client1){
        this.gameId = id ++;
        this.player1 = client;
        this.player2 = client1;
        this.forme = TypeForme.UNDEFINED ;
        this.formeBis = TypeForme.UNDEFINED;
        this.nameOfGame = "no game";
        this.gameFinished = false;
    }

    public void setFormePlayers(TypeForme forme,TypeForme formeBis){
        this.forme = forme;
        this.formeBis = formeBis;

    }
    public int getIdByLogin(String login) {
        if(this.player1.getLogin().equals(login) ||this.player2.getLogin().equals(login)){
            return this.gameId;
        }
        else {return 0;}
    }

    public void setFormePlayer1(TypeForme forme) {
        this.forme = forme;
    }

    public void setFormePlayer2(TypeForme formeBis) {
        this.formeBis = formeBis;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    public void setPlayer1(Joueur player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Joueur player2) {
        this.player2 = player2;
    }

    public Joueur getPlayer1() {
        return player1;
    }

    public Joueur getPlayer2() {
        return player2;
    }

    public void updateArmPlayer1(){
        if(this.forme == TypeForme.SEGMENT){
            this.player1.setArm(TypeArms.Feuille);
        }
        else if (this.forme == TypeForme.CARRE){
            this.player1.setArm(TypeArms.Ciseaux);
        }
        else if (this.forme == TypeForme.CERCLE){
            this.player1.setArm(TypeArms.Pierre);
        }
        else if(this.forme == TypeForme.UNDEFINED){
            this.player1.setArm(TypeArms.UNDEFINED);
        }

    }
    public void updateArmPlayer2(){
        if(this.formeBis == TypeForme.SEGMENT){
            this.player2.setArm(TypeArms.Feuille);
        }
        else if (this.formeBis == TypeForme.CARRE){
            this.player2.setArm(TypeArms.Ciseaux);
        }
        else if (this.formeBis == TypeForme.CERCLE){
            this.player2.setArm(TypeArms.Pierre);
        }
        else if(this.formeBis == TypeForme.UNDEFINED){
            this.player2.setArm(TypeArms.UNDEFINED);
        }

    }



    public void updateWinner() {
        if (player1.getArm() == TypeArms.Pierre && player2.getArm() == TypeArms.Ciseaux) {
            setWinner(player1.getLogin());
        } else if (player1.getArm() == TypeArms.Feuille && player2.getArm() == TypeArms.Pierre) {
            setWinner(player1.getLogin());
        } else if (player1.getArm() == TypeArms.Ciseaux && player2.getArm() == TypeArms.Feuille) {
            setWinner(player1.getLogin());

        } else if (player2.getArm() == TypeArms.Pierre && player1.getArm() == TypeArms.Ciseaux) {
            setWinner(player2.getLogin());
        } else if (player2.getArm() == TypeArms.Feuille && player1.getArm() == TypeArms.Pierre) {
            setWinner(player2.getLogin());
        } else if (player2.getArm() == TypeArms.Ciseaux && player1.getArm() == TypeArms.Feuille) {
            setWinner(player2.getLogin());

        } else if (player1.getArm() == TypeArms.UNDEFINED && (player2.getArm() == TypeArms.Feuille || player2.getArm() == TypeArms.Ciseaux || player2.getArm() == TypeArms.Pierre)) {
            setWinner(player2.getLogin());
        } else if (player2.getArm() == TypeArms.UNDEFINED && (player1.getArm() == TypeArms.Feuille || player1.getArm() == TypeArms.Ciseaux || player1.getArm() == TypeArms.Pierre)) {
            setWinner(player2.getLogin());
        }
        else if(player1.getArm() ==TypeArms.UNDEFINED && player2.getArm() == TypeArms.UNDEFINED){
            setWinner("Draw !!!");
        }
        else if(player1.getArm() ==TypeArms.Ciseaux && player2.getArm() == TypeArms.Ciseaux){
            setWinner("Draw !!!");
        }
        else if(player1.getArm() ==TypeArms.Pierre && player2.getArm() == TypeArms.Pierre){
            setWinner("Draw !!!");
        }
        else if(player1.getArm() ==TypeArms.Feuille && player2.getArm() == TypeArms.Feuille){
            setWinner("Draw !!!");
        }
    }


    public TypeForme getForme() {
        return forme;
    }

    public TypeForme getFormeBis() {
        return formeBis;
    }



    public int getGameId() {
        return gameId;
    }

    public void setNameOfGame(String firstPlayer,String secondPlayer) {
        this.nameOfGame = "| " + firstPlayer + " Vs " + secondPlayer + "|";
    }

    public String getNameOfGame() {
        return nameOfGame;
    }

    public void setGameFinished(Boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public Boolean getGameFinished() {
        return gameFinished;
    }





}
