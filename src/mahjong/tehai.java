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
    hai[] sim=new hai[18];
    int tehaiCount = 0;
    int[][] yuukou = new int[15][38];
    String origin = "";
    tehai(String s){
        origin = s;
        int scount = 0;
        for(int i = 0 ; i <s.length();i++ ){
            
            if(Character.isDigit(s.charAt(i)))
            {
            
            }
            else if(s.charAt(i)=='m'||s.charAt(i)=='p'||s.charAt(i)=='s'||s.charAt(i)=='k')
            {
                
                for(int j = scount; j<i;j++)
                {
                    char[] n = new char[2];
                    n[0]=s.charAt(j);
                    n[1]=s.charAt(i);
                    String t = new String(n);
                    tehai[tehaiCount] =new hai(t);
                    sim[tehaiCount] = new hai(t);
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
        this.sortTehai();
//        yuukou = this.simulation();
    }
    
    public tehai copy(){
        tehai copy = new tehai(this.origin);
        return copy;
    }
   
    public void testPrint()
    {
        if(tehaiCount<13)
        {
            System.out.println("not a valid hand");
        }
        else{
            for(int i = 0;i<tehaiCount;i++)
            {
                tehai[i].testPrint();
            }
        }
        System.out.println();
        if(this.stepsToWin()!=0){
            System.out.println(this.stepsToWin() +" 向聴");
        }
        else {
            System.out.println("tenpai");
        }

    
//        for(int i = 0;i<15;i++){
//            if(yuukou[i][0]==1){
//                System.out.print("打");
//                this.tehai[i].testPrint();
//                System.out.print("摸");
//                int count =0;
//                for(int j =1;j<38;j++){
//                    if(yuukou[i][j]==1){
//                        new hai(j).testPrint();
//                        count++;                      
//                    }
//                }
//                System.out.println("共" + count +"种");
//                
//            }
//        }
    
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
    
    private boolean isKokushi()
    {
        boolean r = false;
        if(this.all19())
        {
            if(this.searchForHai("1m"))
            {
                if(this.searchForHai("1p"))
                {
                    if(this.searchForHai("1s"))
                    {
                        if(this.searchForHai("9m"))
                        {
                            if(this.searchForHai("9p"))
                            {
                                if(this.searchForHai("9s"))
                                {
                                    if(this.searchForHai("1k"))
                                    {
                                        if(this.searchForHai("2k"))
                                        {
                                            if(this.searchForHai("3k"))
                                            {
                                                if(this.searchForHai("4k"))
                                                {
                                                    if(this.searchForHai("5k"))
                                                    {
                                                        if(this.searchForHai("6k"))
                                                        {
                                                            if(this.searchForHai("7k"))
                                                            {
                                                                r=true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return r;
    }
    
    private boolean kotsu(hai a,hai b, hai c)
    {
        return a.identical(b)&&b.identical(c);
    }
    
    private boolean shuntsu(hai a, hai b, hai c)
    {
        return a.increase(b)&&b.increase(c);
    }
     
     private void sortTehai(){
        int[] sortvalue = new int[this.tehaiCount];
        
        for(int i = 0;i<tehaiCount;i++)
        {
            //System.out.println(this.tehai[i].haiSortValue());
            sortvalue[i]=this.tehai[i].haiSortValue();
            
        }
        java.util.Arrays.sort(sortvalue);
        /*for(int i = 0;i<tehaiCount;i++){
            System.out.print(sortvalue[i]);
            
        }*/
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
    }
    
   /* private tehai tempRemove(int index){
        tehai t = this;
        for(int i = 0;i<this.tehaiCount;i++){
            if(i!=index - 1){
                t.tehai[i]=this.tehai[i];
            }
        }
        this.tehai=t.tehai;
        this.tehaiCount--;
        return t;
    }//想不起来当时我为什么写的这个方法，应该改是在判断点数的时候会使用到*/
    
    
   /* private R scoreCheck(){
        tehai t = this;
        R r = new R('y', 32000, 1.0);
        
        if(t.hasPair()){
            if(t.isKokushi()){
                return r;
            }
        }
        return r;
    }*/
    
  /*  private boolean leftMostKotsu()
    {
        return this.kotsu(this.tehai[0],this.tehai[1], this.tehai[2]);
    }*/
    
    private int findNextDifferent(int i){
        int c = i;
        while(c<tehaiCount){
            if(!tehai[i].identical(tehai[c])){
                break;
            }
            c++;
        }
        return c;
    }//用于移动到下一非重复手牌
   /* private boolean leftMostShuntsu(){
        return this.shuntsu(this.tehai[0], this.tehai[this.findNextDifferent(0)], this.tehai[this.findNextDifferent(this.findNextDifferent(0))]);
    }*/
    
    private int[] tileCounter(){
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
    
    
    
     int stepsToWin(){
        int i = 0;
        int steps = 8;
        int steps7 = 6;
        int stepsk = 13;
        while(this.tehai[i]!=null){
            if(this.tehai[i+4]!=null&&this.tehai[i].sp23334(this.tehai[i], this.tehai[i+1], this.tehai[i+2], this.tehai[i+3], this.tehai[i+4])){
                steps -=3;
                i+=5;
            }
            else if(this.tehai[i+2]!=null&&(shuntsu(this.tehai[i],this.tehai[i+1],this.tehai[i+2])||kotsu(this.tehai[i],this.tehai[i+1],this.tehai[i+2]))){
                steps -= 2;
                i += 3;
            }
            else if(this.tehai[i+1]!=null&&this.tehai[i].tatsu(this.tehai[i+1])){
                steps -=1;
                i += 2;
            }
            else {
                i++;
            }
        }
        steps7 = steps7 - tileCounter()[0];
        stepsk = stepsk - this.different19() - this.hasA19Pair();
        return Math.min(Math.min(steps, steps7),stepsk);
    }//向数检测
    
    /*对数组进行操作可能会导致计算空间过大。能否采用别的可能性？粗略计算游戏树，
    每个节点有18个对象的数组，应该会是个相当大的工程量。*/
  /*    private int[][] simulation() {
        int current = this.stepsToWin();
        int[][] board = new int[15][38];
        for(int i = 1; i < 14;i++){
            for(int j = 1; j <37;j++){
                //替换机制
                tehai next = 
                next.tehai[i-1]= new hai(j);
                next.sortTehai();
                if(next.stepsToWin()<current){
                    board[i][0]=1;
                    board[i][j]=1;
                }
            }
        }
        return board;
    }*/
      
      private void resetTehai(){
          for(int i = 0; i < tehaiCount;i++){
              sim[i]=tehai[i].copy();
          }
      }
      

      
      
   
}

  
