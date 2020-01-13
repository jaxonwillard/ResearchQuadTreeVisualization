import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;

public class CSVParser {
    public static ArrayList<Point> parseFromFile(String folder, int numFiles) {
        String line = "";
        String cvsSplitBy = " ";
        ArrayList<Point> originalPoints = new ArrayList<Point>();

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
                    // quadTree.insertPoint(new Point(theta * 100, beta * 100, i));
                    originalPoints.add(new Point(theta, beta, i));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return originalPoints;

        /*
        ArrayList<Point> normalized = Boundary.normalizePoints(originalPoints, 500, 500);
        for(Point point: normalized) {
            quadTree.insertPoint(point);
        }

       return quadTree;
         */
    }
}
