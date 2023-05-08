package GameOfLife.GUIS.MainMenuGuis.Buttons;

import GameOfLife.GUIS.MainMenuGuis.MainMenu;
import GameOfLife.Status;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadSaveButton extends JButton implements ActionListener {
    private World world;
    private MainMenu mainMenu;
    public LoadSaveButton(MainMenu mainMenu, World world){
        this.mainMenu = mainMenu;
        this.world = world;
        this.setIcon(new ImageIcon("LoadSaveGameButton.png"));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.setStatus(Status.LOAD);
        String saveFile = JOptionPane.showInputDialog("Put in the name of save file: ");
        if (saveFile == null || saveFile == ""){
            JOptionPane.showMessageDialog(null,"Could not load save game, try again", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        File file = new File(saveFile+".txt");
        if(!file.exists()){
            JOptionPane.showMessageDialog(null, "This file does not exist, try again", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        world.setLoadFileName(saveFile);

        mainMenu.setVisible(false);
        mainMenu.dispose();
        world.run();
    }
}
