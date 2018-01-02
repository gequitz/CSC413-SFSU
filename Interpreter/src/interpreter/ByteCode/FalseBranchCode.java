/*
 * FALSEBRANCH <label> - pop the top of the stack; if it is false(0) then branch to 
 * <label> else execute the next bytecode.
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839



import interpreter.VirtualMachine;
import java.util.ArrayList;


public class FalseBranchCode extends BranchCode {
    
    private String label;
    private int targetAddress;
 
    public void init(ArrayList<String> args) {
        label = args.get(0);
        
    }
    
    public void execute(VirtualMachine vm) {
        
        if(vm.runStack.pop() == 0) {
            vm.pc = targetAddress;
       }
        
        
        
        //verify if the dumpMode is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("FALSEBRANCH " + " " + label);
        }
    }
    
    public int getTargetAddress(){
        return targetAddress;
    }
    
    public void setTargetAddress(int n){
        targetAddress = n;
    }
    
    public String getLabel(){
        return label;
    }
}