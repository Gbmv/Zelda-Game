package com.example.codigos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Position extends Application {
    public static void main(String[] args) {
        Position.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {


        VBox vertical = new VBox();
        HBox horizontal = new HBox();
        Scene scene  = new Scene(vertical,300,300);

        Text ele1 = new Text("A");
        Text ele2 = new Text("B");
        Text ele3 = new Text("C");
        Text ele4 = new Text("D");
        Text ele5 = new Text("E");


        vertical.getChildren().add(0,ele4);
        vertical.getChildren().add(ele5);

        // Adiciona os elementos "A" e "C" ao HBox
        horizontal.getChildren().add(0,ele3);
        horizontal.getChildren().add(ele1);

        // Adiciona o HBox à VBox
        vertical.getChildren().add(horizontal);


        vertical.getChildren().add(ele2);


        stage.setScene(scene);
        stage.show();

    }
}

