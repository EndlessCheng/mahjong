/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mahjong;
/**
 *
 * @author Hakurei
 */
public class tehai{
    hai[] tehai = new hai[18];
    hai[] rtehai = new hai[18];
    hai[] sim=new hai[18];
    int tehaiCount = 0;
    String origin = "";
    int mod = 0;
    int stw = 0;
    tehai(String s){
        origin = s;
        int scount = 0;
        for(int i = 0 ; i <s.length();i++ ){//tile initialization
            if(Character.isDigit(s.charAt(i)))
            {
            
            }
            else if(s.charAt(i)=='m'||s.charAt(i)=='p'||s.charAt(i)=='s'||s.charAt(i)=='z')
            {
                
                for(int j = scount; j<i;j++)
                {
                    char[] n = new char[2];
                    n[0]=s.charAt(j);
                    n[1]=s.charAt(i);
                    String t = new String(n);
                    tehai[tehaiCount] =new hai(t);
                    //sim[tehaiCount] = new hai(t);不知道做什么用的
                    rtehai[tehaiCount] = new hai(t);
                    tehaiCount++;
                }
                scount = i+1;
            }
            else
            {
                System.out.println("invalid input");
                System.exit(0);
            }
        }
        if((this.tehaiCount-2)%3!=0){
            System.out.println("total tiles:"+ tehaiCount);
            System.out.println("invalid hand");
            System.exit(0);
        }
        else{
            this.mod = (14-tehaiCount)/3;
        }
        this.sortTehai();
        this.stepsToWin();
    }
    
    tehai(String s,String nocalc){//使用于即将变化的手牌，不立刻计算向听数
        origin = s;
        int scount = 0;
        for(int i = 0 ; i <s.length();i++ ){//tile initialization
            if(Character.isDigit(s.charAt(i)))
            {
            
            }
            else if(s.charAt(i)=='m'||s.charAt(i)=='p'||s.charAt(i)=='s'||s.charAt(i)=='z')
            {
                
                for(int j = scount; j<i;j++)
                {
                    char[] n = new char[2];
                    n[0]=s.charAt(j);
                    n[1]=s.charAt(i);
                    String t = new String(n);
                    tehai[tehaiCount] =new hai(t);
                    //sim[tehaiCount] = new hai(t);不知道做什么用的
                    rtehai[tehaiCount] = new hai(t);
                    tehaiCount++;
                }
                scount = i+1;
            }
            else
            {
                System.out.println("invalid input");
                System.exit(0);
            }
        }
        if((this.tehaiCount-2)%3!=0){
            System.out.println("total tiles:"+ tehaiCount);
            System.out.println("invalid hand");
            System.exit(0);
        }
        else{
            this.mod = (14-tehaiCount)/3;
        }
        this.sortTehai();
        if(!nocalc.equalsIgnoreCase("nocalc")){
            this.stepsToWin();
        }//考虑将有效牌也集成
    }
    
    public tehai copy(){        
        tehai copy = new tehai(this.origin,"nocalc");
        return copy;
    }
   
    public void testPrint()
    {
        
       
            for(int i = 0;i<tehaiCount;i++)
            {
                tehai[i].testPrint();
            }
        
        System.out.println();
        int result = this.stw;
        if(result>1){
            System.out.println(result-1 +" 向聴");
        }
        else if(result==1){
            System.out.println("聽牌");
        }
        else if(result==0){
            System.out.println("和牌");
        }
    }
    
    
    private boolean searchForHai(String s)
    {
        boolean r = false;
        int i = 0 ;
        hai h;
        h = new hai(s);
        while(tehai[i]!=null)
        {
            if(tehai[i]==h) 
            {
                r = true;
                break;
            }
            else 
            {
                i++;
            }
        }//end of while
        return r;
    }
    
    
    private boolean all19()
    {
        int i = 0;
        boolean r = true;
        while (tehai[i]!=null)
        {
            if(!tehai[i].is19())
            {
                r = false;
                break;
            }
            i++;
        }
        return r;
    }
       
