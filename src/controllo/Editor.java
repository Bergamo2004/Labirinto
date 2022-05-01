package controllo;

import javax.swing.*;    
import javax.swing.event.*;  
public class Editor {    
      public static void main(String[] a) {  
            JFrame f = new JFrame("prova tabella");          
                            final JTable jt=new JTable(50,50);    
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
                  } }  
                System.out.println("L'elemento selezionato: " + Data);    
              }       
            });  
            JScrollPane sp=new JScrollPane(jt);    
            f.add(sp);  
            f.setSize(300, 200);  
            f.setVisible(true);  
          }  
        }

