package Database;
import java.util.HashMap;

import Joueur.Joueur;

public class Database {
    private static HashMap<String, Joueur> tableClient;


    public Database() {
        this.tableClient = new HashMap<>();
    }

    public void updateClient(Joueur joueur) {
        tableClient.put(joueur.getLogin(),joueur);
    }
    public boolean isInGame(String login) {
        return this.tableClient.get(login).isInGame();
    }

    public void setInGame(String login) {
        this.tableClient.get(login).setInGame(true);
    }


    public void addClient(Joueur client) {
        client.setReady(false);
        tableClient.put(client.getLogin(), client);

    }
    public void setoffline(String gameTag){
        tableClient.remove(gameTag);
    }
    public void setFalse(String gameTag){
        tableClient.get(gameTag).setReady(false);
    }

    public HashMap<String, Joueur> getTableClient() {
        return tableClient;
    }

    public Joueur getClient(String key) {
        return tableClient.get(key);
    }

    public String toString() {
        String display = "";
        String ready = "";
        for (String id : tableClient.keySet()) {
            if (tableClient.get(id).isReady()) {
                ready = "I am ready !";
            } else {
                ready = "I am not ready ";
            }
            display += "| Player name " + tableClient.get(id).getLogin() + " : " + ready + " |";

        }
        return "Database " + "\n" + display;
    }

    public void displayDataBase() {
        if (tableClient.size() == 0){
            System.out.println("all clients are offline !");
        }
        for (String id : tableClient.keySet()) {
            System.out.println(tableClient.get(id).getLogin());
        }
    }

}

