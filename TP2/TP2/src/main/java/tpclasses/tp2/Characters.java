package tpclasses.tp2;

public class Characters {
    // Blabla
    // Atributtes

    int speed;

    String img;
    double positionX;
    double positionY;

    // velociade vx et vy
    // slowdown


    // Constructor
    public  Characters( int speed, String img, double positionX, double positionY){
        this.speed = speed;
        this.img = img;
        this.positionX = positionX;
        this.positionY = positionY;
    }




    // Getters
    public int getSpeed() {
        return speed;
    }





    public String getImg() {
        return img;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    //Setters
    public void setSpeed(int speed) {
        this.speed = speed;
    }





    public void setImg(String img) {
        this.img = img;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}


