/*
 * RETURN <functionname>; Return from the current function; 
 * <funcname> is used as a comment to indicate the current function
 * Return is indicated for intrinsic functions.
 */

package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class ReturnCode extends ByteCode {
    
    protected String functionName;
    protected int returnValue;
    
    public void init(ArrayList<String> args) {
        if(args.size()>0){
            functionName = args.get(0);
        }else{
            functionName = "";
        }
    }
    
    public void execute(VirtualMachine vm) {
        
        vm.pc = vm.returnAddrs.pop();
        vm.runStack.popFrame();
        returnValue = vm.runStack.peek();
        
        //verify if dumpMode is on
        if("ON".equals(vm.dumpMode)) {
            int n = functionName.indexOf("<");
            String temp;
            if(n<0){
                temp = functionName;
            }else{
                temp = functionName.substring(0,n);
            }
            System.out.println("RETURN " + functionName + "    exit " + temp + ": " + returnValue);
        }
        
    }
}

