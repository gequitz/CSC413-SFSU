//draws the map of the walls

package tank;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class Wall extends ImageObject{
    private static BufferedReader map;
    private BufferedImage unbreakable_wall;
    private int mapHeight = 40;
    private int mapWidth = 39;
    
    private String[][] wallMap = new String[mapHeight][mapWidth];

    public Wall(String WallSource) throws IOException{
        super(WallSource);      
               
        map = new BufferedReader(new FileReader("src/resource/background_map.txt"));       
        unbreakable_wall = ImageIO.read(new File("src/resource/Wall2.gif"));
    }

    
     public void setWallMap() throws IOException{
        int row = 0;
        String line;

        while ((line = map.readLine()) != null) {
            for (int col = 0; col < wallMap[row].length; col++) {
                wallMap[row][col] = String.valueOf(line.charAt(col));
            }
            row++;
        }
    }
        
    
    public String[][] getWallMap() {
        return wallMap;
    }
   
    public void setUpdatedWallMap(String[][] newWallMap) {
        this.wallMap = newWallMap;
    }
    
    @Override  
    public void doDrawing( Graphics graphics ) {
        for (x = 0; x < mapWidth; x++) {
            for (y = 0; y < mapHeight; y++) {
                if (wallMap[y][x].equals("1")) {
                    graphics.drawImage( unbreakable_wall, x*unbreakable_wall.getWidth(), y*unbreakable_wall.getHeight(), observer );
                } else if (wallMap[y][x].equals("2")) {
                    graphics.drawImage( image, x*image.getWidth(), y*image.getHeight(), observer );
                }
            }
        }
        
    }
}

