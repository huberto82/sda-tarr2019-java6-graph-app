package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class RunnableDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600,400, Color.AZURE);
        primaryStage.setScene(scene);
        primaryStage.show();
        //tworzymy klko doanimacji
        Circle circle = new Circle(200,200, 20, Color.ORANGE);
        //dodajemy kółko do root-a sceny
        root.getChildren().add(circle);

        Runnable bouncingAnimation = new Bouncing(circle);
        Thread bouncing = new Thread(bouncingAnimation);
        bouncing.start();
    }
}
