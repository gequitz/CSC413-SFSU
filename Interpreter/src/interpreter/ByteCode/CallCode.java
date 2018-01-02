/*
 * Transfer control to the indicated function
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class CallCode extends BranchCode {
    
    protected String functionName;
    protected int targetAddress;
    protected int value;
    
    public void init(ArrayList<String> args) {
        functionName = args.get(0); 
    }
    
    public void execute(VirtualMachine vm) {
        
        vm.returnAddrs.push(vm.pc);
        vm.pc = targetAddress;
        value = vm.runStack.peek();
        
        //verify if the dump is on
        if("ON".equals(vm.dumpMode)) {
            int n = functionName.indexOf("<");
            String temp;
            if(n<0){
                temp = functionName;
            }else{
                temp = functionName.substring(0,n);
            }
            System.out.println("CALL " + functionName + "    " + temp + "(" + value + ")");
        }
    }
    
    public int getTargetAddress(){
        return targetAddress;
    }
    
    public void setTargetAddress(int n){
        targetAddress = n;
    }
    
    public String getLabel(){
        return functionName;
    }
}
