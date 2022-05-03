package controllo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;

import javax.swing.*;    
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;  
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
            f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
            JScrollPane sp=new JScrollPane(jt);    
            f.getContentPane().add(sp);
            
            JPanel panel = new JPanel();
            f.getContentPane().add(panel);
            panel.setLayout(null);
            
            JButton btnNewButton = new JButton("New button");
            btnNewButton.setBounds(59, 60, 89, 23);
            panel.add(btnNewButton);
            
            JButton btnNewButton_1 = new JButton("New button");
            btnNewButton_1.setBounds(59, 147, 89, 23);
            panel.add(btnNewButton_1);
            
            JButton btnNewButton_2 = new JButton("New button");
            btnNewButton_2.setBounds(59, 226, 89, 23);
            panel.add(btnNewButton_2);
            
            JButton btnNewButton_3 = new JButton("New button");
            btnNewButton_3.setBounds(59, 308, 89, 23);
            panel.add(btnNewButton_3);
            
            JButton btnNewButton_4 = new JButton("New button");
            btnNewButton_4.setBounds(59, 394, 89, 23);
            panel.add(btnNewButton_4);
            
            JButton btnNewButton_5 = new JButton("New button");
            btnNewButton_5.setBounds(59, 502, 89, 23);
            panel.add(btnNewButton_5);
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




/*
public void loading() {
try {
    String[]title = {"First Name","Last Name","Picture"};
    String sql="select * from users";
    model = new DefaultTableModel(null,title){
        @Override
        public Class<?> getColumnClass(int column) {
            if (column==2) return ImageIcon.class;
            return Object.class;
        }
    }
    st = conn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    Object[]fila = new Object[4];
    while(rs.next()){
        fila[0] = rs.getString("fna");
        fila[1] = rs.getString("lna");
        fila[2] = new ImageIcon(rs.getBytes("pic"));            
        model.addRow(fila);
    }
    tbl.setModel(model);
}
catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.getMessage());
}
}*/