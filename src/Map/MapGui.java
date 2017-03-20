package Map;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapGui extends JFrame {

    int rows;

    int cols;

    JTable table;

    DefaultTableModel defaultTableModel;

    public void addTile(String t, int i, int y) {
        defaultTableModel.setValueAt( t, i, y);
    }

    public MapGui(int rows, int cols) {
        super("Map.Map ORogue IA");
        this.rows = rows;
        this.cols = cols;

        defaultTableModel = new DefaultTableModel(rows, cols);
    }



    public void initComponents() {
        table = new JTable(defaultTableModel);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFocusable(false);
        table.setCellSelectionEnabled(false);
        table.setShowGrid(false);
        table.setRowMargin(0);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setBackground(Color.black);
        table.setTableHeader(null);
        table.setPreferredScrollableViewportSize(new Dimension(1200, 900));
        table.setRowHeight(20);

        TableColumnModel columnModel = table.getColumnModel();

        for (int i =0; i < cols; i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setResizable(true);
        setPreferredSize(new Dimension(1200,900));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setVisible(true);
    }

    public void setTile(String t, String color, int i, int y) {
        String html = "<html><font color=\"" + color + "\">" + t + "</font></html>";
        table.setValueAt(html, i, y);
        ((AbstractTableModel) table.getModel()).fireTableCellUpdated(i, y);
    }

}
