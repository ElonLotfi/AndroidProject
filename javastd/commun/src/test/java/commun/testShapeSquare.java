package commun;

import org.junit.Test;

import donnees.*;
import java.util.ArrayList;
import Shape.ShapeCheckingSquare;

import static org.junit.Assert.assertEquals;

public class testShapeSquare {

    @Test
    public void testGoodShapeSquare(){

        ArrayList<Point> pointsSquare = new ArrayList<Point>();

        for( int i=0;i<=100;i++){
            pointsSquare.add(new Point(i,0));
        }

        for( int i=0;i<=100;i++){
            pointsSquare.add(new Point(100,i));
        }

        for( int i=100;i<=0;i--){
            pointsSquare.add(new Point(i,100));
        }

        for( int i=100;i<=0;i--){
            pointsSquare.add(new Point(0,i));
        }

        ShapeCheckingSquare shapeCheckingSquare = new ShapeCheckingSquare(pointsSquare);
        assertEquals(true,shapeCheckingSquare.check());

    }

    @Test
    public void testBadShapeSquare(){

        ArrayList<Point> pointsSquare = new ArrayList<Point>();

        pointsSquare.add(new Point(0,0));
        pointsSquare.add(new Point(57,0));
        pointsSquare.add(new Point(2,724));

        pointsSquare.add(new Point(2,1));
        pointsSquare.add(new Point(57,2));
        pointsSquare.add(new Point(2,3));

        pointsSquare.add(new Point(25,5752));
        pointsSquare.add(new Point(27,3));

        pointsSquare.add(new Point(5,2));
        pointsSquare.add(new Point(12,1));
        pointsSquare.add(new Point(0,0));

        ShapeCheckingSquare shapeCheckingSquare = new ShapeCheckingSquare(pointsSquare);
        assertEquals(false,shapeCheckingSquare.check());

    }



    @Test
    public void testSquareScaleAugmented(){


        ArrayList<Point> pointsSquare = new ArrayList<Point>();


        for( int i=0;i<=200;i++){
            pointsSquare.add(new Point(i,0));
        }

        for( int i=0;i<=200;i++){
            pointsSquare.add(new Point(200,i));
        }

        for( int i=200;i<=0;i--){
            pointsSquare.add(new Point(i,200));
        }

        for( int i=200;i<=0;i--){
            pointsSquare.add(new Point(0,i));
        }

        ShapeCheckingSquare shapeCheckingSquare = new ShapeCheckingSquare(pointsSquare);
        assertEquals(true,shapeCheckingSquare.check());

    }
}
