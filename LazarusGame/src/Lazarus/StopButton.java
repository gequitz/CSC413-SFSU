
/*This class extends GameObject and implements Observer. 
The stop button is used to stop the boxes from falling 
and then advancing the level to the next level.
It has setters and update methods.
*/
package Lazarus;


import java.awt.image.BufferedImage;
import java.util.Observer;
import java.util.Observable;
import java.util.Observer;


public class StopButton extends GameObject implements Observer {

    public StopButton(BufferedImage[] sprt) {
        super(sprt);
    }
    
    public StopButton(BufferedImage[] sprt, int x, int y) {
        super(sprt);
        setX(x);
        setY(y);
    }
    
    @Override
    public void update(Observable o, Object arg) {}
    
}

