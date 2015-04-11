/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahjong;

/**
 *
 * @author Darkflamemaster
 */
public class R {
    boolean result = false;
    int score;
    double chance;
    public R(char a,int b,double c){
        if(a == 'y'){
            this.result = true;
        }
        score = b;
        chance = c;
    }
    
}
