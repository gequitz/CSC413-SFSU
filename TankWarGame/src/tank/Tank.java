package tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank extends ImageObject{
    private int[] angle = new int[60];
    private int MAP_HEIGHT = 40;
    private int MAP_WIDTH = 39;
    private int newX = 0;
    private int newY = 0;
    private int DELTA = 10;    
    private int degree = 0;
    private int indx = 0;
    private Icon tankDirection;

    public Tank(String imageLocation) throws IOException {
        super(imageLocation);             
        tankDirection = new Icon("src/resource/TankImage.png", 64);
    }

    public void setDegree() {
        for (int row = 0; row < angle.length; row++) {
            angle[row] = degree;
            degree += 6;
        }
    }

    public void setIndex(int indx) {
        this.indx = indx;
    }

    public boolean collideWithWall(Wall wall) {
        try {           
            wall.setWallMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle tank = new Rectangle(newX,newY,getWidth()-DELTA,getHeight()-DELTA);
        for (int row = 0; row < MAP_HEIGHT; row++) {
            for (int col = 0; col < MAP_WIDTH; col++) {                
                if (wall.getWallMap()[row][col].equals("1") || wall.getWallMap()[row][col].equals("2")) {

                    Rectangle walls = new Rectangle(col * wall.getWidth(), row * wall.getHeight(),
                                    wall.getWidth()-DELTA, wall.getHeight()-DELTA);

                    if (tank.intersects(walls)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public boolean collideWithTank(Tank anotherTank) {
        Rectangle tank = new Rectangle(newX,newY,getWidth()-DELTA,getHeight()-DELTA);
        Rectangle secondTank = new Rectangle(anotherTank.getImageX(), anotherTank.getImageY(),
                            anotherTank.getWidth()-DELTA, anotherTank.getHeight()-DELTA);

        return tank.intersects(secondTank);
    }

    
    public void rotateRight() {
        if (indx > 0) {
            indx--;
        } else {
            indx = 59;
        }
        image = tankDirection.getFrame(indx);
    }
    
    public void rotateLeft() {
        if (indx < 59) {
            indx++;
        } else {
            indx = 0;
        }
        image = tankDirection.getFrame(indx);
    }

    

    public void reverseMove() {
        newX = this.getImageX() - (int) (Math.cos(Math.toRadians(angle[indx])) * DELTA);
        newY = this.getImageY() + (int) (Math.sin(Math.toRadians(angle[indx])) * DELTA);
    }
    
     public void forwardMove() {
       
        newX = this.getImageX() + (int) (Math.cos(Math.toRadians(angle[indx])) * DELTA);
        newY = this.getImageY() - (int) (Math.sin(Math.toRadians(angle[indx])) * DELTA);
    }

    public int fireX() {
        return this.x + this.getWidth() / 2
                + (int) ((int) (this.getWidth() / 2 * (Math.cos(Math.toRadians(50))))
                * 2 * (Math.cos(Math.toRadians(this.angle[indx]))));
    }

    public int fireY() {
        return this.y + this.getHeight() / 2
                - (int) ((int) (this.getHeight() / 2 * (Math.cos(Math.toRadians(50))))
                * 2 * (Math.sin(Math.toRadians(this.angle[indx]))));
    }
    
    public int getIndex() {
        return indx;
    }

    public int[] getAngle() {
        return angle;
    }

    public int getNewX() {
        return this.newX;
    }

    public int getNewY() {
        return this.newY;
    }

    

    @Override   
    public void doDrawing( Graphics graphics ) {
        graphics.drawImage( tankDirection.getFrame(indx), x, y, observer );
    }
}


