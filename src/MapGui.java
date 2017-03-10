import javax.swing.*;
import java.awt.*;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapGui extends JFrame {

    // memory of component pos
    JPanel[][] panelHolder;
    private final Map map;

    public MapGui(Map map) {
        super("Map ORogue IA");
        this.map = map;

        this.panelHolder = new JPanel[map.getRows()][map.getCols()];

        initComponents();
    }

    private void initComponents() {

        GridLayout gridLayout = new GridLayout(map.getRows(), map.getCols(), 0, 0);

        for (int i = 0; i < map.getRows(); i++) {
            for (int j = 0; j < map.getCols(); j++) {


                panelHolder[i][j] = new JPanel();
                panelHolder[i][j].setBackground(Color.black);
                panelHolder[i][j].setPreferredSize(new Dimension(22, 22));
                panelHolder[i][j].add(map.getTile(i, j));
                add(panelHolder[i][j]);
            }
        }

        setLayout(gridLayout);

        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setVisible(true);
    }


}
