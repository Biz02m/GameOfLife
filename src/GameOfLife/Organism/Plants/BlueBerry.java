package GameOfLife.Organism.Plants;

import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.Status;
import GameOfLife.World;

import java.awt.*;

public class BlueBerry extends Plant{
    public BlueBerry(World world, Point p) {
        this.strength = Stats.MAX_STRENGHT;
        this.initiative = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "blueberry";
    }

    @Override
    public void collision(int index) {
        String raport;
        raport = world.getOrganismHolder().getOrganism(index).getName() + " is eating a deadly blueberry on field (" + Integer.toString(this.position.x)+","+Integer.toString(this.position.y) + ") and dies like an idiot";
        world.getLogs().addLog(raport);
        int mainIndex = world.getOrganismHolder().getIndex(this);
        if (world.getOrganismHolder().getOrganism(index).getName() == "human"){
            world.setStatus(Status.STOP);
        }
        world.getOrganismHolder().killElement(index);
        world.getOrganismHolder().killElement(mainIndex);
    }

    @Override
    public Organism createOrganism(Organism organism) {
        return new BlueBerry(this.world, organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.DARK_GRAY);
        boardPanel.getArrayButtons().get(index).setText("BB");
    }
}
