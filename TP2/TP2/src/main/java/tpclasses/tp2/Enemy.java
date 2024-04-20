package tpclasses.tp2;

public class Enemy extends Characters{
    // Atributes
    int gold;
    double jumpSpeed;
    double life;
    int gravity;



    // Constructor

    public Enemy() {
        super(120, "file:///C:\\Users\\gabri\\Desktop\\UDEM\\Hiver-2024\\IFT-1025\\TP2\\TP2\\enemy_image.png"
                ,100 ,300);
        this.jumpSpeed = -300;
        this.life = 1000;
        this.gravity = 500;
    }

    // Getters
    public int getGold() {
        return gold;
    }

    public double getJumpSpeed() {
        return jumpSpeed;
    }

    public double getLife(){
        return life;
    }

    public int getGravity() {
        return gravity;
    }
    // Setters

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setJumpSpeed(double jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }
}

