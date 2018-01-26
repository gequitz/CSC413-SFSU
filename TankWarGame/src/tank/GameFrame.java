package tank;

import javax.swing.*;

public class GameFrame extends JFrame {

  public GameFrame( GamePanel gp ) {
        setTitle( "Tank War" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        add( gp );
        pack();
        Thread th = new Thread( gp );
        th.start();
        setVisible( true );
    }
}
