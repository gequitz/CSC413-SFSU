/* Holds the runtime stack and the frame pointers */

package interpreter;

//Gabriel Equitz, SFSU ID: 915254839

import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;


public class RunTimeStack {
    
    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointers;
    
    
    public RunTimeStack() {
        runTimeStack = new ArrayList<Integer>();
        framePointers = new Stack<Integer>();        
        framePointers.add(0);
    }
    
   
    public void dump() {
        Iterator iter = framePointers.iterator();
        int nextFrame, currentFrame = (Integer) iter.next();
        //print contents of runtime stack 
        for (int i = 0; i < framePointers.size(); i++) {
            if (iter.hasNext()) {
                nextFrame = (Integer) iter.next();
            } else {
                nextFrame = runTimeStack.size();
            }

            System.out.print("[");
            //print contents of current frame
            for (int j = currentFrame; j < nextFrame; j++) {
                System.out.print(runTimeStack.get(j));
                if (j != nextFrame - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            currentFrame = nextFrame;
        }
        System.out.println();
    }
    
    //retrieves the last element
    public int peek() {      
        return runTimeStack.get(runTimeStack.size() - 1);
    }
    
    //pops the last element
    public int pop() {        
        int temp = runTimeStack.get(runTimeStack.size() - 1);        
        runTimeStack.remove(runTimeStack.size() - 1);
        return temp;
    }
    
    //adds the last element
    public int push(int i) {      
        runTimeStack.add(i);
        return i;
    }
    
   //starts a new frame
    public void newFrameAt(int offset) {
        framePointers.push(this.runTimeStack.size() - offset);
    }
    
   //pop the frame
    public void popFrame() {
        int temp = this.peek();
        int temp1 = framePointers.pop();
        for (int i = runTimeStack.size() - 1; i >= temp1; i--) {
            this.pop();
        }
        this.push(temp);
    }
    
   //stores value
    public int store(int offset) {
        int temp = this.pop();
        runTimeStack.set(framePointers.peek() + offset, temp);
        return temp;
    }
    
   //loads value
    public int load(int offset) {
        int temp = runTimeStack.get(framePointers.peek() + offset);
        runTimeStack.add(temp);
        return temp;
    }
    
    //checks if stack is empty
    public Boolean empty() {
        if (runTimeStack.size() == 0) {
            return true;
        }
        return false;
    }
    
    //pushes value into the stack
    public Integer push(Integer i) {
        runTimeStack.add(i);
        return i;
    }
    
    //gets the offset
    public int getOffset() {
        return runTimeStack.size() - framePointers.peek() - 1;
    }
    
    
    //get value
    public int getValueAtOffset(int offset) {        
         return runTimeStack.get(framePointers.peek() + offset);
    }
    
    
}
