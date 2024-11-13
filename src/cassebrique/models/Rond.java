package cassebrique.models;

import java.awt.*;

public class Rond extends Sprite {
    protected int diametre = 20;

    public Rond(int x, int y, Color couleur) {
        super(x, y, couleur);
    }
    public Rond() {

    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x,y,diametre,diametre);
    }
}