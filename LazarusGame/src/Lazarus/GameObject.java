/*
This class has the data fields and behaviors that
are shared among the objects of the game, such as position,
speed image. It contains update, draw, get, set methods.
*/

package Lazarus;



import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class GameObject {

    public int x, y, speed;
    public Rectangle box;
    public BufferedImage[] sprite;
    public boolean isDisplayed = true;
    public int frame = 0;   
    public char collisionKind = ' ';


   
    public GameObject() {}
       
    //sets the dimensions of the BufferedImage
    public GameObject(BufferedImage[] bImg) {
        sprite = bImg;
        box = new Rectangle(sprite[0].getWidth(), sprite[0].getHeight());
    }

    
    //sets the initial position x and y
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        box = new Rectangle(x, y, 0, 0);
    }

    
    //set the image dimensions
    public GameObject(BufferedImage[] bImg, int x, int y) {
        this.x = x;
        this.y = y;
        sprite = bImg;
        box = new Rectangle(x, y, sprite[0].getWidth(), sprite[0].getHeight());
    }

    
    //sets collision type
    public void setCollision(char type) {
        collisionKind = type;
    }

    
    public void collisionEvent() {
        switch (collisionKind) {
            default:
                break;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
        box.x = x;
        box.y = y;
    }

    public void setX(int x) {
        this.x = x;
        box.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
        this.box.y = y;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return box.getBounds();
    }

    public void setSprite(BufferedImage[] bImg) {
        sprite = bImg;
        box.width = sprite[0].getWidth();
        box.height = sprite[0].getHeight();
    }

    public void setIsDisplayed(boolean d) {
        isDisplayed = d;
    }

    public boolean getIsDisplayed() {
        return isDisplayed;
    }

    public void setFrame(int i) {
        frame = i;
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (isDisplayed) {
            g.drawImage(sprite[frame], x, y, obs);
        }
    }

}

