package com.plfgb.app.Game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import com.plfgb.app.Client.Vue;
import com.plfgb.app.DrawingCanvas;
import com.plfgb.app.GlobalState;
import com.plfgb.app.R;

public class GameActivity extends Activity implements Vue {

    private GlobalState state;
    private Button buttonQuitGame ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final TextView home = (TextView)findViewById(R.id.local);
        final TextView away = (TextView)findViewById(R.id.ext);
        final Button buttonGame = (Button) findViewById(R.id.sendGame);
        final DrawingCanvas drawingCanvas = findViewById(R.id.drawingCanvas5);
        state = ((GlobalState) getApplicationContext());
        home.setText(state.getControleur().getClient().getLogin());
        away.setText(state.getControleur().getClient().getOpponentName());

        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    state.getControleur().sendResponseToGame(drawingCanvas.getShapePoint());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                buttonGame.setEnabled(false);
                state.getControleur().sendFinishedGame(state.getControleur().getClient().getLogin());

            }
        });

    }


    @Override
    public void onBackPressed(){
        state.getControleur().getClient().setOpponentFound(false);
        state.getControleur().getClient().setReady(false);
        state.getControleur().getClient().setInGame(false);
        try {
            state.getControleur().sendUpdateClient();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.finish();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
