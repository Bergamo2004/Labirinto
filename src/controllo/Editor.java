package controllo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;

import javax.swing.*;    
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;  
public class Editor {    
      public static void main(String[] a) {  
            JFrame f = new JFrame("prova tabella");          
                            final  JTable jt=new JTable(50,50);    
            jt.setCellSelectionEnabled(true);  
            ListSelectionModel select= jt.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
            select.addListSelectionListener(new ListSelectionListener() {  
              public void valueChanged(ListSelectionEvent e) {  
                String Data = null;  
                int[] row = jt.getSelectedRows();  
                int[] columns = jt.getSelectedColumns();  
                for (int i = 0; i < row.length; i++) {  
                  for (int j = 0; j < columns.length; j++) {  
                   Data = (String) jt.getValueAt(row[i], columns[j]);  
                   String[][] data = getTableData();
                   String[] cols = getTableCols();
                   jt.setCellSelectionEnabled(true);
                 
                  } }  
                System.out.println("L'elemento selezionato: " + Data);    
                changeTable(jt,columns.length);
              }       
            });  
            JScrollPane sp=new JScrollPane(jt);    
            f.add(sp);  
            f.setSize(900, 865);  
            f.setVisible(true);  
          }

	protected static String[] getTableCols() {
		// TODO Auto-generated method stub
		return null;
	}

	protected static String[][] getTableData() {
		// TODO Auto-generated method stub
		return null;
	}  
	
	
	
	
	
    public static void changeTable(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setBackground(Color.GREEN);             
                return c;
            }
        });
    }
}

class MyTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Color getBackground() {
        return super.getBackground();
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
        
//Color rosso=new Color(255,0,0);
//jt.setBackground(rosso);


/* public void changeTable(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int st_val = Integer.parseInt(table.getValueAt(row, 2).toString());
                int req_val = 2000;
                if (st_val < req_val) {
                    c.setBackground(Color.MAGENTA);
                } else {
                    c.setBackground(Color.GREEN);
                }
                return c;
            }
        });
    }
    */
