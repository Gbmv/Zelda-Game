package tpclasses.tp2;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Viewer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int width = 640;
        int height = 440;

        Pane root = new Pane();
        Scene scene = new Scene(root, width, height);

        // Load background images
        Image backgroundImage = new Image("file:///C:/Users/gabri/Desktop/UDEM/Hiver-2024/IFT-1025/TP2/TP2/bg.png");
        ImageView backgroundViewer1 = new ImageView(backgroundImage);
        ImageView backgroundViewer2 = new ImageView(backgroundImage);

        // Set the positions of the image views
        backgroundViewer1.setTranslateX(0);
        backgroundViewer2.setTranslateX(width);

        // Add the image views to the root pane
        root.getChildren().addAll(backgroundViewer1, backgroundViewer2);

        // Define the transition for moving the images
        TranslateTransition translationImage1 = new TranslateTransition(Duration.seconds(30), backgroundViewer1);
        translationImage1.setFromX(0);
        translationImage1.setToX(-width);
        translationImage1.setInterpolator(Interpolator.LINEAR);
        translationImage1.setCycleCount(TranslateTransition.INDEFINITE);
        translationImage1.play();

        TranslateTransition translationImage2 = new TranslateTransition(Duration.seconds(30), backgroundViewer2);
        translationImage2.setFromX(width);
        translationImage2.setToX(0);
        translationImage2.setInterpolator(Interpolator.LINEAR);
        translationImage2.setCycleCount(TranslateTransition.INDEFINITE);
        translationImage2.play();

        stage.setTitle("UNO reverse Flappy Enemy VS Hero with GUNS ");
        stage.setScene(scene);
        stage.show();
    }
}
