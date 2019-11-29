import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class QuadTree {

    Boundary boundary;
    int capacity;
    int dimensions;
    ArrayList<Point> traverseList;
    ArrayList<Point> points = new ArrayList<>(0);
    QuadTree[] children;
//    QuadTree northWest;
//    QuadTree northEast;
//    QuadTree southWest;
//    QuadTree southEast;
    boolean isDivided;
    Pane pane;
    public QuadTree(Boundary boundary, int capacity, int dimensions,  Pane pane){
        this.boundary   = boundary;
        this.capacity   = capacity;
        this.pane       = pane;
        this.dimensions = dimensions;
        this.children   = new QuadTree[(int)Math.pow(2, dimensions)];
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
     * Check that point is in boundary and that point is not equal to any points currently in the tree.
     * Insert point into correct node in QuadTree, if QuadTree is at capacity, subdivide
     * @param point
     */
    public void insertPointHelper(Point point){
        for (Point p : this.points){
            if (p.compareTo(point)) return;
        }
        if (!this.boundary.contains(point)){
            return;}

        if (this.points.size() < this.capacity && !this.isDivided){
            this.points.add(point);
        }
        else{
            if (!this.isDivided){
                subdivide();
            }
            for (QuadTree q : this.children){
                q.insertPointHelper(point);
            }
//            this.northEast.insertPointHelper(point);
//            this.northWest.insertPointHelper(point);
//            this.southEast.insertPointHelper(point);
//            this.southWest.insertPointHelper(point);
        }
        if (this.isDivided){
            int pointsLength = this.points.size();
            for (int i=0; i<pointsLength; i++){
                Point p = this.points.get(i);
//                this.northEast.insertPointHelper(p);
//                this.northWest.insertPointHelper(p);
//                this.southEast.insertPointHelper(p);
//                this.southWest.insertPointHelper(p);
                for (QuadTree q : this.children){
                    q.insertPointHelper(p);
                }
            }
            this.points.clear();
        }
    }

    /**
     * create a new QuadTree for each child, dump everything from points into children
     */
    void subdivide(){

        double x = this.boundary.startCoordinates[0];
        double y = this.boundary.startCoordinates[1];
        double w = this.boundary.endCoordinates[0];
        double h = this.boundary.endCoordinates[1];

        double[] sCoord = {x,y};
        double[] eCoord = {w/2, h/2};
        Boundary nw = new Boundary(sCoord, eCoord);
        sCoord[0] = x/2;
        Boundary ne = new Boundary(sCoord,eCoord);
        sCoord[0] = x;
        sCoord[1] = y/2;
        Boundary sw = new Boundary(sCoord, eCoord);
        sCoord[0] = x/2;
        Boundary se = new Boundary(sCoord, eCoord);
//        double[] newEndCoordinates = new double[this.boundary.endCoordinates.length];
//        for (int i=0; i<newEndCoordinates.length; i++){
//            newEndCoordinates[i] = this.boundary.endCoordinates[i]/2;
//        }
        this.children[0] = new QuadTree(nw, this.capacity, this.dimensions, this.pane);
        this.children[1] = new QuadTree(ne, this.capacity, this.dimensions, this.pane);
        this.children[2] = new QuadTree(sw, this.capacity, this.dimensions, this.pane);
        this.children[3] = new QuadTree(se, this.capacity, this.dimensions, this.pane);
        for (QuadTree q : this.children){
            q.boundary.setFill(Color.TRANSPARENT);

            this.pane.getChildren().add(q.boundary);
        }

//
//
//
//        for (int i=0; i<this.children.length; i++){
//            double[] tempArray = this.boundary.startCoordinates;
//            if (i==1 || i == 3){tempArray[0] += newEndCoordinates[0]/2;}
//            if (i==2 || i == 3){tempArray[1] += newEndCoordinates[1]/2;}
//            Boundary boundary = new Boundary(tempArray, newEndCoordinates);
//            this.children[i] = new QuadTree(boundary, this.capacity, this.dimensions, this.pane);
//            boundary.setFill(Color.TRANSPARENT);
//            this.pane.getChildren().add(boundary);
//
//        }

//        this.northEast = new QuadTree(ne, this.capacity, this.dimensions,  this.pane);
//        this.northWest = new QuadTree(nw, this.capacity, this.dimensions, this.pane);
//        this.southEast = new QuadTree(se, this.capacity, this.dimensions, this.pane);
//        this.southWest = new QuadTree(sw, this.capacity, this.dimensions,this.pane);
        this.isDivided=true;

//        this.northWest.boundary.setFill(Color.TRANSPARENT);
//        this.northEast.boundary.setFill(Color.TRANSPARENT);
//        this.southEast.boundary.setFill(Color.TRANSPARENT);
//        this.southWest.boundary.setFill(Color.TRANSPARENT);
//        this.pane.getChildren().addAll(northWest.boundary, northEast.boundary, southWest.boundary, southEast.boundary);
    }

    private ArrayList<Point> setTraverseListHelper(){
        ArrayList<Point> traverseList = new ArrayList<>();
        if (this.isDivided){
//            traverseList.addAll(this.southWest.setTraverseListHelper());
//            traverseList.addAll(this.southEast.setTraverseListHelper());
//            traverseList.addAll(this.northWest.setTraverseListHelper());
//            traverseList.addAll(this.northEast.setTraverseListHelper());
            for (int i=0; i<this.children.length; i++){
                traverseList.addAll(this.children[i].setTraverseListHelper());
            }
        }
        traverseList.addAll(this.points);
        return traverseList;
    }
    void setTraverseList(){
        this.traverseList = setTraverseListHelper();
        for (Point p : this.traverseList){
            System.out.println(p);
        }
    }


    @Override
    public String toString(){return toString(this, new StringBuilder(), "");}
    private String toString(QuadTree tree, StringBuilder toReturn, String recLevel){
        recLevel = recLevel + "= ";
        if (tree.isDivided){
//            toReturn.append(toString(tree.northEast, new StringBuilder(), recLevel));
//            toReturn.append(toString(tree.northWest, new StringBuilder(), recLevel));
//            toReturn.append(toString(tree.southEast, new StringBuilder(), recLevel));
//            toReturn.append(toString(tree.southWest, new StringBuilder(), recLevel));
            for (QuadTree q : this.children){
                toReturn.append(toString(q, new StringBuilder(), recLevel));
            }
        }
        else {
            if (tree.points.size() > 0) toReturn.append("\n");
            for (int i=0; i<tree.points.size(); i++){
                if (i < tree.points.size()-1)toReturn.append(recLevel + tree.points.get(i) + "\n");
                else toReturn.append(recLevel + tree.points.get(i));
            }
        }
        return toReturn.toString();
    }








}
