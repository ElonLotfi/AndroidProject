package commun;

import org.junit.Test;

import java.util.ArrayList;

import Shape.ShapeCheckingTriangle;
import donnees.Point;

import static org.junit.Assert.assertEquals;

public class TestShapeTriangle {

    @Test
    public void testPerfectTriangle(){

        int x=100;
        ArrayList<Point> shapeTriangle= new ArrayList<>();

        for (int i=0;i<=x;i++){
            shapeTriangle.add(new Point(i,0));
        }

        int j=0;
        for (int i=0;i<=x/2;i++){
            shapeTriangle.add(new Point(i,j));
            j++;
        }

        j=50;
        for (int i=x/2;i<=x;i++){
            shapeTriangle.add(new Point(i,j));
            j--;
        }

        ShapeCheckingTriangle shapeCheckingTriangle = new ShapeCheckingTriangle(shapeTriangle);
        assertEquals(true,shapeCheckingTriangle.check());
    }

    @Test
    public void testTriangleNotFinished(){
        int x=100;
        ArrayList<Point> shapeTriangle= new ArrayList<>();

        for (int i=0;i<=x;i++){
            shapeTriangle.add(new Point(i,0));
        }


        int j=50;
        for (int i=x/2;i<=x;i++){
            shapeTriangle.add(new Point(i,j));
            j--;
        }

        ShapeCheckingTriangle shapeCheckingTriangle = new ShapeCheckingTriangle(shapeTriangle);
        assertEquals(false,shapeCheckingTriangle.check());
    }

    @Test
    public void testBadTriangle(){
        int x=100;
        ArrayList<Point> shapeTriangle= new ArrayList<>();

        for (int i=0;i<=x;i++){
            shapeTriangle.add(new Point(i,0));
        }

        ShapeCheckingTriangle shapeCheckingTriangle = new ShapeCheckingTriangle(shapeTriangle);
        assertEquals(false,shapeCheckingTriangle.check());
    }


}

