package GameOfLife.GUIS.MainMenuGuis.Buttons;

import GameOfLife.Organism.Organism;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButton extends JButton implements ActionListener {
    private World world;
    public SaveButton(World world){
        this.world = world;
        this.setBounds(50,50,100,100);
        this.setIcon(new ImageIcon("SaveGameButton.png"));
        this.setBackground(Color.WHITE);
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) { //save game basically
        String fileName = JOptionPane.showInputDialog("Input the name of save file: ");
        if (fileName == "" || fileName == null){
            JOptionPane.showMessageDialog(null, "Could not save, try again!", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        File file = new File(fileName+".txt");
        if(file.exists()){
            file.delete();
        }
        try {
            FileWriter writer = new FileWriter(fileName+".txt");
            writer.append(Integer.toString(world.getBoardSizeX())+"\n");
            writer.append(Integer.toString(world.getBoardSizeY())+"\n");
            for (Organism i : world.getOrganismHolder().getOrganism()){
                writer.append(i.getName()+"\n");
                writer.append(Integer.toString(i.getRealStrength())+"\n");
                writer.append(Integer.toString(i.getX())+"\n");
                writer.append(Integer.toString(i.getY())+"\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
