package GameOfLife.GUIS.MainMenuGuis;

import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.MenuPanel;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private World world;
    private MenuPanel menuPanel;
    private BoardPanel boardPanel;
    public Screen(World world) {
        this.world = world;
        this.setSize(1600,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.menuPanel = new MenuPanel(world);
        this.boardPanel = new BoardPanel(world);
        this.setLayout(new BorderLayout());

        this.add(boardPanel,BorderLayout.CENTER);
        this.add(menuPanel,BorderLayout.SOUTH);
    }

    public void paintObjects() {
        for (int i = 0; i < world.getOrganismHolder().getNumberOfOrganisms(); i++) {
            world.getOrganismHolder().getOrganism(i).drawToScreen(boardPanel);
        }
    }

    public void clearBoard() {
        for(int i = 0; i < world.getBoardSizeX()* world.getBoardSizeY(); i++){
            boardPanel.getArrayButtons().get(i).setBackground(Color.WHITE);
            boardPanel.getArrayButtons().get(i).setText(" ");
        }
    }

}
