package GameOfLife.Organism.Animal;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.World;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(World world, Point p){
        this.strength = 9;
        this.initiative = 5;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "wolf";
    }

    public Wolf(World world, Point p, int strength){
        this.strength = strength;
        this.initiative = 5;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "wolf";
    }

    @Override
    public Organism createOrganism(Organism organism){
        return new Wolf(world, organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.GRAY);
        boardPanel.getArrayButtons().get(index).setText("W");
    }
}
