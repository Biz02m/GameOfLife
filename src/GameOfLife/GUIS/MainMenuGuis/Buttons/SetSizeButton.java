package GameOfLife.GUIS.MainMenuGuis.Buttons;

import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class SetSizeButton extends JButton {
    public SetSizeButton(World world){
        this.setIcon(new ImageIcon("SetSizeButton.png"));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.addActionListener(e -> {
            try {
                int x = Integer.parseInt(JOptionPane.showInputDialog("<html>Current size Of board is " + world.getBoardSizeX() + "x" + world.getBoardSizeY() + "<br>Set length of x:"));
                world.setBoardSizeX(x);
                int y = Integer.parseInt(JOptionPane.showInputDialog("<html>Current size Of board is " + world.getBoardSizeX() + "x" + world.getBoardSizeY() + "<br>Set length of Y:"));
                world.setBoardSizeY(y);
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null,"<html>Could not set the size of board<br>Board size is "+world.getBoardSizeX()+"x"+world.getBoardSizeY()+"<br>Try again","WARNING",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
