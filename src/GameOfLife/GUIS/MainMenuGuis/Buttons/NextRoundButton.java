package GameOfLife.GUIS.MainMenuGuis.Buttons;

import GameOfLife.Directions;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextRoundButton extends JButton implements ActionListener {
    private World world;
    public NextRoundButton(World world){
        this.world = world;
        this.setBounds(50,50,100,100);
        this.setIcon(new ImageIcon("NextRoundButton.png"));
        this.setBackground(Color.WHITE);
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        world.setHumanDirection(Directions.NOWHERE);
        world.nextTurn();
    }
}
