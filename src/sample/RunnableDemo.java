package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RunnableDemo extends Application {
    private int counter = 0;
    private Text counterDisplay = new Text(10,20, "Licznik trafień: ");

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600,400, Color.AZURE);
        primaryStage.setScene(scene);
        primaryStage.show();
        //tworzymy kółko do animacji
        Circle circle = new Circle(200,200, 20, Color.ORANGE);
        //dodajemy kółko do root-a sceny
        root.getChildren().addAll(circle, counterDisplay);
        Runnable bouncingAnimation = new Bouncing(circle);
        Thread bouncing = new Thread(bouncingAnimation);
        //Ustawienie lambdy obsługi zdarzenia kliknięcia myszą
        circle.setOnMouseClicked(event -> {
            if (!bouncing.isAlive())
                bouncing.start();
            else {
                counter++;
                counterDisplay.setText("Licznik trafień: " + counter);
                System.out.println("Wspolrzedne myszy " + event.getX() + " " + event.getY());
            }
        });
    }
}
