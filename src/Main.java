import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting main");
        double visualWidth = 500;
        double visualHeight = 500;
        String input =
                "/Users/steven/Documents/school/honors/mag_phyx_vis/magPhyxVis/data1/commands";

        ArrayList<Point> points = CSVParser.parseFromFile(input, 100);
        ArrayList<Point> normalized = Boundary.normalizePoints(points, visualWidth, visualHeight);
        // Boundary surroundingBoundary = Boundary.makeABoundary(normalized);
        Boundary surroundingBoundary = new Boundary(0, 0, visualWidth + 2, visualHeight + 2);

        System.out.println("Boundary from " + surroundingBoundary.getX() + ": " + surroundingBoundary.getY()
          + " to " + surroundingBoundary.getX() + surroundingBoundary.getWidth() + ": "
          + surroundingBoundary.getY() + surroundingBoundary.getHeight());

        QuadTree quadTree = new QuadTree(surroundingBoundary, 1, new BorderPane());

        for(Point point: normalized) {
            System.out.println("Inserting point " + point.getMyId() + " at coordinates [" + point.getX() + ": "
              + point.getY() + "]");
            if(!quadTree.insertPoint(point)) {
                System.out.println("Didn't insert that last one");
            }
            // System.out.println("Size: " + quadTree.points.size());
        }

        quadTree.setTraverseList();

        String output =
                "/Users/steven/Documents/school/honors/mag_phyx_vis/magPhyxVis/data1/commands/zorder.csv";
        quadTree.saveOrder(output);
    }
}
