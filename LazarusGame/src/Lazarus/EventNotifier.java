/*
This class implements the Observable class.
It will notify the observer whenever 
collisions and keyboard events occur.
*/

package Lazarus;


import java.awt.event.KeyEvent;
import java.util.Observable;


public class EventNotifier extends Observable {

  
    public Object event;

    public void setValue(KeyEvent e) {
              
        event = e;
        setChanged();        
        notifyObservers(this);
    }

  
}


