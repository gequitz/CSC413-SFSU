
package Lazarus;


/**
 * Lazarus Game
 * @author Gabriel Equitz (ID: 915254839) and Michael Schweibert (ID: 915254839)
 * Team 6 - CSC413
 */

/* The goal of Lazarus (the game) is to get the character, Lazarus, out of the pit. Boxes are dropped on 
Lazarus, but once Lazarus hits the stop button the machine that drops boxes is stopped.
If a box falls on Lazarus, the game is lost. Lazarus must
climb on the boxes to get to the stop button. The kinds of boxes are, from lightest to heaviest:
cardboard, wood, stone and  metal. If a 
heavier box falls on a lighter one, the lighter one is crushed.
The movement of Lazarus is controlled by the left and right 
arrow keys. 
There are three levels for the game.  
*/

/*
This class instantiates objects of the SoundEffects.java 
and LazarusWorld.java classes,
as well as sets up the frame for the game. 
It has the “main” method that initializes the game.
*/

import java.awt.event.WindowAdapter;
import javax.swing.JFrame;


public class GameStart {
    public static void main(String argv[]) {
        SoundEffects play_sound = new SoundEffects();          
       // play_sound.play_continously("src/resource/Music.wav"); 
        play_sound.play_continously("src/resource/Music.mid"); 
        final LazarusWorld lw = new LazarusWorld();
        lw.init();
        JFrame jf = new JFrame("Get Lazarus out of the Pit");
        jf.addWindowListener(new WindowAdapter() {});        
        jf.getContentPane().add("Center", lw);
        jf.setSize(lw.getWindowWidth(), lw.getWindowHeight());
        jf.setResizable(true);
        jf.setVisible(true);
        lw.start();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}
