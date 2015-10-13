/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahjong;

import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.net.*;

/**
 *
 * @author Administrator
 */
public class Sniffer {
    private static final int INFINITE = -1;
    private static final int PACKET_COUNT = INFINITE; 
  /*
    private static final String HOST = "203.239.110.20";
    private static final String FILTER = 
      "host " + HOST + " and proto TCP and port 23";
  */
    private static final String FILTER = "";
    public Sniffer(String device) throws Exception {
    // Initialize jpcap
    PacketCapture pcap = new PacketCapture();
    System.out.println("Using device '" + device + "'");
    pcap.open(device, 65535, true, 1000);
    pcap.setFilter(FILTER, true);
    pcap.addPacketListener(new PacketHandler());

    System.out.println("Capturing packets...");
    pcap.capture(PACKET_COUNT);
  }
}
