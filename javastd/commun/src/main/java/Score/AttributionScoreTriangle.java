package Score;

import java.util.ArrayList;

import Shape.ShapeCheckingTriangle;
import donnees.Point;

public class AttributionScoreTriangle extends AttributionScore {
    public AttributionScoreTriangle(ArrayList<Point> shapePoints) {
        super(shapePoints);
        shapeChecking = new ShapeCheckingTriangle(getShapePoints());
    }
}
