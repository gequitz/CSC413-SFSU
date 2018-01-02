/*
 * LOAD n <id>; push the value in the slot which is offset n from 
 * the start of the frame onto the top of the stack; <id> is used
 * as a comment, it is the variable name from which the data is loaded. 
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class LoadCode extends ByteCode {
    
    private int n;
    private String id;
    
    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(0));
        id = args.get(1);
    }
    
    public void execute(VirtualMachine vm) {
        
        vm.runStack.load(n);
        
        //verify if dump mode is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("LOAD " + n + " " + id + "    <load " + id + ">");
        }
    }
}
