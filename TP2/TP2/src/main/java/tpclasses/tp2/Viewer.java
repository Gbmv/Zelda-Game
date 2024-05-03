package tpclasses.tp2;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Viewer extends Application {
    boolean gameStop = false;
    AnimationTimer timer;
    public static void main(String[] args) {
        launch(args);
    }
    boolean cooldownAtivo = false;
    long lastShoot=0;

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
        Button button = new Button("Resume");
        button.setFocusTraversable(false);

        Text coinCounter = new Text("Coin: 0");
        Text lifeCounter = new Text("Life: 100");
        HBox hBox = new HBox();

        hBox.setSpacing(10);
        hBox.getChildren().addAll(lifeCounter, button, coinCounter);

        // Botton pause
        hBox.setLayoutX((width - hBox.getBoundsInParent().getWidth()) / 2); // Centralize horizontalmente
        hBox.setLayoutY(height - 35); // Coloque 35 pixels acima da parte inferior da janela
        hBox.setAlignment(Pos.CENTER);

        root.getChildren().add(hBox);


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

        HeroStealthy heroStealthy = new HeroStealthy();
        HeroBodyToBody heroBodyToBody = new HeroBodyToBody();


        List<ImageView> herosTank = new ArrayList<>();
        List<ImageView> herosBody = new ArrayList<>();
        List<ImageView> herosSthy = new ArrayList<>();
        List<ImageView> coins = new ArrayList<>();

        // Adicione um evento de clique ao botão
        button.setOnMouseClicked(event -> {
            if (!gameStop) {
                // Se o jogo não estiver pausado, pause-o
                timer.stop(); // Pare o AnimationTimer
                translationImage1.pause();
                translationImage2.pause();
                button.setText("Resume"); // Altere o texto do botão para "Resume"
            } else {
                // Se o jogo estiver pausado, retome-o
                timer.start(); // Retome o AnimationTimer
                translationImage1.play();
                translationImage2.play();
                button.setText("Play"); // Altere o texto do botão para "Resume"
            }
            gameStop = !gameStop; // Inverta o estado do jogo
        });
        // ----------------------------------------------------------------------------------------------------------//

        Timeline cooldown = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cooldownAtivo = false; // Quando o tempo acabar, o cooldown é desativado
            }
        }));
        cooldown.setCycleCount(1);

