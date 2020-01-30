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
import Score.AttributionScoreSegment;


public class DrawingActivity extends Activity implements Vue {

    GlobalState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        state = ((GlobalState) getApplicationContext());
        final DrawingCanvas drawingCanvas = findViewById(R.id.drawingCanvas5);

        final Button button = (Button) findViewById(R.id.button);
        final TextView text = (TextView) findViewById(R.id.textOfsegment);
        final TextView textScore= (TextView) findViewById(R.id.textScore);
        if(state.getClient()!=null){
            textScore.setText("Your score : "+ state.getClient().getScore());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttributionScore scoreSegment = new AttributionScoreSegment(drawingCanvas.getShapePoint().getPoints());
                scoreSegment.calculateScore();
                if(state.getClient()!=null){
                    int newScore = scoreSegment.getScore() + state.getClient().getScore();
                    state.getClient().setScore(newScore);
                }
                Intent intent = new Intent(getApplicationContext(),DrawingSquare.class);
                startActivity(intent);

            }




        });

    }



    @Override
    public void onBackPressed(){
        state.getControleur().getClient().setScore(0);
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
