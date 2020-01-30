package Score;

import java.util.ArrayList;

import Shape.ShapeChecking;
import Shape.ShapeCheckingCircle;
import donnees.Point;


public abstract class AttributionScore {


    protected int score =0;
    protected int MAX_SCORE=3;
    protected ShapeChecking shapeChecking;
    protected ArrayList<Point> shapePoints;

    protected int limitBeforeAddingMalus = 5;

    public AttributionScore(ArrayList<Point> shapePoints){
        this.score = score ;
        this.shapePoints=shapePoints;
    }

    public void calculateScore() {
        shapeChecking.check();
        if(!shapeChecking.check()){
            this.score = -1;
        }
        else if(shapeChecking.getEqualMarge() > limitBeforeAddingMalus  ){
            this.score = MAX_SCORE - 1;
        }else {
            this.score += MAX_SCORE;
        }
    }


    public int getMAX_SCORE() {
        return MAX_SCORE;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void resetScore(){
        this.score = 0;
    }
    public int getScore(){return this.score;}

    public ArrayList<Point> getShapePoints() {
        return shapePoints;
    }

    public void setShapePoints(ArrayList<Point> shapePoints) {
        this.shapePoints = shapePoints;
    }
}
