/*
This class extends GameWorld.java. It loads the
background, the sprites images, converts image files 
in buffered images, and checks the collisions
between boxes, Lazarus, wall and stop buttons.
*/
package Lazarus;




import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


public class LazarusWorld extends GameWorld {
    
    
    public BufferedImage[] LazarusSprtStand, LazarusSprtAfraid, LazarusSprtSquished, LazarusSprtLeft, LazarusSprtRight, LazarusSprtJumpLeft,
            LazarusSprtJumpRight, WallSprt, CardSprt, MetalSprt, StoneSprt, WoodSprt, StopSprt;   
    public int[] pressedKeys = { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    private static HashMap<String, Image> sprites;     
    private ArrayList<Lazarus>  lazarus_array = new ArrayList<>();  
    private ArrayList<Box> box_array = new ArrayList<>();  
    private ArrayList<StopButton> stop_array = new ArrayList<>();  
    private  int backgroundWidth = 656, backgroundHeight = 520;
    private boolean displayTitle = true;
    public int speed = 5;
    private int level = 1;
   
    
    
    protected BufferedImage endImage = new BufferedImage(backgroundWidth,backgroundHeight,BufferedImage.TYPE_INT_RGB); 

    @Override
    public void init() {
        setWidth(backgroundWidth);
        setHeight(backgroundHeight);
        setFocusable(true);
        setBackground(Color.darkGray);
        sprites = new HashMap<String, Image>();

        try {
                               
            loadSprites();            
          
            WallSprt = convertToSprite(convertToBuffered(sprites.get("Wall")), 40, 1); 
            CardSprt = convertToSprite(convertToBuffered(sprites.get("CardBox")), 40, 1);
            MetalSprt = convertToSprite(convertToBuffered(sprites.get("MetalBox")), 40, 1);
            StoneSprt = convertToSprite(convertToBuffered(sprites.get("StoneBox")), 40, 1);
            WoodSprt = convertToSprite(convertToBuffered(sprites.get("WoodBox")), 40, 1);
            StopSprt = convertToSprite(convertToBuffered(sprites.get("Button")), 40, 1);
            LazarusSprtStand = convertToSprite(convertToBuffered(sprites.get("LazarusStand")), 40, 1);
            LazarusSprtAfraid = convertToSprite(convertToBuffered(sprites.get("LazarusAfraid")), 40, 10);
            LazarusSprtSquished = convertToSprite(convertToBuffered(sprites.get("LazarusSquished")), 40, 11);
            LazarusSprtLeft = convertToSprite(convertToBuffered(sprites.get("LazarusLeft")), 80, 7);
            LazarusSprtRight = convertToSprite(convertToBuffered(sprites.get("LazarusRight")), 80, 7);
            LazarusSprtJumpLeft = convertToSprite(convertToBuffered(sprites.get("LazarusJumpLeft")), 80, 7);
            LazarusSprtJumpRight = convertToSprite(convertToBuffered(sprites.get("LazarusJumpRight")), 80, 7);
            
            lazarus_array.add(new Lazarus(LazarusSprtStand,LazarusSprtLeft,LazarusSprtRight,LazarusSprtJumpLeft,LazarusSprtJumpRight,
                    LazarusSprtSquished,LazarusSprtAfraid,pressedKeys,speed));
            loadGameMap("/resource/level0.txt", 40, 40);
            selectRandomBox();
            BufferedImage[][] BoxKind = {CardSprt, WoodSprt, StoneSprt, MetalSprt};
            int rand = ThreadLocalRandom.current().nextInt(0, 4);            
            box_array.add(new Box(BoxKind[rand], lazarus_array.get(0).getX(), 0, speed, (char)(rand + 49), true));
            eventNotifier = new EventNotifier();
            eventNotifier.addObserver(lazarus_array.get(0));
            KeyControl key = new KeyControl();
            addKeyListener(key);
        } catch (Exception e) {
            System.out.println("Error - Incorrect file name for sprite: " + e);
        }
    }

    //loads the images
    private void loadSprites() {

        sprites.put("title", getSprite("/resource/Title.png"));
        sprites.put("background", getSprite("/resource/Background.png"));
        sprites.put("CardBox", getSprite("/resource/CardBox.png"));
        sprites.put("MetalBox", getSprite("/resource/MetalBox.png"));
        sprites.put("StoneBox", getSprite("/resource/StoneBox.png"));
        sprites.put("WoodBox", getSprite("/resource/WoodBox.png"));
        sprites.put("Wall", getSprite("/resource/Wall.png"));
        sprites.put("Button", getSprite("/resource/Button.png"));
        sprites.put("LazarusStand", getSprite("/resource/Lazarus_stand.png"));
        sprites.put("LazarusAfraid", getSprite("/resource/Lazarus_afraid_strip10.png"));
        sprites.put("LazarusLeft", getSprite("/resource/Lazarus_left_strip7.png"));
        sprites.put("LazarusRight", getSprite("/resource/Lazarus_right_strip7.png"));
        sprites.put("LazarusJumpLeft", getSprite("/resource/Lazarus_jump_left_strip7.png"));
        sprites.put("LazarusJumpRight", getSprite("/resource/Lazarus_jump_right_strip7.png"));
        sprites.put("LazarusSquished", getSprite("/resource/Lazarus_squished_strip11.png"));        

    }

  
    public void selectRandomBox() {
        BufferedImage[][] BoxKind = {CardSprt, WoodSprt, StoneSprt, MetalSprt};
        int rand = ThreadLocalRandom.current().nextInt(0, 4);        
        box_array.add(new Box(BoxKind[rand], 0, 440, speed, (char)(rand + 49), false)); 
    }

    public void dropBox() {
        if (!lazarus_array.get(0).isDead()) {
            if (!box_array.get(box_array.size() - 2).isDropping()) {
                box_array.get(box_array.size() - 1).setXY(lazarus_array.get(0).getX() - lazarus_array.get(0).getX() % 40, 0);              
                box_array.get(box_array.size() - 1).getDropping(true);
                selectRandomBox();
            }
        }
    }

    
    public boolean verifyCollision(GameObject obj1, GameObject obj2, char type) {
        if (obj1.box.intersects(obj2.box)) {
            obj1.setCollision(type);
            return true;
        }
        return false;
    }

    public void verifyLazarusAndStop() {
        if (!lazarus_array.get(0).isDead()) {
            for (int i = 0; i < stop_array.size(); i++) {                
                if (verifyCollision(lazarus_array.get(0), stop_array.get(i), ' ')) {
                    box_array.clear();
                    stop_array.clear();  
                    try {                       
                        loadGameMap("/resource/level" + level + ".txt", 40, 40); 
                    } catch (Exception e) {
                        System.out.println("Error: Incorrect file name for level: " + e);
                    }
                    selectRandomBox();
                    BufferedImage[][] BoxKind = {CardSprt, WoodSprt, StoneSprt, MetalSprt};
                    int rand = ThreadLocalRandom.current().nextInt(0, 4);                    
                    box_array.add(new Box(BoxKind[rand], lazarus_array.get(0).getX(), 0, speed, (char)(rand + 49), true));
                    level++;
                }
            }
        }
    }

    public void verifyLazarusAndBox() {
        if (!lazarus_array.get(0).isDead()) {
            for (int i = 0; i < box_array.size(); i++) {
                if (box_array.get(i).getIsDisplayed()) {
                    if (verifyCollision(lazarus_array.get(0), box_array.get(i), '0')) {
                        lazarus_array.get(0).update();           
                    }
                }
            }
        }
    }

    public void verifyBoxAndBox() {
        for (int i = 0; i < box_array.size(); i++) {
            for (int j = 0; j < box_array.size(); j++) {
                if (j != i && box_array.get(j).getIsDisplayed()) {
                    if (box_array.get(i).isDropping()) {
                        if (box_array.get(j).getBoxKind() > box_array.get(i).getBoxKind() || box_array.get(j).getBoxKind() == '0' ||
                                box_array.get(i).getBoxKind() == box_array.get(j).getBoxKind()) {
                            if (verifyCollision(box_array.get(i), box_array.get(j), '0')) {                               
                                SoundEffects play_sound = new SoundEffects();
                                play_sound.play_once("src/resource/Wall.wav");                                 
                                box_array.get(i).update();
                                if (displayTitle) {
                                    displayTitle = false;
                                }
                            }
                        
                        } else if (verifyCollision(box_array.get(i), box_array.get(j), ' ')){ 
                            SoundEffects play_sound = new SoundEffects();
                            play_sound.play_once("src/resource/Crush.wav");                                 
                            box_array.get(j).setIsDisplayed(false); 
                            
                        }
                    }
                }
            }
        }
    }

    public void verifyBoxAndLazarus() {
        if (!lazarus_array.get(0).isDead()) {
            for (int i = 0; i < box_array.size(); i++) {
                if (box_array.get(i).isDropping() && !lazarus_array.get(0).isDead()) {
                    if (verifyCollision(lazarus_array.get(0), box_array.get(i), '1')) {  
                        SoundEffects play_sound = new SoundEffects();
                        play_sound.play_once("src/resource/Squished.wav"); 
                        lazarus_array.get(0).update();
                    }
                }
            }
        }
    }

    
    
    public void loadGameMap(String fileName, int pixelX, int pixelY) throws Exception {
        int x = 0;
        int y = 0;
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            BufferedReader aReader = new BufferedReader(new InputStreamReader(in));
            int a = aReader.read();
            while (a > -1) {
                while (a != '\n') {
                    if (a == ' ') {
                       x += pixelX; 
                    }
                    else if(a == 's' ){
                       stop_array.add(new StopButton(StopSprt, x, y));
                       x += pixelX; 
                    }
                    else if(a == 'x'){
                        box_array.add(new Box(WallSprt, x, y, speed, '0', false));
                        x += pixelX; 
                    }
                    else if(a == 'l'){
                        lazarus_array.get(0).setXY(x,y);
                        x += pixelX;
                    }                    
                    a = aReader.read();
                }
                a = aReader.read();
                x = 0;
                y += pixelY;
                if (a == ' ') {
                    a = aReader.read();
                }
            }
        } catch (IOException e) {
            System.out.println("Error in loading game map: " + e);
        }
    }