// Pode colocar a vida e coins como getLife, getMoney


         timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();
            double speed_y = 0;

            double gravity = enemy.getGravity();

            private double timerHero;
            private double timerCoin;
            private double timerHeroTank;
            double backgroundSpeed = 50;

            // Height of the background
            final double backgroundHeight = backgroundImage.getHeight();
            int numberCoin = 0;

            int numberLife = 100;


            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) * 1e-9;
                timerHero+=deltaTime;
                timerCoin+=deltaTime;
                timerHeroTank +=deltaTime;
                lastTime = now;


                // Defines the translation in the background
                backgroundViewer1.setTranslateX(backgroundViewer1.getTranslateX() - backgroundSpeed * deltaTime);
                backgroundViewer2.setTranslateX(backgroundViewer2.getTranslateX() - backgroundSpeed * deltaTime);

                if (backgroundViewer1.getTranslateX() <= 1-width){
                    backgroundViewer1.setTranslateX(width);
                }
                if (backgroundViewer2.getTranslateX() <= 1-width){
                    backgroundViewer2.setTranslateX(width);
                }

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
                    boolean colision = checkCollision(enemyView, heroSthyViwer);

                    if(newX <-width){
                        root.getChildren().remove(heroSthyViwer);
                        herosSthy.remove(heroSthyViwer);
                        break;
                    }
                    // Check for collision between enemy and hero
                    if (colision) {
                        // Handle the collision here
                        // For example:
                        root.getChildren().remove(heroSthyViwer); // Remove hero from screen
                        herosSthy.remove(heroSthyViwer); // Remove hero from the list
                        numberCoin -=10;
                        if(numberCoin<=0){
                            numberCoin = 0;
                        }
                        coinCounter.setText("Coins: " + numberCoin);

//                         E pressed, the enemy is dead

                    }
                }

                for(ImageView heroBodyView: herosBody ){
                    double newX = heroBodyView.getTranslateX()-backgroundSpeed * deltaTime;
                    heroBodyView.setTranslateX(newX);
                    boolean colision = checkCollision(enemyView, heroBodyView);

                    if(newX <-width){
                        root.getChildren().remove(heroBodyView);
                        herosBody.remove(heroBodyView);
                        break;
                    }
                    // Check for collision between enemy and hero
                    if (colision) {
                        // Handle the collision here
                        // For example:
                        numberLife-= (int) enemy.getLife();
                        root.getChildren().remove(heroBodyView); // Remove hero from screen
                        herosBody.remove(heroBodyView); // Remove hero from the list

                    }
                }

                for(ImageView heroTankView:herosTank){
                    double newX = heroTankView.getTranslateX()-backgroundSpeed * deltaTime;
                    heroTankView.setTranslateX(newX);
                    boolean colision =checkCollision(enemyView, heroTankView);

                    if(newX <-width){
                        root.getChildren().remove(heroTankView);
                        herosTank.remove(heroTankView);
                        break;
                    }
                    // Check for collision between enemy and hero
                    if (colision) {
                        numberLife-=50;
                        enemy.setLife(enemy.getLife()-50);
                        // Handle the collision here
                        // For example:
                        root.getChildren().remove(heroTankView); // Remove hero from screen
                        herosTank.remove(heroTankView); // Remove hero from the list
                        lifeCounter.setText("Life: " + String.valueOf(numberLife));
                    }
                }


                // Enemy is dead
                if (numberLife <= 0) {
                    root.getChildren().removeAll(herosSthy);
                    root.getChildren().removeAll(herosBody);
                    root.getChildren().removeAll(herosTank);
                    gameOver(root, height, numberCoin,lastTime);
                    root.getChildren().remove(hBox);
                    translationImage1.stop();
                    translationImage2.stop();
                    timer.stop();


                }


                //-----------------------------Implementation of the coins------------------------------------------------//

                if (timerCoin >= 2) {
                    createCoin(root, width, coins,backgroundHeight);
                    timerCoin  = 0;
                }

                // Animate the movement of existing coins
                for (ImageView coinView : coins) {
//                    int conterCoins = 0;
                    double newX = coinView.getTranslateX() - backgroundSpeed * deltaTime;
                    coinView.setTranslateX(newX);
                    boolean colision = checkCollision(enemyView, coinView);
                    // Remove coin if it goes out of screen
                    if (newX < -width ) {
                        root.getChildren().remove(coinView);
                        coins.remove(coinView);
                        break;
                    }
                    // Handle the collision
                    if (colision) {
                        coins.remove(coinView); // Remove coin from the list
                        root.getChildren().remove(coinView); // Remove coin from screen
                        backgroundSpeed = backgroundSpeed+ 10; // Increases the speed
                        numberCoin++; // enemy.setMoney(+1)
                        coinCounter.setText("Coin: " + String.valueOf(numberCoin));
                        if (speed_y >=300){
                            speed_y = 300;
                        }
                        break;
                    }

                }
                //----------------------------------------------------------------------------------------------------//






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
                    if (event.getCode() == KeyCode.E) {
                        if(now-lastShoot >=10e8){
                            lastShoot = now;


                        for(ImageView heroSthyViwer:herosSthy){
                            if (Math.abs(enemyView.getLayoutY() - heroSthyViwer.getTranslateY()) <= heroSthyViwer.getFitHeight() / 2) {
                                numberCoin +=8;
                                root.getChildren().remove(heroSthyViwer); // Remove hero from screen
                                herosSthy.remove(heroSthyViwer); // Remove hero from the list
                                //System.out.println("hit");
                                heroSthyViwer.setTranslateY(height);
                                coinCounter.setText("Coins: " + numberCoin);
                                break;
                            }
                        }
                        for(ImageView heroBodyView: herosBody){
                            if (Math.abs(enemyView.getLayoutY() - heroBodyView.getTranslateY()) <= heroBodyView.getFitHeight() / 2) {
                                numberCoin +=5;
                                root.getChildren().remove(heroBodyView); // Remove hero from screen
                                herosSthy.remove(heroBodyView); // Remove hero from the list
                                heroBodyView.setTranslateY(height);
                                //System.out.println("hit");
                                coinCounter.setText("Coins: " + numberCoin);
                                break;
                            }
                        }
                        for(ImageView heroTankView:herosTank){
                            if (Math.abs(enemyView.getLayoutY() - heroTankView.getTranslateY()) <= heroTankView.getFitHeight() / 2) {
                                numberCoin +=7;
                                root.getChildren().remove(heroTankView); // Remove hero from screen
                                herosTank.remove(heroTankView); // Remove hero from the list
                                //System.out.println("hit");
                                heroTankView.setTranslateY(height);
                                coinCounter.setText("Coins: " + numberCoin);
                                break;
                            }
                        }


                        }


                    }
                });
                //---------------------------------------------------------------------------------------------------//

                //--------------------------------------- Implementation of the gun----------------------------------//




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




    // Coin position outside the screen
    coinView.setTranslateX(width);
    root.getChildren().add(coinView);
    coins.add(coinView);


}

    private void createHeroTank(Pane root , List<ImageView> herosTank , double width,  double backgroundHeight){



        Image heroTank = new Image("file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\heroTank.png");
        ImageView heroTankView = new ImageView(heroTank);
        heroTankView.setFitHeight(75);
        heroTankView.setFitWidth(75);



        // Transport part
        heroTankView.setTranslateX(width);
        heroTankView.setTranslateY(Math.random()*(backgroundHeight-heroTankView.getFitHeight()));





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

    //-----------------------------------Implementation of the colision----------------------------------------------//
    private boolean checkCollision(Node node1, Node node2) {
        return node1.getBoundsInParent().intersects(node2.getBoundsInParent());
    }
    private void gameOver(Pane root, double height, int coins, long lastTime) {
        // Create the Game Over text
        Text textOver = new Text("Game Over!!! Merci Robin");
        textOver.setFont(Font.font("Arial", 21));

        // Create the texts for Coins and Time
        Text coinText = new Text("Coins: " + coins );
        Text tempText = new Text("Time: " +(System.nanoTime()-lastTime)/1e6+ "s") ; // Calculating the time elapsed

        // Create a VBox to organize the elements vertically
        VBox overVbox = new VBox();
        overVbox.getChildren().addAll(textOver,coinText,tempText);
        overVbox.setAlignment(Pos.CENTER); // Center the elements vertically

        overVbox.setBackground(new Background(new BackgroundFill(Color.web("00FFFF"),CornerRadii.EMPTY, null)));


        // Center the VBox on the scene
        overVbox.setLayoutX(100);
        overVbox.setLayoutY(100);

        // Add the VBox to the root pane
        root.getChildren().add(overVbox);
    }



}












