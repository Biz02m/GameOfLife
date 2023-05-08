package GameOfLife.Organism.Animal;

import GameOfLife.Directions;
import GameOfLife.GUIS.MainMenuGuis.ScreenPanels.BoardPanel;
import GameOfLife.Organism.Organism;
import GameOfLife.World;

import java.awt.*;
import java.util.ArrayList;

public class Human extends Animal {
    private boolean powerReady;
    private int powerRounds;
    private int waitRounds;

    public Human(World world, Point p) {
        this.strength = 5;
        this.initiative = 4;
        this.direction = Directions.UP;
        this.powerReady = true;
        this.powerRounds = 0;
        this.waitRounds = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "human";
    }
    public Human(World world, Point p, int strength) {
        this.strength = strength;
        this.initiative = 4;
        this.direction = Directions.UP;
        this.powerReady = true;
        this.powerRounds = 0;
        this.waitRounds = 0;
        this.position.x = p.x;
        this.position.y = p.y;
        this.world = world;
        this.name = "human";
    }

    @Override
    public Organism createOrganism(Organism organism) {
        return new Human(world, organism.getPosition());
    }

    @Override
    public Directions readDirection() {
        return world.getHumanDirection();
    }

    @Override
    public void action() {
        if (isPowerReadyCheck()) {
            world.getLogs().addLog("Waring the whole place is on fire");
            superPower();
            world.getLogs().addLog("Human is taking his turn");
            String x1 = Integer.toString(position.x);
            String y1 = Integer.toString(position.y);
            moveOrganism(readDirection());
            String x2 = Integer.toString(position.y);
            String y2 = Integer.toString(position.y);
            world.getLogs().addLog("Human is moving from ("+x1+","+y1+") to ("+x2+","+y2+")");
            superPower();
        }
        else {
            super.action();
        }
    }

    public boolean isPowerReadyCheck() {
        if (this.powerRounds != 0) {
            this.powerRounds--;
            world.getLogs().addLog("Human power left rounds: " + Integer.toString(this.powerRounds));
            return true;
        }
	else if (!this.powerReady){
            this.waitRounds++;
            if (this.waitRounds > 5) {
                this.powerReady = true;
            }
            return false;
        }
        return false;
    }

    public void superPower() {
        ArrayList<Integer> foundIndexes = territorialAction();
        String raport;
        for (Integer foundIndex : foundIndexes) {
            raport = "burning occures to: " + world.getOrganismHolder().getOrganism(foundIndex).getName();
            world.getLogs().addLog(raport);
            world.getOrganismHolder().killElement(foundIndex);
        }
    }

    public void setPowerReady(boolean powerReady) {
        this.powerReady = powerReady;
    }

    public void setPowerRounds(int powerRounds) {
        this.powerRounds = powerRounds;
    }

    public void setWaitRounds(int waitRounds) {
        this.waitRounds = waitRounds;
    }

    public boolean getPowerReady(){
        return this.powerReady;
    }

    @Override
    public void drawToScreen(BoardPanel boardPanel) {
        int index;
        if(this.powerRounds != 0) {
            Point start = new Point();
            Point stop = new Point();
            setSearchField(start,stop);
            for(int y = start.y; y <= stop.y; y++) {
                for (int x = start.x; x <= stop.x; x++){
                    index = (y * world.getBoardSizeX()) + x;
                    boardPanel.getArrayButtons().get(index).setBackground(Color.ORANGE);
                    boardPanel.getArrayButtons().get(index).setText("^");
                }
            }
        }
        index = (this.position.y * world.getBoardSizeX()) + this.position.x;
        boardPanel.getArrayButtons().get(index).setBackground(Color.BLACK);
        boardPanel.getArrayButtons().get(index).setText("H");
    }
}
