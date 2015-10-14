/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahjong;

import java.util.Arrays;
import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.TCPPacket;

/**
 *
 * @author Administrator
 */
class PacketHandler implements PacketListener 
{   
    String haiString ="";
    
    public void packetArrived(Packet packet) {
    try {
      // only handle TCP packets
        
      if(packet instanceof TCPPacket) {
	TCPPacket tcpPacket = (TCPPacket)packet;
	byte[] data = tcpPacket.getTCPData();
        
        //System.out.println(Arrays.toString(data));
        
        //System.out.println(Arrays.toString(data));
	    
	String srcHost = tcpPacket.getSourceAddress();
	String dstHost = tcpPacket.getDestinationAddress();
        
        if(srcHost.equals("133.242.10.78")||dstHost.equals("133.242.10.78")){
            int zeroCount = 0;
            for(int i = 0;i<data.length;i++){
                if(data[i]==0){
                    zeroCount++;
                }
            }
            byte[] dataTrim = new byte[data.length-zeroCount];
            int offset = 0;
            for(int i = 0;i<data.length;i++){
                if(data[i]!=0){
                    dataTrim[i-offset]=data[i];
                }
                else{
                    offset++;
                }
            }
            //System.out.println(Arrays.toString(dataTrim));
            String isoData =new String(dataTrim);
            
            //System.out.println("Tenhou packet detected:");
            System.out.println(srcHost+" -> " + dstHost + ": " + isoData);
            
            if(isoData.contains("INIT")){
                System.out.println("起始手牌");
//                for(int i=0;i<isoData.length();i++){
//                    if(Character.isWhitespace(isoData.charAt(i))){
//                        isoData.replace(isoData.charAt(i), ' ');
//                    }
//                }
                String[] temp = isoData.split(" ");
                for (String temp1 : temp) {
                    System.out.println(temp1);
                    if (temp1.startsWith("hai=")){
                        String[] temp2 = temp1.split("\"");
                        String[] handRaw = temp2[1].split(",");
                        for(String temp3 : handRaw){
                            haiString += hai.tenhouConv(temp3);
                            
                        }
                        System.out.println(haiString);
                        
                    }
                    if(temp1.contains("<T")){
                        haiString += hai.tenhouConv(temp1.split("<T")[1].split("/")[0]);
                        System.out.println("增加牌:"+hai.tenhouConv(temp1.split("<T")[1].split("/")[0]));
                    }
                }
                
            }
            else if(haiString.length()==26&&isoData.contains("<T")){
                String[] temp = isoData.split(" ");
                 for (String temp1 : temp) {
                     System.out.println(temp1);
                    if(temp1.contains("<T")){
                    
                        haiString += hai.tenhouConv(temp1.split("<T")[1].split("/")[0]);
                        System.out.println("增加牌:"+hai.tenhouConv(temp1.split("<T")[1].split("/")[0]));
                    }
                 }
            }
            else if(haiString.length()==28){
                System.out.println(haiString);
            }
        }
        
            
	

	
      }
      if(haiString.length()==28){
          tehai t = new tehai(haiString);
          int s =Mahjong.simulate(t, 0);
      }
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }
}
