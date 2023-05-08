package GameOfLife.Organism.Animal;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.Organism.Plants.Plant;
import GameOfLife.World;

import java.awt.*;

public class Fox extends Animal{
    public Fox(World world, Point p){
        this.strength = 3;
        this.initiative = 7;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "fox";
    }

    public Fox(World world, Point p, int strength){
        this.strength = strength;
        this.initiative = 7;
        this.direction = Directions.DOWN;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "fox";
    }

    @Override
    public Directions readDirection() {
        Directions direction = super.readDirection();
        Point point = new Point();
        point.x = this.position.x;
        point.y = this.position.y;
        boolean status = true;
        while (status) {
            direction = super.readDirection();
            switch (direction) {
                case RIGHT -> status = this.checkIfCollision(point.x + 1, point.y);
                case UP -> status = this.checkIfCollision(point.x, point.y - 1);
                case LEFT -> status = this.checkIfCollision(point.x - 1, point.y);
                case DOWN -> status = this.checkIfCollision(point.x, point.y + 1);
            }
        }
        return direction;
    }

    public boolean checkIfCollision(int x, int y) {
        String x1 = Integer.toString(x);
        String y1 = Integer.toString(y);
        for (int i = 0; i < world.getOrganismHolder().getNumberOfOrganisms(); i++){
            if (x == world.getOrganismHolder().getOrganism(i).getX() && y == world.getOrganismHolder().getOrganism(i).getY() && this.strength < world.getOrganismHolder().getOrganism(i).getStrength() && !(world.getOrganismHolder().getOrganism(i) instanceof Plant)) {
                world.getLogs().addLog("Sniff Sniff... Fox detected an animal stronger than himself at field ("+x1+","+y1+")");
                return true;
            }
        }
        world.getLogs().addLog("Sniff Sniff, on field (" + x1 + "," + y1 + ") there is no animal stronger than me HA");
        return false;
    }

    @Override
    public Organism createOrganism(Organism organism){
        return new Fox(world,organism.getPosition());
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(new Color(229,110,31));
        boardPanel.getArrayButtons().get(index).setText("F");
    }
}
