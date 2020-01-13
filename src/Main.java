import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting main");
        // Boundary boundary = new Boundary(-1, -1, 2, 2);
        // QuadTree quadTree = new QuadTree(boundary, 1);
        Boundary boundary = new Boundary(-10, -10, 20, 20);
        String input =
                "/Users/steven/Documents/school/honors/mag_phyx_vis/magPhyxVis/data1/commands";
        QuadTree quadTree = CSVParser.parseFromFile(input, 100, new BorderPane(), boundary);
        quadTree.setTraverseList();

        String output =
                "/Users/steven/Documents/school/honors/mag_phyx_vis/magPhyxVis/data1/commands/zorder.csv";
        quadTree.saveOrder(output);
    }
}
