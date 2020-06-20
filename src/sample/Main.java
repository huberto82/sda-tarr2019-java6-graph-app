package sample;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //kontener grupujacy elementy grafiki
        Group root = new Group();
        //scene to obiekt zawartości okna, scena, którą można wymienić
        Scene scene = new Scene(root, 600, 400);
        //primaryStage to obiekt okna graficznego w systemie
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graphic app");
        primaryStage.show();

        Rectangle rectangle = new Rectangle(100,100,100,100);
        rectangle.setFill(Color.BEIGE);
        rectangle.setStroke(Color.BROWN);
        rectangle.setStrokeWidth(2);
        root.getChildren().add(rectangle);
        //naiwne i niedzialajace rozwiązanie animacji
//        double startX = rectangle.getX();
//        for (double delta = 0.0; delta < 100; delta++){
//            rectangle.setX(startX + delta);
//            Thread.sleep(10);
//        }

        //Amimacja z użyciem wątku
        AnimationThread anim = new AnimationThread(rectangle);
        //uruchamiamy obiekt klasy Thread tylko przez start
        anim.start();
        //Animacja z użyciem gotowych klas transition
        TranslateTransition move = new TranslateTransition(Duration.millis(1000), rectangle);
        move.setToY(200);
        move.play();

        RotateTransition rotate = new RotateTransition(Duration.millis(1000), rectangle);
    }
}
