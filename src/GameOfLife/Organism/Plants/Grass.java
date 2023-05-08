package GameOfLife.Organism.Plants;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.World;

import java.awt.*;

public class Grass extends Plant{
    public Grass(World world, Point p) {
        this.strength = Stats.MAX_STRENGHT;
        this.initiative = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "grass";
    }

    @Override
    public Organism createOrganism(Organism organism) {
        return new Grass(this.world, organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.yellow);
        boardPanel.getArrayButtons().get(index).setText("G");
    }
}
