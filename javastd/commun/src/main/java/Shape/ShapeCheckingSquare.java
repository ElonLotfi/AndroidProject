package Shape;
import donnees.*;
import java.util.ArrayList;
import java.util.Collections;

public class ShapeCheckingSquare extends ShapeChecking{

    public ShapeCheckingSquare(ArrayList<Point> listOfpoint) {
        super(listOfpoint);
    }

    @Override
    public boolean check() {
        ArrayList<PairPoint> pairPoints = new ArrayList<PairPoint>();

        // Calculate angle, middle point and length for each pair of Point
        for (int i=0;i<=listOfpoint.size()-1;i+=20){
            for (int j=0;j<=listOfpoint.size()-1 ;j++){
                Point p1 = listOfpoint.get(i);
                Point p2 = listOfpoint.get(j);

                pairPoints.add(new PairPoint(p1,p2));
            }
        }

        // find a pair of Point which have same length and same middle Point to find 2 orthogonal diagonals
        ArrayList<PairPoint> pairPointsSameLengthSameMiddlePoint = new ArrayList<PairPoint>();
        for(int i=0;i<=pairPoints.size()-1;i++){
            for (int j=0;j<=pairPoints.size()-1;j++){
                boolean sameLength = pairPoints.get(i).getLength() == pairPoints.get(j).getLength() ;
                boolean sameX = (pairPoints.get(i).getMiddlePoint().getX() == pairPoints.get(j).getMiddlePoint().getX());
                boolean sameY = (pairPoints.get(i).getMiddlePoint().getY() == pairPoints.get(j).getMiddlePoint().getY());
                boolean sameMiddlePoint = sameX && sameY;

                if(sameLength && sameMiddlePoint && (!pairPoints.get(i).equals(pairPoints.get(j)))){
                    pairPointsSameLengthSameMiddlePoint.add(pairPoints.get(i));
                    pairPointsSameLengthSameMiddlePoint.add(pairPoints.get(j));
                }
            }

        }

        if(pairPointsSameLengthSameMiddlePoint.size()==0) return false;
        Collections.sort(pairPointsSameLengthSameMiddlePoint);

        // find 2 values which have angle = PI/2
        int values =0;
        for (int i=0;i<= pairPointsSameLengthSameMiddlePoint.size()-1;i++){
            if(pairPointsSameLengthSameMiddlePoint.get(i).getAngle()==Math.round((Math.PI/2)*100.0)/100.0){
                values+=1;
                if(values ==2){
                    return true;
                }
            }
        }
        return false;
    }
}
