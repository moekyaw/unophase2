/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.*;


public class Player {
    private String playerid;
    private String name;
    private int cardinhand = 0;
    private List<Card> cardinhandlist = new ArrayList<Card>();

    public Player(String playerid,String name) {
        this.playerid=playerid;
        this.name =name ;
    }

    public void addcard(Card c) {

        setCardinhandlist(c);
        
    }

    public void removecard() {

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cardinhand
     */
    public int getCardinhand() {
        return cardinhand;
    }

    /**
     * @param cardinhand the cardinhand to set
     */
    public void setCardinhand(int cardinhand) {
        this.cardinhand = cardinhand;
    }

    /**
     * @return the cardinhandlist
     */
    public List<Card> getCardinhandlist() {
        return cardinhandlist;
    }

    /**
     * @param cardinhandlist the cardinhandlist to set
     */
    public void setCardinhandlist(Card c) {
        this.cardinhandlist.add(c);
    }
    public void setCardinhandlist( List<Card> cardinhandlist){
    this.cardinhandlist=cardinhandlist;
    }
     public String getPlayerid() {
        return playerid;
    }

   
    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    @Override
    public String toString() {
        return "\n Player : " + "name=" + name + "\n Card In Hand : \n" + cardinhandlist;
    }

    
   

}
