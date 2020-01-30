package serveur;
import com.corundumstudio.socketio.Configuration;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class Serveur {

    ConnexionServeur connexionServeur;

    public Serveur(ConnexionServeur connexionServeur) {
        this.connexionServeur=connexionServeur;
    }


    public ConnexionServeur getConnexionServeur() {
        return connexionServeur;
    }



    public static final void main(String []args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();

        config.setHostname("88.181.169.76");
        config.setPort(10101);

        ConnexionServeur connexionServeur =new ConnexionServeur(config);

        Serveur serveur = new Serveur(connexionServeur);
        serveur.getConnexionServeur().demarrer();


        System.out.println("fin du main");

    }

}