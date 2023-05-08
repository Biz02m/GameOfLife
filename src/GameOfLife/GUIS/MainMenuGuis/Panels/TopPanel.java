package GameOfLife.GUIS.MainMenuGuis.Panels;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    JLabel topLabel = new JLabel();
    public TopPanel(){
        this.setBackground(Color.WHITE);
        topLabel.setIcon(new ImageIcon("upperdecor.png"));
        this.add(topLabel);
        this.setPreferredSize(new Dimension(100,100));
    }
}
