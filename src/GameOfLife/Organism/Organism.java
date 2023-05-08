package GameOfLife.Organism;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Stats;
import GameOfLife.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public abstract class Organism {
    protected int strength;
    protected int initiative;
    protected Point position = new Point();
    protected Directions direction;
    protected String name;
    protected World world;

    public void action() {
        Directions direction = readDirection();
        Random rand = new Random();
        this.direction = direction;
        String raport;
        String x1 = Integer.toString(this.position.x);
        String y1 = Integer.toString(this.position.y);
        moveOrganism(direction);
        String x2 = Integer.toString(this.position.x);
        String y2 = Integer.toString(this.position.y);
        raport = this.name + " (" + x1 + "," + y1 + ") wants to go to (" + x2 + "," + y2 + ")";
        world.getLogs().addLog(raport);

        int index = checkIfCollision(this);
        int mainIndex = world.getOrganismHolder().getIndex(this);

        if(index != -1) {
            if(world.getOrganismHolder().getOrganism(index).getName() == world.getOrganismHolder().getOrganism(mainIndex).getName()) {
                world.getLogs().addLog(world.getOrganismHolder().getOrganism(index).getName() + " at: (" +x1+","+y1+") is trying to have a baby");
                this.position.x = Integer.parseInt(x1);
                this.position.y = Integer.parseInt(y1);
                int chance = rand.nextInt(100);
                if(chance > Stats.CHANCE_OF_REPRODUCTION) {
                    world.getLogs().addLog(world.getOrganismHolder().getOrganism(mainIndex).getName() + " is having a baby at (" + x1 +","+y1+")");
                    reproduce();
                }
                else {
                    world.getLogs().addLog("Unfortunatelly the baby has died");
                }
            }
            else if (world.getOrganismHolder().compareStrength(this,index)) {
                collision(index);
            }
            else {
                world.getOrganismHolder().getOrganism(index).collision(mainIndex);
            }
        }
        else {
            world.getLogs().addLog(this.getName() + " has moved to another field without fighting");
        }
    }

    public void reproduce(){
        Directions direction = readDirection();
        Organism newOrganism = createOrganism(this);
        newOrganism.moveOrganism(direction);
        if(checkIfCollision(newOrganism) != -1 || (this.position.x == newOrganism.getX() && this.position.y == newOrganism.getY())) {
            newOrganism = null;
            //umm, do nothing I guess? garbage collector will delete it anyway
        }
        else {
            createLogToReproducing(newOrganism);
            world.getOrganismHolder().addElement(newOrganism);
        }
    }



    public void moveOrganism(Directions direction){
        switch (direction) {
            case RIGHT:
                if (position.x + 1 < world.getBoardSizeX() - 1) this.position.x++;
                else {
                    this.position.x = world.getBoardSizeX() - 1;
                }
                break;
            case UP:
                if (position.y - 1 > 0) this.position.y--;
                else {
                    this.position.y = 0;
                }
                break;
            case LEFT:
                if (this.position.x - 1 > 0) this.position.x--;
                else {
                    this.position.x = 0;
                }
                break;
            case DOWN:
                if (this.position.y + 1 < world.getBoardSizeY() - 1) this.position.y++;
                else {
                    this.position.y = world.getBoardSizeY() - 1;
                }
                break;
            case NOWHERE:
        }
    }

    public Directions readDirection() {
        Random rand = new Random();
        int direction = rand.nextInt(4);
        return switch (direction) {
            case 0 -> Directions.RIGHT;
            case 1 -> Directions.UP;
            case 2 -> Directions.LEFT;
            case 3 -> Directions.DOWN;
            default -> Directions.NOWHERE;
        };
    }

    public int checkIfCollision(Organism organism){
        int i = 0;
        boolean isOnX = false;
        boolean isOnY = false;
        for(; i < world.getOrganismHolder().getNumberOfOrganisms(); i++) {
            Organism temp = world.getOrganismHolder().getOrganism(i);
            if (organism.getX() == temp.getX()) {
                isOnX = true;
            }
            if (organism.getY() == temp.getY()) {
                isOnY = true;
            }
            if (isOnX && isOnY && organism != temp) {
                return i;
            }
            else {
                isOnX = false;
                isOnY = false;
            }
        }
        if (isOnX == false || isOnY == false) {
            return -1; //-1 means no collision has occured
        }
        return -1;
    }

    public void collision(int index) {
        int mainIndex = world.getOrganismHolder().getIndex(this);
        String raport;
        String mainObject = world.getOrganismHolder().getOrganism(mainIndex).getName();
        String object = world.getOrganismHolder().getOrganism(index).getName();
        raport = mainObject + " is fighting with: " + object;
        world.getLogs().addLog(raport);
        world.getOrganismHolder().killElement(index);
        raport = mainObject + " kills: " + object;
        world.getLogs().addLog(raport);
    }

    public ArrayList<Integer> territorialAction(){
        ArrayList<Integer> foundIndexes = new ArrayList<Integer>();
        Point start = new Point();
        Point stop = new Point();
        Point objectPosition = new Point();
        setSearchField(start,stop);

        for (int i = 0; i < world.getOrganismHolder().getNumberOfOrganisms(); i++) {
            objectPosition.x = world.getOrganismHolder().getOrganism(i).getX();
            objectPosition.y = world.getOrganismHolder().getOrganism(i).getY();
            if (checkSearchField(start,stop,objectPosition) && world.getOrganismHolder().getOrganism(i) != this) {
                foundIndexes.add(i);
            }
        }
        foundIndexes.sort(Comparator.naturalOrder());
        foundIndexes.sort(Comparator.reverseOrder());
        return foundIndexes;
    }

    public void createLogToReproducing(Organism newOrganism){

    }

    public void setSearchField(Point start, Point stop){
        start.x = this.position.x - Stats.RANGE_OF_BURNING;
        start.y = this.position.y - Stats.RANGE_OF_BURNING;
        stop.x = this.position.x + Stats.RANGE_OF_BURNING;
        stop.y = this.position.y + Stats.RANGE_OF_BURNING;

        if(start.x <= 0) start.x = 0;
        if(start.y <= 0) start.y = 0;
        if(stop.x >= world.getBoardSizeX() - 1) stop.x = world.getBoardSizeX() - 1;
        if(stop.y >= world.getBoardSizeY() - 1) stop.y = world.getBoardSizeY() - 1;
    }

    public boolean checkSearchField(Point start, Point stop, Point objectPosition){
        return (objectPosition.x >= start.x && objectPosition.x <= stop.x) && (objectPosition.y >= start.y && objectPosition.y <= stop.y);
    }

    public void drawToScreen(BoardPanel screen) {

    }

    public int getRealStrength(){
        return getStrength();
    }

    public Organism createOrganism(Organism organism){
        return organism;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public Point getPosition() {
        return position;
    }

    public int getX(){
        return position.x;
    }

    public int getY(){
        return position.y;
    }

    public Directions getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setX(int x){
        this.position.x = x;
    }

    public void setY(int y){
        this.position.y = y;
    }


}
