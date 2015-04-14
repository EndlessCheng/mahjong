/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mahjong;

/**
 *
 * @author Hakurei
 */
public class shoku {
    public char shoku;
    
    public shoku(char in)
    {
        shoku = in;
    }
    
     public final static shoku MANZU = new shoku('m');
     public final static shoku SOUZU = new shoku('s');
     public final static shoku PINZU = new shoku('p');
     public final static shoku KAZE = new shoku('k');
     
     public int shokuValue()
     {
         if (MANZU==this) return 0;
         else if(SOUZU==this) return 10;
         else if (PINZU==this) return 20;
         else return 30;
     } 
             
       
         
}
