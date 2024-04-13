package tpclasses.tp2;

public class Characters {

    int gravity;
    int speed;
    int damage;
    String img;
    double positionX;
    double positionY;



    public void Characters(int gravity, int speed, int damage, String img, double positionX, double positionY){
        this.gravity = gravity;
        this.speed = speed;
        this.damage = damage;
        this.img = img;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Getters
    public int getSpeed() {
        return speed;
    }


    public int getGravity() {
        return gravity;
    }

    public int getDamage() {
        return damage;
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

    public void setDamage(int damage) {
        this.damage = damage;
    }



    public void setGravity(int gravity) {
        this.gravity = gravity;
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


