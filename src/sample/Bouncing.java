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
        double deltaX = 2;
        double deltaY = 2;
        while(true){
            //=========================
            if (node.getCenterX() > scene.getWidth() - node.getRadius() && deltaX > 0){
                deltaX = -deltaX;
            } else
            if (node.getCenterX() < node.getRadius() && deltaX < 0) {
                deltaX = -deltaX;
            }
            if(node.getCenterY() > scene.getHeight() - node.getRadius() && deltaY > 0){
                deltaY = -deltaY;
            } else
            if (node.getCenterY() < node.getRadius() && deltaY < 0){
                deltaY = -deltaY;
            }
            final double dx = deltaX;
            final double dy = deltaY;
            Platform.runLater(() -> {
                node.setCenterX(node.getCenterX() + dx);
                node.setCenterY(node.getCenterY() + dy);
            });
            //============================

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
