package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by FatePc on 3/23/2017.
 */
public class PanelUnit extends JPanel{
    JLabel name;
    JProgressBar lifeBar;
    JLabel posX;
    JLabel posY;

    int hp;

    public PanelUnit(String name, int hp, int posX, int posY) {
        this.name = new JLabel(name);
        this.hp = hp;
        this.lifeBar = new JProgressBar(SwingConstants.HORIZONTAL);
        this.lifeBar.setValue(hp);
        lifeBar.setStringPainted(true);
        this.posX = new JLabel("x :" + posX);
        this.posY = new JLabel("y :" + posY);
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(700, 50));
        this.setLayout(new GridLayout(1, 4, 5, 5));
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        this.setBackground(Color.WHITE);
        this.add(name);
        this.add(posX);
        this.add(posY);
        this.add(lifeBar);
    }
}
