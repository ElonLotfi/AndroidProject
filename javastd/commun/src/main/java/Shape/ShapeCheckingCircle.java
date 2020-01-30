package Shape;
import donnees.*;
import java.util.ArrayList;

public class ShapeCheckingCircle extends ShapeChecking {


    public ShapeCheckingCircle(ArrayList<Point> listOfpoint) {
        super(listOfpoint);
    }

    @Override
    public boolean check() {
        ArrayList<Point> list = new ArrayList<>();
        Point p1 = listOfpoint.get(0);
        Point p2 = listOfpoint.get(1);
        PairPoint pairPoint= new PairPoint(p1,p2);
        pairPoint.calculateMiddlePoint();;
        list.add(pairPoint.getMiddlePoint());

        int j = 0;

      for (int i=2; i < listOfpoint.size();i++) {

          Point p = listOfpoint.get(i);
          PairPoint pairPoint1 = new PairPoint(p, list.get(j));
          j++;
          list.add(pairPoint1.getMiddlePoint());
      }

       int x=0,y=0;
        for (int i=0 ;i< list.size();i++) {
             x+= list.get(i).getX();
            y += list.get(i).getY();
        }
        Point barycentre = new Point(x/list.size(),y/list.size());

        PairPoint distance= new PairPoint(p1,barycentre);
        int rayon = distance.getLength();

        for (int i=1;i<listOfpoint.size();i++) {
            PairPoint t = new PairPoint(listOfpoint.get(i), barycentre);
            if (!((rayon >= t.getLength()+marge||rayon>=t.getLength()-marge)&&(rayon<=t.getLength()+marge||rayon<=t.getLength()-marge))) {
                return false;
            }
        }

        return true;
    }
}
