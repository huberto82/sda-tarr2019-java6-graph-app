package sample;

import javafx.application.Platform;
import javafx.scene.Node;

public class Bouncing implements Runnable {
    private final Node node;

    public Bouncing(Node node){
        this.node = node;
    }

    @Override
    public void run() {
        int count = 0;
        while(count < 100){
            //to wymusza zmianę współrzędnych w wątku UI - wątku obsługujacym okno
            Platform.runLater(
                    //klasa anonimowa!!!
                    //definicja klasy bez nazwy implementującej interfejs Runnable
                    //z jednoczesnym utworzeniem obiektu tej klasy
                    new Runnable() {
                        @Override
                        public void run() {
                            node.setLayoutX(node.getLayoutX() + 1);
                        }
                    }
            );
            //poniższa lambda jest odpowiednikiem klasy anonimowej powyżej
            //ale zapis jest bardziej zwiezły
            //bo wiele elementów możemy wywnioskować
            //jest odpowiednikiem klasy anonimowej aly tylko gdy implementowany interfejs jest funkcyjny!!!
            Platform.runLater(() -> node.setLayoutX(node.getLayoutX() + 1));
            count++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
