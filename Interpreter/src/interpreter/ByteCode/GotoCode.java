/*
 * GOTO <label>
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class GotoCode extends BranchCode {
    
    private String label;
    private int targetAddress;
    
    public void init(ArrayList<String> args) {
         label = args.get(0);
    }
    
    public void execute(VirtualMachine vm) {
        
        vm.pc = targetAddress;
        
        //verify if the dump is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("GOTO" + " " + label);
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

