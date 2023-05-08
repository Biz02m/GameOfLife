package GameOfLife.Organism.Animal;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.World;

import java.awt.*;

public class Sheep extends Animal{
    public Sheep(World world, Point p){
        this.strength = 4;
        this.initiative = 4;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "sheep";
    }

    public Sheep(World world, Point p, int strength){
        this.strength = strength;
        this.initiative = 4;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "sheep";
    }

    @Override
    public Organism createOrganism(Organism organism){
        return new Sheep(world,organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.pink);
        boardPanel.getArrayButtons().get(index).setText("S");
    }
}
