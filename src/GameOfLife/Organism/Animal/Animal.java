package GameOfLife.Organism.Animal;

import GameOfLife.Organism.Organism;

public abstract class Animal extends Organism {

    @Override
    public void createLogToReproducing(Organism newOrganism) {
        world.getLogs().addLog(this.getName() + " from field (" + Integer.toString(this.position.x) + "," + Integer.toString(this.position.y) + ") has reproduced to (" + Integer.toString(newOrganism.getX()) + "," + Integer.toString(newOrganism.getY()) + ")");
    }
}
