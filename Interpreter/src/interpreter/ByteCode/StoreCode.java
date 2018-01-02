/*
 * STORE n <id> - pop the top of the stack; store value into the offset n from the start
 * of the frame; <id> is used as a comment, it's the variable name where the data is stored.
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class StoreCode extends ByteCode {
    
    private int n;//offset
    private String id;
    private int value;
    
    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(0));
        id = args.get(1);
    }
    
    public void execute(VirtualMachine vm) {
        
        value = vm.runStack.peek();
        vm.runStack.store(n);
        
        //verify if the dumpMode is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("STORE " + n + " " + id + "    " + id + " = " + value);
        }
    }
}

