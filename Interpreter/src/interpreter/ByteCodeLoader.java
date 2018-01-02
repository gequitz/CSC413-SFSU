/* Loads the codes from the source file and  bytcodes objects into instance of program class;
* resolves symbolic addresses.
*/

package interpreter;

//Gabriel Equitz, SFSU ID: 915254839 

import interpreter.ByteCode.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class ByteCodeLoader extends Object{
    
    private BufferedReader byteSource;
       
    
    public ByteCodeLoader(String codeFile) throws IOException {
       byteSource = new BufferedReader(new FileReader(codeFile));
    }
    
   //load the bytecodes
    public Program loadCodes() throws  IOException{ 
       Program program = new Program();  
       
      
       ArrayList<String> args = new ArrayList<String>();
       
       //read line of the program
       String code = byteSource.readLine();
      
       
       while (code != null) {
      
            //clear argument
            args.clear();  
            StringTokenizer tok = new StringTokenizer(code);
            
           
            String save_token = tok.nextToken();
            if (save_token != null) {
           
                String codeClass = CodeTable.getClassName(save_token);
            
                while(tok.hasMoreTokens() ) {           
                   args.add(tok.nextToken());
                }
            
           
                try{
                    if (codeClass != null){
                        ByteCode byteCode = (ByteCode)(Class.forName("interpreter.ByteCode."+codeClass).newInstance());             
            
                        //initialize bytcode instance
                        byteCode.init(args);            
               
                        program.add(byteCode);
               
                    }
                     code = byteSource.readLine();
            
                }
                catch(InstantiationException |IllegalAccessException | ClassNotFoundException  e){
                   System.out.println("Exception occurred " + e + "\n");
                }
                       
            }
          
        }
       //resolve the target addresses for branching bytcodes
       program.resolveAddrs();  
       return program;
    }     
 
}



