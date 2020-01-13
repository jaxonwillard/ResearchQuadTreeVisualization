import javafx.scene.shape.Rectangle;

public class Boundary extends Rectangle {
    double[] xy = new double[2];
    double[] wh = new double[2];

    Boundary(double x, double y, double w, double h) {
        // super(x,y,w,h);
        this.xy[0] = x;
        this.xy[1] = y;
        this.wh[0] = w;
        this.wh[1] = h;
    }

    public boolean contains(Point p){
        double px = p.coordinates[0];
        double py = p.coordinates[1];
        double bx = this.xy[0];
        double by = this.xy[1];
        double w = this.wh[0];
        double h = this.wh[1];

        /*
        returns true if point is within boundaries or is on left or top boundary
         */
        return px >= bx && px < bx + w && py >= by && py < by + h;
    }
}

