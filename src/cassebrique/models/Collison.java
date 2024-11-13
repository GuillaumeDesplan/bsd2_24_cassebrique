
package cassebrique.models;

public class Collision {
    private Sprite spriteTouche;
    private String direction;

    public Collision(Sprite spriteTouche, String direction) {
        this.spriteTouche = spriteTouche;
        this.direction = direction;
    }

    public Sprite getSpriteTouche() {
        return spriteTouche;
    }

    public void setSpriteTouche(Sprite spriteTouche) {
        this.spriteTouche = spriteTouche;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
