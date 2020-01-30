package Shape;
import donnees.*;
import java.util.ArrayList;


public abstract class ShapeChecking {
    protected int marge = 2;
    protected int equalMarge = 0;
    protected ArrayList<Point> listOfpoint ;

     public ShapeChecking(ArrayList<Point> listOfpoint){
         this.listOfpoint = listOfpoint;

     }


    protected double calculateYMarge(Point p1 , Point p2){
        return (Math.abs(p2.getY() - p1.getY()));
    }

    protected double calculateXMarge(Point p1 , Point p2){
        return (Math.abs(p2.getX() - p1.getX()));
    }

    public abstract boolean check();


    public int getEqualMarge() {
        return equalMarge;
    }

}
