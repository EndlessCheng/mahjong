/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mahjong;
import java.math.*;

/**
 *
 * @author Hakurei
 */
public class hai {
    private shoku shoku;
    private int value;
    
    public hai(String s1){
        switch(s1.charAt(0))
        {
            
            case '9':value = 9;break;
            case '8':value = 8;break;
            case '7':value = 7;break;
            case '6':value = 6;break;
            case '5':value = 5;break;
            case '4':value = 4;break;
            case '3':value = 3;break;
            case '2':value = 2;break;
            case '1':value = 1;break;
         }
        switch(s1.charAt(1))
        {
             case 'm':shoku = shoku.MANZU;break;
             case 's':shoku = shoku.SOUZU;break;
             case 'p':shoku = shoku.PINZU;break;
             case 'k':shoku = shoku.KAZE;break;
        }
     }
    
    public hai(int s){
        s = s -1;
        if(s/10==0){
            shoku =shoku.MANZU;
        }
        else if(s/10==1){
            shoku = shoku.SOUZU;
        }
        else if(s/10==2){
            shoku = shoku.PINZU;
        }
        else if(s/10==3){
            shoku = shoku.KAZE;
        }
        else{
            System.out.print("nonexisting tile, error");
            System.exit(0);
        }
        if(s==9||s==19||s==29){
            value = 5;
        }
        else{
            value = s-(s/10)*10+1;
        }
    }/*int型的constructor.0-8 为万字1-9 9-17为索子 18-26为饼子 27-33为字牌*/
            
    public boolean is19()
    {
        if(this.value==1||this.value==9||this.shoku==shoku.KAZE)
        {
            return true;
        }
        else return false;
    }
    
    public void testPrint()
    {
        System.out.print(this.value);
        System.out.print(this.shoku.shoku);
        
    }
    
    public boolean identical(hai a)
    {
        if(a.shoku==this.shoku&&a.value==this.value)
        {
            return true;
        }
        else return false;
    }
    
    public boolean increase(hai a)
    {
        return a.shoku==this.shoku&&a.value==this.value+1;
    }
    
    public int haiSortValue()
    {
        return this.shoku.shokuValue()+value;
    }
    
    public boolean tatsu(hai h){
        return h.shoku==this.shoku&&Math.abs(h.value-this.value)<=2;
    }
    
    public boolean sp23334(hai a, hai b, hai c, hai d,hai e){
        if(a.shoku==b.shoku&&b.shoku==c.shoku&&c.shoku==d.shoku&&d.shoku==e.shoku){
            if(a.value+1==b.value&&b.value==c.value&&c.value==d.value&&d.value+1==e.value){
                return true;
            }
        }
        return false;
    }
    
    public hai copy(){
        int v = this.shoku.shokuValue()+this.value;
        hai n = new hai(v);
        return n;
        }
}
