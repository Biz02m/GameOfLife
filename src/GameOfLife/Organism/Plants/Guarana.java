package GameOfLife.Organism.Plants;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.World;

import java.awt.*;

public class Guarana extends Plant{
    public Guarana(World world, Point p) {
        this.strength = Stats.MAX_STRENGHT;
        this.initiative = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "guarana";
    }

    @Override
    public Organism createOrganism(Organism organism) {
        return new Guarana(this.world, organism.getPosition());
    }

    @Override
    public void collision(int index) {
        String raport;
        raport = world.getOrganismHolder().getOrganism(index).getName() + " eats " + this.name + " on field (" + Integer.toString(this.position.x) + "," + Integer.toString(this.position.y) + ")";
        world.getLogs().addLog(raport);
        raport = world.getOrganismHolder().getOrganism(index).getName() + " gains +3 to strength";
        world.getLogs().addLog(raport);
        int newStrength = world.getOrganismHolder().getOrganism(index).getRealStrength() + 3;
        world.getOrganismHolder().getOrganism(index).setStrength(newStrength);
        int mainIndex = world.getOrganismHolder().getIndex(this);
        world.getOrganismHolder().killElement(mainIndex);
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.magenta);
        boardPanel.getArrayButtons().get(index).setText("GU");
    }
}
