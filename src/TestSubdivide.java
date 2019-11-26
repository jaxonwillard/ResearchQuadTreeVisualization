import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TestSubdivide extends Rectangle {
    Pane pane;
    double x;
    double y;
    double w;
    double h;

    public TestSubdivide(double x, double y, double w, double h, Pane p){
        super(x,y,w,h);
        this.setFill(Color.WHITE);
        this.pane = p;
        this.pane.getChildren().add(this);
        this.x =x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.setOnMouseClicked(event -> {
            subdivide();
        });
    }
    public void subdivide(){

//        Rectangle nw = new Rectangle(0,0, 200, 200);
//        Rectangle ne = new Rectangle(200, 0, 200, 200);
//        Rectangle sw = new Rectangle(0, 200, 200, 200);
//        Rectangle se = new Rectangle(200, 200, 200, 200);
        TestSubdivide nw = new TestSubdivide(   this.x,            this.y,          this.w/2,     this.h/2,    this.pane);
        TestSubdivide ne = new TestSubdivide(this.w/2+this.x,   this.y,          this.w/2,     this.h/2,   this.pane);
        TestSubdivide sw = new TestSubdivide(   this.x,         this.y+this.h/2, this.w/2,     this.h/2,   this.pane);
        TestSubdivide se = new TestSubdivide(this.w/2+this.x,this.y+this.h/2, this.w/2,     this.h/2,   this.pane);
        nw.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        ne.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        se.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        sw.setFill(Color.color(Math.random(), Math.random(), Math.random()));


        this.pane.getChildren().addAll(nw,ne,sw,se);
    }
}
