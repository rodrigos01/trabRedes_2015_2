/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabRedes.routing;

import java.util.List;
import trabRedes.Packet;
import trabRedes.messenger.view.PacketTableModel;

/**
 *
 * @author rodrigo.santos
 */
public class RouterTableModel extends PacketTableModel {

    public RouterTableModel(List<Packet> dataSet) {
        super(dataSet);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Packet packet = dataSet.get(rowIndex);
        switch(columnIndex) {
            case 2:
                return packet.getDestination().getHostAddress();
            case 3:
                return packet.getDestinationPort();
            default:
                return super.getValueAt(rowIndex, columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 2:
                return "Destino";
            case 3:
                return "Porta";
            default:
                return super.getColumnName(column);
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    
    
}
