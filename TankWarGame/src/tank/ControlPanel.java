package tank;

import java.io.IOException;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class ControlPanel extends GamePanel implements KeyListener {
    private SoundEffects sound;
    private Icon explosion;
    private Icon bullet;    
    private boolean w_is_pressed, s_is_pressed, a_is_pressed, d_is_pressed, space_is_pressed;
    private boolean up_is_pressed, down_is_pressed, left_is_pressed, right_is_pressed, enter_is_pressed;
    

    public ControlPanel() {
        super();
        this.setFocusable( true );
        this.addKeyListener( this );        
        new Thread( this ).start();
        try {  
            this.explosion = new Icon("src/resource/Explosion_small_strip6.png", 32 );
            this.bullet = new Icon("src/resource/Shell_basic_strip60.png", 24 );
            this.sound = new SoundEffects(); 
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        tank_a.setDegree();
        tank_b.setDegree();

        do {
            if ( ! ( player_a.endGame() || player_b.endGame() ) ) {

                if ( left_is_pressed ) {
                    tank_b.rotateLeft();
                }
                if ( right_is_pressed ) {
                    tank_b.rotateRight();
                }

                if ( up_is_pressed ) {
                    tank_b.forwardMove();
                    if ( !( ( tank_b.collideWithWall( breakable_wall ) ) || ( tank_b.collideWithTank( tank_a ) ) ) ) {
                        tank_b.setImageX( tank_b.getNewX() );
                        tank_b.setImageY( tank_b.getNewY() );
                    }
                }

                if ( down_is_pressed ) {
                    tank_b.reverseMove();
                    if ( !( ( tank_b.collideWithWall( breakable_wall ) ) || ( tank_b.collideWithTank( tank_a ) ) ) ) {
                        tank_b.setImageX( tank_b.getNewX() );
                        tank_b.setImageY( tank_b.getNewY() );
                    }
                }

                if ( enter_is_pressed ) {
                   
                    doAnimation( new GameAnimation( this.explosion, tank_b.fireX(), tank_b.fireY(),
                            1, false ) );
                  
                    doAnimation( new TankBullet( this, tank_b, tank_a, player_b, player_a,
                            breakable_wall, this.bullet, tank_b.fireX(), tank_b.fireY(), 5, false ) );

                    sound.play_once("src/resource/Explosion_small.wav");
                }

                if ( a_is_pressed ) {
                    tank_a.rotateLeft();
                }

                if ( d_is_pressed ) {
                    tank_a.rotateRight();
                }

                if ( w_is_pressed ) {
                    tank_a.forwardMove();
                    if ( !( ( tank_a.collideWithWall( breakable_wall ) ) || ( tank_a.collideWithTank( tank_b ) ) ) ) {
                        tank_a.setImageX( tank_a.getNewX() );
                        tank_a.setImageY( tank_a.getNewY() );
                    }
                }

                if ( s_is_pressed ) {
                    tank_a.reverseMove();

                    if ( !( ( tank_a.collideWithWall( breakable_wall ) ) || ( tank_a.collideWithTank( tank_b ) ) ) ) {
                        tank_a.setImageX( tank_a.getNewX() );
                        tank_a.setImageY( tank_a.getNewY() );
                    }
                }

                if ( space_is_pressed ) {
                   
                    doAnimation( new GameAnimation( this.explosion, tank_a.fireX(), tank_a.fireY(),
                            1, false ) );
                   
                    doAnimation( new TankBullet( this, tank_a, tank_b, player_a, player_b,
                            breakable_wall, this.bullet, tank_a.fireX(), tank_a.fireY(), 5, false ) );

                    sound.play_once("src/resource/Explosion_small.wav");
                }
            }

           
            this.repaint();
          

            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }

        } while ( true );

    }
    
    
    @Override
    public void keyReleased( KeyEvent k ) {
        
        if ( k.getKeyCode() == KeyEvent.VK_A ) {
            a_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_D ) {
            d_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_W ) {
            w_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_S ) {
            s_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_SPACE ) {
            space_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_LEFT ) {
            left_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_RIGHT ) {
            right_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_UP ) {
            up_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_DOWN ) {
            down_is_pressed = false;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_ENTER ) {
            enter_is_pressed = false;
        }
        
    }

    @Override
    public void keyTyped( KeyEvent k ) {}
    
    
    @Override
    public void keyPressed( KeyEvent k ) {
        
        if ( k.getKeyCode() == KeyEvent.VK_D ) {
            d_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_W ) {
            w_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_S ) {
            s_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_SPACE ) {
            space_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_LEFT ) {
            left_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_RIGHT ) {
            right_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_UP ) {
            up_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_DOWN ) {
            down_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_ENTER ) {
            enter_is_pressed = true;
        }
        else if ( k.getKeyCode() == KeyEvent.VK_A ) {
            a_is_pressed = true;
        } 
        
        
       
    }

    

    
}



