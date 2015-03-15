// 참조
// http://www.java2s.com/Code/Java/Swing-Components/CellBorderTableExample.htm

package windowBuilder04;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class CellBorderTableExample05 extends JFrame {

	public CellBorderTableExample05() {
		super("Cell Border Example");

		final Color color = UIManager.getColor("Table.gridColor");

		DefaultTableModel dm = new DefaultTableModel(12, 6) {
			public void setValueAt(Object obj, int row, int col) {
				if (obj instanceof MyData) {
					super.setValueAt(obj, row, col);
				} else {
					MyData myData = null;
					Object oldObject = getValueAt(row, col);
					if (oldObject == null) {
						myData = new MyData(obj, new LinesBorder(color, 0));
					} else if (oldObject instanceof MyData) {
						myData = (MyData) oldObject;
					} else {
						System.out.println("error");
						return;
					}
					myData.setObject(obj);
					super.setValueAt(myData, row, col);
				}
			}
		};

		JTable table = new JTable(dm);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setDefaultRenderer(Object.class, new BorderCellRenderer());

		JScrollPane scroll = new JScrollPane(table);
		ThicknessPanel thicknessPanel = new ThicknessPanel();
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(thicknessPanel);
		box.add(new ButtonPanel(table, thicknessPanel));
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(box, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		CellBorderTableExample05 frame = new CellBorderTableExample05();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(400, 240);
		frame.setVisible(true);
	}

	class ThicknessPanel extends JPanel {
		JComboBox[] combos;

		// 두께 패널
		ThicknessPanel() {
			String[] str = { "top", "left", "bottom", "right" };
			int n = str.length;
			setLayout(new GridLayout(n, 2));
			setBorder(new TitledBorder("Thickness"));
			combos = new JComboBox[n];
			for (int i = 0; i < n; i++) {
				combos[i] = new JComboBox(new Object[] { "0", "1", "2", "3" });
				add(new JLabel(str[i]));
				add(combos[i]);
			}
		}

		// 두께 지정
		public Insets getThickness() {
			Insets insets = new Insets(0, 0, 0, 0);
			insets.top = combos[0].getSelectedIndex();
			insets.left = combos[1].getSelectedIndex();
			insets.bottom = combos[2].getSelectedIndex();
			insets.right = combos[3].getSelectedIndex();
			return insets;
		}
	}

	class ButtonPanel extends JPanel {
		JTable table;

		ThicknessPanel thicknessPanel;

		Color color = UIManager.getColor("Table.gridColor");

		ButtonPanel(JTable table, ThicknessPanel thicknessPanel) {
			this.table = table;
			this.thicknessPanel = thicknessPanel;
			setLayout(new GridLayout(3, 1));
			setBorder(new TitledBorder("Append Lines"));
			final JCheckBox oneBlock = new JCheckBox("Block");
			JButton b_and = new JButton("REPLACE");
			JButton b_or = new JButton("OR");
			add(oneBlock);
			add(b_and);
			add(b_or);
			b_and.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setCellBorder(true, oneBlock.isSelected(), 3, 3);

				}
			});
			b_or.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setCellBorder(false, oneBlock.isSelected(), 3, 3);
				}
			});
		}

		/******************* STart 필요한 부분 ****************/
		// 특정 위치에 밑줄 가능
		private void setCellBorder(boolean isReplace, boolean isBlock, int row, int column) {
			boolean isTop, isLeft, isBottom, isRight;

			Insets insets = thicknessPanel.getThickness();			

			MyData myData = (MyData) table.getValueAt(row, column);
			if (myData == null) {
				myData = new MyData("", new LinesBorder(color, 0));
			}
			LinesBorder border = (LinesBorder) myData.getBorder();

			if (isBlock) {
				Insets tmp = new Insets(0, 0, 0, 0);
				// if (isTop)
				tmp.top = Math.max(tmp.top, insets.top);
				// if (isLeft)
				tmp.left = Math.max(tmp.left, insets.left);
				// if (isBottom)
				tmp.bottom = Math.max(tmp.bottom, insets.bottom);
				// if (isRight)
				tmp.right = Math.max(tmp.right, insets.right);
				border.append(tmp, isReplace);
			} else {
				border.append(insets, isReplace);
			}

			table.setValueAt(myData, row, column);

			table.clearSelection();
			table.revalidate();
			table.repaint();
		}
	}

	class MyData implements CellBorder {
		private Border border;

		private Object obj;

		public MyData(Object obj, Border border) {
			this.obj = obj;
			this.border = border;
		}

		public void setObject(Object obj) {
			this.obj = obj;
		}

		public String toString() {
			return obj.toString();
		}

		// CellBorder
		public void setBorder(Border border) {
			this.border = border;
		}

		public Border getBorder() {
			return border;
		}

		public void setBorder(Border border, int row, int col) {
		}

		public Border getBorder(int row, int col) {
			return null;
		}
	}

	/******************* END 필요한 부분 ****************/
}

