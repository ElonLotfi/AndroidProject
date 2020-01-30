package com.plfgb.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import com.plfgb.app.Client.Client;
import com.plfgb.app.Client.Connexion;
import com.plfgb.app.Client.Controleur;
import com.plfgb.app.Client.Vue;

public class MainActivity extends AppCompatActivity implements Vue {
    private Controleur controleur ;
    private Client client ;
    private final String SERVER_ADDRESS= "88.181.169.76" ; // Le server utilisé : 88.181.169.76
    private int port=10101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.ok);
        final EditText editText = (EditText) findViewById(R.id.entry);
        init();
        button.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controleur.getClient().logInGame(client.getLogin());
                storeClient();
                try {
                    controleur.sendClient(); // envoyer le client entier au server avec le statue ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);

            }

        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                try {
                    client.setLogin(editText.getText().toString());
                    controleur.resetScore(0);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    client.setLogin(editText.getText().toString());
                    controleur.resetScore(0);
                    if(s.length()!=0){
                        button.setEnabled(true);
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();


                }
            }
        });

    }

    public void init(){
        controleur = new Controleur(this);
        Connexion connexionClient = new Connexion(SERVER_ADDRESS,port,controleur);
        client = new Client(connexionClient,controleur);
        controleur.getConnexion().seConnecter();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        controleur.forceDisconnect(controleur.getClient().getLogin());
    }

    public void storeClient(){
        GlobalState state = ((GlobalState) getApplicationContext());
        state.setControleur(controleur);
        state.setClient(client);
    }

    @Override
    public void displayMessage(String string) {

    }

    @Override
    public void displayToastMessage(final String string) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),string,Toast.LENGTH_LONG).show();
            }
        });
    }




}

