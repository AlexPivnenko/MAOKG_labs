package lab3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Computer extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 700, 300);

        //Support Arc
        Arc support1 = new Arc(79, 61, 11, 11, 150, 220);
        support1.setStroke(Color.BLACK);
        support1.setStrokeWidth(2);
        support1.setFill(Color.WHITE);
        root.getChildren().add(support1);

        //Support Rectangles
        Rectangle support2 = new Rectangle(52, 70, 55, 5);
        support2.setStroke(Color.BLACK);
        support2.setStrokeWidth(2);
        support2.setFill(Color.WHITE);
        root.getChildren().add(support2);
        Rectangle support3 = new Rectangle(36, 73, 83, 19);
        support3.setStroke(Color.BLACK);
        support3.setStrokeWidth(2);
        support3.setFill(Color.LIGHTBLUE);
        root.getChildren().add(support3);

        //Buttons
        Rectangle button1 = new Rectangle(40, 78, 10, 3);
        button1.setStroke(Color.BLACK);
        button1.setStrokeWidth(1);
        button1.setFill(Color.WHITE);
        root.getChildren().add(button1);
        Rectangle button2 = new Rectangle(106, 78, 10, 3);
        button2.setStroke(Color.BLACK);
        button2.setStrokeWidth(1);
        button2.setFill(Color.WHITE);
        root.getChildren().add(button2);
        Rectangle button3 = new Rectangle(93, 78, 10, 3);
        button3.setStroke(Color.BLACK);
        button3.setStrokeWidth(1);
        button3.setFill(Color.WHITE);
        root.getChildren().add(button3);

        // White Border of a Laptop
        Path whiteBorder = new Path();
        whiteBorder.setStrokeWidth(2);
        MoveTo moveTo_11 = new MoveTo(40, 10);
        QuadCurveTo curve_11 = new QuadCurveTo(71, 4, 104, 2);
        QuadCurveTo curve_12 = new QuadCurveTo(106, 28, 112, 59);
        QuadCurveTo curve_13 = new QuadCurveTo(79, 62, 46, 67);
        QuadCurveTo curve_14 = new QuadCurveTo(45, 38, 40, 10);
        whiteBorder.getElements().addAll(moveTo_11, curve_11, curve_12, curve_13, curve_14);
        whiteBorder.setFill(Color.WHITE);
        root.getChildren().add(whiteBorder);

        // Blue Screen
        Path bluescreen = new Path();
        bluescreen.setStrokeWidth(2);
        MoveTo moveTo_21 = new MoveTo(49, 16);
        QuadCurveTo curve_21 = new QuadCurveTo(72, 10, 97, 9);
        QuadCurveTo curve_22 = new QuadCurveTo(101, 29, 102, 51);
        QuadCurveTo curve_23 = new QuadCurveTo(79, 57, 55, 58);
        QuadCurveTo curve_24 = new QuadCurveTo(49, 34, 49, 16);
        bluescreen.getElements().addAll(moveTo_21, curve_21, curve_22, curve_23, curve_24);
        bluescreen.setFill(Color.LIGHTBLUE);
        root.getChildren().add(bluescreen);

        //Eyes
        Ellipse eye1 = new Ellipse(66, 27, 2, 5);
        eye1.setStroke(Color.BLACK);
        eye1.setStrokeWidth(1);
        eye1.setFill(Color.WHITE);
        root.getChildren().add(eye1);
        Ellipse eye2 = new Ellipse(83, 25, 2, 5);
        eye2.setStroke(Color.BLACK);
        eye2.setStrokeWidth(1);
        eye2.setFill(Color.WHITE);
        root.getChildren().add(eye2);
        Ellipse eyeApple1 = new Ellipse(66, 27, 1, 1);
        eyeApple1.setFill(Color.BLACK);
        root.getChildren().add(eyeApple1);
        Ellipse eyeApple2 = new Ellipse(83, 25, 1, 1);
        eyeApple2.setFill(Color.BLACK);
        root.getChildren().add(eyeApple2);

        //Mouth
        Path smile = new Path();
        MoveTo moveTo_31 = new MoveTo(57, 35);
        smile.setStrokeWidth(1);
        QuadCurveTo curve_31 = new QuadCurveTo(66, 44, 80, 41);
        QuadCurveTo curve_32 = new QuadCurveTo(77, 48, 71, 41.8);
        smile.getElements().addAll(moveTo_31, curve_31, curve_32);
        smile.setFill(Color.WHITE);
        root.getChildren().add(smile);
        Path mouth = new Path();
        MoveTo moveTo_32 = new MoveTo(80, 41);
        QuadCurveTo curve_33 = new QuadCurveTo(88, 37, 93, 31);
        mouth.setStrokeWidth(1);
        mouth.getElements().addAll(moveTo_32, curve_33);
        root.getChildren().add(mouth);

        //Arms
        Line arm1 = new Line(45, 50, 28, 60);
        arm1.setStroke(Color.BLACK);
        arm1.setStrokeLineCap(StrokeLineCap.ROUND);
        arm1.setStrokeWidth(3);
        root.getChildren().add(arm1);
        Line arm2 = new Line(109, 43, 131, 49);
        arm2.setStroke(Color.BLACK);
        arm2.setStrokeLineCap(StrokeLineCap.ROUND);
        arm2.setStrokeWidth(3);
        root.getChildren().add(arm2);

        int cycleCount = 2; //animation
        int time = 3000;


        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);


        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(50);
        translateTransition.setToX(500);
        translateTransition.setCycleCount(cycleCount);
        translateTransition.setAutoReverse(true);


        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(0.1);
        scaleTransition.setToY(0.1);
        scaleTransition.setCycleCount(cycleCount);
        scaleTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                scaleTransition,
                rotateTransition,
                translateTransition
        );

        parallelTransition.setCycleCount(Timeline.INDEFINITE);
         parallelTransition.play();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Computer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