class BorderCellRenderer extends JLabel implements TableCellRenderer {
	protected Border noFocusBorder;

	protected Border columnBorder;

	public BorderCellRenderer() {
		noFocusBorder = new EmptyBorder(1, 2, 1, 2);
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		setFont(table.getFont());

		if (hasFocus) {
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			if (table.isCellEditable(row, column)) {
				setForeground(UIManager.getColor("Table.focusCellForeground"));
				setBackground(UIManager.getColor("Table.focusCellBackground"));
			}
		} else {
			if (value instanceof CellBorder) {
				Border border = ((CellBorder) value).getBorder();
				setBorder(border);
			} else {
				if (columnBorder != null) {
					setBorder(columnBorder);
				} else {
					setBorder(noFocusBorder);
				}
			}
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}

	public void setColumnBorder(Border border) {
		columnBorder = border;
	}

	public Border getColumnBorder() {
		return columnBorder;
	}

}

interface CellBorder {

	public Border getBorder();

	public Border getBorder(int row, int column);

	public void setBorder(Border border);

	public void setBorder(Border border, int row, int column);

}

class LinesBorder extends AbstractBorder implements SwingConstants {
	protected int northThickness;

	protected int southThickness;

	protected int eastThickness;

	protected int westThickness;

	protected Color northColor;

	protected Color southColor;

	protected Color eastColor;

	protected Color westColor;

	public LinesBorder(Color color) {
		this(color, 1);
	}

	public LinesBorder(Color color, int thickness) {
		setColor(color);
		setThickness(thickness);
	}

	public LinesBorder(Color color, Insets insets) {
		setColor(color);
		setThickness(insets);
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		Color oldColor = g.getColor();

		g.setColor(northColor);
		for (int i = 0; i < northThickness; i++) {
			g.drawLine(x, y + i, x + width - 1, y + i);
		}
		g.setColor(southColor);
		for (int i = 0; i < southThickness; i++) {
			g.drawLine(x, y + height - i - 1, x + width - 1, y + height - i - 1);
		}
		g.setColor(eastColor);
		for (int i = 0; i < westThickness; i++) {
			g.drawLine(x + i, y, x + i, y + height - 1);
		}
		g.setColor(westColor);
		for (int i = 0; i < eastThickness; i++) {
			g.drawLine(x + width - i - 1, y, x + width - i - 1, y + height - 1);
		}

		g.setColor(oldColor);
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(northThickness, westThickness, southThickness,
				eastThickness);
	}

	public Insets getBorderInsets(Component c, Insets insets) {
		return new Insets(northThickness, westThickness, southThickness,
				eastThickness);
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void setColor(Color c) {
		northColor = c;
		southColor = c;
		eastColor = c;
		westColor = c;
	}

	public void setColor(Color c, int direction) {
		switch (direction) {
		case NORTH:
			northColor = c;
			break;
		case SOUTH:
			southColor = c;
			break;
		case EAST:
			eastColor = c;
			break;
		case WEST:
			westColor = c;
			break;
		default:
		}
	}

	public void setThickness(int n) {
		northThickness = n;
		southThickness = n;
		eastThickness = n;
		westThickness = n;
	}

	public void setThickness(Insets insets) {
		northThickness = insets.top;
		southThickness = insets.bottom;
		eastThickness = insets.right;
		westThickness = insets.left;
	}

	public void setThickness(int n, int direction) {
		switch (direction) {
		case NORTH:
			northThickness = n;
			break;
		case SOUTH:
			southThickness = n;
			break;
		case EAST:
			eastThickness = n;
			break;
		case WEST:
			westThickness = n;
			break;
		default:
		}
	}

	public void append(LinesBorder b, boolean isReplace) {
		if (isReplace) {
			northThickness = b.northThickness;
			southThickness = b.southThickness;
			eastThickness = b.eastThickness;
			westThickness = b.westThickness;
		} else {
			northThickness = Math.max(northThickness, b.northThickness);
			southThickness = Math.max(southThickness, b.southThickness);
			eastThickness = Math.max(eastThickness, b.eastThickness);
			westThickness = Math.max(westThickness, b.westThickness);
		}
	}

	public void append(Insets insets, boolean isReplace) {
		if (isReplace) {
			northThickness = insets.top;
			southThickness = insets.bottom;
			eastThickness = insets.right;
			westThickness = insets.left;
		} else {
			northThickness = Math.max(northThickness, insets.top);
			southThickness = Math.max(southThickness, insets.bottom);
			eastThickness = Math.max(eastThickness, insets.right);
			westThickness = Math.max(westThickness, insets.left);
		}
	}

}