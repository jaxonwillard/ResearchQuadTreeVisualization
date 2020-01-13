import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting main");
        // Boundary boundary = new Boundary(-1, -1, 2, 2);
        // QuadTree quadTree = new QuadTree(boundary, 1);
        double visualWidth = 500;
        double visualHeight = 500;
        Boundary boundary = new Boundary(-6.0, -6.0, 12, 12);
        String input =
                "/Users/steven/Documents/school/honors/mag_phyx_vis/magPhyxVis/data1/commands";
        ArrayList<Point> points = CSVParser.parseFromFile(input, 100);
        ArrayList<Point> normalized = Boundary.normalizePoints(points, visualWidth, visualHeight);
        Boundary surroundingBoundary = Boundary.makeABoundary(normalized);


        QuadTree quadTree = new QuadTree(surroundingBoundary, 1, new BorderPane());

        for(Point point: normalized) {
            quadTree.insertPoint(point);
        }

        quadTree.setTraverseList();

        String output =
                "/Users/steven/Documents/school/honors/mag_phyx_vis/magPhyxVis/data1/commands/zorder.csv";
        quadTree.saveOrder(output);
    }
}
