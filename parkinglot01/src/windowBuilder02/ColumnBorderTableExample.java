// 참조
// http://www.java2s.com/Code/Java/Swing-Components/ColumnBorderTableExample.html

package windowBuilder02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ColumnBorderTableExample extends JFrame {
	public ColumnBorderTableExample() {
		super("Column Border Example");

		DefaultTableModel dm = new DefaultTableModel(4, 10);
		JTable table = new JTable(dm);
		table.setIntercellSpacing(new Dimension(0, 0));

		Color color = table.getGridColor();
		BorderCellRenderer[] renderers = new BorderCellRenderer[6];
		renderers[0] = createRenderer(color, new Insets(0, 0, 0, 1));
		renderers[1] = createRenderer(color, new Insets(0, 1, 0, 1));
		renderers[2] = createRenderer(color, new Insets(0, 1, 0, 2));
		renderers[3] = createRenderer(color, new Insets(0, 2, 0, 2));
		renderers[4] = createRenderer(color, new Insets(0, 2, 0, 0));
		renderers[5] = createRenderer(Color.red, new Insets(0, 1, 1, 1));

		TableColumnModel model = table.getColumnModel();
		model.getColumn(1).setCellRenderer(renderers[0]);
		model.getColumn(2).setCellRenderer(renderers[0]);
		model.getColumn(3).setCellRenderer(renderers[0]);
		model.getColumn(4).setCellRenderer(renderers[1]);
		model.getColumn(5).setCellRenderer(renderers[2]);
		model.getColumn(6).setCellRenderer(renderers[3]);
		model.getColumn(7).setCellRenderer(renderers[4]);
		model.getColumn(8).setCellRenderer(renderers[5]);

		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		ColumnBorderTableExample frame = new ColumnBorderTableExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(300, 120);
		frame.setVisible(true);
	}

	private static BorderCellRenderer createRenderer(Color color, Insets insets) {
		BorderCellRenderer renderer = new BorderCellRenderer();
		renderer.setColumnBorder(new LinesBorder(color, insets));
		return renderer;
	}
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
