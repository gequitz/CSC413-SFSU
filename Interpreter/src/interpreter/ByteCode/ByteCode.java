/*
 * Abstract class for the bytecodes
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839


import interpreter.VirtualMachine;
import java.util.ArrayList;


public abstract class ByteCode {
    
   // initializes bytecode
    public abstract void init(ArrayList<String> args);
    
   //executes the virtual machine
    public abstract void execute(VirtualMachine vm);
}

