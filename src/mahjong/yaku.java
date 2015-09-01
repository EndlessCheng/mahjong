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
public class yaku {
    //清一色
    public boolean chiniisou(tehai h){
        //日后补充门清判定
        boolean r = true;
        int s = h.tehai[0].shoku.shokuValue();
        for(int i =1; i< h.tehaiCount; i++)
        {
            if(h.tehai[i].shoku.shokuValue()!=s)
            {
                r = false;
            }
        }
        return r;
        
    }
    
    //混一色
    public boolean honiisou(tehai h){
        boolean r= true;
        int s1 = h.tehai[0].shoku.shokuValue();
        int s2 = 0;
        for(int i =1; i< h.tehaiCount; i++)
        {
            if(h.tehai[i].shoku.shokuValue()==s1){}                
            else if(h.tehai[i].shoku.shokuValue()!=s1 && s2 == 0){
                s2 = h.tehai[i].shoku.shokuValue();
            }
            else r = false;
            
        }
        if(s1!=30&&s2!=30&&r){
            r = false;
        }
        return r;
    }
    //断幺
    public boolean tanyao(tehai h){
        for(int i = 0;i<h.tehaiCount; i++){
            if(h.tehai[i].is19()){
                return false;
            }
                
        }
        return true;
            
    }
    
    //pinhu
    //tanyao
    //riichi
    //kazehai
    //bakazehai
    //sanenhai
    //ipeikou
    //dora
    //
    
    //    private boolean isKokushi()
//    {
//        boolean r = false;
//        if(this.all19())
//        {
//            if(this.searchForHai("1m"))
//            {
//                if(this.searchForHai("1p"))
//                {
//                    if(this.searchForHai("1s"))
//                    {
//                        if(this.searchForHai("9m"))
//                        {
//                            if(this.searchForHai("9p"))
//                            {
//                                if(this.searchForHai("9s"))
//                                {
//                                    if(this.searchForHai("1k"))
//                                    {
//                                        if(this.searchForHai("2k"))
//                                        {
//                                            if(this.searchForHai("3k"))
//                                            {
//                                                if(this.searchForHai("4k"))
//                                                {
//                                                    if(this.searchForHai("5k"))
//                                                    {
//                                                        if(this.searchForHai("6k"))
//                                                        {
//                                                            if(this.searchForHai("7k"))
//                                                            {
//                                                                r=true;
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return r;
//    }
}
