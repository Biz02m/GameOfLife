package GameOfLife.GUIS.MainMenuGuis;

import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class SelectOrganism extends JFrame {
    private World world;
    public SelectOrganism(World world){
        this.world = world;
        ImageIcon logo = new ImageIcon("GOL.png");
        this.setSize(600,600);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100,50));
        topPanel.add(new JLabel("Choose an organism below: "));

        SelectionPanel selectionPanel = new SelectionPanel(world, this);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(selectionPanel, BorderLayout.CENTER);
    }


}
