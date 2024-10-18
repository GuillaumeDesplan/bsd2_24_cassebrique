package cassebrique;

import cassebrique.models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CasseBrique extends Canvas implements KeyListener {

    public JFrame fenetre = new JFrame();
    public ArrayList<Balle> listeBalle = new ArrayList<>();
    public ArrayList<Brique> listeBrique = new ArrayList<>();
    public ArrayList<Bonus> listeBonus = new ArrayList<>();
    public Barre barre;

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    public boolean toucheDroite = false;
    public boolean toucheGauche = false;

    public CasseBrique() throws InterruptedException {
        this.fenetre.setSize(LARGEUR, HAUTEUR);
        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);

        this.fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
        panneau.add(this);
        this.fenetre.setContentPane(panneau);

        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        this.fenetre.pack();
        this.fenetre.setResizable(false);
        this.fenetre.requestFocus();
        this.fenetre.addKeyListener(this);
        this.fenetre.setVisible(true);
        this.createBufferStrategy(2);

        lancerUnePartie();
    }

    public void lancerUnePartie() throws InterruptedException {
        listeBalle = new ArrayList<>();
        listeBalle.add(new Balle(100, 500, 3, 4, Color.magenta));

        barre = new Barre(
                CasseBrique.LARGEUR / 2 - Barre.largeurDefaut / 2,
                CasseBrique.HAUTEUR - 100);

        listeBrique = new ArrayList<>();
        for (int indexLigne = 0; indexLigne < 5; indexLigne++) {
            for (int indexColonne = 0; indexColonne < 7; indexColonne++) {
                Brique brique = new Brique(
                        indexColonne * (Brique.largeurDefaut + 2),
                        indexLigne * (Brique.hauteurDefaut + 2),
                        Color.cyan);
                listeBrique.add(brique);
            }
        }

        while (true) {
            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

            for (Balle balle : listeBalle) {
                balle.deplacer(barre, listeBrique, listeBonus);
                balle.dessiner(dessin);
            }

            if (toucheDroite) barre.deplacementDroite();
            if (toucheGauche) barre.deplacementGauche();
            barre.dessiner(dessin);

            for (Brique brique : listeBrique) {
                brique.dessiner(dessin);
            }

            for (int i = 0; i < listeBonus.size(); i++) {
                Bonus bonus = listeBonus.get(i);
                bonus.deplacer();
                bonus.dessiner(dessin);

                if (bonus.collisionAvecBarre(barre)) {
                    bonus.appliquerEffet(barre);
                    listeBonus.remove(i);
                } else if (bonus.getY() >= HAUTEUR) {
                    listeBonus.remove(i);
                }
            }

            dessin.dispose();
            this.getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }
    }
    
    public void verifierEtGenererBonus(Brique brique) {
        if (Math.random() < 0.1) {  // 10% de chance
            Bonus bonus = new Bonus(brique.getX() + Brique.largeurDefaut / 2, brique.getY());
            listeBonus.add(bonus);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) toucheDroite = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) toucheGauche = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) toucheDroite = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) toucheGauche = false;
    }
}
