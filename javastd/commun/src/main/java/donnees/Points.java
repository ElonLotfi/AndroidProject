package donnees;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Points {
    private ArrayList<Point> points;

    public Points() throws JSONException { points = new ArrayList<Point>();}

    public JSONObject convertToJson() throws JSONException {
        JSONObject jsonPoints= new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(int i =0 ; i < this.getPoints().size(); i++)
        {
            Point pt = this.getPoints().get(i);
            JSONObject p = new JSONObject();
            p.put("x", pt.getX());
            p.put("y", pt.getY());
            jsonArray.put(p);
        }

        jsonPoints.put("points",jsonArray);

        return jsonPoints;

    }

    public ArrayList<Point> getPoints() {
        return points;
    }
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }


}
