package tpclasses.tp2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class HeroBodyToBody extends Characters{

    public HeroBodyToBody() {
        super(50, "file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroSthy.webp",
                100, 200);
    }
    public void createHeroBody(Pane root , List<ImageView> herosBody , double width,   double backgroundHeight){
        Image heroBody = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroBody.png");

        ImageView heroBodyView = new ImageView(heroBody);
        heroBodyView.setFitWidth(100);
        heroBodyView.setFitHeight(100);

        double positionY = Math.random() * (backgroundHeight - heroBodyView.getFitHeight());

        heroBodyView.setTranslateX(width);
        heroBodyView.setTranslateY( Math.random() * (positionY) );

        float colisionCenterX = (float) (heroBodyView.getTranslateX()+(heroBodyView.getFitWidth()/2));
        float colisionCenterY = (float) (positionY+(heroBodyView.getFitHeight()/2));

        float radius = (float)(heroBodyView.getFitWidth()/2);


        herosBody.add(heroBodyView);
        root.getChildren().add(heroBodyView);


    }
}

