package cassebrique.models;

import java.awt.*;

public class Brique extends Rectangle {

    public static final int hauteurDefaut = 40;
    public static final int largeurDefaut = 70;

    private int resistance;

    public Brique(int x, int y, Color cyan) {
        super(x, y, largeurDefaut, hauteurDefaut, Color.CYAN);
        this.resistance = 3;
    }

    public void mettreAJourCouleur() {
        switch (resistance) {
            case 2 -> setCouleur(Color.ORANGE);
            case 1 -> setCouleur(Color.RED);
            default -> setCouleur(Color.CYAN);
        }
    }

    public void diminuerResistance() {
        if (resistance > 0) {
            resistance--;
            mettreAJourCouleur();
        }
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
        mettreAJourCouleur();
    }
}
