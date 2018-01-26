

package tank;

import java.awt.*;

public class GameAnimation {
  private Icon element;
  private int frame_count, frame_delay, current_frame;   
  private int x,y, duration; 
  
  private boolean loop;
  protected boolean stop;

  public GameAnimation( Icon element, int x, int y, int frame_delay, boolean loop) {
    this.element = element;
    this.x = x;
    this.y = y;
    this.loop = loop;
    this.stop = false;
    this.duration = 0;
    this.frame_count = 0;
    this.frame_delay = frame_delay;
    this.current_frame = 0;    
  }

  public boolean isStopped() {
    return this.stop;
  }

  public int totalTime() {
    return this.frame_delay * element.frameCount();
  }

  
  public void doDrawing( Graphics graphics ) {
    if( ! stop ) {
      duration++;
      frame_count++;
      if ( frame_count > frame_delay ) {
        frame_count = 0;
        stop = ( duration > this.totalTime() ) && ! loop;
        current_frame = ( current_frame + 1 ) % element.frameCount();
      }
      graphics.drawImage(element.getFrame( current_frame ), x, y,null );
    }
  }
}

