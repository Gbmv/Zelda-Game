package tpclasses.tp2;

public class Enemy extends Characters{
    // Atributes
    int gold;

    // Constructor
    public void Enemy(int gold){
//       super();
        this.gold = gold;
    }


    // Getters
    public int getGold() {
        return gold;
    }

    // Setters

    public void setGold(int gold) {
        this.gold = gold;
    }
}
