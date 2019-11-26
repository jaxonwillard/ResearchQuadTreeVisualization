import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class QuadTree {

    Boundary boundary;
    int capacity;
    ArrayList<Point> traverseList;
    ArrayList<Point> points = new ArrayList<>(0);
    QuadTree northWest;
    QuadTree northEast;
    QuadTree southWest;
    QuadTree southEast;
    boolean isDivided;
    Pane pane;
    public QuadTree(Boundary b, int c, Pane p){
        this.boundary = b;
        this.capacity = c;
        this.pane = p;
        this.boundary.setFill(Color.BLACK);
        this.boundary.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
    }

    /**
     * Add point to pane and insert point into QuadTree
     * @param point
     */
    public void insertPoint(Point point){
        this.pane.getChildren().add(point);
        insertPointHelper(point);

    }

    /**
     * Insert point into correct node in QuadTree, if QuadTree is at capacity, subdivide
     * @param point
     */
    public void insertPointHelper(Point point){
        if (!this.boundary.contains(point)){
            return;}
        if (this.points.size() < this.capacity){
            this.points.add(point);
        } else{
            if (!this.isDivided){
                subdivide();
            }
            this.northEast.insertPointHelper(point);
            this.northWest.insertPointHelper(point);
            this.southEast.insertPointHelper(point);
            this.southWest.insertPointHelper(point);
        }
        if (this.isDivided){
            for (int i=0; i<this.points.size(); i++){
                Point p = this.points.remove(i);
                this.northEast.insertPointHelper(p);
                this.northWest.insertPointHelper(p);
                this.southEast.insertPointHelper(p);
                this.southWest.insertPointHelper(p);
            }
        }
    }

    public void subdivide(){
        double x = this.boundary.xy[0];
        double y = this.boundary.xy[1];
        double w = this.boundary.wh[0];
        double h = this.boundary.wh[1];

        Boundary nw = new Boundary(x, y, w/2, h/2);
        Boundary ne = new Boundary(x+w/2, y, w/2, h/2);
        Boundary sw = new Boundary(x, y+h/2, w/2, h/2);
        Boundary se = new Boundary(x+w/2, y+h/2, w/2, h/2);

        this.northEast = new QuadTree(ne, this.capacity, this.pane);
        this.northWest = new QuadTree(nw, this.capacity, this.pane);
        this.southEast = new QuadTree(se, this.capacity, this.pane);
        this.southWest = new QuadTree(sw, this.capacity, this.pane);
        this.isDivided=true;


        this.northWest.boundary.setFill(Color.TRANSPARENT);
        this.northEast.boundary.setFill(Color.TRANSPARENT);
        this.southEast.boundary.setFill(Color.TRANSPARENT);
        this.southWest.boundary.setFill(Color.TRANSPARENT);
        this.pane.getChildren().addAll(northWest.boundary, northEast.boundary, southWest.boundary, southEast.boundary);
    }

    public ArrayList<Point> setTraverseListHelper(){
        ArrayList<Point> traverseList = new ArrayList<>();
        if (this.isDivided){
            traverseList.addAll(this.southWest.setTraverseListHelper());
            traverseList.addAll(this.southEast.setTraverseListHelper());
            traverseList.addAll(this.northWest.setTraverseListHelper());
            traverseList.addAll(this.northEast.setTraverseListHelper());
        }
        traverseList.addAll(points);
        return traverseList;
    }
    public void setTraverseList(){
        this.traverseList = setTraverseListHelper();
        for (Point p : this.traverseList){
            System.out.println(p);
        }
    }






}
