/*
 * author: Gabriel Equitz
 */
package csc413_project1;

public class Operand {
   
  private int operandValue;  

  public Operand( String token ) {
      operandValue = Integer.parseInt(token);

  }

  //constructor
  public Operand( int value ) {
     operandValue = value;
  }

  //get value of the operand
  public int getValue() {
      return operandValue;
  }

  //parse token to get the digits
  public static boolean check( String token ) {
      ///System.out.println(token);
      try {
            Integer.parseInt(token);
      } catch (NumberFormatException e) {
            return false;
      }
        
      return true;
  }
}