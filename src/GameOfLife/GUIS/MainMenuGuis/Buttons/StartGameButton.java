package GameOfLife.GUIS.MainMenuGuis.Buttons;

import GameOfLife.GUIS.MainMenuGuis.MainMenu;
import GameOfLife.Status;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class StartGameButton extends JButton {
    public StartGameButton(MainMenu mainMenu, World world){
        this.setIcon(new ImageIcon("StartGameButton.png"));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.addActionListener( e -> {
            world.setStatus(Status.START);
            mainMenu.setVisible(false);
            mainMenu.dispose();
            world.run();
        });
    }
}
