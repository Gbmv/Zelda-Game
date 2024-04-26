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
    public void createHeroBody(Pane root , List<ImageView> herosBody , double width,   double backgroundHeight,float random){
        Image heroBody = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroBody.png");
        ImageView heroBodyView = new ImageView(heroBody);
        heroBodyView.setFitWidth(100);
        heroBodyView.setFitHeight(100);
        heroBodyView.setTranslateX(width);
        heroBodyView.setTranslateY( random * backgroundHeight );
        herosBody.add(heroBodyView);
        root.getChildren().add(heroBodyView);
    }
}

