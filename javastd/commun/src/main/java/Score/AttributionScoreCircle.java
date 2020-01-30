package Score;



import Shape.ShapeChecking;
import Shape.*;
import donnees.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AttributionScoreCircle extends AttributionScore {

    public AttributionScoreCircle(ArrayList<Point> shapePoints){
        super(shapePoints);
        shapeChecking = new ShapeCheckingCircle(getShapePoints());
    }


}
