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
            case '1':value = 1;break;
            case '9':value = 9;break;
            case '8':value = 8;break;
            case '7':value = 7;break;
            case '6':value = 6;break;
            case '5':value = 5;break;
            case '4':value = 4;break;
            case '3':value = 3;break;
            case '2':value = 2;break;
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
        if(s<=8){
            shoku = shoku.MANZU;
            value = s + 1;
        }
        else if(s>8&&s<=17){
            shoku = shoku.SOUZU;
            value = s - 8;
        }
        else if(s>17&&s<=26){
            shoku = shoku.PINZU;
            value = s - 17;
        }
        else if(s>26&&s<=33){
            shoku = shoku.KAZE;
            value = s -26;
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
}
