package tank;


import java.awt.*;
import java.io.IOException;


public class TankBullet extends GameAnimation{
    private final int speed = 20;
    private int index;
    private int[] angle;
    private int x, y;    
    private Icon explosion, bullet_direction;   
    private Tank tank_a, tank_b;
    private Wall wall;
    private int mapHeight = 40;
    private int mapWidth = 39;
    private int rectWidth1 = 5;
    private int rectWidth2 = 10;
    private int rectHeight1 = 5;
    private int rectHeight2 = 10;
    
    
    private GamePanel gamePanel;
    private Player player_a;
    private Player player_b;

    
    public TankBullet(GamePanel gamePanel, Tank tank_a, Tank tank_b, Player player_a, Player player_b,
                 Wall wall, Icon bullet_direction, int x, int y, int frame_delay, boolean loop) {
        super(bullet_direction, x, y, frame_delay, loop);
       
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        this.player_a = player_a;
        this.player_b = player_b;
        this.bullet_direction = bullet_direction;
        this.angle = tank_a.getAngle();
        this.index = tank_a.getIndex();
        this.tank_a = tank_a;
        this.tank_b = tank_b;
        this.wall = wall;
        
        

        try {            
           explosion = new Icon("src/resource/Explosion_small_strip6.png", 24);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean collideWithWall() {
        try {           
            wall.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Rectangle bullets = new Rectangle(x, y, rectWidth2, rectHeight2);

        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {               
                if ( wall.getWallMap()[row][col].equals("1") || wall.getWallMap()[row][col].equals("2")){
                    Rectangle walls = new Rectangle(col * wall.getWidth(), row * wall.getHeight(),
                                      wall.getWidth(), wall.getHeight());
                    if (bullets.intersects(walls)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void hitWall() {
        try {            
            wall.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Rectangle bullets = new Rectangle(x, y, rectWidth2, rectHeight2);

        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                
                if (wall.getWallMap()[row][col].equals("2")) {
                    Rectangle walls = new Rectangle(col * wall.getWidth(), row * wall.getHeight(),
                                            wall.getWidth(), wall.getHeight());

                    if (bullets.intersects(walls)) {
                       
                        wall.getWallMap()[row][col] = "0"; 
                       
                        wall.setUpdatedWallMap(wall.getWallMap());
                    }
                }
            }
        }
    }

    public boolean collideWithTank() {
        Rectangle bullets = new Rectangle(x, y, rectWidth1, rectHeight1);
        Rectangle tank_aRec = new Rectangle(tank_a.getImageX(), tank_a.getImageY(), tank_a.getWidth()-10, tank_a.getHeight()-10);
        Rectangle tank_bRec = new Rectangle(tank_b.getImageX(), tank_b.getImageY(), tank_b.getWidth()-10, tank_b.getHeight()-10);

        return (bullets.intersects(tank_aRec) || bullets.intersects(tank_bRec));
    }

    public void hitTank() {
        Rectangle bullets = new Rectangle(x, y, rectWidth1, rectHeight1);
        Rectangle tank_aRec = new Rectangle(tank_a.getImageX(), tank_a.getImageY(), tank_a.getWidth()-10, tank_a.getHeight()-10);
        Rectangle tank_bRec = new Rectangle(tank_b.getImageX(), tank_b.getImageY(), tank_b.getWidth()-10, tank_b.getHeight()-10);

        if (bullets.intersects(tank_aRec)) {
            player_a.hitTank();
            if (player_a.die()) {              
                player_b.increaseScore();
                explosion(tank_a);
                player_a.setInitPosition();
                tank_a.setIndex(0);
            }
        }

        if (bullets.intersects(tank_bRec)) {
            player_b.hitTank();
            if (player_b.die()) {               
                player_a.increaseScore();
                explosion(tank_b);
                player_b.setInitPosition();
                tank_b.setIndex(0);
            }
        }
    }

    public void forwardMove() {
        x += (int) (Math.cos(Math.toRadians(angle[index])) * speed);
        y -= (int) (Math.sin(Math.toRadians(angle[index])) * speed);
    } 
    
    public void explosion(Tank deadTank) {        
        gamePanel.doAnimation(new GameAnimation(this.explosion, deadTank.getImageX(), deadTank.getImageY(), 5, false));
    }

    
    @Override
    public void doDrawing( Graphics graphics ) {
  
        if( !stop ) {
            forwardMove();
            graphics.drawImage(bullet_direction.getFrame(index), x, y, null);
            if (collideWithWall()) {
                hitWall();
                stop = true;
            }
            if (collideWithTank()) {
                hitTank();
                stop = true;
            }
        }
    }
}
