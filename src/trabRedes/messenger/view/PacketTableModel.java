/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabRedes.messenger.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import trabRedes.Packet;

/**
 *
 * @author rodrigo.santos
 */
public class PacketTableModel extends AbstractTableModel {
    
    protected List<Packet> dataSet;

    public PacketTableModel(List<Packet> dataSet) {
        this.dataSet = dataSet;
    }
    
    public void add(Packet packet) {
        dataSet.add(packet);
        int index = dataSet.size() - 1;
        fireTableRowsInserted(index, index);
    }
    
    public void remove(int index) {
        dataSet.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public void update(int index, Packet packet) {
        dataSet.set(index, packet);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public int getRowCount() {
        return dataSet.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Origem";
            case 1:
                return "Porta";
            case 2:
                return "Mensagem";
            default:
                return "";
        }
    }
        

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Packet packet = dataSet.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return packet.getSource().getHostAddress();
            case 1:
                return packet.getSourcePort();
            case 2:
                return packet.getMessage();
            default:
                return "";
        }
        
    }
    
}
