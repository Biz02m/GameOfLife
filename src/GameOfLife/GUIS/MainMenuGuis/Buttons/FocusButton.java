package GameOfLife.GUIS.MainMenuGuis.Buttons;

import GameOfLife.Directions;
import GameOfLife.Status;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FocusButton extends JButton implements KeyListener {
    private World world;
    public FocusButton(World world){
        this.world = world;
        this.setBounds(50,50,100,100);
        this.setIcon(new ImageIcon("Focus.png"));
        this.setBackground(Color.WHITE);
        JLabel author = new JLabel("Mikołaj Bisewski 188594");
        this.add(author);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){
            world.setStatus(Status.STOP);
        }
        else if(keyCode == KeyEvent.VK_RIGHT) {
            world.setHumanDirection(Directions.RIGHT);
        }
        else if(keyCode == KeyEvent.VK_UP) {
            world.setHumanDirection(Directions.UP);
        }
        else if(keyCode == KeyEvent.VK_LEFT) {
            world.setHumanDirection(Directions.LEFT);
        }
        else if(keyCode == KeyEvent.VK_DOWN) {
            world.setHumanDirection(Directions.DOWN);
        }
        else if(keyCode == KeyEvent.VK_P) {
            world.setHumanDirection(Directions.NOWHERE);
            world.getOrganismHolder().startPower();
        }
        else {
            world.setHumanDirection(Directions.NOWHERE);
        }
        world.nextTurn();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
