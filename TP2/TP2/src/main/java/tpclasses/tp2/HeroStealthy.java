package tpclasses.tp2;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

public class HeroStealthy extends Characters{

    // Atributs
    int money;

    //Getters

    public int getMoney() {
        return money;
    }

    // Setters
    public void setMoney(int money) {
        this.money = money;
    }

    public HeroStealthy() {
        super(50, "file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroSthy.webp",
                100, 200);
        this.money = 5;
    }

//    public void createHeroStealhy(Pane root){
//        Image heroSthy = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroSthy.png");
//        ImageView heroSthyvViwer = new ImageView(heroSthy);
//        heroSthyvViwer.setFitHeight(50);
//        heroSthyvViwer.setFitWidth(50);
//        TranslateTransition translationHeroSthy = new TranslateTransition(Duration.seconds(3),heroSthyvViwer);
//        translationHeroSthy.setFromY(50);
//        translationHeroSthy.setToY(100);
//        translationHeroSthy.setCycleCount(TranslateTransition.INDEFINITE);
//        translationHeroSthy.setAutoReverse(true);
//        root.getChildren().add(heroSthyvViwer);
//        translationHeroSthy.play();
//    }

    public void createHeroStealhy(Pane root , List<ImageView> herosSthy , double width){
        Image heroSthy = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroSthy.png");
        ImageView heroSthyViwer = new ImageView(heroSthy);
        heroSthyViwer.setFitHeight(100);
        heroSthyViwer.setFitWidth(100);
        heroSthyViwer.setTranslateX(width);
        heroSthyViwer.setTranslateY(50);
        herosSthy.add(heroSthyViwer);

        TranslateTransition translationHeroSthy = new TranslateTransition(Duration.seconds(3),heroSthyViwer);
        translationHeroSthy.setFromY(50);
        translationHeroSthy.setToY(150);
        translationHeroSthy.setCycleCount(TranslateTransition.INDEFINITE);
        translationHeroSthy.setAutoReverse(true);
        root.getChildren().add(heroSthyViwer);
        translationHeroSthy.play();
    }
}
