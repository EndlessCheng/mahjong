package mahjong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.sourceforge.jpcap.util.*;
import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;

public class Mahjong {
    String hand;
     private static String readUserInput() throws IOException
     {
        InputStreamReader r = new InputStreamReader(System.in);
        return new BufferedReader(r).readLine();
    }
     
    public void writeHand(String s){
        this.hand += s;
    }
   
    public static void main(String[] args) throws CloneNotSupportedException
<<<<<<< HEAD
    {   
        Mahjong m = new Mahjong();
        try {
            if(1==1){
                Sniffer sniffer = new Sniffer("\\Device\\NPF_{874B5A44-BE03-460C-92F4-56B47158FC6B}");
            } 
            else{
                System.out.println("Usage: java Sniffer [device name]");
                System.out.println("Available network devices on your machine:");
                String[] devs = PacketCapture.lookupDevices();
                for(int i = 0; i < devs.length ; i++)
                System.out.println("\t" + devs[i] + "\t" + "num:" + i);
            }
        } catch(Exception e) {
            e.printStackTrace();
            }
       
//        String str="";
//       try
//       {
//           str = readUserInput();
//       }
//       catch(Exception e){   
//            e.printStackTrace();  
//       }
//       String haiString ="";
//                for(int i=0;i<str.length();i++){
//                    if(Character.isWhitespace(str.charAt(i))){
//                        str.replace(str.charAt(i), ' ');
//                    }
//                }
//                String[] temp = str.split(" ");
//                for (String temp1 : temp) {
//                    if (temp1.startsWith("hai=")){
//                        String[] temp2 = temp1.split("\"");
//                        String[] handRaw = temp2[1].split(",");
//                        for(String temp3 : handRaw){
//                            haiString += hai.tenhouConv(temp3);
//                        }
//                        
//                    }
//                    else if(temp1.startsWith("<T")){
//                        haiString += hai.tenhouConv(temp1.split("/")[0].substring(2));
//                    }
//                }
//            
//            System.out.println(haiString);
//       tehai t = new tehai(str);
//       t.testPrint();
//       //m.simulate(t,0);
//       int s = m.simulate(t,0);
//       //System.out.println(s);
=======
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
>>>>>>> origin/master
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
<<<<<<< HEAD
                if(next.getStw()<current&&j%10!=0&&current>1&&a<2){
=======
                if(next.getStw()<current&&j%10!=0&&current>1){
>>>>>>> origin/master
                    board[i][j]=simulate(next,a+1);
                    board[i][0]+=board[i][j];
                    sum+=board[i][j];
                }
<<<<<<< HEAD
                else if(next.getStw()<current&&j%10!=0&&(current==1||a==2))
=======
                else if(next.getStw()<current&&j%10!=0&&current==1)
>>>>>>> origin/master
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
<<<<<<< HEAD
                //h.tehai[i].testPrint();
                //System.out.println(board[i][0]);
=======
                h.tehai[i].testPrint();
                System.out.println(board[i][0]);
>>>>>>> origin/master
                if(board[i][0]!=0&&board[i][0]>board[max][0]){
                    max = i;
                }
                i++;
                
            }
<<<<<<< HEAD
            System.out.print("Recommended：");
=======
            System.out.print("推荐打：");
>>>>>>> origin/master
            h.tehai[max].testPrint();
                
        }
        
        return sum;
    }
 }
