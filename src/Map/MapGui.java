package Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapGui extends JFrame {

    // memory of component pos
    JPanel panel;

    public void addTile(Tile t) {
        JPanel tile = new JPanel();
        tile.setBackground(Color.black);
        tile.add(t);
        panel.add(tile);
    }

    public MapGui(int rows, int cols) {
        super("Map.Map ORogue IA");
        panel = new JPanel(new GridLayout(rows, cols, 0, 0));
    }



    public void initComponents() {

        panel.setBackground(Color.black);
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setResizable(false);
        setPreferredSize(new Dimension(1200,900));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setVisible(true);
    }


}
