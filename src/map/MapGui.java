package map;

import adapter.Controller;
import units.Enemy;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapGui extends JFrame {

    int rows;

    int cols;

    JTable table;
    BorderLayout root_layout;
    JPanel hud_panel;
    HashMap<Integer, PanelUnit> idtoPanelUnit;

    DefaultTableModel defaultTableModel;

    int selectedX = -1;
    int selectedY = -1;

    boolean focus = false;

    public void addTile(String t, int i, int y) {
        defaultTableModel.setValueAt(t, i, y);
    }

    public MapGui(int cols, int rows) {
        super("map.map ORogue IA");
        this.rows = rows;
        this.cols = cols;
        defaultTableModel = new DefaultTableModel(rows, cols) {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };

        idtoPanelUnit = new HashMap<Integer, PanelUnit>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                addTile(".", i, j);
            }
        }

        initComponents();
    }

    public void setFocus(int x, int y) {
        if (x == -1 && y == -1) {
            focus = false;
            defaultTableModel.fireTableCellUpdated(selectedX, selectedY);
        } else {
            System.out.println("Cell to play with x "+ x + " y " + y);
            if (focus) {
                int oldX = selectedX;
                int oldY = selectedY;
                defaultTableModel.fireTableCellUpdated(oldX, oldY);
            }
            focus = true;
            selectedX = x;
            selectedY = y;
            defaultTableModel.fireTableCellUpdated(x, y);

        }
    }

    public void notify(Tile tile) {
        if (tile.isHide()) {
            // we update the corresponding tile
            this.table.setValueAt("<html><font color=\"" + "gray" + "\" >" + tile.getAscii() + "</font></html>",
                    tile.getPosX(), tile.getPosY());
            defaultTableModel.fireTableCellUpdated(tile.getPosX(), tile.getPosY());
        } else {
            // we update the corresponding tile
            this.table.setValueAt("<html><font color=\"" + tile.getColor() + "\" >" + tile.getAscii() + "</font></html>",
                    tile.getPosX(), tile.getPosY());
            defaultTableModel.fireTableCellUpdated(tile.getPosX(), tile.getPosY());
        }

    }

    public class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            //System.out.println("keyTyped=" +KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key : "  + KeyEvent.getKeyText(e.getKeyCode()));
            Controller.getInstance().getAdapter().sendAction(KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        }
    }

    public void initComponents() {
        setTitle("OROGUE -- IA");

        //vue de la map
        table = new JTable(defaultTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFocusable(false);
        table.setCellSelectionEnabled(false);
        table.setShowGrid(false);
        table.setRowMargin(0);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setTableHeader(null);
        table.setPreferredScrollableViewportSize(new Dimension(1180, 600));
        table.setRowHeight(20);
        table.setBackground(Color.black);

        TableColumnModel columnModel = table.getColumnModel();

        for (int i =0; i < cols; i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
            columnModel.getColumn(i).setCellRenderer(new CustomRenderer());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.black);
        scrollPane.getViewport().setBackground(Color.black);
        add(scrollPane, BorderLayout.NORTH);

        //info contrôles
        JTextPane zone_info = new JTextPane();
        zone_info.setContentType("text/html");
        zone_info.setPreferredSize(new Dimension(350, 100));
        zone_info.addKeyListener(new MyKeyListener());

        String content_info = "<html>" +
                "<font>Touche de déplacement : <br></font>" +
                "<font color=blue>Z (haut)<br> Q(gauche)<br> S(bas)<br> D(droite)<br></font>" +
                "<font>Touche de d'attaque : <br></font>" +
                "<font color=green>U or 8(haut)<br> H or 4(gauche)<br> M or 2(bas)<br> K or 6(droite)<br></font>" +
                "</html>";

        zone_info.setText(content_info);
        add(zone_info, BorderLayout.EAST);

        //info joueurs
        hud_panel = new JPanel(new GridLayout(10, 1));
        UIManager.put("ProgressBar.background", Color.ORANGE);
        UIManager.put("ProgressBar.foreground", Color.BLUE);
        UIManager.put("ProgressBar.selectionBackground", Color.RED);
        UIManager.put("ProgressBar.selectionForeground", Color.GREEN);

        JScrollPane unitList = new JScrollPane(hud_panel);

        add(unitList, BorderLayout.WEST);

        // fenetre globale
        setResizable(true);
        setPreferredSize(new Dimension(1200,900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void setTile(String t, int i, int y) {
        //String html = "<html><font color=\"" + color + "\">" + t + "</font></html>";
        table.setValueAt(t, i, y);
        ((AbstractTableModel) table.getModel()).fireTableCellUpdated(i, y);
    }

    public void addUnit(Enemy e) {
        PanelUnit panelUnit = new PanelUnit(e);
        idtoPanelUnit.put(e.getId(), panelUnit);
        hud_panel.add(panelUnit);
        hud_panel.revalidate();
    }

    public void updateUnit(Enemy e) {
        idtoPanelUnit.get(e.getId()).update(e);
    }



    public class CustomRenderer extends DefaultTableCellRenderer
    {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(selectedX == row && selectedY == column && focus){
                cellComponent.setBackground(Color.lightGray);
            } else {
                cellComponent.setBackground(Color.black);
            }

            return cellComponent;
        }
    }

}
