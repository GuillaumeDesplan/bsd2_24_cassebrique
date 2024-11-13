package cassebrique.models;

import java.awt.*;

public class Brique extends Rectangle {

    protected int resistance;
    public static int hauteurDefaut = 40;
    public static int largeurDefaut = 70;

    public Brique(int x, int y, Color couleur, int resistance) {
        super(x, y, largeurDefaut, hauteurDefaut , couleur);
        this.resistance = resistance;
    }

    public Brique(int x, int y, Color couleur) {
        super(x, y, largeurDefaut, hauteurDefaut , couleur);
        this.resistance = 1;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}