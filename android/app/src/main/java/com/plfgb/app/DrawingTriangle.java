package com.plfgb.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.plfgb.app.Client.Vue;
import Score.AttributionScore;
import Score.AttributionScoreTriangle;

public class DrawingTriangle extends Activity implements Vue {
    GlobalState state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_triangle);

        state = ((GlobalState) getApplicationContext());
        final Button buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        final TextView text = (TextView) findViewById(R.id.textOfTriangle);
        final Button quitTraining = (Button) findViewById(R.id.quitTraining);
        final DrawingCanvas drawingCanvas = findViewById(R.id.drawingCanvas5);
        final TextView textScore= (TextView) findViewById(R.id.textScoreTriangle);
        quitTraining.setEnabled(false);
        if(state.getClient()!=null){

            textScore.setText("Your score : "+ state.getClient().getScore());
        }



        buttonTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttributionScore scoreTriangle = new AttributionScoreTriangle(drawingCanvas.getShapePoint().getPoints());
                scoreTriangle.calculateScore();
                if(state.getClient()!=null) {
                    int newScore = scoreTriangle.getScore() + state.getClient().getScore();
                    state.getClient().setScore(newScore);
                    textScore.setText("YOUR FINAL SCORE : "+ state.getClient().getScore());
                }
                quitTraining.setEnabled(true);
                buttonTriangle.setEnabled(false);



            }

        });

        quitTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.getClient().setScore(0);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed(){
        state.getClient().setScore(0);
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(intent);
    }


    @Override
    public void displayMessage(final String string) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),string,Toast.LENGTH_LONG).show();
            }
        });




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