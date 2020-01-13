import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.util.Formatter;

public class Point extends Circle {
    double[] coordinates = new double[2];
    private int id;
    public Point(double x, double y, int id){
        super(x,y,1.2,Color.BLACK);
        this.coordinates[0]=x;
        this.coordinates[1]=y;
        this.id = id;
    }
    public Point(double limit){
        super(Math.random()*limit, Math.random()*limit,1.2,Color.BLACK);
        this.coordinates[0]=this.getCenterX();
        this.coordinates[1]=this.getCenterY();
    }

    public Point(double x, double y) {
        this(x, y, 0);
    }

    public int getMyId() { return this.id; }
    public double getX() { return this.coordinates[0]; }
    public double getY() { return this.coordinates[1]; }

    @Override
    public String toString(){
        Formatter toReturn = new Formatter();
        return toReturn.format("%d[%.1f,%.1f]", this.id, this.coordinates[0], this.coordinates[1]).toString();
    }
    public boolean compareTo(Point p){
        return this.coordinates[0]==p.coordinates[0] && this.coordinates[1]==p.coordinates[1];}
}
