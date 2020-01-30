package donnees;


public class Point {


    private int y;
    private int x;
    public Point(){

    }

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
