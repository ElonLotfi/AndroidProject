package Score;

import Shape.ShapeChecking;
import Shape.ShapeCheckingSegment;
import donnees.Point;
import Shape.ShapeCheckingSquare;

import java.util.ArrayList;

public class AttributionScoreSquare extends AttributionScore {

    public AttributionScoreSquare(ArrayList<Point> shapePoints){
        super(shapePoints);
        shapeChecking = new ShapeCheckingSquare(getShapePoints());
    }
}

