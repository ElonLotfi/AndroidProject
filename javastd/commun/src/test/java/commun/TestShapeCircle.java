package commun;

import org.junit.Test;

import Shape.ShapeCheckingCircle;
import donnees.*;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

public class TestShapeCircle {

    @Test
     public void testPerfectCircle() {

        ArrayList<Point> pointsCircle = new ArrayList<Point>();

        pointsCircle.add(new Point(-1,-3));
        pointsCircle.add(new Point(1,-3));
        pointsCircle.add(new Point(-3,-1));
        pointsCircle.add(new Point(3,-1));
        pointsCircle.add(new Point(-3,1));
        pointsCircle.add(new Point(1,3));
        pointsCircle.add(new Point(3,1));
        pointsCircle.add(new Point(-1,3));


        ShapeCheckingCircle shapeCheckingCircle = new ShapeCheckingCircle(pointsCircle);
        assertEquals(true,shapeCheckingCircle.check());



    }


    @Test
    public void testBadCircle() {

        ArrayList<Point> pointsCircle = new ArrayList<Point>();

        pointsCircle.add(new Point(-1,-3));
        pointsCircle.add(new Point(1,-3));
        pointsCircle.add(new Point(-3,-1));
        pointsCircle.add(new Point(3,-1));
        pointsCircle.add(new Point(-3,1));
        pointsCircle.add(new Point(1,3));
        pointsCircle.add(new Point(3,10));
        pointsCircle.add(new Point(-1,3));

        ShapeCheckingCircle shapeCheckingCircle = new ShapeCheckingCircle(pointsCircle);
        assertEquals(false,shapeCheckingCircle.check());



    }


    @Test
    public void testPerfectCirclewithMarge() {

        ArrayList<Point> pointsCircle = new ArrayList<Point>();
        for(int i=0;i<100;i++){
        int marge_de_2=(int)(Math.random()*5)+1;
        pointsCircle.add(new Point(-1,-3));
        pointsCircle.add(new Point(1,-3));
        pointsCircle.add(new Point(-3,-1));
        pointsCircle.add(new Point(1,-1));
        pointsCircle.add(new Point(-3,1));
        pointsCircle.add(new Point(1,marge_de_2));
        pointsCircle.add(new Point(marge_de_2,1));
        pointsCircle.add(new Point(-1,marge_de_2));


        ShapeCheckingCircle shapeCheckingCircle = new ShapeCheckingCircle(pointsCircle);
        assertEquals(true,shapeCheckingCircle.check());}



    }

    @Test
    public void testBadCirclewithMarge() {

        ArrayList<Point> pointsCircle = new ArrayList<Point>();
        for(int i=0;i<100;i++){
            int marge_de_2=(int)(Math.random()*14)+6;

            pointsCircle.add(new Point(-1,-3));
            pointsCircle.add(new Point(1,-3));
            pointsCircle.add(new Point(-3,-1));
            pointsCircle.add(new Point(1,-1));
            pointsCircle.add(new Point(-3,1));
            pointsCircle.add(new Point(1,marge_de_2));
            pointsCircle.add(new Point(marge_de_2,1));
            pointsCircle.add(new Point(-1,marge_de_2));


            ShapeCheckingCircle shapeCheckingCircle = new ShapeCheckingCircle(pointsCircle);
            assertEquals(false,shapeCheckingCircle.check());}



    }

    @Test // Echelle différente
    public void testCircleScaleAugmented() {


        ArrayList<Point> pointsCircle = new ArrayList<Point>();
        for(int i=0;i<100;i++){

            pointsCircle.add(new Point(-120,-40));
            pointsCircle.add(new Point(-120,40));
            pointsCircle.add(new Point(-104,-72));
            pointsCircle.add(new Point(-104,72));
            pointsCircle.add(new Point(-72,-104));
            pointsCircle.add(new Point(-40,-120));
            pointsCircle.add(new Point(-40,120));
            pointsCircle.add(new Point(-40,120));
            pointsCircle.add(new Point(72,-104));
            pointsCircle.add(new Point(72,104));
            pointsCircle.add(new Point(104,-72));
            pointsCircle.add(new Point(104,72));
            pointsCircle.add(new Point(120,-40));
            pointsCircle.add(new Point(120,40));

            ShapeCheckingCircle shapeCheckingCircle = new ShapeCheckingCircle(pointsCircle);
            assertEquals(false,shapeCheckingCircle.check());}



    }
}
