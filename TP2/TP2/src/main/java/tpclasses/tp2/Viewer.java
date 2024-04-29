package tpclasses.tp2;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Viewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        // Size of the screen
        int width = 640;
        int height = 440;


        Pane root = new Pane();
        Scene scene = new Scene(root, width, height);
        Canvas canvas = new Canvas(width,height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        Random random = new Random();





        //---------------------------------------------- Background --------------------------------------------------//

        // Load background images
        Image backgroundImage = new Image("file:///C:/Users/gabri/Desktop/UDEM/Hiver-2024/IFT-1025/TP2/TP2/bg.png");
        ImageView backgroundViewer1 = new ImageView(backgroundImage);
        ImageView backgroundViewer2 = new ImageView(backgroundImage);

        // Set the positions of the image views
        backgroundViewer1.setTranslateX(0);
        backgroundViewer2.setTranslateX(width);


        // Add the image views to the root pane
        root.getChildren().addAll(backgroundViewer1, backgroundViewer2);

        // Enemy following the background
        Enemy enemy = new Enemy();
        Image enemyImg = new Image(enemy.getImg());
        ImageView enemyView = new ImageView(enemyImg);
        enemyView.setLayoutX(enemy.getPositionX());
        enemyView.setLayoutY(enemy.getPositionY());

        // Size of the enemy
        enemyView.setFitHeight(100);
        enemyView.setFitWidth(100);

        // Adding the enemy to the root
        root.getChildren().add(enemyView);

        // Define the properties fo the translation of the background
        TranslateTransition translationImage1 = new TranslateTransition(Duration.seconds(10), backgroundViewer1);
        translationImage1.setFromX(0);
        translationImage1.setToX(-width);
        translationImage1.setInterpolator(Interpolator.LINEAR);
        translationImage1.setCycleCount(TranslateTransition.INDEFINITE);


        TranslateTransition translationImage2 = new TranslateTransition(Duration.seconds(10), backgroundViewer2);
        translationImage2.setFromX(width);
        translationImage2.setToX(0);
        translationImage2.setInterpolator(Interpolator.LINEAR);
        translationImage2.setCycleCount(TranslateTransition.INDEFINITE);
        // ----------------------------------------------------------------------------------------------------------//

        List<ImageView> herosTank = new ArrayList<>();
        List<ImageView> herosBody = new ArrayList<>();
        List<ImageView> herosSthy = new ArrayList<>();
        List<ImageView> coins = new ArrayList<>();

        HeroStealthy heroStealthy = new HeroStealthy();
        HeroBodyToBody heroBodyToBody = new HeroBodyToBody();


        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();
            double speed_y = 0;

            final double gravity = enemy.getGravity();

            private double timerHero;
            private double timerCoin;
            private double timerHeroTank;
            final double backgroundSpeed = 50;

            // Height of the background
            final double backgroundHeight = backgroundImage.getHeight();


            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) * 1e-9;
                timerHero+=deltaTime;
                timerCoin+=deltaTime;
                timerHeroTank +=deltaTime;

                lastTime = now;

                System.out.println(backgroundHeight);
            //-------------------------Implementation of the heros----------------------------------------------------//
