/*
 * POP n: Pop top n levels of runtime stack.
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class PopCode extends ByteCode {
    
    protected int n;

    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm) {
        //Pop top n levels of runtime stack.
        for(int i = 0; i < n; i++) {
            vm.runStack.pop();
        }
        
        //verify if  dumpMode is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("POP" + " " + n);
        }
    }
}

