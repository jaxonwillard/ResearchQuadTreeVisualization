import javafx.scene.shape.Rectangle;

public class Boundary extends Rectangle {
    double[] startCoordinates;
    double[] endCoordinates;

//    Boundary(double x, double y, double w, double h) {
//        super(x,y,w,h);
//        this.startCoordinates[0] = x;
//        this.startCoordinates[1] = y;
//        this.endCoordinates[0] = w;
//        this.endCoordinates[1] = h;
//    }
    Boundary(double[] startCoordinates, double[] endCoordinates) {
        super(startCoordinates[0], startCoordinates[1], endCoordinates[0], endCoordinates[1]);
        this.startCoordinates = startCoordinates;
        this.endCoordinates = endCoordinates;
    }


    public boolean contains(Point p){
        double px = p.coordinates[0];
        double py = p.coordinates[1];
        double bx = this.startCoordinates[0];
        double by = this.startCoordinates[1];
        double w = this.endCoordinates[0];
        double h = this.endCoordinates[1];

        /*
        returns true if point is within boundaries or is on left or top boundary
         */
        return px >= bx && px < bx + w && py >= by && py < by + h;
    }
}

