package GameOfLife.Organism.Animal;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Stats;
import GameOfLife.World;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal{
    private int realStrength;
    public Turtle(World world, Point p){
        this.realStrength = 2;
        this.strength = this.realStrength + 3; //he has to be able to defend against animals weaker than 5
        this.initiative = 1;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "turtle";
    }

    public Turtle(World world, Point p, int strength){
        this.realStrength = strength;
        this.strength = this.realStrength + 3;
        this.initiative = 1;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "turtle";
    }

    @Override
    public void action() {
        Random rand = new Random();
        this.realStrength = this.strength - 3; //update real strength in case turtle ate a guarana plant
        Directions direction = readDirection();
        this.direction = direction;
        world.getLogs().addLog("Turtle is thinking whether to move or not");
        int mainIndex = world.getOrganismHolder().getIndex(this);
        int chance = rand.nextInt(100);
        if (chance >= Stats.TURTLE_CHANCE_TO_MOVE) {
            String x1 = Integer.toString(this.position.x);
            String y1 = Integer.toString(this.position.y);
            moveOrganism(direction);
            String x2 = Integer.toString(this.position.x);
            String y2 = Integer.toString(this.position.y);
            world.getLogs().addLog("Turtle decides to move from (" + x1 + "," + y1 +") to (" + x2 +"," + y2 + ")");
            int index = checkIfCollision(this);
            if (index != -1) {
                if(world.getOrganismHolder().getOrganism(index).getName() == world.getOrganismHolder().getOrganism(mainIndex).getName()) {
                    this.position.x = Integer.parseInt(x1);
                    this.position.y = Integer.parseInt(y1);
                    int chanceToReproduce = rand.nextInt(100);
                    if(chanceToReproduce > Stats.CHANCE_OF_REPRODUCTION) {
                        world.getLogs().addLog(world.getOrganismHolder().getOrganism(mainIndex).getName() + " is having a baby at (" + x1 +","+y1+")");
                        reproduce();
                    }
                    else {
                        world.getLogs().addLog("Unfortunatelly the baby has died");
                    }
                }
                else if (this.realStrength > world.getOrganismHolder().getOrganism(index).getStrength()) {
                    super.collision(index);
                }
                else if (this.strength > world.getOrganismHolder().getOrganism(index).getStrength()) {
                    direction = oppositeDir(mainIndex);
                    this.direction = direction;
                    this.moveOrganism(direction);
                    world.getLogs().addLog("turtle says: whopsies, i bumped into someone and i am still alive !");
                }
                else {
                    world.getOrganismHolder().getOrganism(index).collision(mainIndex);
                }
            }
        }
        else {
            world.getLogs().addLog("Turtle is too lazy to move");
        }
    }

    public Directions oppositeDir(int index) {
        Directions temp = world.getOrganismHolder().getOrganism(index).getDirection();
        return switch (temp) {
            case RIGHT -> Directions.LEFT;
            case UP -> Directions.DOWN;
            case LEFT -> Directions.RIGHT;
            case DOWN -> Directions.UP;
            default -> Directions.NOWHERE;
        };
    }

    @Override
    public void collision(int index) {
        if (world.getOrganismHolder().getOrganism(index).getStrength() > this.realStrength) {
            String raport;
            String object1 = this.getName();
            String object2 = world.getOrganismHolder().getOrganism(index).getName();
            world.getLogs().addLog("Turtle: IM WALKING HERE");
            raport = object1 + " deflects attack of: " + object2 + " to another field";
            world.getLogs().addLog(raport);
            Directions direction = oppositeDir(index);
            world.getOrganismHolder().getOrganism(index).moveOrganism(direction);
        }
        else {
            super.collision(index);
        }
    }

    @Override
    public int getRealStrength() {
        return this.realStrength;
    }

    @Override
    public Organism createOrganism(Organism organism){
        return new Turtle(world,organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.green);
        boardPanel.getArrayButtons().get(index).setText("T");
    }
}
