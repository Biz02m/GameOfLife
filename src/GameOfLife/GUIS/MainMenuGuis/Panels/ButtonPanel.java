package GameOfLife.GUIS.MainMenuGuis.Panels;

import GameOfLife.GUIS.MainMenuGuis.Buttons.LoadSaveButton;
import GameOfLife.GUIS.MainMenuGuis.Buttons.QuitGameButton;
import GameOfLife.GUIS.MainMenuGuis.Buttons.SetSizeButton;
import GameOfLife.GUIS.MainMenuGuis.Buttons.StartGameButton;
import GameOfLife.GUIS.MainMenuGuis.MainMenu;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    ButtonPanel(World world, MainMenu mainMenu){
        StartGameButton startGameButton = new StartGameButton(mainMenu,world);
        QuitGameButton quitGameButton = new QuitGameButton();
        SetSizeButton setSizeButton = new SetSizeButton(world);
        LoadSaveButton loadSaveButton = new LoadSaveButton(mainMenu,world);

        this.setLayout(new GridLayout(4,1));
        this.add(startGameButton);
        this.add(quitGameButton);
        this.add(setSizeButton);
        this.add(loadSaveButton);
    }
}
