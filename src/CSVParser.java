import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;

public class CSVParser {
    public static QuadTree parseFromFile(String folder, int numFiles, Pane pane, Boundary boundary) {
        String line = "";
        String cvsSplitBy = " ";
        // Boundary boundary = new Boundary(-10, -10, 20, 20);
        QuadTree quadTree = new QuadTree(boundary, 1, pane);

        for(int i = 0; i < numFiles; i++) {
            String filepath = folder + "/commands" + String.format("%02d" , i) + ".csv";
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] params = line.split(cvsSplitBy);

                    if(params.length < 10) { break; }
                    double theta = Double.parseDouble(params[9]);
                    double beta = Double.parseDouble(params[10]);
                    System.out.println("Theta = " + theta + " , beta = " + beta + "");
                    quadTree.insertPoint(new Point(theta * -100, beta * 100, i));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

       return quadTree;
    }
}
