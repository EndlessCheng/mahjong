package mahjong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mahjong {
     private static String readUserInput() throws IOException
     {
        InputStreamReader r = new InputStreamReader(System.in);
        return new BufferedReader(r).readLine();
    }
   
    public static void main(String[] args)
    {
       String str="";
       try
       {
           str = readUserInput();
       }
       catch(Exception e){   
            e.printStackTrace();  
       }
       tehai t = new tehai(str);
       t.testPrint();
    }
    
    
}
