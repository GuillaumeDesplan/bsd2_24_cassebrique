package cassebrique.models;

import java.awt.*;

public class Rond extends Sprite {

    protected int diametre = 20;

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x,y,diametre,diametre);
    }





    public int getDiametre() {
        return diametre;
    }

    public void setDiametre(int diametre) {
        this.diametre = diametre;
    }

}

