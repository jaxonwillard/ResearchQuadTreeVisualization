import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Boundary extends Rectangle {
    double[] xy = new double[2];
    double[] wh = new double[2];

    Boundary(double x, double y, double w, double h) {
        super(x,y,w,h);
        this.xy[0] = x;
        this.xy[1] = y;
        this.wh[0] = w;
        this.wh[1] = h;
    }

    public static Boundary makeABoundary(ArrayList<Point> points) {
        double x_min = Double.MAX_VALUE;
        double x_max = -Double.MAX_VALUE;
        double y_min = Double.MAX_VALUE;
        double y_max = -Double.MAX_VALUE;

        for(Point point: points) {
            if(point.getX() > x_max) { x_max = point.getX(); }
            if(point.getY() > y_max) { y_max = point.getY(); }
            if(point.getX() < x_min) { x_min = point.getX(); }
            if(point.getY() < y_min) { y_min = point.getY(); }
        }

        double width = x_max - x_min;
        double height = y_max - y_min;

        return new Boundary(x_min, y_min, width, height);
    }

    public static ArrayList<Point> normalizePoints(ArrayList<Point> points, double width, double height) {
        final double epsilon = 2.002;
        Boundary boundary = makeABoundary(points);
        double xoffset = boundary.getX();
        double yoffset = boundary.getY();
        double xscale = width / boundary.getWidth();
        double yscale = height / boundary.getHeight();

        boundary.xy[0] = boundary.getX() - epsilon;
        boundary.xy[1] = boundary.getY() - epsilon;
        boundary.wh[0] = boundary.getWidth() + 2 * epsilon;
        boundary.wh[1] = boundary.getHeight() + 2 * epsilon;

        ArrayList<Point> normalized = new ArrayList<>();
        for(Point point: points) {
            normalized.add(new Point(
                (point.getX() - xoffset) * xscale,
                (point.getY() - yoffset) * yscale,
                    point.getMyId()));
        }

        return normalized;
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

