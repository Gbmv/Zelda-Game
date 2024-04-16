package tpclasses.tp2;

public class Enemy extends Characters{
    // Atributes
    int gold;
    double jumpSpeed;


    // Constructor

    public Enemy() {
        super(500, 120,
                "file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\enemy_image.png",
                100,  300);
        this.jumpSpeed = -300;
    }

    // Getters
    public int getGold() {
        return gold;
    }

    public double getjumpSpeed(){
        return jumpSpeed;
    }

    // Setters

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setJumpSpeed(double jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }
}
