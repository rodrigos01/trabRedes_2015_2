/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabRedes.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabRedes.Packet;
import trabRedes.routing.RoutingTable;

/**
 *
 * @author rodrigo
 */
public class Server extends DatagramSocket implements Runnable {

    public static final int DEFAULT_PORT = 0; //Get any available port
    private ServerListener listener;
    
    Thread serverThread, senderThread;
    boolean isStarted = false;
    
    public final RoutingTable table = new RoutingTable();

    public Server(int port) throws SocketException, UnknownHostException {
        super(port, InetAddress.getLocalHost());
    }
    
    public Server() throws SocketException, UnknownHostException {
        this(DEFAULT_PORT);
    }
    
    /**
     * Este método inicia a thread de recebimento de pacotes.
     */
    public void start() {
        serverThread = new Thread(this);
        serverThread.start();
        
        isStarted = true;
    }

    public ServerListener getListener() {
        return listener;
    }

    public void setListener(ServerListener listener) {
        this.listener = listener;
    }

    /**
     * Este método espera em uma thread separada pelos pacotes enviados 
     * por clientes.
     */
    @Override
    public void run() {
        while(true) {
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData,1024);
            try {
                receive(receivePacket);
                Packet proccessedpacket = proccessPacket(receivePacket);
                if(listener!=null){
                    listener.onPacketReceived(proccessedpacket);
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    /**
     * Este método é chamado quando um pacote é recebido. Ele processa o pacote 
     * e envia ao listener.
     * @param packet 
     */
    private Packet proccessPacket(DatagramPacket packet) {

        byte[] dados = packet.getData();
        ByteArrayInputStream byteInput = new ByteArrayInputStream(dados);

        ObjectInputStream inStream;
        Packet received = null;
        try {
            inStream = new ObjectInputStream(byteInput);
            received = (Packet) inStream.readObject();
            inStream.close();
            byteInput.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return received;
    }
    
    public void send(Packet data) throws IOException {
        
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream outStream = new ObjectOutputStream(byteOutput);
        outStream.writeObject(data);
        outStream.close();
        byteOutput.close();
        
        InetAddress serverAddress = data.getDestination();
        int port = data.getDestinationPort();

        byte[] out = byteOutput.toByteArray();
        DatagramPacket packet = new DatagramPacket(out, out.length, serverAddress, port);
        this.send(packet);
    }
    
    public interface ServerListener {
    
        public void onPacketReceived(Packet packet);

    }

    
}
