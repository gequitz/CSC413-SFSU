/*
 * LABEL <label>; target for branches
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class LabelCode extends ByteCode {
    
    private String label;
    
    
    public void init(ArrayList<String> args) {
        label = args.get(0);
    }
    
     public void execute(VirtualMachine vm) {
         
         //check if the dumpMode is on
         if("ON".equals(vm.dumpMode)) {
             System.out.println("LABEL" + " " + label);
         }
     }
     
     public String getLabel() {
        return label;
    }
}

