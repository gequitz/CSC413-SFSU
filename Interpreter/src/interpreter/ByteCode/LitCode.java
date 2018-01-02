/*
 * LIT n - loads the literal value n
 * LIT 0 i - this form of LIT was generated to load 0 on the stack
 * in order to initialize the variable i to 0 and reserve space on 
 * runtime stack for i.
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class LitCode extends ByteCode {
    
    protected int n;
    protected String id;
    
    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(0));
        if(args.size()>1){
            id = args.get(1);
        }else{
            id = "";
        }
    }
    
    public void execute(VirtualMachine vm) {
        
        if("".equals(id)) {
            vm.runStack.push(n);
        } else {
            vm.runStack.push(0);
        }
        
        //verify if dumpMode is on
        if("ON".equals(vm.dumpMode)) {
            String output = "LIT " + n + " " + id;
            if(!id.equals("")){
                output = output + "    int " + id;
            }
            System.out.println(output);
        }
        
    }
}


