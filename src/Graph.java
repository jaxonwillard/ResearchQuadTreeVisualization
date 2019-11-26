import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Graph extends Application {
    public void start(Stage stage){
        BorderPane borderPane = new BorderPane();
        Scene sc = new Scene(borderPane);
        Boundary boundary = new Boundary(0,0,550,550);
        QuadTree qt = new QuadTree(boundary, 2, borderPane);
        borderPane.setCenter(qt.boundary);
        HBox buttons = new HBox();
        Button setTraverse = new Button("Click to set traverse");
        Button traverse = new Button("Click to traverse");
        Button setAndTraverse = new Button("Click for full traversal");
        buttons.getChildren().addAll(traverse, setTraverse, setAndTraverse);
        borderPane.setBottom(buttons);
        stage.setScene(sc);
        stage.show();
        for (int i=0; i<2000; i++){
//            qt.insertPoint(new Point(200 + 100 * Math.sin(i) + Math.random() * 50, Math.random() * 50 + 200 + 100 * Math.cos(i)));
//            qt.insertPoint(new Point(qt.boundary.getWidth()));
//            qt.insertPoint(new Point(i,i));
        }

        borderPane.setOnMousePressed(event -> {
            Point point1 = new Point(event.getX(), event.getY());
            qt.insertPoint(point1);

        });
        AtomicInteger nextIndex = new AtomicInteger();
        AtomicInteger lastIndex = new AtomicInteger(-1);
        setAndTraverse.setOnMouseClicked(event -> {
            qt.setTraverseList();
            for (Point p : qt.traverseList){
                drawLine(nextIndex, lastIndex, qt, borderPane);
            }
        });
        setTraverse.setOnMouseClicked(event -> {
            qt.setTraverseList();
        });


        traverse.setOnMouseClicked(event -> {
            drawLine(nextIndex, lastIndex, qt, borderPane);

        });

    }

    public static void drawLine(AtomicInteger nextIndex, AtomicInteger lastIndex, QuadTree qt, BorderPane borderPane){
        if (nextIndex.get() < qt.traverseList.size()){
            if (lastIndex.get() > -1){
                Line edge = new Line(qt.traverseList.get(lastIndex.get()).coordinates[0], qt.traverseList.get(lastIndex.get()).coordinates[1],
                        qt.traverseList.get(nextIndex.get()).coordinates[0], qt.traverseList.get(nextIndex.get()).coordinates[1]);
                edge.setStroke(Color.WHITE);
                edge.setStrokeWidth(1);
                borderPane.getChildren().add(edge);
            }
            lastIndex.set(nextIndex.get());
            nextIndex.getAndIncrement();
        }

    }

}
