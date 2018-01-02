/*
 * WRITE; Write the value on top of the stack to output; leave the value 
 * on top of the stack
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class WriteCode extends ByteCode {

    public void init(ArrayList<String> args) {
        
    }
    
     public void execute(VirtualMachine vm) {
         System.out.println(vm.runStack.peek());
         
         //verify if the dumpMode is on
         if("ON".equals(vm.dumpMode)) {
             System.out.println("WRITE");
         }
     }
}



