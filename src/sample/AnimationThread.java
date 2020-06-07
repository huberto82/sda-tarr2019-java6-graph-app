package sample;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class AnimationThread extends Thread{
    private Rectangle rectangle;

    public AnimationThread(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    @Override
    public void run() {
       double startX = rectangle.getX();
       double delta = 0;
       while(delta < 100){
           final double d = delta;
           Platform.runLater(() -> rectangle.setX(startX + d));
           delta++;
           try {
               Thread.sleep(10);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
