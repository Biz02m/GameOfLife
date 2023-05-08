package GameOfLife.GUIS.MainMenuGuis.ScreenPanels;

import GameOfLife.GUIS.MainMenuGuis.Buttons.FocusButton;
import GameOfLife.GUIS.MainMenuGuis.Buttons.NextRoundButton;
import GameOfLife.GUIS.MainMenuGuis.Buttons.SaveButton;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(World world) {
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.BLUE);
        FocusButton fButton = new FocusButton(world);
        NextRoundButton nextRoundButton = new NextRoundButton(world);
        SaveButton saveButton = new SaveButton(world);
        this.setLayout(new GridLayout(1,3,0,10));
        this.add(fButton);
        this.add(nextRoundButton);
        this.add(saveButton);
    }
}
