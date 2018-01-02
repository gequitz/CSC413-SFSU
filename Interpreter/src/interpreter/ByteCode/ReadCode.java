/*
 * READ; Read an integer; prompt the user for input; put the value just read on top of the stack
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ReadCode extends ByteCode {
    
    public void init(ArrayList<String> args) {
        
    }
    
     public void execute(VirtualMachine vm) {
         //ask the user to enter an integer
         System.out.print("Please input an integer: ");
         
         try {
             BufferedReader in = new BufferedReader( new InputStreamReader(System.in ) );
             String line = in.readLine();
             vm.runStack.push(Integer.parseInt(line));
         } catch( java.io.IOException ex ) {   
         }
         
         //verify if dump mode is on
         if("ON".equals(vm.dumpMode)) {
             System.out.println("READ");
         }
     }
}