/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;


public class Card {

    private String color;
    private String type;
    private Integer value;
    private String image;

    public Card() {

    }

    public Card(String color, String type, Integer value, String image) {
        this.color = color;
        this.type = type;
        this.value = value;
        this.image = image;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "\t Card :" + "Color=" + getColor() + ", type=" + getType() + ", value=" + getValue() + ", image=" + getImage() + "\n";
    }

}
