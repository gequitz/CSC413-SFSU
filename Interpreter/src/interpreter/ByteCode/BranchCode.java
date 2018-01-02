/*
 * Abstract class for branching bytecode. Superclass for CallCode, GotoCode and FalseBranchCode
 * Subclass of ByteCode 
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;



public abstract class BranchCode extends ByteCode {
    public abstract void init(ArrayList<String> args);
    public abstract void execute(VirtualMachine vm);
    public abstract int getTargetAddress();
    public abstract void setTargetAddress(int n);
    public abstract String getLabel();
}



