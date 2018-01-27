/*
This class extends JApplet and implements Runnable.
It has methods to convert image
files in buffered images, as well as, start, run, get and set methods. 
*/

package Lazarus;


import java.net.URL;
import javax.swing.JApplet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;



public class GameWorld extends JApplet implements Runnable {
    public Graphics2D g2;
    public Thread gThread;
    int width, height;   
    public EventNotifier eventNotifier;
   

    @Override
    public void start() {
        gThread = new Thread(this);
        gThread.setPriority(Thread.MIN_PRIORITY);
        gThread.start();
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (gThread == me) {
            repaint();
            try {
                gThread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
    } 

    
    //returns the image file
    public Image getSprite(String name) {
        URL url = GameWorld.class.getResource(name);
        Image img = java.awt.Toolkit.getDefaultToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
            System.out.println("Cannot get image file: " + e);
        }
        return img;
    }


    // convert image to buffered image
    public BufferedImage convertToBuffered(Image img) {
        int width = img.getWidth(this);
        int height = img.getHeight(this);
        BufferedImage aImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2temp = aImage.createGraphics();
        g2temp.drawImage(img, 0, 0, this);
        g2temp.dispose();
        return aImage;
    }

    
    //converts to an array of sprite images
    public BufferedImage[] convertToSprite(BufferedImage aImage, int pixelW, int n) {
        BufferedImage[] converted = new BufferedImage[aImage.getTileWidth() / pixelW];
        int width = aImage.getWidth() / n;
        int height = aImage.getHeight();
        int divider = 0;
        for (int i = 0; i < n; i++) {
            converted[i] = aImage.getSubimage(divider, 0, width, height);
            divider += pixelW;
        }
        return converted;
    }

    
    
    public int getWindowWidth() {
        return width;
    }

    public int getWindowHeight() {
        return height;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

  
    
    public EventNotifier getEventNotifier() {
        return this.eventNotifier;
    }

    
    
    /* 
    This class extends KeyAdapter and will get 
    the keyboard actions, which then is passed to EventNotifier.
    */
    public class KeyControl extends KeyAdapter {
        public void keyPressed(KeyEvent e) {            
            eventNotifier.setValue(e);
        }
    }
    
    
}

