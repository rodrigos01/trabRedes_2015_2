/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabRedes;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author rodrigo.santos
 */
public class Packet implements Serializable {

    private InetAddress source, destination;
    private int sourcePort, destinationPort;
    private String message;

    public InetAddress getSource() {
        return source;
    }

    public void setSource(InetAddress source) {
        this.source = source;
    }
    
    public void setSource(String source) throws UnknownHostException {
        this.source = InetAddress.getByName(source);
    }

    public InetAddress getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) throws UnknownHostException {
        this.destination = InetAddress.getByName(destination);
    }

    public void setDestination(InetAddress destination) {
        this.destination = destination;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
