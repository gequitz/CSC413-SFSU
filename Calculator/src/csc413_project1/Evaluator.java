/*
 * Gabriel Equitz - sfsu id: 915254839
 */
package csc413_project1;
import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
 // private static final String DELIMITERS = "+-*^/#! ";
  private static final String DELIMITERS = "+-*/#!^ ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval( String expression ) {
    String token;
    operandStack.clear();
    operatorStack.clear();
    expression = expression + "!";
    
    operatorStack.push(new PoundOperator());

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    

    while ( this.tokenizer.hasMoreTokens() ) {
      
      
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
          if ( ! Operator.check( token )) {
            System.out.println( "*****invalid token******" );
            System.exit( 1 );
          }

          
         
          Operator newOperator = (Operator) Operator.operators.get(token);
          
          if(newOperator.priority() <= operatorStack.peek().priority()) {

            //while ( operatorStack.peek().priority() >= newOperator.priority() && operandStack.size() > 1 ) {
            while ( newOperator.priority() <= operatorStack.peek().priority()  && operandStack.size() > 1 ) {    
            
              Operator oldOpr = operatorStack.pop();
              Operand op2 = operandStack.pop();
              Operand op1 = operandStack.pop();
              operandStack.push( oldOpr.execute( op1, op2 ));
            }
            operatorStack.push(newOperator);
          }
          else{
              operatorStack.push(newOperator);
          }

          
        } 
        
      }

    }

    
    
    operatorStack.clear();
    return operandStack.peek().getValue();
  }
}