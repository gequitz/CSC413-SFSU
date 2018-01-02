/* Holds the bytecode objects and resolves address for branch instructions */

package interpreter;


//Gabriel Equitz, SFSU ID: 915254839


import interpreter.ByteCode.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Program {
    
    
    private ArrayList<ByteCode> program ;
    
    public Program(){
        program = new ArrayList<ByteCode>();
    }
    
    //load the bytecodes
    public ByteCode getCode(int pc) {
        return program.get(pc);
    }
    //labelList is the hashmap used to resolve target addresses  
    private static HashMap<String, Integer> labelList = new HashMap<String, Integer>();
    
   //add the bytecodes
    public void add(ByteCode byteCode) {
        if (byteCode instanceof LabelCode){
            LabelCode label = (LabelCode)byteCode;
            labelList.put(label.getLabel(), program.size());
        }
        program.add(byteCode);
    }
    
   
   
    //resolves address 
    public void resolveAddrs() {
        Integer jumpAddress;
        for (int i=0; i < program.size(); i++) {            
            if (program.get(i) instanceof BranchCode){
                BranchCode temp = (BranchCode)program.get(i);
                jumpAddress = new Integer(labelList.get(temp.getLabel()));
                temp.setTargetAddress(jumpAddress.intValue());
            }          
        }
    }
    
    public ArrayList<ByteCode> getByteCodeList() {
        return program;
    }
}
