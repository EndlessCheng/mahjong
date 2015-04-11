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
public class Game {
    int[] yama = new int[34];
    public Game()
    {
        for(int i = 0; i< yama.length;i++){
            yama[i] = 4;
        }
        int draw = (int) (Math.random()*34);
    }
    
}
