/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.*;

public class Game {

    private String gameid;
    private Integer numberofplayers;
    private String status;
    private String name;
    private List<Player> listofplayers = new ArrayList<Player>();

    private Deck theDeck = new Deck();

    public Game() {
    }

    public Game(String gameid, String name, String status) {
        this.gameid = gameid;
        this.name = name;
        this.status = status;
    }

    public void CreateGame() {
        getTheDeck().NewDeck();
        Collections.shuffle(getTheDeck().getThedeck());

    }

    public void AddPlayer(Player p) {

        setListofplayers(p);

    }

    public void DistributeCard() {
        //clean the card
        for(Player p:listofplayers){
            p.setCardinhandlist(new ArrayList<Card>() );
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < getListofplayers().size(); j++) {
                getListofplayers().get(j).addcard(getTheDeck().DrawCard());
                getTheDeck().getThedeck().remove(getTheDeck().DrawCard());
              
                
            }
        }
    }

    public Card DiscardCard() {
        Card c = new Card();
        c = getTheDeck().getThedeck().get(0);
        getTheDeck().getThedeck().remove(c);
        return c;

    }

    public List<Player> getListofplayers() {
        return listofplayers;
    }

    public void setListofplayers(Player p) {
        this.getListofplayers().add(p);
    }

    public String getGameid() {
        return gameid;
    }

    /**
     * @param gameid the gameid to set
     */
    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public Integer getNumberofplayers() {
        return numberofplayers;
    }

    public void setNumberofplayers(Integer numberofplayers) {
        this.numberofplayers = numberofplayers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListofplayers(List<Player> listofplayers) {
        this.listofplayers = listofplayers;
    }

    public Deck getTheDeck() {
        return theDeck;
    }

    public void setTheDeck(Deck theDeck) {
        this.theDeck = theDeck;
    }

    @Override
    public String toString() {
        return "Game :" + "Game ID =" + getGameid() + "\n " + "Discard " + DiscardCard() + "\n" + "Cards On Deck : " + getTheDeck().getThedeck().size() + getListofplayers();
    }

}
