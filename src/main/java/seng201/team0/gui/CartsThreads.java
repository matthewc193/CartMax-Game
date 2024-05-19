package seng201.team0.gui;


import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

import java.util.Random;

public class CartsThreads extends Thread {
    InGameScreenController inGameScreenController;

    public  CartsThreads(InGameScreenController inGameScreenController) {
        this.inGameScreenController = inGameScreenController;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            for (int i = 0; i < 6; i++) {
                int randomNumber = random.nextInt(4001 - 2500) + 2500;
                try {
                    // Sleep for 1 second (1000 milliseconds) between animations
                    SequentialTransition translate = inGameScreenController.animateCart2222(inGameScreenController.mineCart, (ImageView) inGameScreenController.getCartImageViews().get(i));
                    if (i != 0) {
                        CartsThreads.sleep(randomNumber);
                    }
                    translate.play();
                } catch (InterruptedException e) {
                    // Handle the interruption appropriately
                    CartsThreads.currentThread().interrupt();
                    System.out.println("Thread was interrupted, stopping animations");
                    break;
                }
            }
        }
    }
}