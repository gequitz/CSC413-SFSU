/*This class extends GameObject and implements Observer. 
This class has data fields for the position, speed, kind of box 
and the box situation (dropping or not). 
There are getters methods, method to detect the collision event, draw and update methods. */

package Lazarus;


import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class Box extends GameObject implements Observer {
    //box kinds: 0: wall, 1: cardboard, 2: wood, 3: stone, 4: metal
    char boxKind; 
    int positionX, positionY;
    boolean dropping;

    public Box(BufferedImage[] sprt) {
        super(sprt);
    }

    public Box(BufferedImage[] sprite, int x, int y, int speed, char boxKind, boolean drop) {
        super(sprite);
        setXY(x,y);
        positionX = x;
        positionY = y;
        this.speed = speed;
        this.boxKind = boxKind;
        this.dropping = drop;
    }
        
  
    
    @Override
     public void collisionEvent() {
        if (collisionKind == '0') {           
                dropping = false;
                setY(positionY);            
        }
        else if(collisionKind == '1') {
            isDisplayed = false;
        }     
    }

    public void drop() {
        positionY = y;
        setY(y + speed);
    }

  
    
    public boolean getDropping(boolean bln) {
        return dropping = bln;
    }

    public boolean isDropping() {
        return dropping;
    }

    
    public char getBoxKind() {
        return boxKind;
    }

    public void update() {
        collisionEvent();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void draw(Graphics g, ImageObserver obs) {
        if (isDisplayed) {
            g.drawImage(sprite[frame], x, y, obs);
            if (dropping) {
                drop();
            }
        }
    }
}
