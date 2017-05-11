package gui;

import adapter.Controller;
import gui.PanelUnit;
import map.Tile;
import units.Enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapGui extends JFrame {

    int width;

    int height;

    JTable table;
    BorderLayout root_layout;
    JPanel hud_panel;
    HashMap<Integer, PanelUnit> idtoPanelUnit;

    DefaultTableModel defaultTableModel;

    JScrollPane scrollPane;

    int selectedX = -1;
    int selectedY = -1;

    boolean focus = false;

    public void addTile(String t, int x, int y) {
        defaultTableModel.setValueAt(t, x, y);
    }

    public MapGui(int height, int width) {
        super("map.map ORogue IA");
        this.width = width;
        this.height = height;

        defaultTableModel = new DefaultTableModel(height, width) {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };

        idtoPanelUnit = new HashMap<Integer, PanelUnit>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                addTile(".", i, j);
            }
        }

        initComponents();
    }

    public static void scrollToCenter(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
        Rectangle viewRect = viewport.getViewRect();
        rect.setLocation(rect.x - viewRect.x, rect.y - viewRect.y);

        int centerX = (viewRect.width - rect.width) / 2;
        int centerY = (viewRect.height - rect.height) / 2;
        if (rect.x < centerX) {
            centerX = -centerX;
        }
        if (rect.y < centerY) {
            centerY = -centerY;
        }
        rect.translate(centerX, centerY);
        viewport.scrollRectToVisible(rect);
    }

    public void setFocus(int y, int x) {

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

            //scrollPane.getViewport().setViewPosition(new Point(selectedY, selectedX));
            //scrollToCenter(table, x, y);
            //scrollPane.getViewport().setViewPosition(new Point(x, y));
        }
        /*Rectangle bounds = scrollPane.getViewport().getViewRect();
        Dimension size = scrollPane.getViewport().getViewSize();
        int x_view = (size.width - bounds.width) / 2;
        int y_view = (size.height - bounds.height) / 2;*/

    }

    public void notify(Tile tile) {
        if (tile.isHide()) {
            // we update the corresponding tile
            this.table.setValueAt("<html><font color=\"" + "gray" + "\" >" + tile.getAscii() + "</font></html>",
                    tile.getPosY(), tile.getPosX());
            defaultTableModel.fireTableCellUpdated(tile.getPosY(), tile.getPosX());
        } else {
            // we update the corresponding tile
            this.table.setValueAt("<html><font color=\"" + tile.getColor() + "\" >" + tile.getAscii() + "</font></html>",
                    tile.getPosY(), tile.getPosX());
            defaultTableModel.fireTableCellUpdated(tile.getPosY(), tile.getPosX());
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
        try {
            setTitle("OROGUE -- IA");

            BackgroundPane backgroundPane = new BackgroundPane();
            backgroundPane.setBackground(ImageIO.read(this.getClass().getResourceAsStream("/Bg_orogue.png")));
            this.setContentPane(backgroundPane);

            //vue de la map
            table = new JTable(defaultTableModel);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setFocusable(false);
            table.setCellSelectionEnabled(false);
            table.setShowGrid(false);
            table.setRowMargin(0);
            table.setIntercellSpacing(new Dimension(5, 5));
            table.setTableHeader(null);
            table.setPreferredScrollableViewportSize(new Dimension(1180, 560));
            table.setRowHeight(20);
            table.setBackground(Color.black);

            TableColumnModel columnModel = table.getColumnModel();

            for (int i = 0; i < width; i++) {
                columnModel.getColumn(i).setPreferredWidth(20);
                columnModel.getColumn(i).setCellRenderer(new CustomRenderer());
            }

            scrollPane = new JScrollPane(table);
            scrollPane.setBackground(Color.black);
            scrollPane.getViewport().setBackground(Color.black);
            add(scrollPane, BorderLayout.NORTH);

            // jpanel for the bottom
            JPanel panel_bot = new JPanel();
            panel_bot.setOpaque(false);
            panel_bot.setBorder(new EmptyBorder(20,20,20,20));

            //info contrôles
            JTextPane zone_info = new JTextPane();
            zone_info.setContentType("text/html");
            zone_info.setEditable(false);
            zone_info.setPreferredSize(new Dimension(400, 350));
            zone_info.setOpaque(false);
            zone_info.addKeyListener(new MyKeyListener());

            String content_info = "<html>" +
                    "<font color=yellow>Touche de déplacement : <br></font>" +
                    "<font color=white>Z (haut)<br> Q(gauche)<br> S(bas)<br> D(droite)<br></font>" +
                    "<font color=yellow >Touche de d'attaque : <br></font>" +
                    "<font color=white>U or 8(haut)<br> H or 4(gauche)<br> M or 2(bas)<br> K or 6(droite)<br></font>" +
                    "</html>";

            zone_info.setText(content_info);

            //info joueurs
            hud_panel = new JPanel(new GridLayout(10, 1));
            hud_panel.setPreferredSize(new Dimension(420, 350));
            hud_panel.setOpaque(false);

            UIManager.put("ProgressBar.background", Color.white);
            UIManager.put("ProgressBar.foreground", Color.GREEN);

            JScrollPane unitList = new JScrollPane(hud_panel);
            unitList.setOpaque(false);
            unitList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            unitList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

            JPanel empty_panel = new JPanel();
            empty_panel.setPreferredSize(new Dimension(580, 300));
            empty_panel.setOpaque(false);

            panel_bot.add(empty_panel, BorderLayout.WEST);
            panel_bot.add(unitList, BorderLayout.CENTER);
            panel_bot.add(zone_info, BorderLayout.EAST);

            add(panel_bot, BorderLayout.SOUTH);

            // fenetre globale
            setResizable(true);
            setPreferredSize(new Dimension(1300, 1000));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setResizable(false);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