    public void drawWall() {
        for (int i = 0; i < box_array.size(); i++) {
            box_array.get(i).draw(g2, this);
        }
    }

    public void drawStopButton() {
        for (int i = 0; i < stop_array.size(); i++) {
            stop_array.get(i).draw(g2, this);
        }
    }

    public void drawBackground(){
        g2.drawImage(sprites.get("background"), 0, 0, this);
    }


    
    public void draw() {
        drawBackground();
        if (level > 3) {   // game won         
           g2.setColor(Color.white);                          
           g2.setFont(new Font("Calibri", 0, 60));            
           g2.drawString("\t\tYou Win!", 200, 400);
           
        } else {           
            verifyLazarusAndStop();
            verifyBoxAndLazarus();
            verifyLazarusAndBox();
            verifyBoxAndBox();
            dropBox();
            drawWall();
            drawStopButton();
            lazarus_array.get(0).draw(g2, this);
            if (displayTitle) {
                g2.drawImage(sprites.get("title"), 70, 0, this);
            }
        }
    }

   @Override
    public void paint(Graphics gr) {        
        BufferedImage bimg = (BufferedImage) createImage(backgroundWidth, backgroundHeight);
        g2 = bimg.createGraphics();
        draw();
        g2.dispose();
        gr.drawImage(bimg, 0, 0, this);
    } 

    
}


