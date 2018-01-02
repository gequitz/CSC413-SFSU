/* Determines if instructions are dumped */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;


public class DumpCode extends ByteCode {
    
    private String dumpState;
    
    public void init(ArrayList<String> args) {
        dumpState = args.get(0);
    }
    
     public void execute(VirtualMachine vm) {
         //sets the dumpMode
         vm.dumpMode = dumpState;
         
     }
}
