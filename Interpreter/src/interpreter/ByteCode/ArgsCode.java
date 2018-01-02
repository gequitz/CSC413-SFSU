/* Used prior to calling a function
n= #number of arguments
this instruction is immediately followed by a CALL instruction;
the function has n args so ARGS n instructs the interpreter to set up
a new frame n down from the top, so it will include the arguments
*/

package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;



public class ArgsCode extends ByteCode {
    
    private int n;
        
    public void init(ArrayList<String> args) {
        n = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm) {
        vm.runStack.newFrameAt(n);
        
        //verify if dump mode is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("ARGS" + " " + n);
        }
    }
}
