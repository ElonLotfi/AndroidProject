package commun;

import Score.AttributionScoreSegment;
import org.junit.Test;
import Score.AttributionScore;

import donnees.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestScore {

    @Test
    public void testBestScore(){

        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int xDepart = 0;
        int xArrivee = 20;
        int y=0;
        for (int i=xDepart;i<= xArrivee;i++) {
            pointsSegment.add(new Point(i, y));

        }

        AttributionScore attributionScore = new AttributionScoreSegment(pointsSegment);
        attributionScore.calculateScore();
        assertEquals(3,attributionScore.getScore());

    }

    @Test
    public void testBadScore(){

        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int xDepart = 0;
        int xArrivee = 20;
        int yMarge=0;
        for (int i=xDepart;i<= xArrivee;i++) {
            if(i==xArrivee/2){
                yMarge+=10;
            }
            pointsSegment.add(new Point(i, yMarge));

        }

        AttributionScore attributionScore = new AttributionScoreSegment(pointsSegment);
        attributionScore.calculateScore();
        assertEquals(-1,attributionScore.getScore());

    }

    @Test
    public void testAcceptableScore(){

        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int xDepart = 0;
        int xArrivee = 20;
        int yMarge=0;
        for (int i=xDepart;i<= xArrivee;i++) {
            if(i==xArrivee/2){
                yMarge+=4;
            }
            pointsSegment.add(new Point(i, yMarge));

        }

        AttributionScore attributionScore = new AttributionScoreSegment(pointsSegment);
        attributionScore.calculateScore();
        assertEquals(2,attributionScore.getScore());

    }

}
