package tank;

import java.awt.*;

public class Player {
    private String tankPosition;
    private int tankX, tankY;
    private int health = 200;
    private int lives = 3;
    private int score = 0;
    private boolean end = false;
    private int MAP_HEIGHT = 40;
    private int MAP_WIDTH = 39;  
    private Tank tank;
    private Wall wall;

   
    public Player(Tank tank, Wall wall, String tankPosition) {
        this.tank = tank;
        this.wall = wall;
        this.tankPosition = tankPosition;
    }

    public void setInitPosition() {
        for (int row = 0; row < MAP_HEIGHT; row++) {
            for (int col = 0; col < MAP_WIDTH; col++) {                
                if (wall.getWallMap()[row][col].equals( tankPosition)) {
                    tankX = col * wall.getWidth();
                    tankY = row * wall.getHeight();
                }
            }
        }
        tank.setImageX(tankX);
        tank.setImageY(tankY);
    }

    public void displayHealth(Graphics graphics, int x, int y) {
        if (health > 120){
            graphics.setColor(Color.green);
            graphics.fillRect(x, y, health,30);
        } else if (health > 40 && health <= 120) {
            graphics.setColor(Color.yellow);
            graphics.fillRect(x, y, health,30);
        } else if (health > 0 && health <= 40){
            graphics.setColor(Color.red);
            graphics.fillRect(x, y, health,30);
        } else {
            lives -= 1;
            health = 200;
        }
    }

    public void displayLives(Graphics graphics, int x, int y) {
        int pixel = 40;       
        graphics.setColor(Color.WHITE);

        for (int life = 0; life < lives-1; life ++) {
            graphics.fillOval(x + life*pixel ,y,25,25);
        }

        if (lives == 0) {
            this.end = true;
            graphics.setColor(Color.black);
            Font font = graphics.getFont().deriveFont( 70.0f );
            graphics.setFont(font);
            graphics.drawString("Game Over!", 450, 500);

        }
    }

    public void displayPoints(Graphics graphics, int x, int y) {
        graphics.setColor(Color.black);        
        Font font = graphics.getFont().deriveFont( 30.0f );
        graphics.setFont(font);
        graphics.drawString(Integer.toString(score),x , y );
    }
    
    
     public boolean endGame() {
        return end;
    }

    public boolean die() {
        return health == 0;
    }

    public void hitTank() {
        this.health -= 40;
    }

       
    public void increaseScore() {
        score += 10;
    }

   
}



