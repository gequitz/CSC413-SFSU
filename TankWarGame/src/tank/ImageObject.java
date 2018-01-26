package tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


public class ImageObject {
  protected int x;
  protected int y;
  protected BufferedImage image;
  protected ImageObserver observer;

  public ImageObject(String imageLocation ) throws IOException {
    this( imageLocation, null );
  }

  public ImageObject( String imageLocation, ImageObserver observer ) throws IOException {
    x = 0;
    y = 0;  
    image = ImageIO.read( new File( imageLocation ));
    this.observer = observer;
  }

  public void setImageX( int x ) {
    this.x = x;
  }

  public void setImageY( int y ) {
    this.y = y;
  }

  public int getImageX() {
    return this.x;
  }

  public int getImageY() {
    return this.y;
  }

  public int getWidth() {
    return this.image.getWidth();
  }

  public int getHeight() {
    return this.image.getHeight();
  }
 
  
  public void doDrawing( Graphics graphics ) {
    graphics.drawImage( image, x, y, observer );
  }
}


