package GameOfLife.Organism.Plants;

import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.Status;
import GameOfLife.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Hogweed extends Plant{
    public Hogweed(World world, Point p) {
        this.strength = Stats.MAX_STRENGHT;
        this.initiative = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "hogweed";
    }

    @Override
    public void action() {
        ArrayList<Integer> poisonedIndexes = territorialAction();
        String raport;
        for (Integer poisonedIndex : poisonedIndexes) {
            if (world.getOrganismHolder().getOrganism(poisonedIndex).getName() != "hogweed") {
                raport = "Hogweed poisons within its vicinity: " + world.getOrganismHolder().getOrganism(poisonedIndex).getName();
                world.getLogs().addLog(raport);
                if (world.getOrganismHolder().getOrganism(poisonedIndex).getName() == "human") {
                    world.setStatus(Status.STOP);
                }
                world.getOrganismHolder().killElement(poisonedIndex);
            }
        }
        super.action();
    }

    @Override
    public void collision(int index) {
        world.getLogs().addLog("Hogweed is confused... " + world.getOrganismHolder().getOrganism(index).getName() + " commited suicide by eating hogweed");
        world.getOrganismHolder().killElement(index);
    }

    @Override
    public boolean tryToSpread() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        return chance > 98;
    }

    @Override
    public Organism createOrganism(Organism organism) {
        return new Hogweed(this.world, organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(new Color(156, 29, 58));
        boardPanel.getArrayButtons().get(index).setText("HG");
    }
}
