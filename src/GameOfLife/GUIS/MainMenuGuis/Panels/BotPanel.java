package GameOfLife.GUIS.MainMenuGuis.Panels;

import javax.swing.*;
import java.awt.*;

public class BotPanel extends JPanel {
    JLabel botLabel = new JLabel();
    public BotPanel(){
        this.setBackground(Color.WHITE);
        botLabel.setIcon(new ImageIcon("bottomdecor.png"));
        this.add(botLabel);
        this.setPreferredSize(new Dimension(100,100));
    }
}
