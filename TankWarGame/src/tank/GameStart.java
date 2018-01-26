
package tank;

/**
 *
 * @author Gabriel Equitz (ID: 915254839 ) and Michael Schweibert (ID: 916188512)
 * Team 6 - CSC413
 */

/* The project consisted of writing a java computer code to build a tank war game.
The goal of this game is to destroy the enemy tank. Both players drive his or her own tank. 
In the arena, there are breakable and unbreakable walls.
Bullets fired by the tanks can destroy the breakable walls.
Each tank has three lives, with each life having three health levels. 
The levels have the color codes of green, yellow and red.
Health goes down when the tank is hit by a bullet. 
For one player the controls for movement are in the arrow keys and the key “ Enter”
is to fire a bullet. For the second player the controls are in keys w,a,s,d and 
space key is to fire. Each life destroyed allows the winner player to score ten points. 
*/


public class GameStart {
    public static void main( String[] args ) {
        
    SoundEffects play_sound = new SoundEffects();    
    play_sound.play_continously("src/resource/Music.wav");   
    new GameFrame( new ControlPanel() );
  }
}
