import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicInteger;
public class Graph extends Application {
    public void start(Stage stage){
        AtomicInteger textIteration = new AtomicInteger(1);
        BorderPane borderPane = new BorderPane();
        Scene sc = new Scene(borderPane);
        Boundary boundary = new Boundary(0,0,500,500);
        QuadTree qt = new QuadTree(boundary, 1, borderPane);
        borderPane.setCenter(qt.boundary);
        HBox buttons = new HBox();
        Button printTree = new Button("Print Tree");
        Button setTraverse = new Button("Set Traverse Path");
        Button traverse = new Button("Traverse");
        Button setAndTraverse = new Button("Full Traversal");
        buttons.getChildren().addAll(setTraverse, traverse, setAndTraverse, printTree);
        borderPane.setBottom(buttons);
        stage.setScene(sc);
        stage.show();
        for (int i=0; i<0; i++){
            qt.insertPoint(new Point(qt.boundary.getWidth()));
        }

        printTree.setOnMouseClicked(event -> {
            System.out.println(qt);
        });
        borderPane.setOnMousePressed(event -> {
            if (!qt.traverseSet){
            Point point1 = new Point(event.getX(), event.getY());
            qt.insertPoint(point1);}
            else{
                Text text = new Text();
                text.setX(event.getX());
                text.setY(event.getY());
                text.setText("" + textIteration.getAndIncrement());
                qt.insertText(text);
            }
        });
        AtomicInteger nextIndex = new AtomicInteger();
        AtomicInteger lastIndex = new AtomicInteger(-1);
        setAndTraverse.setOnMouseClicked(event -> {
            qt.setTraverseList();
            for (Point p : qt.traverseList){
                drawLine(nextIndex, lastIndex, qt, borderPane);
            }
            qt.traverseSet = true;
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
                edge.setStroke(Color.BLACK);
                edge.setStrokeWidth(.75);
                borderPane.getChildren().add(edge);
            }
            lastIndex.set(nextIndex.get());
            nextIndex.getAndIncrement();
        }
    }
}
