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
       //m.simulate(t,0);
       int s = m.simulate(t,0);
       //System.out.println(s);
    }
    
    public static int simulate(tehai h,int adv){
        //System.out.println("now calculating:");
        //h.testPrint();
        int current = h.getStw();
        int[][] board = new int[h.tehaiCount][38];
        int a = adv;
        int sum =0;
        
        //原代码    
        
        for(int i = 0; i < h.tehaiCount;i++){
            for(int j = 1; j <38;j++){
                //替换机制
                tehai next = h.copy();
                //next.testPrint();
                next.replace(i, j);
                //next.testPrint();
                if(next.getStw()<current&&j%10!=0&&current>1){
                    board[i][j]=simulate(next,a+1);
                    board[i][0]+=board[i][j];
                    sum+=board[i][j];
                }
                else if(next.getStw()<current&&j%10!=0&&current==1)
                {
                    board[i][j]=1;
                    board[i][0]+=1;
                    sum+=1;
                    //System.out.println(sum);
                }
            }
        }
        
//        for(int i=0;i<h.tehaiCount;i++)
//        {
//            if(board[i][0]!=0)
//            {
//                sum+=board[i][0];
//                //System.out.println(sum);
//            }
//        }
//        int c = 0;
//        int count =0;
//        while(h.tehai[c]!=null/*&&h.findNextDifferent(h.tehai,c)!=-1*/){
//            if(board[c][0]!=0){
//                int scount=0;
//                System.out.print("打");
//                h.tehai[c].testPrint();                
//                System.out.print("摸");
//                for(int j =1;j<38;j++){             
//                    if(board[c][j]!=0){
//                        new hai(j).testPrint();
//                        count+=board[c][0];
//                        scount++;
//                    }
//                }
//               System.out.println("共" + scount +"种");
//                
//            }
//            if(h.findNextDifferent(h.tehai,c)!=-1){
//                c=h.findNextDifferent(h.tehai,c);
//            }
//            else{
//                break;
//            }
//        }
        if(a == 0){
            int max =0 ;
            int i = 0;
            while(h.tehai[i]!=null){
                h.tehai[i].testPrint();
                System.out.println(board[i][0]);
                if(board[i][0]!=0&&board[i][0]>board[max][0]){
                    max = i;
                }
                i++;
                
            }
            System.out.print("推荐打：");
            h.tehai[max].testPrint();
                
        }
        
        return sum;
    }
 }
