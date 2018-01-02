//Halts code execution
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class HaltCode extends ByteCode {
    
    public void init(ArrayList<String> args) {
        
    }
    
    public void execute(VirtualMachine vm) {
        //terminate the program
        vm.isRunning = false;
        
        //check if the dumpMode is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("HALT");
        }
    }
}

