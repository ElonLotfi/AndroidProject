package com.plfgb.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.plfgb.app.Client.Vue;
import com.plfgb.app.Game.GameLauncherActivity;


public class MenuActivity extends Activity implements Vue {

    GlobalState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button button = (Button) findViewById(R.id.game);
        final Button buttonTraining = (Button) findViewById(R.id.buttonTraining);
        state = (GlobalState) getApplicationContext();
        if(!state.getControleur().getClient().isOnline()){
            button.setEnabled(false);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), GameLauncherActivity.class);
                startActivity(intent);

            }

        });

        buttonTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),DrawingActivity.class);
                startActivity(intent);

            }

        });




    }


    @Override
    public void onBackPressed(){

    }


    @Override
    public void displayMessage(final String string) {

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
