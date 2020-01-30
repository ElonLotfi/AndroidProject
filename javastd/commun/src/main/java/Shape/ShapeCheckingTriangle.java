package Shape;

import java.util.ArrayList;

import donnees.Point;

public class ShapeCheckingTriangle extends ShapeChecking {


    public ShapeCheckingTriangle(ArrayList<Point> listOfpoint) {
        super(listOfpoint);
        marge=1;
    }

    @Override
    public boolean check() {
        ArrayList<Point> listMiddlePoint = new ArrayList<>();
        for(int i=0;i<=listOfpoint.size()-2;i++){
            Point p1 = listOfpoint.get(i);
            Point p2 = listOfpoint.get(i+1);
            Point middlePoint= new Point((int)(p1.getX()+p2.getX()/2),(int)(p1.getY()+p2.getY()/2));
            listMiddlePoint.add(middlePoint);

        }

        int x=0,y=0;
        for (int i=0 ;i< listMiddlePoint.size();i++) {
            x+= listMiddlePoint.get(i).getX();
            y += listMiddlePoint.get(i).getY();
        }

        Point barycentre = new Point(x/listMiddlePoint.size(),y/listMiddlePoint.size());


        PairPoint distance= new PairPoint(listMiddlePoint.get(0),barycentre);
        int numberOfPointWithSameBarycentre=1;
        int pointGravite = distance.getLength();

        for (int i=1;i<listOfpoint.size();i++) {
            PairPoint t = new PairPoint(listOfpoint.get(i), barycentre);
            if (((pointGravite >= t.getLength()+marge||pointGravite>=t.getLength()-marge)&&(pointGravite<=t.getLength()+marge||pointGravite<=t.getLength()-marge)) && (t.getAngle()>=3.35 && t.getAngle()<=3.38)){

                numberOfPointWithSameBarycentre+=1;

            }
        }

        if(numberOfPointWithSameBarycentre==3) return true;
        return false;
    }
}
