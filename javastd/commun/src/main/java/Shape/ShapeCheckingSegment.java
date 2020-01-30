package Shape;

import donnees.*;
import java.util.ArrayList;


public class ShapeCheckingSegment extends ShapeChecking {

    public enum TypeSegment {
        HORIZONTAL,
        VERTICAL,
        UNDEFINED;
    }

    private TypeSegment typeSegment;

    public ShapeCheckingSegment(ArrayList<Point> listOfpoint) {
        super(listOfpoint);
        marge=4;
        if(listOfpoint.size()!=0){
            determineSegment();
        }else{
            typeSegment=TypeSegment.UNDEFINED;
        }

    }

    private void calculNumberTimeReachedLimit(Point p1,Point p2){
        if(this.typeSegment==TypeSegment.HORIZONTAL){
            if (calculateYMarge(p1,p2) == marge){
                equalMarge +=1;
            }
        }else{
            if (calculateXMarge(p1,p2) == marge){
                equalMarge +=1;
            }
        }

    }


    public void determineSegment(){
        Point firstPoint= this.listOfpoint.get(0);
        Point lastPoint = this.listOfpoint.get(listOfpoint.size()-1);

        if(firstPoint.getX() == lastPoint.getX() || calculateYMarge(firstPoint,lastPoint) <= marge ){
            typeSegment = TypeSegment.HORIZONTAL;
        }else if(firstPoint.getY() == lastPoint.getY() || calculateXMarge(firstPoint,lastPoint) <= marge){
            typeSegment= TypeSegment.VERTICAL;
        }else{
            typeSegment=  TypeSegment.UNDEFINED;
        }
    }


    @Override
    public boolean check() {
        if(typeSegment==TypeSegment.UNDEFINED) return false;
        for(int i = 0 ;i<listOfpoint.size()-1;i++){
            calculNumberTimeReachedLimit(listOfpoint.get(0),listOfpoint.get(i));
        }

        return true;

    }


}
