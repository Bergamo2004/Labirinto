package controllo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
    
    public class CustomRenderer extends DefaultTableCellRenderer  
    { 
        public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
    { 
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        Color rosso=new Color(255,0,0);
        if (table.isCellSelected(row, column)) {
            setBackground(rosso);
        }
        return c; 
    } 

    } 