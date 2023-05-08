package GameOfLife.GUIS.MainMenuGuis.ScreenPanels;

import GameOfLife.GUIS.MainMenuGuis.SelectOrganism;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardPanel extends JPanel implements ActionListener {
    private ArrayList<JButton> arrayButtons;
    private World world;

    public BoardPanel(World world) {
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.WHITE);
        this.world = world;
        arrayButtons = new ArrayList<JButton>();
        for(int i = 0; i < world.getBoardSizeX()* world.getBoardSizeY(); i++){
            arrayButtons.add(new JButton());
        }
        for(int i = 0; i < world.getBoardSizeX()* world.getBoardSizeY(); i++){
            arrayButtons.get(i).addActionListener(this);
            arrayButtons.get(i).setBackground(Color.WHITE);
            this.add(arrayButtons.get(i));
        }
        this.setLayout(new GridLayout(world.getBoardSizeY(), world.getBoardSizeX()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < world.getBoardSizeX()* world.getBoardSizeY(); i++){
            if(e.getSource() == arrayButtons.get(i)) {
                int y = (int) Math.floor(i/ world.getBoardSizeX());
                int x = i - (y*world.getBoardSizeX());
                world.setSelectedButton(new Point(x,y));
                System.out.println(x+" "+y);
                SelectOrganism selectOrganism = new SelectOrganism(world);
            }
        }
    }

    public ArrayList<JButton> getArrayButtons() {
        return arrayButtons;
    }
}
