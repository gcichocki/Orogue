package Map;

import adapter.Controller;
import units.Enemy;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
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

    public void addTile(String t, int i, int y) {
        defaultTableModel.setValueAt( t, i, y);
    }

    public MapGui(int rows, int cols) {
        super("Map.Map ORogue IA");
        this.rows = rows;
        this.cols = cols;
        defaultTableModel = new DefaultTableModel(rows, cols) {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };

        idtoPanelUnit = new HashMap<Integer, PanelUnit>();
    }

    public void setFocus(int x, int y) {

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

    public void setTile(String t, String color, int i, int y) {
        String html = "<html><font color=\"" + color + "\">" + t + "</font></html>";
        table.setValueAt(html, i, y);
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

}
