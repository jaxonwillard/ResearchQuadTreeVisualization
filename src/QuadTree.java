import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class QuadTree {
    boolean traverseSet;
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
    public QuadTree(Boundary boundary, int capacity, Pane pane){
        this.traverseSet = false;
        this.boundary = boundary;
        this.capacity = capacity;
        this.pane = pane;
        this.boundary.setFill(Color.WHITE);
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
    public void insertText(Text text){
        this.pane.getChildren().add(text);
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
            System.out.println("This boundary already contains the point " + point.getMyId());
            return;}

        if (this.points.size() < this.capacity && !this.isDivided){
            this.points.add(point);
        }
        else{
            if (!this.isDivided){
                subdivide();
            }
            this.northEast.insertPointHelper(point);
            this.northWest.insertPointHelper(point);
            this.southEast.insertPointHelper(point);
            this.southWest.insertPointHelper(point);
        }
        if (this.isDivided){
            int pointsLength = this.points.size();
            for (int i=0; i<pointsLength; i++){
                Point p = this.points.get(i);
                this.northEast.insertPointHelper(p);
                this.northWest.insertPointHelper(p);
                this.southEast.insertPointHelper(p);
                this.southWest.insertPointHelper(p);
            }
            this.points.clear();
        }
    }

    /**
     * create a new QuadTree for each child, dump everything from points into children
     */
    void subdivide(){
        System.out.println("Now subdividing");

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

    private ArrayList<Point> setTraverseListHelper(){
        ArrayList<Point> traverseList = new ArrayList<>();
        if (this.isDivided){
            traverseList.addAll(this.southWest.setTraverseListHelper());
            traverseList.addAll(this.southEast.setTraverseListHelper());
            traverseList.addAll(this.northWest.setTraverseListHelper());
            traverseList.addAll(this.northEast.setTraverseListHelper());
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
            toReturn.append(toString(tree.northEast, new StringBuilder(), recLevel));
            toReturn.append(toString(tree.northWest, new StringBuilder(), recLevel));
            toReturn.append(toString(tree.southEast, new StringBuilder(), recLevel));
            toReturn.append(toString(tree.southWest, new StringBuilder(), recLevel));
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

    public void saveOrder(String filepath) throws IOException {
        StringBuilder builder = new StringBuilder();
        int numPoints = traverseList.size();
        builder.append("Id,parent\n");

        for(int i = 0; i < numPoints; i++) {
            System.out.println("I am " + traverseList.get(i).getMyId()
            + " and my parent was " + traverseList.get(i > 0 ? i - 1: 0));
            builder.append(traverseList.get(i).getMyId()).append(",")
                    .append(traverseList.get(i > 0 ? i - 1: 0).getMyId()).append("\n");
        }

        Path file = Paths.get(filepath);
        // builder.deleteCharAt(builder.length() - 1);
        Files.write(file, Collections.singleton(builder));
    }

}
