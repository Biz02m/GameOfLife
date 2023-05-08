package GameOfLife.GUIS.MainMenuGuis;

import GameOfLife.GUIS.MainMenuGuis.Panels.*;
import GameOfLife.GUIS.MainMenuGuis.Panels.SidePanel;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu(World world){
        TopPanel topPanel = new TopPanel();
        BotPanel botPanel = new BotPanel();
        SidePanel sidePanel1 = new SidePanel();
        JLabel author = new JLabel("<html>Mikołaj Bisewski <br>188594");
        sidePanel1.add(author);
        SidePanel sidePanel2 = new SidePanel();
        CenterPanel centerPanel = new CenterPanel(world,this);

        ImageIcon logo = new ImageIcon("GOL.png");
        this.setSize(600,600);
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        this.add(topPanel,BorderLayout.NORTH);
        this.add(botPanel,BorderLayout.SOUTH);
        this.add(sidePanel1,BorderLayout.EAST);
        this.add(sidePanel2,BorderLayout.WEST);
        this.add(centerPanel,BorderLayout.CENTER);
    }

}
