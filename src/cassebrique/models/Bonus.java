package cassebrique.models;

import java.awt.*;
import java.util.Random;

public class Bonus extends Rond {

    public enum TypeBonus {
        VITESSE, TAILLE
    }

    private int vitesseY = 5;
    private boolean estMalus;
    private TypeBonus type;

    public Bonus(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.diametre = 20;

        Random random = new Random();
        this.estMalus = random.nextBoolean();
        this.type = random.nextBoolean() ? TypeBonus.VITESSE : TypeBonus.TAILLE;
    }

    public void deplacer() {
        y += vitesseY;
    }

    public boolean collisionAvecBarre(Barre barre) {
        return (x + diametre >= barre.getX() &&
                x <= barre.getX() + Barre.largeurDefaut &&
                y + diametre >= barre.getY() &&
                y <= barre.getY() + Barre.hauteurDefaut);
    }

    public void appliquerEffet(Barre barre) {
        if (type == TypeBonus.TAILLE) {
            if (estMalus) {
                barre.diminuerTaille();
            } else {
                barre.augmenterTaille();
            }
        } else if (type == TypeBonus.VITESSE) {
            if (estMalus) {
                barre.diminuerVitesse();
            } else {
                barre.augmenterVitesse();
            }
        }
    }

    public void dessiner(Graphics2D g) {
        g.setColor(estMalus ? Color.RED : Color.GREEN);  // Rouge pour un malus, vert pour un bonus
        g.fillOval(x, y, diametre, diametre);
    }

    // Getters et Setters
    public int getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public boolean isMalus() {
        return estMalus;
    }

    public void setMalus(boolean estMalus) {
        this.estMalus = estMalus;
    }

    public TypeBonus getType() {
        return type;
    }

    public void setType(TypeBonus type) {
        this.type = type;
    }
}
