package com.plfgb.app.Game;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;

import com.plfgb.app.Client.Controleur;
import com.plfgb.app.Game.GameActivity;

public class ThreadSearchOpponent extends AsyncTask<Integer,Void,String> {

    private Controleur controleur;
    private Activity currentActivity;


    public ThreadSearchOpponent(Controleur controleur,Activity currentActivity){
        this.controleur=controleur;
        this.currentActivity=currentActivity;

    }

    @Override
    protected String doInBackground(Integer... integers) {
        while(this.isCancelled()==false){
            try {
                try {
                    Thread.sleep(500); // Time to wait before sending another request of search
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                controleur.searchClientOpponent();
                if(controleur.getClient().isOpponentFound() && controleur.getClient().isReady()){
                    this.cancel(true);
                    controleur.searchClientOpponentName(controleur.getClient().getLogin());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(currentActivity, GameActivity.class);
                    currentActivity.startActivity(intent);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }



}
