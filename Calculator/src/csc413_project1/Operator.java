/*
 * Author: Gabriel Equitz 
 */
package csc413_project1;
import java.util.*;

public abstract class Operator {
  // The Operator class should contain an instance of a HashMap
  // This map will use keys as the tokens we're interested in,
  // and values will be instances of the Operators.

  // Example:
  // Where does this declaration go? What should its access level be?
  // Class or instance variable? Is this the right declaration?
  // HashMap operators = new HashMap();
  // operators.put( "+", new AdditionOperator() );
  // operators.put( "-", new SubtractionOperator() );
    
    static HashMap operators = new HashMap();
    static{
        operators.put("#", new PoundOperator());
        operators.put("!", new ExclamationOperator());
        operators.put("+", new AdditionOperator());
        operators.put("-", new SubtractionOperator());
        operators.put("*", new MultiplicationOperator());
        operators.put("/", new DivisionOperator());      
        operators.put("^", new ExponentiationOperator());
    }

  public abstract int priority();
  public abstract Operand execute( Operand operandOne, Operand operandTwo );

  public static boolean check( String token ) {
      return operators.containsKey(token);
  }
}

//poundOperator
class PoundOperator extends Operator {

    @Override
    public int priority() {
        return 0;
    } 

    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        return null;
    } 
} // end PoundOperator

//exclamationOperator
class ExclamationOperator extends Operator {

    @Override
    public int priority() {
        return 1;
    } 

    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        return null;
    } 
} // end ExclamationOperator

//additonOperator
class AdditionOperator extends Operator {

    @Override
    public int priority() {
        return 2;
    } 

    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        Operand result = new Operand(operOne.getValue() + operTwo.getValue());

        return result;
    } 
} // end AdditionOperator

//subtractionOperator
class SubtractionOperator extends Operator {

    @Override
    public int priority() {
        return 2;
    } 

    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        Operand result = new Operand(operOne.getValue() - operTwo.getValue());

        return result;
    } 
} // end SubtractionOperator

//multiplication Operator
class MultiplicationOperator extends Operator {

    @Override
    public int priority() {
        return 3;
    } // end priority

    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        Operand result = new Operand(operOne.getValue() * operTwo.getValue());

        return result;
    } 
} // end MultiplicationOperator

//Division Operator
class DivisionOperator extends Operator {

    @Override
    public int priority() {
        return 3;
    } 

    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        Operand result = new Operand(operOne.getValue() / operTwo.getValue());

        return result;
    } 
} // end DivisionOperator

class ExponentiationOperator extends Operator {

    @Override
    public int priority() {
        return 4;
    } 
    
    @Override
    public Operand execute(Operand operOne, Operand operTwo) {
        int aTempValue = 1;
        for (int i= 0; i< operTwo.getValue(); i++){
            aTempValue = aTempValue*operOne.getValue();
        }
        Operand result = new Operand(aTempValue);

        return result;
    } 
} // end Exponentiation Operator



