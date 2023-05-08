package GameOfLife;


import GameOfLife.GUIS.MainMenuGuis.MainMenu;
import GameOfLife.GUIS.MainMenuGuis.Screen;
import GameOfLife.Organism.Animal.*;
import GameOfLife.Organism.Organism;
import GameOfLife.Organism.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class World {
    private int boardSizeX;
    private int boardSizeY;
    private Status status;
    private Directions humanDirection;
    private int turn;
    private OrganismHolder organismHolder = new OrganismHolder(this);
    private Logs logs = new Logs();
    private Screen screen;
    private String LoadFileName;
    Point selectedButton = new Point();

    World(int boardSizeX, int boardSizeY){
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.status = Status.WAIT;
    }

    public void run(){
        if(this.status == Status.LOAD){
            loadSaveGame();
        }
        else{
            generateOrganisms();
        }
        screen = new Screen(this);
        screen.clearBoard();
        screen.paintObjects();
    }

    public void loadSaveGame(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.LoadFileName+".txt"));
            int numberOfLines = getNumberOfLines();
            numberOfLines = (int) Math.floor(numberOfLines/4);
            String line = reader.readLine();
            this.setBoardSizeX(Integer.parseInt(line));
            line = reader.readLine();
            this.setBoardSizeY(Integer.parseInt(line));

            String name = "";
            int strength = 0;
            int x = 0 ;
            int y = 0;

            for (int i = 0; i < numberOfLines; i++){
                for (int j = 1; j < 5; j++){
                    line = reader.readLine();
                    switch (j) {
                        case 1 -> name = line;
                        case 2 -> strength = Integer.parseInt(line);
                        case 3 -> x = Integer.parseInt(line);
                        case 4 -> y = Integer.parseInt(line);
                    }
                }
                createLoadedOrganism(name,strength,x,y);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createLoadedOrganism(String name, int strength, int x, int y){
        if (Objects.equals(name, "human")){
            this.getOrganismHolder().addElement(new Human(this, new Point(x,y), strength));
        }
        else if (Objects.equals(name, "wolf")){
            this.getOrganismHolder().addElement(new Wolf(this, new Point(x,y), strength));
        }
        else if (Objects.equals(name, "sheep")){
            this.getOrganismHolder().addElement(new Sheep(this, new Point(x,y), strength));
        }
        else if (Objects.equals(name, "fox")){
            this.getOrganismHolder().addElement(new Fox(this, new Point(x,y), strength));
        }
        else if (Objects.equals(name, "turtle")){
            this.getOrganismHolder().addElement(new Turtle(this, new Point(x,y), strength));
        }
        else if (Objects.equals(name, "antelope")){
            this.getOrganismHolder().addElement(new Antelope(this, new Point(x,y), strength));
        }
        else if (Objects.equals(name, "grass")){
            this.getOrganismHolder().addElement(new Grass(this, new Point(x,y)));
        }
        else if (Objects.equals(name, "dandelion")){
            this.getOrganismHolder().addElement(new Dandelion(this, new Point(x,y)));
        }
        else if (Objects.equals(name, "blueberry")){
            this.getOrganismHolder().addElement(new BlueBerry(this, new Point(x,y)));
        }
        else if (Objects.equals(name, "hogweed")){
            this.getOrganismHolder().addElement(new Hogweed(this, new Point(x,y)));
        }
        else if (Objects.equals(name, "guarana")){
            this.getOrganismHolder().addElement(new Guarana(this, new Point(x,y)));
        }
        else {
            JOptionPane.showMessageDialog(null, "Could not create: "+name+" not recognized", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getNumberOfLines(){
        try {
            BufferedReader counter = new BufferedReader(new FileReader(this.LoadFileName+".txt"));
            String line = counter.readLine();
            int lines = 0;
            while (line != null) {
                lines++;
                line = counter.readLine();
            }
            counter.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void nextTurn(){
        this.turn++;
        this.logs.clearLogs();
        for (int i = 0; i < this.organismHolder.getNumberOfOrganisms(); i++) {
            this.organismHolder.getOrganism(i).action();
        }
        if (!checkIfHumanDead()) {
            this.getLogs().addLog("Human dead, proceeding without human");
        }

        screen.clearBoard();
        screen.paintObjects();
        System.out.println("Turn :"+Integer.toString(turn));
        this.logs.viewLogs();
        System.out.println();
    }

    public boolean checkIfHumanDead(){
        for (Organism i : this.getOrganismHolder().getOrganism()){
            if (i instanceof Human){
                return true;
            }
        }
        return false;
    }

    public void generateOrganisms(){
        int x;
        int y;
        Point p = new Point();

        findEmptySpace(p);
        Human human = new Human(this, p);
        this.getOrganismHolder().addElement(human);

        findEmptySpace(p);
        Wolf wolf = new Wolf(this,p);
        this.getOrganismHolder().addElement(wolf);

        findEmptySpace(p);
        Antelope antelope = new Antelope(this,p);
        this.getOrganismHolder().addElement(antelope);

        findEmptySpace(p);
        Sheep sheep = new Sheep(this,p);
        this.getOrganismHolder().addElement(sheep);

        findEmptySpace(p);
        Turtle turtle = new Turtle(this,p);
        this.getOrganismHolder().addElement(turtle);

        findEmptySpace(p);
        Fox fox = new Fox(this,p);
        this.getOrganismHolder().addElement(fox);

        findEmptySpace(p);
        Grass grass = new Grass(this,p);
        this.getOrganismHolder().addElement(grass);

        findEmptySpace(p);
        Dandelion dandelion = new Dandelion(this,p);
        this.getOrganismHolder().addElement(dandelion);

        findEmptySpace(p);
        Guarana guarana = new Guarana(this,p);
        this.getOrganismHolder().addElement(guarana);

        findEmptySpace(p);
        BlueBerry blueBerry = new BlueBerry(this,p);
        this.getOrganismHolder().addElement(blueBerry);

        findEmptySpace(p);
        Hogweed hogweed = new Hogweed(this,p);
        this.getOrganismHolder().addElement(hogweed);
    }

    public void findEmptySpace(Point p){
        Random rand = new Random();
        if (getOrganismHolder().getNumberOfOrganisms() == 0){
            p.x = rand.nextInt(this.boardSizeX);
            p.y = rand.nextInt(this.boardSizeY);
            return;
        }

        for (int i = 0; i < getOrganismHolder().getNumberOfOrganisms(); i++) {
            p.x = rand.nextInt(this.boardSizeX);
            p.y = rand.nextInt(this.boardSizeY);
            if(getOrganismHolder().getOrganism(i).getX() == p.x && getOrganismHolder().getOrganism(i).getY() == p.y){
                i = 0;
            }
        }
    }

    public void showMenu(World world){
        MainMenu menu = new MainMenu(world);
    }

    public void setBoardSizeX(int boardSizeX) {
        this.boardSizeX = boardSizeX;
    }

    public void setBoardSizeY(int boardSizeY) {
        this.boardSizeY = boardSizeY;
    }

    public void setLoadFileName(String loadFileName) {
        LoadFileName = loadFileName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getBoardSizeX() {
        return boardSizeX;
    }

    public int getBoardSizeY() {
        return boardSizeY;
    }

    public Status getStatus() {
        return status;
    }

    public OrganismHolder getOrganismHolder() {
        return organismHolder;
    }

    public Logs getLogs() {
        return logs;
    }

    public void setHumanDirection(Directions humanDirection) {
        this.humanDirection = humanDirection;
    }

    public Directions getHumanDirection() {
        return humanDirection;
    }

    public void setSelectedButton(Point selectedButton) {
        this.selectedButton = selectedButton;
    }

    public Point getSelectedButton() {
        return selectedButton;
    }
}
