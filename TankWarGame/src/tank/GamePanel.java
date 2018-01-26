package tank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;


public abstract class GamePanel extends JPanel implements Runnable {
  
    protected final String BACKGROUND_IMAGE = "src/resource/Background.bmp";
    protected final String TANK_IMAGE = "src/resource/TankImage.png";
    protected final int WIDTH = 1250;
    protected final int HEIGHT = 1280;    
    protected final int PANEL_HEIGHT = 700;    
    protected Dimension dimension;
    protected ImageObject background;   
    protected Wall breakable_wall;    
    protected Tank tank_a,tank_b;
    protected Player player_a, player_b;
      
    protected ArrayList< GameAnimation > game_animation;
    protected BufferedImage miniImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    protected BufferedImage tank_aViewImg = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    protected BufferedImage tank_bViewImg = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    protected Graphics miniGame ;
    
    

    public GamePanel() {
        try {
          this.background = new ImageObject( BACKGROUND_IMAGE );   
          this.tank_a = new Tank( TANK_IMAGE);
          this.tank_b = new Tank( TANK_IMAGE);
          this.breakable_wall = new Wall("src/resource/Wall1.gif");         
          this.breakable_wall.setWallMap();          
          player_a = new Player(tank_a,breakable_wall,"3");
          player_b = new Player(tank_b,breakable_wall,"4");          
          player_a.setInitPosition();
          player_b.setInitPosition();
          
        } catch( IOException exception ) {
          System.err.println( "Error in loading images." );
          exception.printStackTrace();
        }
        this.game_animation = new ArrayList<>();
        this.dimension = new Dimension( WIDTH, PANEL_HEIGHT );
    }

    @Override
    public Dimension getPreferredSize() {
         return this.dimension;
    }

    @Override
    public void paintComponent( Graphics graphics ) {
        super.paintComponent( graphics );
        miniGame = miniImage.createGraphics();
        for( int x = 0; x < WIDTH; x += background.getWidth() ) {
            for( int y = 0; y < HEIGHT; y+= background.getHeight() ) {
                background.setImageX( x );
                background.setImageY( y );              
                background.doDrawing(miniGame);
            }
        }

       
        GameAnimation animation;
        for( int counter = 0; counter < game_animation.size(); counter++ ) {
            animation = game_animation.get( counter );
            if( animation.isStopped() ) {
                game_animation.remove( counter );
            } else {               
                animation.doDrawing( miniGame );
            }
        }

      
        breakable_wall.doDrawing(miniGame);
        tank_a.doDrawing( miniGame );
        tank_b.doDrawing( miniGame );
        miniGame = graphics;

        tank_aViewImg = miniImage.getSubimage(getTankPositionX(tank_a), getTankPositionY(tank_a), WIDTH/2, PANEL_HEIGHT);
        tank_bViewImg = miniImage.getSubimage(getTankPositionX(tank_b), getTankPositionY(tank_b), WIDTH/2, PANEL_HEIGHT);

        miniGame.drawImage(tank_aViewImg,0,0,WIDTH/2-1,PANEL_HEIGHT,this);
        miniGame.drawImage(tank_bViewImg,WIDTH/2,0,WIDTH/2,PANEL_HEIGHT,this);      
        miniGame.drawImage(miniImage, HEIGHT/2-175, 225, 320, 250,this);
              
        player_a.displayHealth(miniGame, 5, PANEL_HEIGHT-35);
        player_b.displayHealth(miniGame, WIDTH/2+10, PANEL_HEIGHT-35);
      
        player_a.displayLives(miniGame, 230, PANEL_HEIGHT-35);
        player_b.displayLives(miniGame, WIDTH/2+240, PANEL_HEIGHT-35 );
                
        player_a.displayPoints(miniGame,WIDTH/2-200, PANEL_HEIGHT-10);
        player_b.displayPoints(miniGame,WIDTH-200, PANEL_HEIGHT-10);
        
        
    }

   
    public int getTankPositionX(Tank tanks) {
        int positionX;
        positionX = tanks.getImageX() - WIDTH/4;

        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > (WIDTH/2)) {
            positionX = (WIDTH/2);
        }
        return positionX;
    }

   
    public int getTankPositionY(Tank tanks) {
        int positionY;
        positionY = tanks.getImageY() - PANEL_HEIGHT/2;

        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > (HEIGHT - PANEL_HEIGHT)) {
            positionY = (HEIGHT - PANEL_HEIGHT);
        }
        return positionY;
    }

        
    public void doAnimation( GameAnimation animation ) {
        game_animation.add( animation );
    }


}
