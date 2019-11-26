import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle {
    double[] coordinates = new double[2];
    public Point(double x, double y){
        super(x,y,2,Color.WHITE);
        this.coordinates[0]=x;
        this.coordinates[1]=y;
    }
    public Point(double limit){
        super(Math.random()*limit, Math.random()*limit,2,Color.WHITE);
        this.coordinates[0]=this.getCenterX();
        this.coordinates[1]=this.getCenterY();
    }

    @Override
    public String toString(){
        return "[" + this.coordinates[0] + ", " + this.coordinates[1] + "]";
    }
}
