import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Butterfly extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 250, 200);

        Ellipse body = new Ellipse(125, 75, 10, 30);

        Polygon leftTopWing = new Polygon(20, 60, 80, 15, 125, 75);
        Polygon leftBottomWing = new Polygon(20, 90, 80, 140, 125, 75);
        Polygon rightTopWing = new Polygon(230, 60, 170, 15, 125, 75);
        Polygon rightBottomWing = new Polygon(230, 90, 170, 140, 125, 75);

        Line rightAntenna = new Line(128, 60, 135, 10);
        Line leftAntenna = new Line(122, 60, 115, 10);

        root.getChildren().add(body);
        root.getChildren().add(leftTopWing);
        root.getChildren().add(leftBottomWing);
        root.getChildren().add(rightTopWing);
        root.getChildren().add(rightBottomWing);
        root.getChildren().add(rightAntenna);
        root.getChildren().add(leftAntenna);

        body.setFill(Color.GREENYELLOW);
        leftTopWing.setFill(Color.CYAN);
        leftBottomWing.setFill(Color.CYAN);
        rightTopWing.setFill(Color.CYAN);
        rightBottomWing.setFill(Color.CYAN);
        rightAntenna.setStroke(Color.GREY);
        leftAntenna.setStroke(Color.GREY);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}