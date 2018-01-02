/* Executes each bytecode loaded into the program */

package interpreter;

//Gabriel Equitz, SFSU ID: 915254839 

import interpreter.ByteCode.ByteCode;
import interpreter.ByteCode.DumpCode;
import java.util.Stack;


public class VirtualMachine {
    
    public RunTimeStack runStack;
    public int pc;    
    public Stack<Integer> returnAddrs;
    public boolean isRunning;
    public Program program;
    public String dumpMode = "OFF";//default  
    
    
    public VirtualMachine(Program program) {
        this.program = program;
    }
    
   // public int getPC(){
   //     return pc;
   // }
    
   // public void setPC(int aPC){
   //     pc = aPC;
   // }
    
   //execute the program
    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if("ON".equals(dumpMode) && !(code instanceof DumpCode)) {
                runStack.dump();
            }
            pc++;
        }
    }
}
