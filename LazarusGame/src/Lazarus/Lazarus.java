/*
This class extends GameObject and implements Observer.
It has methods related to the position and movements of Lazarus. 
It has get, set, collision event identification, update and draw methods.
*/
package Lazarus;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;


public class Lazarus extends GameObject implements Observer {
         
    int[] keyPressed;
    boolean dropping = false;
    boolean jump = false;
    boolean dead = false;      
    char direction1 = ' ';
    char direction2;
    int tempX, tempY, lastX, lastY; 
   
  

    BufferedImage[] spriteLeft, spriteRight, spriteJumpLeft, spriteJumpRight, spriteSquished, spriteAfraid;

    public Lazarus(BufferedImage[] sprite0, BufferedImage[] spriteLeft, BufferedImage[] spriteRight,
                     BufferedImage[] spriteJumpLeft, BufferedImage[] spriteJumpRight,
                     BufferedImage[] spriteSquished, BufferedImage[] spriteAfraid, int[] keyPressed, int speed) {

        super(sprite0);
        
        this.spriteJumpRight = spriteJumpRight;
        this.spriteSquished = spriteSquished;
        this.spriteAfraid = spriteAfraid;
        this.spriteLeft = spriteLeft;
        this.spriteRight = spriteRight;
        this.spriteJumpLeft = spriteJumpLeft;
        
        setSpeed(speed);
        this.keyPressed = keyPressed;

    }

    
    //event depending on the collision kind
    @Override
    public void collisionEvent() {
        switch (collisionKind) {
            case '0':
                if (dropping) {
                    dropping = false;
                    jump = false;
                    setY(tempY);
                } else if (!jump) {
                    if (direction2 == '2')
                        setBound(x - 40, y - 40);
                    else if (direction2 == '3')
                        setBound(x + 40, y - 40);
                    jumpWall();
                } else if (jump){
                    jump = false;
                    wallCollision();
                }
                break;
            case '1':
               
                dead = true;
                break;
            default:
                break;
        }
        
        collisionKind = ' ';
    }

    public void wallCollision() {
        frame = 0;
        setXY(lastX, lastY + 40);
    }

    public void jumpWall() {
        jump = true;
        if (direction2 == '2')
            jumpLeft();
        else if (direction2 == '3')
            jumpRight();
    }

    public void moveLeft() {
        direction1 = '2';
        direction2 = '2';
        setX(getX() - speed);

        if (frame > 7)
            frame = 0;
    }

    public void moveRight() {
        direction1 = '3';
        direction2 = '3';

        setX(getX() + speed);

        if (frame > 7)
            frame = 0;
    }
    public void jumpLeft() {
        direction1 = '0';
        direction2 = '0';
        setX(getX() - speed);

        if (frame > 7)
            frame = 0;

    }

    public void jumpRight() {
        direction1 = '1';
        direction2 = '1';

        setX(getX() + speed);

        if (frame > 7)
            frame = 0;
    }

    

    public void drop() {
        tempY = y;
        setY(y + 40);
    }

  

    public void setBound(int x, int y) {
        box.x = x;
        box.y = y;
    }



    public boolean isDead() {
        return dead;
    }

  

    public void update() {
        if (!dead) {
            collisionEvent();
        }
    }

    @Override
    public void update(Observable o, Object arg){       
        EventNotifier ge = (EventNotifier) arg;       
            KeyEvent e = (KeyEvent) ge.event;
            int kPressed = e.getKeyCode();            
             if (kPressed == keyPressed[0] || kPressed == keyPressed[1]) {
                if (frame == 0 && !dropping) {
                    if (isDisplayed) { 
                        SoundEffects play_sound = new SoundEffects();
                        play_sound.play_once("src/resource/Move.wav");                                 
                        lastX = this.x;
                        lastY = this.y;                        
                        if(kPressed == keyPressed[0])
                         moveLeft();
                        if (kPressed == keyPressed[1])
                         moveRight();                        
                    }
                }
            }         
       
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {
        if (isDisplayed) {
            if (dead) {                
                g.setColor(Color.white);                          
                g.setFont(new Font("Calibri", 0, 60));            
                g.drawString("\t\tYou Lose!", 200, 300);                     
                if (frame != 10) {
                    g.drawImage(spriteSquished[frame], x, y, obs);
                    frame++;   
                } else {
                    frame = 0;
                    setIsDisplayed(false);
                }
            } else {
                switch (direction1) {
                    case '0':
                        if ((this.x % 40) != 0) {
                            g.drawImage(spriteJumpLeft[frame], x - 20, y - 40, obs);
                            jumpLeft();
                            frame++;
                        } else {
                            frame = 0;                            
                            direction1 = ' ';
                            setY(y - 40);
                            g.drawImage(sprite[frame], x, y, obs);
                            dropping = true;
                        }
                        break;
                    case '1':
                        if ((this.x % 40) != 0) {
                            g.drawImage(spriteJumpRight[frame], x - 20, y - 40, obs);
                            jumpRight();
                            frame++;
                        } else {
                            frame = 0;                           
                            direction1 = ' ';
                            setY(y - 40);
                            g.drawImage(sprite[frame], x, y, obs);
                            dropping = true;
                        }
                        break;
                    case '2':
                        if ((this.x % 40) != 0) {
                            g.drawImage(spriteLeft[frame], x - 20, y - 40, obs);
                            moveLeft();
                            frame++;
                        } else {
                            frame = 0;                           
                            direction1 = ' ';
                            g.drawImage(sprite[frame], x, y, obs);
                            dropping = true;
                        }
                        break;
                    case '3':
                        if ((this.x % 40) != 0) {
                            g.drawImage(spriteRight                                                       [frame], x - 20, y - 40, obs);
                            moveRight();
                            frame++;
                        } else {
                            frame = 0;                           
                            direction1 = ' ';
                            g.drawImage(sprite[frame], x, y, obs);
                            dropping = true;
                        }
                        break;
                    default:
                        g.drawImage(sprite[0], x, y, obs);
                        break;
                }
                if (dropping) {
                    drop();
                }

            }
        }
    }
}


