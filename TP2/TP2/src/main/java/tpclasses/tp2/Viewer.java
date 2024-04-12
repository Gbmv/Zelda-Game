package tpclasses.tp2;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Viewer extends Application {
    public static void main(String[] args) {
        Viewer.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        int weight = 640;
        int height = 440;

        Pane root = new Pane();
        Scene scene = new Scene(root, weight, height);
        Canvas canvas = new Canvas(weight, height);
        GraphicsContext context = canvas.getGraphicsContext2D();


        //                                  Background
        Image background = new Image("file:///C:/Users/gabri/Desktop/UDEM/Hiver-2024/IFT-1025/TP2/TP2/bg.png");
        ImageView backgroundViwer = new ImageView(background);

        // Dimensions of the viwer
        backgroundViwer.setFitWidth(640);
        backgroundViwer.setFitHeight(440);

        // Define the transition of the image
        TranslateTransition traslationImage = new TranslateTransition(Duration.seconds(30), backgroundViwer);

        // Define how much the image will move
        traslationImage.setByX(-200);
        // Define how much the transition will appears
        traslationImage.setCycleCount(TranslateTransition.INDEFINITE);


        // Adding to the root all the nodes
        root.getChildren().add(canvas);
        root.getChildren().add(backgroundViwer);
        stage.setTitle("UNO reverse Flappy Enemy VS Hero with GUNS ");
        stage.setScene(scene);
        stage.show();
        traslationImage.play();
    }
}
