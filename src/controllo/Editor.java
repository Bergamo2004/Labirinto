package controllo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.*;    
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;  
public class Editor {
      public static void main(String[] a) {  
            JFrame f = new JFrame("prova tabella");          
                              
                            
                            
        	ImageIcon icon = new ImageIcon("C:\\Users\\jackv\\Documents\\GitHub\\Labirinto\\res\\tiles\\Tree.png");

            Object[][] data = new Object [50][50];
            DefaultTableModel model = new DefaultTableModel(data,50);
            JTable jt = new JTable(model) {
              public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
              }
            };
            
            jt.setCellSelectionEnabled(true);  
            ListSelectionModel select= jt.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
            select.addListSelectionListener(new ListSelectionListener() {  
              public void valueChanged(ListSelectionEvent e) {  

                int riga=jt.getSelectedRow();
                int colonna=jt.getSelectedColumn();          
                for (int i = 0; i <= 50; i++) {  
                  for (int j = 0; j <= 50; j++) {  
                	  data[riga][colonna] = icon;
                   jt.setCellSelectionEnabled(true);

                   refreshTabella(data);

              
                  } }  
                System.out.println("L'elemento selezionato: ");    
               // changeTable(jt,columns.length);
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

	
	
public static void refreshTabella(Object[][] data)
{
    DefaultTableModel model = new DefaultTableModel(data,50);
    JTable jt = new JTable(model) {
      public Class getColumnClass(int column) {
        return (column == 0) ? Icon.class : Object.class;
      }
    };
}

}
	
  /*  public static void changeTable(JTable table, int column_index) {
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
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
        
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
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
public class Main extends JFrame 
{
  public Main() 
  {
    //Headers for JTable 
    String[] columns = {"Id", "Name", "Address", "Image"};
    //data for JTable in a 2D table
    Object[][] data = {
      {1, "Thomas", "Alaska", new ImageIcon("user1.png") },
      {2, "Jean", "Arizona", new ImageIcon("user2.png") },
      {3, "Yohan", "California", new ImageIcon("user3.png") },
      {4, "Emily", "Florida", new ImageIcon("user4.png") }
    };
    DefaultTableModel model = new DefaultTableModel(data, columns);
      
    JTable table = new JTable(model) {
      public Class getColumnClass(int column) {
        return (column == 3) ? Icon.class : Object.class;
      }
    };
    
    //Set row height to 60 pixels
    table.setRowHeight(60);
    JScrollPane scrollPane = new JScrollPane(table);
    getContentPane().add(scrollPane);
      
    JLabel labelHead = new JLabel("List of employees");
    labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
    getContentPane().add(labelHead,BorderLayout.PAGE_START);
  }
  public static void main(String[] args) 
  {
    Main frame = new Main();  
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setSize(500, 330);
    frame.setVisible(true);
  }
}
}*/