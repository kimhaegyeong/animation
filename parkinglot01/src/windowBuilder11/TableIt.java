// 출처
// http://www.java2s.com/Code/Java/Swing-JFC/JTableselectionevents.html

package windowBuilder11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TableIt {
	 class MyTableCellRenderer extends JLabel implements TableCellRenderer {
		    final Border blueBorder = BorderFactory.createLineBorder(Color.BLUE);

		    MyTableCellRenderer() {
		      setOpaque(true);
		    }

		    public Component getTableCellRendererComponent(JTable table,
		        Object value, boolean isSelected, boolean hasFocus, int row,
		        int col) {
		      setBackground((Color) value);
		      if (isSelected) {
		        setBorder(blueBorder);
		      } else {
		        setBorder(BorderFactory.createEmptyBorder());
		      }
		      return this;
		    }
		  }

		  class MyTableModel extends DefaultTableModel {
		    Object data[][] = { { "1", Color.RED }, { "2", Color.ORANGE },
		        { "3", Color.YELLOW }, { "4", Color.GREEN },
		        { "5", Color.BLUE }, { "6", Color.MAGENTA },
		        { "7", Color.CYAN }, { "8", Color.PINK },
		        { "9", Color.BLACK }, { "10", Color.GRAY } };

		    MyTableModel() {
		      setColumnIdentifiers(new String[] { "ID", "Name", "Color" });
		      for (int i = 0, n = data.length; i < n; i++)
		        addRow(new Object[] { new Integer(i + 1), data[i][0],
		            data[i][1] });
		    }

		    public boolean isCellEditable(int row, int column) {
		      return (column != 0);
		    }
		  }

		  public TableIt() {
		    JFrame f = new JFrame();
		    TableModel tm = new MyTableModel();
		    final JTable table = new JTable(tm);

		    TableColumnModel tcm = table.getColumnModel();
		    TableColumn column = tcm.getColumn(tcm.getColumnCount() - 1);
		    TableCellRenderer renderer = new MyTableCellRenderer();
		    column.setCellRenderer(renderer);

		    JButton selectionType = new JButton("Next Type");
		    selectionType.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        boolean rowSet = table.getRowSelectionAllowed();
		        boolean colSet = table.getColumnSelectionAllowed();
		        boolean cellSet = table.getCellSelectionEnabled();

		        boolean setRow = !rowSet;
		        boolean setCol = rowSet ^ colSet;
		        boolean setCell = rowSet & colSet;

		        table.setCellSelectionEnabled(setCell);
		        table.setColumnSelectionAllowed(setCol);
		        table.setRowSelectionAllowed(setRow);
		        System.out.println("Row Selection Allowed? " + setRow);
		        System.out.println("Column Selection Allowed? " + setCol);
		        System.out.println("Cell Selection Enabled? " + setCell);
		        table.repaint();
		      }
		    });
		    JButton selectionMode = new JButton("Next Mode");
		    selectionMode.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        ListSelectionModel lsm = table.getSelectionModel();
		        int mode = lsm.getSelectionMode();
		        int nextMode;
		        String nextModeString;
		        if (mode == ListSelectionModel.SINGLE_SELECTION) {
		          nextMode = ListSelectionModel.SINGLE_INTERVAL_SELECTION;
		          nextModeString = "Single Interval Selection";
		        } else if (mode == ListSelectionModel.SINGLE_INTERVAL_SELECTION) {
		          nextMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
		          nextModeString = "Multiple Interval Selection";
		        } else {
		          nextMode = ListSelectionModel.SINGLE_SELECTION;
		          nextModeString = "Single Selection";
		        }
		        lsm.setSelectionMode(nextMode);
		        System.out.println("Selection Mode: " + nextModeString);
		        table.repaint();
		      }
		    });
		    JPanel jp = new JPanel();
		    jp.add(selectionType);
		    jp.add(selectionMode);
		    JScrollPane jsp = new JScrollPane(table);
		    Container c = f.getContentPane();
		    c.add(jsp, BorderLayout.CENTER);
		    c.add(jp, BorderLayout.SOUTH);
		    f.setSize(300, 250);
		    f.show();
		  }

		  public static void main(String args[]) {
		    new TableIt();
		  }
}
