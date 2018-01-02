/* Program that acts as an interpreter for a mock computar language.
 *
 * Gabriel Equitz - SFSU ID: 915254839
 */
package interpreter;

import java.io.*;

/**
 * <pre>   
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine   
 * </pre>
 */
public class Interpreter {

    private ByteCodeLoader bcl;

    public Interpreter(String codeFile) {
        try {
            CodeTable.init();
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }

    public void run() throws  IOException {
    
        Program program = bcl.loadCodes();
        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
    }
    
    

    public static void main(String args[]) throws  IOException {
        
        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: " + "java interpreter.Interpreter <file>");
            System.exit(1);
        }
        (new Interpreter(args[0])).run();
       
    }
    
}