import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.util.Formatter;

public class Point extends Circle {
    double[] coordinates = new double[2];
    public Point(double x, double y){
        super(x,y,1,Color.WHITE);
        this.coordinates[0]=x;
        this.coordinates[1]=y;
    }
    public Point(double limit){
        super(Math.random()*limit, Math.random()*limit,1,Color.WHITE);
        this.coordinates[0]=this.getCenterX();
        this.coordinates[1]=this.getCenterY();
    }

    @Override
    public String toString(){
        Formatter toReturn = new Formatter();
        return toReturn.format("[%.1f,%.1f]", this.coordinates[0], this.coordinates[1]).toString();
    }
    public boolean compareTo(Point p){
        return this.coordinates[0]==p.coordinates[0] && this.coordinates[1]==p.coordinates[1];}
}
