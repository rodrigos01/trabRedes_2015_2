/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabRedes;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author rodrigo.santos
 */
public class Host {
    
    private InetAddress address;
    private int port;

    public Host(String address) throws UnknownHostException {
        
        String[] gw = address.split(":");
        this.address = InetAddress.getByName(gw[0]);
        this.port = Integer.parseInt(gw[1]);
        
    }
    
    public Host(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
    
}
