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
       
       
       
       
       int save = m.simulate(t);
       System.out.println(save);
//       int c = 0;
//       while(t.tehai[c]!=null){
//           if(save[c][0]==1){
//               System.out.print("打");
//               t.tehai[c].testPrint();                
//               System.out.print("摸");
//               int count =0;
//               for(int j =1;j<38;j++){             
//                   if(save[c][j]==1){
//                       new hai(j).testPrint();
//                        count++;                      
//                    }
//                }
//               System.out.println("共" + count +"种");
//                
//            }
//            c++;
//        }
    }
    
    public int simulate(tehai h){
        int current = h.getStw();
        int[][] board = new int[h.tehaiCount][38];

        //原代码    
        
        for(int i = 0; i < h.tehaiCount;i++){
            for(int j = 1; j <37;j++){
                //替换机制
                tehai next = h.copy();
                next.replace(i, j);
//                if(next.getStw()<current&&j%10!=0&&current>1){
//                    board[i][0]=1;
//                    //board[i][j]=1;
//                    board[i][j]=simulate(next);
//                }
//                else if(next.getStw()<current&&j%10!=0&&current==1)
//                {
                if(next.getStw()<current&&j%10!=0){
                    board[i][0]=1;
                    board[i][j]=1;
                //}
                }
            }
        }
        int c = 0;
        int count =0;
        while(h.tehai[c]!=null/*&&h.findNextDifferent(h.tehai,c)!=-1*/){
            if(board[c][0]==1){
                int scount=0;
                System.out.print("打");
                h.tehai[c].testPrint();                
                System.out.print("摸");
                for(int j =1;j<38;j++){             
                    if(board[c][j]!=0){
                        new hai(j).testPrint();
                        count+=board[c][j];
                        scount++;
                    }
                }
               System.out.println("共" + scount +"种");
                
            }
            if(h.findNextDifferent(h.tehai,c)!=-1){
                c=h.findNextDifferent(h.tehai,c);
            }
            else{
                break;
            }
        }
        return count;
    }
    
    
    
    
    
}
