package GameOfLife.Organism.Animal;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.World;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal{
    private int realStrength;
    public Antelope(World world, Point p){
        this.strength = Stats.MAX_STRENGHT;
        this.realStrength = 2;
        this.initiative = 4;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "antelope";
    }

    public Antelope(World world, Point p, int strength){
        this.strength = Stats.MAX_STRENGHT;
        this.realStrength = strength;
        this.initiative = 4;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "antelope";
    }

    @Override
    public void moveOrganism(Directions direction) {
        switch (direction) {
            case RIGHT:
                if (position.x + 2 < world.getBoardSizeX() - 1) this.position.x += 2;
                else {
                    this.position.x = world.getBoardSizeX() - 1;
                }
                break;
            case UP:
                if (position.y - 2 > 0) this.position.y -= 2;
                else {
                    this.position.y = 0;
                }
                break;
            case LEFT:
                if (this.position.x - 2 > 0) this.position.x -= 2;
                else {
                    this.position.x = 0;
                }
                break;
            case DOWN:
                if (this.position.y + 2 < world.getBoardSizeY() - 1) this.position.y += 2;
                else {
                    this.position.y = world.getBoardSizeY() - 1;
                }
                break;
            case NOWHERE:
        }
    }

    @Override
    public void collision(int index) {
        if (this.realStrength > world.getOrganismHolder().getOrganism(index).getStrength()) {
            super.collision(index);
        }
        else {
           Random rand = new Random();
           int chance = rand.nextInt(100);
           String raport;
           if (chance >= 50) {
               Directions directions = readDirection();
               moveOrganism(directions);
               raport = "antelope managed to escape the attack of: "+ world.getOrganismHolder().getOrganism(index).getName() + " and currently is on a field: ("+Integer.toString(this.position.x)+","+Integer.toString(this.position.y)+")";
               world.getLogs().addLog(raport);
           }
           else {
               raport = "antelope did not manage to escape the grasp of: "+world.getOrganismHolder().getOrganism(index).getName()+" and is currently dead";
               world.getLogs().addLog(raport);
               int mainIndex = world.getOrganismHolder().getIndex(this);
               world.getOrganismHolder().getOrganism(index).collision(mainIndex);
           }
        }
    }

    @Override
    public int getRealStrength() {
        return this.realStrength;
    }

    @Override
    public void setStrength(int strength){
        this.realStrength = strength;
    }

    @Override
    public Organism createOrganism(Organism organism){
        return new Antelope(world,organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.MAGENTA);
        boardPanel.getArrayButtons().get(index).setText("A");
    }
}
