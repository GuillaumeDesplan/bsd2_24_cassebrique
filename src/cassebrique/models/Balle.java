package cassebrique.models;

import cassebrique.CasseBrique;
import java.awt.*;
import java.util.ArrayList;

public class Balle extends Rond {

    protected int vitesseX;
    protected int vitesseY;
    private int collisions;

    public Balle(int x, int y, int vitesseX, int vitesseY, Color couleur) {
        super();
        this.x = x;
        this.y = y;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.couleur = couleur;
        this.collisions = 0;
    }

    public Balle(int x, int y, int vitesseX, int vitesseY) {
        this(x, y, vitesseX, vitesseY, Color.GREEN);
    }

    public boolean collisionAvecBrique(Brique brique) {
        return (x + diametre >= brique.getX() &&
                x <= brique.getX() + Brique.largeurDefaut &&
                y + diametre >= brique.getY() &&
                y <= brique.getY() + Brique.hauteurDefaut);
    }

    public void deplacer(Barre barre, ArrayList<Brique> listeBrique) {
        x += vitesseX;
        y += vitesseY;

        if (x >= CasseBrique.LARGEUR - diametre || x <= 0) {
            vitesseX = -vitesseX;
        }
        if (y <= 0) {
            vitesseY = -vitesseY;
        }

        if (y + diametre >= barre.getY() &&
                x + diametre >= barre.getX() &&
                x <= barre.getX() + Barre.largeurDefaut) {
            vitesseY = -vitesseY;
        }

        for (int i = 0; i < listeBrique.size(); i++) {
            Brique brique = listeBrique.get(i);
            if (collisionAvecBrique(brique)) {
                vitesseY = -vitesseY;
                brique.diminuerResistance();


                if (brique.getResistance() <= 0) {
                    listeBrique.remove(i);
                }
                break;
            }
        }

        if (y >= CasseBrique.HAUTEUR - diametre) {
            vitesseY = -vitesseY;
        }
    }

    public int getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
