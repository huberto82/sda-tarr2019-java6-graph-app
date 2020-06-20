package sample;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Bouncing implements Runnable {
    private final Circle node;
    private final Scene scene;

    public Bouncing(Circle node){
        this.node = node;
        this.scene = node.getParent().getScene();
    }

    @Override
    public void run() {
        double delta = 1;
        while(true){
            if (node.getCenterX() > scene.getWidth() - node.getRadius()){
                delta = -1;
            }
            if (node.getCenterX() < node.getRadius()) {
                delta = 1;
            }
            final double dx = delta;
            Platform.runLater(() -> node.setCenterX(node.getCenterX() + dx));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
