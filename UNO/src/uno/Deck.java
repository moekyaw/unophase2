/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import uno.Card;
import java.util.*;


public class Deck {

    private final static Integer NUMBEROFCARDS = 108;

    private List<Card> thedeck = new ArrayList<Card>();
    String[] color = {"Red", "Yellow", "Green", "Blue"};
    String[] type = {"Number", "Skip", "Reverse", "Draw 2", "Change Color", "Draw 4"};

    public List<Card> NewDeck() {
		for (int i = 0; i < 4; i++) {
            Card c = new Card();
            c.setColor(color[i]);
            c.setType(type[0]);
            c.setValue(0);
            c.setImage(ImgPathResolver.resolve(c));
            setThedeck(c);

        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k < 10; k++) {
                    Card c = new Card();
                    c.setColor(color[j]);
                    c.setValue(k);
                    c.setType(type[0]);
                    c.setImage(ImgPathResolver.resolve(c));
                    setThedeck(c);
                }
                for (int l = 0; l <= 2; l++) {
                    Card c = new Card();
                    c.setColor(color[j]);
                    c.setValue(20);
                    c.setType(type[l + 1]);
                  c.setImage(ImgPathResolver.resolve(c));
                    setThedeck(c);
                }

            }
        }

        for (int i = 0; i < 4; i++) {
            Card c = new Card();
            c.setColor("Wild");
            c.setType(type[4]);
            c.setValue(50);
            c.setImage(ImgPathResolver.resolve(c)); 
            setThedeck(c);

        }

        for (int i = 0; i < 4; i++) {
            Card c = new Card();
            c.setColor("Wild");
            c.setType(type[5]);
            c.setValue(50);
            c.setImage(ImgPathResolver.resolve(c));
            setThedeck(c);

        }

        return thedeck;
    }

    public Card DrawCard() {

        return getThedeck().get(0);

    }

    /**
     * @return the thedeck
     */
    public List<Card> getThedeck() {
        return thedeck;
    }

    /**
     * @param thedeck the thedeck to set
     */
    public void setThedeck(Card c) {
        this.thedeck.add(c);
    }

    @Override
    public String toString() {
        return thedeck + "\n";
    }

}
