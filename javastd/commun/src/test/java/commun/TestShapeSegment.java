package commun;

import org.junit.Test;
import Shape.*;

import donnees.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestShapeSegment {

    @Test
    public void testPerfectHorizontalSegment(){

        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int xDepart = 0;
        int xArrivee = 20;
        int y=0;
        for (int i=xDepart;i<= xArrivee;i++) {
            pointsSegment.add(new Point(i, y));

        }

        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(true,segmentCheck.check());

    }

    @Test
    public void testPerfectVerticalSegment(){

        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int yDepart = 0;
        int yArrivee = 20;
        int x=0;
        for (int i=yDepart;i<= yArrivee;i++) {
            pointsSegment.add(new Point(x, i));

        }

        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(true,segmentCheck.check());

    }

    @Test
    public void testBadHorizontalSegment(){
        int badMarge=10;
        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int xDepart = 0;
        int xArrivee = 20;
        int yMarge=0;
        for (int i=xDepart;i <= xArrivee;i++){
            if(i==xArrivee/2){
                yMarge+=badMarge;
            }
            pointsSegment.add(new Point(i,yMarge));

        }
        pointsSegment.add(new Point((int)pointsSegment.get(pointsSegment.size()-1).getX(),yMarge));
        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(false,segmentCheck.check());




    }

    @Test
    public void testBadVerticalSegment(){
        int badMarge=10;
        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int yDepart = 0;
        int yArrivee = 20;
        int xMarge=0;
        for (int i=yDepart;i <= yArrivee;i++){
            if(i==yArrivee/2){
                xMarge+=badMarge;
            }
            pointsSegment.add(new Point(xMarge,i));

        }
        pointsSegment.add(new Point((int)pointsSegment.get(pointsSegment.size()-1).getX(),xMarge));
        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(false,segmentCheck.check());


    }

    @Test
    public void testSegmentVide(){
        ArrayList<Point> pointsSegmentVide = new ArrayList<Point>() ;
        ShapeChecking segmentCheckVide = new ShapeCheckingSegment(pointsSegmentVide);
        assertEquals(false,segmentCheckVide.check());

    }

    @Test
    public void testAcceptableHorizontalSegment(){
        int limit_marge=2;
        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int xDepart = 0;
        int xArrivee = 20;
        int yMarge=0;
        for (int i=xDepart;i <= xArrivee;i++){
            if(i==xArrivee/2){
                yMarge+=limit_marge;
            }
            pointsSegment.add(new Point(i,yMarge));

        }
        pointsSegment.add(new Point((int)pointsSegment.get(pointsSegment.size()-1).getX(),yMarge));
        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(true,segmentCheck.check());

    }

    @Test
    public void testAcceptableVerticalSegment(){
        int limit_marge = 2;
        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int yDepart = 0;
        int yArrivee = 20;
        int xMarge=0;
        for (int i=yDepart;i <= yArrivee;i++){
            if(i==yArrivee/2){
                xMarge+=limit_marge;
            }
            pointsSegment.add(new Point(xMarge,i));

        }
        pointsSegment.add(new Point((int)pointsSegment.get(pointsSegment.size()-1).getX(),xMarge));
        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(true,segmentCheck.check());

    }

    @Test
    public void testSegmentScaleAugmented(){
        ArrayList<Point> pointsSegment = new ArrayList<Point>() ;
        int yDepart = 0;
        int yArrivee = 1000;
        int x=0;
        for (int i=yDepart;i<= yArrivee;i++) {
            pointsSegment.add(new Point(x, i));

        }

        ShapeChecking segmentCheck = new ShapeCheckingSegment(pointsSegment);
        assertEquals(true,segmentCheck.check());

    }


}
