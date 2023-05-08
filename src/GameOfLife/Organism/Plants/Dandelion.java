package GameOfLife.Organism.Plants;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.World;

import java.awt.*;
import java.util.Random;

public class Dandelion extends Plant{
    public Dandelion(World world, Point p) {
        this.strength = Stats.MAX_STRENGHT;
        this.initiative = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "dandelion";
    }

    @Override
    public Organism createOrganism(Organism organism) {
        return new Dandelion(this.world, organism.getPosition());
    }

    @Override
    public boolean tryToSpread() {
        Random rand = new Random();
        int chance;
        for (int i = 0; i < 3; i++) {
            chance = rand.nextInt(100);
            if (chance > Stats.CHANCE_TO_SPREAD) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.CYAN);
        boardPanel.getArrayButtons().get(index).setText("D");
    }
}