    private boolean kotsu(hai a,hai b, hai c)
    {
        return a.identical(b)&&b.identical(c)&&!b.used()&&!c.used();
    }
    
    private boolean shuntsu(hai a, hai b, hai c)
    {
        return a.increase(b)&&b.increase(c)&&!a.isJihai()&&!b.used()&&!c.used();
    }
     
     private void sortTehai(){
        int[] sortvalue = new int[this.tehaiCount];
        
        for(int i = 0;i<tehaiCount;i++){
            sortvalue[i]=this.tehai[i].haiSortValue();
        }
        java.util.Arrays.sort(sortvalue);
        for(int j = 0;j<tehaiCount;j++)
        {
            if(this.tehai[j].haiSortValue()==sortvalue[j])
            {
                
            }
            else
            {
                for(int k = j; k<tehaiCount;k++)
                {
                    if(this.tehai[k].haiSortValue()==sortvalue[j])
                    {
                        hai temp = this.tehai[j];
                        this.tehai[j]=this .tehai[k];
                        this.tehai[k]= temp;
                        
                    }
                }
                    
            }
        }
        
    }
     
     private void RsortTehai(){
        int[] sortvalue = new int[this.tehaiCount];
        
        for(int i = 0;i<tehaiCount;i++)
        {
            sortvalue[i]=this.tehai[i].haiRSortValue();
            
        }
        java.util.Arrays.sort(sortvalue);
        for(int j = 0;j<tehaiCount;j++)
        {
            if(this.tehai[j].haiRSortValue()==sortvalue[j])
            {
                
            }
            else
            {
                for(int k = j; k<tehaiCount;k++)
                {
                    if(this.tehai[k].haiRSortValue()==sortvalue[j])
                    {
                        hai temp = this.tehai[j];
                        this.tehai[j]=this .tehai[k];
                        this.tehai[k]= temp;
                        
                    }
                }
                    
            }
        }
        
    }
    
    private boolean hasPair(){
        boolean r = false;
        int i = 0;
        int j = 0;
        loop:while(i<tehaiCount){
            while(j<tehaiCount){
                if(this.tehai[i].identical(this.tehai[j])){
                 r = true;  
                 break loop;    
                }
            }
        }
        return r;
        //此处实际上可break for，无需全部执行，待修改，可能需要换while?
        //大概已经修改完成，需验证
        
    }
    
    void replace(int index, int n){
        tehai[index] = new hai(n);
        this.sortTehai();
        this.stepsToWin();
    }
    
    public int findNextDifferent(hai[] h,int i){
        int c = i;
        while(h[c]!=null){
            if(!tehai[i].identical(tehai[c]) && i!=c){
                return c;
            }
            else c++;
        }
        return -1;
    }//用于移动到下一非重复手牌
    
    private int findUnused(hai[] h,int i){
        int c = i;
        while(h[c]!=null){
            if(!h[c].used()){
                return c;
            }
            else c++;
        }
        return -1;
    }

    
    private int[] tileCounter(){//用于简易分析手牌组成
        int[] board = new int[14];
        int[] r = new int[3];
        int i = 0;
        int j = 0;
        while(this.tehai[i]!=null){
            if(i==0){
                board[j]++;
            }
            else if(this.tehai[i].identical(this.tehai[i-1])){
                board[j]++;
            }
            else{
                j++;
                board[j]++;
            }
            i++;
        }
        for(int k=0;k<board.length;k++)
        {
            if(board[k]>=2){
                r[0]++;
            }
            if(board[k]>=3){
                r[1]++;
            }
            if(board[k]==4){
                r[2]++;
            }
            
        }
        return r;
    }
    
    private int hasA19Pair(){
        hai temp = new hai("9k");
        int r = 0;
        for(int i = 0;i<tehaiCount;i++){
            if(this.tehai[i].is19()&&this.tehai[i]!=temp){
                temp = this.tehai[i];
            }
            else if(this.tehai[i].is19()&&this.tehai[i]==temp){
                r = 1;
            }
        }
        return r;
    }
    
