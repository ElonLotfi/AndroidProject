package Score;

import Shape.ShapeChecking;
import Shape.ShapeCheckingCircle;
import Shape.ShapeCheckingSegment;
import donnees.Point;

import java.util.ArrayList;

public class AttributionScoreSegment extends AttributionScore {

    public AttributionScoreSegment(ArrayList<Point> shapePoints){
        super(shapePoints);
        shapeChecking = new ShapeCheckingSegment(getShapePoints());
    }
}
