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
   
    public static void main(String[] args) throws CloneNotSupportedException
    {
       Mahjong m = new Mahjong();
      
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
        int[][] save = m.simulate(t);
        for(int i = 0;i<15;i++){
            if(save[i][0]==1){
                System.out.print("打");
                t.tehai[i].testPrint();
                System.out.print("摸");
                int count =0;
                for(int j =1;j<38;j++){
                    if(save[i][j]==1){
                        new hai(j).testPrint();
                        count++;                      
                    }
                }
                System.out.println("共" + count +"种");
                
            }
        }
    }
    
    public int[][] simulate(tehai h){
         int current = h.stepsToWin();
        int[][] board = new int[15][38];
        for(int i = 1; i < 14;i++){
            for(int j = 1; j <37;j++){
                //替换机制
                tehai next = h.copy();
                next.replace(i-1, j);
                if(next.stepsToWin()<current){
                    board[i][0]=1;
                    board[i][j]=1;
                }
            }
        }
        return board;
    }
    
    
    
}