    private int different19(){
        hai temp = new hai("9k");
        int count = 0;
        for (int i = 0; i< tehaiCount ;i++){
            if(this.tehai[i].is19()&&this.tehai[i]!=temp){
                temp = this.tehai[i];
                count++;
            }
        }
        return count;
    }//可以用此方法重写国士判定，待解决
    
    
    
     private void stepsToWin(){//必要针对不同牌数的情况
        int steps = 9 - this.mod*2;
        int usefulTatsu = 4 - this.mod;
        int tatsuCount = 0;
        int steps7 = 7;
        int stepsk = 14;
        int pairused = 0;
        hai[] localCopy = (hai[])this.tehai.clone();
            while(findUnused(localCopy,0)!=-1){
                int i = findUnused(localCopy,0);
                    if(findNextDifferent(localCopy,i)!=-1&&findNextDifferent(localCopy,findNextDifferent(localCopy,i))!=-1&&shuntsu(localCopy[i],localCopy[findNextDifferent(localCopy,i)],localCopy[findNextDifferent(localCopy,findNextDifferent(localCopy,i))])){
                            steps -= 2;
                            usefulTatsu --;
                            localCopy[i].setUsed();
                            localCopy[findNextDifferent(localCopy,i)].setUsed();
                            localCopy[findNextDifferent(localCopy,findNextDifferent(localCopy,i))].setUsed();                            
                        }
                    else if(localCopy[i+2]!=null&&kotsu(localCopy[i],localCopy[i+1],localCopy[i+2])){
                        steps -= 2;
                        usefulTatsu --;
                        localCopy[i].setUsed();
                        localCopy[i+1].setUsed();
                        localCopy[i+2].setUsed();                    
                    }
                    else if(localCopy[i+1]!=null&&localCopy[i].identical(localCopy[i+1])){
                        steps -= 1;
                        pairused ++;
                        if(pairused>1){
                            tatsuCount++;
                            
                        }
                        localCopy[i].setUsed();
                        localCopy[i+1].setUsed();
                    }
                    else if(findNextDifferent(localCopy,i)!=-1&&localCopy[i].tatsu(localCopy[findNextDifferent(localCopy,i)])/*&&!localCopy[i].isJihai()*/){
                        steps -= 1;
                        tatsuCount++;
                        localCopy[i].setUsed();
                        localCopy[findNextDifferent(localCopy,i)].setUsed();
                    }
                    else{
                        localCopy[i].setUsed();
                    }
                
            }

            if(this.mod == 0){
                steps7 = steps7 - tileCounter()[0];
                stepsk = stepsk - this.different19() - this.hasA19Pair();
            }
            if(tatsuCount>usefulTatsu){
                steps = steps + tatsuCount-usefulTatsu;
            }
            if(steps==0&&pairused==0){
                steps +=1;  
            }
//            else if(steps==1){
//                if(usefulTatsu==1){}
//                else steps+= pairused;
//            }
        this.stw = Math.min(Math.min(steps, steps7),stepsk);
        //return Math.min(Math.min(steps, steps7),stepsk);
            
    }//向数检测
      
      private void resetTehai(){
          for(int i = 0; i < tehaiCount;i++){
              sim[i]=tehai[i].copy();
          }
      }
      
    public int getStw(){
        return this.stw;
    }
    
    public boolean equalTo(String s){
        tehai target = new tehai(s);
        return false;
    }
    
    
    
    public int evaluation(){
        //各种特殊牌型
        //清一色
        return 1;
    }
    
    public int[][] simulate(){
        int current = this.getStw();
        int[][] board = new int[this.tehaiCount][38];
        for(int i = 0; i < this.tehaiCount;i++){
            for(int j = 1; j <38;j++){
                //替换机制
                tehai next = this.copy();
                next.replace(i, j);
                if(next.getStw()<current&&j%10!=0){
                    board[i][0]=1;
                    board[i][j]=1;
                }
            }
        }
        return board;
    }
    
      

      
      
   
}

  
