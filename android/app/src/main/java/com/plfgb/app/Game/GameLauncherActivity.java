package com.plfgb.app.Game;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import com.plfgb.app.Client.Vue;
import com.plfgb.app.GlobalState;
import com.plfgb.app.R;

public class GameLauncherActivity extends Activity implements Vue {

    private TextView textStatusInformation;
    private GlobalState state;
    ThreadSearchOpponent threadSearchOpponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelauncher);

        textStatusInformation = findViewById(R.id.textStatusInformation);
        final Button buttonReady = (Button) findViewById(R.id.buttonReady);
        final Button buttonStopSearch = (Button) findViewById(R.id.buttonStop);
        buttonStopSearch.setEnabled(false);
        state = (GlobalState) getApplicationContext();

        buttonReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textStatusInformation.setText("Waiting an opponent to start...");
                buttonStopSearch.setEnabled(true);
                try {
                    state.getControleur().getClient().setReady(true);
                    state.getControleur().sendUpdateClient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                threadSearchOpponent = new ThreadSearchOpponent(state.getControleur(), GameLauncherActivity.this);
                threadSearchOpponent.execute();


            }
        });

        buttonStopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textStatusInformation.setText("The search has been cancelled");
                threadSearchOpponent.cancel(true);
                buttonStopSearch.setEnabled(false);
                state.getControleur().getClient().setOpponentFound(false);
                state.getControleur().getClient().setReady(false);
                try {
                    state.getControleur().sendUpdateClient();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public void onBackPressed(){
        threadSearchOpponent.cancel(true);
        this.finish();
    }


    @Override
    public void displayToastMessage(final String string) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),string,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void displayMessage(String string) {

    }
}