//                System.out.println(timerHero);
//                if(timerHeroTank>=0.5){
//                  createHeroTank(root,herosTank,width,backgroundHeight);
//                }
                if ((timerHero >= 3)) {
//                     Gives a random number between 0 and 2
                    int randomChoice = random.nextInt(3);

                    if(randomChoice == 0 ){
                        heroStealthy.createHeroStealhy(root, herosSthy, width,backgroundHeight);
                    } else if (randomChoice == 1) {
                        heroBodyToBody.createHeroBody(root,herosBody,width,backgroundHeight);
                    }
                    else{
                     createHeroTank(root,herosTank,width,backgroundHeight);
                    }

                    timerHero = 0;

                }


                for(ImageView heroSthyViwer: herosSthy ){
                    double newX = heroSthyViwer.getTranslateX()-backgroundSpeed * deltaTime;
                    heroSthyViwer.setTranslateX(newX);

                    if(newX <-width){
                        root.getChildren().remove(heroSthyViwer);
                        herosSthy.remove(heroSthyViwer);
                        break;
                    }

                    // Check for collision between enemy and hero
                    if (checkCollision(enemyView, heroSthyViwer)) {
                        // Handle the collision here
                        // For example:
                        root.getChildren().remove(heroSthyViwer); // Remove hero from screen
                        herosSthy.remove(heroSthyViwer); // Remove hero from the list

                        // You might also reduce health or trigger other effects here
                    }
                }

                for(ImageView heroBodyView: herosBody ){
                    double newX = heroBodyView.getTranslateX()-backgroundSpeed * deltaTime;
                    heroBodyView.setTranslateX(newX);

                    if(newX <-width){
                        root.getChildren().remove(heroBodyView);
                        herosBody.remove(heroBodyView);
                        break;
                    }

                    // Check for collision between enemy and hero
                    if (checkCollision(enemyView, heroBodyView)) {
                        // Handle the collision here
                        // For example:
                        root.getChildren().remove(heroBodyView); // Remove hero from screen
                        herosBody.remove(heroBodyView); // Remove hero from the list

                        // You might also reduce health or trigger other effects here
                    }
                }

                for(ImageView heroTankView:herosTank){
                    double newX = heroTankView.getTranslateX()-backgroundSpeed * deltaTime;
                    heroTankView.setTranslateX(newX);

                    if(newX <-width){
                        root.getChildren().remove(heroTankView);
                        herosTank.remove(heroTankView);
                        break;
                    }

                    // Check for collision between enemy and hero
                    if (checkCollision(enemyView, heroTankView)) {
                        // Handle the collision here
                        // For example:
                        root.getChildren().remove(heroTankView); // Remove hero from screen
                        herosTank.remove(heroTankView); // Remove hero from the list
                    }
                }


            //-----------------------------Implementation of the coins------------------------------------------------//

                if (timerCoin >= 2) {
                    createCoin(root, width, coins,backgroundHeight);
                    timerCoin  = 0;
                }

                // Animate the movement of existing coins
                for (ImageView coinView : coins) {
                    double newX = coinView.getTranslateX() - backgroundSpeed * deltaTime;
                    coinView.setTranslateX(newX);


                    // Remove coin if it goes out of screen
                    if (newX < -width ) {
                        root.getChildren().remove(coinView);
                        coins.remove(coinView);
                        break;
                    }
                    if (checkCollision(enemyView, coinView)) {
                        // Handle the collision here
                        // For example:
                        root.getChildren().remove(coinView); // Remove coin from screen
                        herosTank.remove(coinView); // Remove coin from the list


                    }

                }
                //----------------------------------------------------------------------------------------------------//


                // Defines the translation in the background
                backgroundViewer1.setTranslateX(backgroundViewer1.getTranslateX() - backgroundSpeed * deltaTime);
                backgroundViewer2.setTranslateX(backgroundViewer2.getTranslateX() - backgroundSpeed * deltaTime);

                if (backgroundViewer1.getTranslateX() <= 1-width){
                    backgroundViewer1.setTranslateX(width);
                }
                if (backgroundViewer2.getTranslateX() <= 1-width){
                    backgroundViewer2.setTranslateX(width);
                }



                // -------------------------Implementations of the space bar  ------------------------------------//

                speed_y += gravity * deltaTime; // gravity accelertion
                double newY = enemyView.getLayoutY() + speed_y * deltaTime; // new Y position

                // Verify if the enemy it not in the floor
                if (newY >= enemy.getPositionY()) {
                    newY = enemy.getPositionY();
                }

                // Verify if it is in the sky
                if (newY <0 || newY> canvas.getHeight()-enemyView.getFitHeight()){
                    if (newY<0){
                        newY = 0;
                    }
                    else {
                        newY = canvas.getHeight()-enemyView.getFitHeight();
                    }
                    speed_y = -speed_y;
                }

                // Update the position of the enemy
                enemyView.setLayoutY(newY);



                // Space bar pressed, the enemy jumps
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.SPACE) {
                        speed_y = enemy.getJumpSpeed(); // The speed of the jump
                    }
                });
                //---------------------------------------------------------------------------------------------------//

                //-----------------------------------Implementation of the colision----------------------------------//























                //---------------------------------------------------------------------------------------------------//
                // Clean the context
                context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            }
        };
        timer.start();
        stage.setTitle("UNO reverse Flappy Enemy VS Hero with GUNS ");
        stage.setScene(scene);
        stage.show();
    }


private void createCoin(Pane root, double width, List<ImageView> coins, double backgroundHeight) {

    // Load coin image
    Image coin = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\coin.png");
    ImageView coinView = new ImageView(coin);
    coinView.setFitWidth(25);
    coinView.setFitHeight(25);

    // Why error with where the image is spawed
    coinView.setTranslateY( Math.random() * (backgroundHeight - coinView.getFitHeight()));

    //    System.out.println(coinView.getTranslateY());

    float colisionCenterX = (float) (coinView.getTranslateX()+(coinView.getFitWidth()/2));
    float colisionCenterY = (float) (coinView.getTranslateY()+(coinView.getFitHeight()/2));

    float radiusCoin = (float)(coinView.getFitWidth()/2);


    // Coin position outside the screen
    coinView.setTranslateX(width);
    root.getChildren().add(coinView);
    coins.add(coinView);


}

    private void createHeroTank(Pane root , List<ImageView> herosTank , double width,  double backgroundHeight){



        Image heroTank = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroTank.png");
        ImageView heroTankView = new ImageView(heroTank);
        heroTankView.setFitHeight(100);
        heroTankView.setFitWidth(100);



        // Transport part
        heroTankView.setTranslateX(width);
        heroTankView.setTranslateY(Math.random()*(backgroundHeight-heroTankView.getFitHeight()));

        float colisionCenterX = (float) (heroTankView.getTranslateX()+(heroTankView.getFitWidth()/2));
        float colisionCenterY = (float) (heroTankView.getTranslateY()+(heroTankView.getFitHeight()/2));



       Timeline timeline = new Timeline();
       timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), event -> {
            double destX = heroTankView.getTranslateX() + getRandomNumber(-31, 30);
            double destY = heroTankView.getTranslateY() + getRandomNumber(-31, 30);

            heroTankView.setTranslateX(destX);
            heroTankView.setTranslateY(destY);

        });

       timeline.getKeyFrames().addAll(keyFrame);
       timeline.play();


        herosTank.add(heroTankView);
        root.getChildren().add(heroTankView);

    }







    public int getRandomNumber(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }
    private boolean checkCollision(Node node1, Node node2) {
        return node1.getBoundsInParent().intersects(node2.getBoundsInParent());
    }

}












