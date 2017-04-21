package Map;

import units.Enemy;

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

    int maxHp;

    public PanelUnit(Enemy e) {
        this.name = new JLabel("Mon unit n°"  +e.getId());
        this.lifeBar = new JProgressBar(SwingConstants.HORIZONTAL);
        this.maxHp = e.getHp();
        this.lifeBar.setValue(100*e.getHp()/this.maxHp);
        lifeBar.setStringPainted(true);
        this.posX = new JLabel("x :" + e.getPosX());
        this.posY = new JLabel("y :" + e.getPosY());
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

    public void update(Enemy e) {
        this.lifeBar.setValue(100*e.getHp()/this.maxHp);
        this.posX.setText("x : " + e.getPosX());
        this.posY.setText("y : " + e.getPosY());
        this.revalidate();
    }
}
