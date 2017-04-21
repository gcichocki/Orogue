package Map;

import adapter.Controller;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapGui extends JFrame {

    int rows;

    int cols;

    JTable table;
    BorderLayout root_layout;
    JProgressBar life_bar;

    DefaultTableModel defaultTableModel;

    public void addTile(String t, int i, int y) {
        defaultTableModel.setValueAt( t, i, y);
    }

    public MapGui(int rows, int cols) {
        super("Map.Map ORogue IA");
        this.rows = rows;
        this.cols = cols;
        defaultTableModel = new DefaultTableModel(rows, cols);
        addKeyListener(new MyKeyListener());
    }

    public class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            //System.out.println("keyTyped=" +KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Controller.getInstance().getAdapter().sendAction(KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        }
    }




    public void initComponents() {
        setTitle("OROGUE -- IA");
        root_layout = new BorderLayout();
        setLayout(root_layout);

        //vue de la map
        JPanel map_panel = new JPanel();
        map_panel.setPreferredSize(new Dimension(600, 600));
        map_panel.setBackground(Color.black);
        table = new JTable(defaultTableModel);
        table.setBackground(Color.black);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFocusable(false);
        table.setCellSelectionEnabled(false);
        table.setShowGrid(false);
        table.setRowMargin(0);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setBackground(Color.black);
        table.setTableHeader(null);
        table.setPreferredScrollableViewportSize(new Dimension(700, 400));
        table.setRowHeight(20);

        TableColumnModel columnModel = table.getColumnModel();

        for (int i =0; i < cols; i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.black);
        map_panel.add(scrollPane);
        add(map_panel, BorderLayout.NORTH);

        //info contrôles
        JPanel control_panel = new JPanel(new BorderLayout());
        control_panel.setPreferredSize(new Dimension(300, 200));

        JLabel up_text = new JLabel("Flèche haut / Z");
        JLabel down_text = new JLabel("Flèche bas / S");
        JLabel left_text = new JLabel("Flèche gauche / Q");
        JLabel right_text = new JLabel("Flèche droite / D");

        control_panel.add(up_text, BorderLayout.NORTH);
        control_panel.add(down_text, BorderLayout.SOUTH);
        control_panel.add(left_text, BorderLayout.WEST);
        control_panel.add(right_text, BorderLayout.EAST);

        add(control_panel, BorderLayout.EAST);

        //info joueurs
        JPanel hud_panel = new JPanel(new BorderLayout());
        hud_panel.setPreferredSize(new Dimension(500, 500));
        life_bar = new JProgressBar(JProgressBar.HORIZONTAL);
        life_bar.setValue(80);
        JLabel life_label = new JLabel("Vie : ");
        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(200,200));

        hud_panel.add(life_label, BorderLayout.NORTH);
        hud_panel.add(life_bar, BorderLayout.WEST);
        hud_panel.add(padding, BorderLayout.SOUTH);

        add(hud_panel, BorderLayout.WEST);


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
