package GameOfLife.Organism.Plants;

import GameOfLife.Organism.Organism;
import GameOfLife.Stats;

import java.util.Random;

public abstract class Plant extends Organism {

    public boolean tryToSpread(){
        Random rand = new Random();
        int chance = rand.nextInt(100);
        return chance > Stats.CHANCE_TO_SPREAD;
    }

    @Override
    public void action() {
        if (tryToSpread()) {
            reproduce();
        }
    }

    @Override
    public void collision(int index) {
        String raport;
        raport = world.getOrganismHolder().getOrganism(index).getName() + " is eating " + this.name + " on field (" + Integer.toString(this.position.x) + "," + Integer.toString(this.position.y) + ")";
        world.getLogs().addLog(raport);
        int mainIndex = world.getOrganismHolder().getIndex(this);
        world.getOrganismHolder().killElement(mainIndex);
    }

    @Override
    public void createLogToReproducing(Organism newOrganism) {
        world.getLogs().addLog(this.getName() + " from field (" + Integer.toString(this.position.x) + "," + Integer.toString(this.position.y) + ") has spread " + this.getName() + " on field (" + Integer.toString(newOrganism.getX()) + "," + Integer.toString(newOrganism.getY()) + ")");
    }
}
