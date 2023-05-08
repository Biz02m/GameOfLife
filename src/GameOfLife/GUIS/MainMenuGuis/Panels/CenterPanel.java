package GameOfLife.GUIS.MainMenuGuis.Panels;

import GameOfLife.GUIS.MainMenuGuis.MainMenu;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    JLabel centerLabel = new JLabel();
    public CenterPanel(World world, MainMenu mainMenu){
        ButtonPanel buttonPanel = new ButtonPanel(world,mainMenu);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setIcon(new ImageIcon("menupicture.png"));
        this.add(centerLabel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(100,100));
    }
}
