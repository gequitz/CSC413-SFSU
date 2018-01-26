package tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Icon {
  private int tileSize;
  private String elementsFile;
  private BufferedImage[] images;

  public Icon( String elementsFile, int tileSize ) throws IOException {
    this.elementsFile = elementsFile;
    this.tileSize = tileSize;
    this.loadImages();
  }

  public int frameCount() {
    return this.images.length;
  }
  
  public BufferedImage getFrame( int frame ) {
    return this.images[ frame ];
  }
  private void loadImages() throws IOException {
    BufferedImage image = ImageIO.read( new File( elementsFile ));
    this.images = new BufferedImage[ image.getWidth() / tileSize ];

    for( int index = 0; index < this.images.length; index++ ) {
      this.images[ index ] = image.getSubimage(
              index * this.tileSize, 0, this.tileSize, this.tileSize
      );
    }
  }

  

  
}