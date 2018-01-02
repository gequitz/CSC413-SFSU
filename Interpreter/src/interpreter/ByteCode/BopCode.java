/*
 * This function levels of the stack and perform indicated operations. Operations are:
 * +, -, /, *, ==, !=, <=, >, >=, < | &, where | and & are logical operators, not bit operators
 * lower level is the first operand.
 */
package interpreter.ByteCode;

//Gabriel Equitz, SFSU ID: 915254839

import interpreter.VirtualMachine;
import java.util.ArrayList;



public class BopCode extends ByteCode {
    
    private String op;
    
    public void init(ArrayList<String> args) {
        op = args.get(0);
    }
    
    public void execute(VirtualMachine vm) {
        //pop operands
        int topOper = vm.runStack.pop();
        int secondOper = vm.runStack.pop();
        
        //do operations
        switch(op) {
            case "+":
                vm.runStack.push(secondOper+topOper);
                break;
            case "-":
                vm.runStack.push(secondOper-topOper);
                break;
            case "/":
                vm.runStack.push(secondOper/topOper);
                break;
            case "*":
                vm.runStack.push(secondOper*topOper);
                break;
            case "==":
                if(secondOper == topOper) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "!=":
                if(secondOper != topOper) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "<=":
                if(secondOper <= topOper) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case ">":
                if(secondOper > topOper) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case ">=":
                if(secondOper >= topOper) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "<":
                if(secondOper < topOper) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "|":
                if(secondOper == 0 &&  topOper == 0) {
                    vm.runStack.push(0);
                } else {
                    vm.runStack.push(1);
                }
                break;
            case "&":
                if(secondOper == 1  &&  topOper == 1) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;    
        }
        
        //check if the dump is on
        if("ON".equals(vm.dumpMode)) {
            System.out.println("BOP" + " " + op);
        }
    }
}


