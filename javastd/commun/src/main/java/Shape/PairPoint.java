package Shape;

import donnees.*;

public class PairPoint implements Comparable{

    private Point p1;
    private Point p2;
    private double angle;
    private Point middlePoint;
    private int length;


    public PairPoint(Point p1,Point p2){
        this.p1=p1;
        this.p2=p2;
        calculateAngle();
        calculateLength();
        calculateMiddlePoint();
    }



    @Override
    public boolean equals(Object pairPoint){
        PairPoint pairPoint1 = (PairPoint) pairPoint;
        boolean samePairX = this.getP1().getX() == pairPoint1.getP1().getX() && this.getP2().getX() == pairPoint1.getP2().getX();
        boolean samePairY = this.getP1().getY() == pairPoint1.getP1().getY() && this.getP2().getY() == pairPoint1.getP2().getY();

        return samePairX && samePairY;
    }

    public void calculateAngle(){

        angle =  Math.round((Math.atan2(p2.getY()-p1.getY(),p2.getX()-p1.getX())+Math.PI)*100.0)/100.0;
    }

    public void calculateLength(){

        length= (int) (Math.sqrt((p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getX() - p1.getX()) * (p2.getX() - p1.getX())));

    }

    public void calculateMiddlePoint(){
        middlePoint= new Point((int)(p1.getX()+p2.getX()/2),(int)(p1.getY()+p2.getY()/2));
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Point getMiddlePoint() {
        return middlePoint;
    }

    public void setMiddlePoint(Point middlePoint) {
        this.middlePoint = middlePoint;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    @Override
    public int compareTo(Object o) {
        PairPoint p1 = (PairPoint)o;

        if(this.getAngle()>p1.getAngle()){
            return 1;
        }else if(this.getAngle()==p1.getAngle()){
            return 0;
        }

        return -1;

    }
}
