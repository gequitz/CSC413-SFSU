/*
 * Author: Gabriel Equitz
 */
package csc413_project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {
    
  Evaluator calculatorEvaluator = new Evaluator();  
  private TextField txField = new TextField();
  private Panel buttonPanel = new Panel();

  // total of 20 buttons on the calculator,
  // numbered from left to right, top to bottom
  // bText[] array contains the text for corresponding buttons
  private static final String[] bText = {
    "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
    "*", "0", "^", "=", "/", "(", ")", "C", "CE"
  };
  private Button[] buttons = new Button[ bText.length ];

  public static void main(String argv[]) {
    EvaluatorUI calc = new EvaluatorUI();
  }

  String myExpr = "";
  public EvaluatorUI() {
    setLayout( new BorderLayout() );

    add( txField, BorderLayout.NORTH );
    txField.setEditable( false );

    add( buttonPanel, BorderLayout.CENTER );
    buttonPanel.setLayout( new GridLayout( 5, 4 ));

    //create 20 buttons with corresponding text in bText[] array
    for ( int i = 0; i < 20; i++ ) {
      buttons[ i ] = new Button( bText[ i ]);
    }

    //add buttons to button panel
    for (int i=0; i<20; i++) {
      buttonPanel.add( buttons[ i ]);
    }

    //set up buttons to listen for mouse input
    for ( int i = 0; i < 20; i++ ) {
      buttons[ i ].addActionListener( this );
    }

    setTitle( "Calculator" );
    setSize( 400, 400 );
    setLocationByPlatform( true  );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );
  }

  public void actionPerformed( ActionEvent arg0 ) {
    // You need to fill in this fuction
    
    String input = txField.getText();
    
         
    
    if (arg0.getSource() == buttons[19]) {	            
	   txField.setText("");  // erase all characters
	   myExpr = "";   
    } else if (arg0.getSource() == buttons[18]) {	// clear one character       
        
	if(!txField.getText().equals("")) //if txField is empty, there is no error.
	txField.setText(txField.getText().substring(0,txField.getText().length()-1)); //erase one character
	
    } else if (arg0.getSource() == buttons[14]) {
	// System.out.println(myExpr);
	  txField.setText("" + Integer.toString(calculatorEvaluator.eval(myExpr))); //calculate expression
    } else  {
        for (int i=0; i<buttons.length; i++){
           if (arg0.getSource() == buttons[i]) {
	      txField.setText(input + bText[i]);
	      myExpr = myExpr + bText[i];
           }
        }
    }

     
     
  }
}


	