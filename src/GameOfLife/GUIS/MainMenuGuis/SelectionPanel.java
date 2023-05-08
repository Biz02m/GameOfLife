package GameOfLife.GUIS.MainMenuGuis;

import GameOfLife.Directions;
import GameOfLife.Organism.Animal.*;
import GameOfLife.Organism.Organism;
import GameOfLife.Organism.Plants.*;
import GameOfLife.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPanel extends JPanel implements ActionListener {
    private JButton AntelopeBtn;
    private JButton FoxBtn;
    private JButton SheepBtn;
    private JButton TurtleBtn;
    private JButton WolfBtn;
    private JButton BlueBerryBtn;
    private JButton DandelionBtn;
    private JButton GrassBtn;
    private JButton GuaranaBtn;
    private JButton HogweedBtn;
    private JButton HumanBtn;
    private World world;
    private SelectOrganism selectOrganism;

    public SelectionPanel(World world, SelectOrganism selectOrganism){
        this.world = world;
        this.selectOrganism = selectOrganism;
        this.setLayout(new GridLayout(3,3,10,10));
        this.setPreferredSize(new Dimension(100,100));

        this.AntelopeBtn = new JButton("Antelope");
        AntelopeBtn.addActionListener(this);
        AntelopeBtn.setBackground(Color.magenta);

        this.FoxBtn = new JButton("Fox");
        FoxBtn.addActionListener(this);
        FoxBtn.setBackground(new Color(229,110,31));

        this.SheepBtn = new JButton("Sheep");
        SheepBtn.addActionListener(this);
        SheepBtn.setBackground(Color.pink);

        this.TurtleBtn = new JButton("Turtle");
        TurtleBtn.addActionListener(this);
        TurtleBtn.setBackground(Color.green);

        this.WolfBtn = new JButton("Wolf");
        WolfBtn.addActionListener(this);
        WolfBtn.setBackground(Color.GRAY);

        this.BlueBerryBtn = new JButton("Blueberry");
        BlueBerryBtn.addActionListener(this);
        BlueBerryBtn.setBackground(Color.DARK_GRAY);

        this.DandelionBtn = new JButton("Dandelion");
        DandelionBtn.addActionListener(this);
        DandelionBtn.setBackground(Color.CYAN);

        this.GrassBtn = new JButton("Grass");
        GrassBtn.addActionListener(this);
        GrassBtn.setBackground(Color.yellow);

        this.GuaranaBtn = new JButton("Guarana");
        GuaranaBtn.addActionListener(this);
        GuaranaBtn.setBackground(Color.magenta);

        this.HogweedBtn = new JButton("Hogweed");
        HogweedBtn.addActionListener(this);
        HogweedBtn.setBackground(new Color(156, 29, 58));

        this.HumanBtn = new JButton("human");
        HumanBtn.addActionListener(this);
        HumanBtn.setBackground(Color.BLACK);

        this.add(AntelopeBtn);
        this.add(FoxBtn);
        this.add(SheepBtn);
        this.add(TurtleBtn);
        this.add(WolfBtn);
        this.add(BlueBerryBtn);
        this.add(DandelionBtn);
        this.add(GrassBtn);
        this.add(GuaranaBtn);
        this.add(HogweedBtn);
        this.add(HumanBtn);

    }


    public Organism createOrganism(String organism){
        return switch (organism) {
            case "antelope" -> new Antelope(world, world.getSelectedButton());
            case "fox" -> new Fox(world, world.getSelectedButton());
            case "sheep" -> new Sheep(world, world.getSelectedButton());
            case "turtle" -> new Turtle(world, world.getSelectedButton());
            case "wolf" -> new Wolf(world, world.getSelectedButton());
            case "blueberry" -> new BlueBerry(world, world.getSelectedButton());
            case "dandelion" -> new Dandelion(world, world.getSelectedButton());
            case "grass" -> new Grass(world, world.getSelectedButton());
            case "guarana" -> new Guarana(world, world.getSelectedButton());
            case "hogweed" -> new Hogweed(world, world.getSelectedButton());
            case "human" -> new Human(world, world.getSelectedButton());
            default -> null;
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Organism newOrganism = null;
        if(e.getSource() == this.AntelopeBtn){
            newOrganism = createOrganism("antelope");
        }
        else if(e.getSource() == this.FoxBtn){
            newOrganism = createOrganism("fox");
        }
        else if(e.getSource() == this.SheepBtn){
            newOrganism = createOrganism("sheep");
        }
        else if(e.getSource() == this.TurtleBtn){
            newOrganism = createOrganism("turtle");
        }
        else if(e.getSource() == this.WolfBtn){
            newOrganism = createOrganism("wolf");
        }
        else if(e.getSource() == this.BlueBerryBtn){
            newOrganism = createOrganism("blueberry");
        }
        else if(e.getSource() == this.DandelionBtn){
            newOrganism = createOrganism("dandelion");
        }
        else if(e.getSource() == this.GrassBtn){
            newOrganism = createOrganism("grass");
        }
        else if(e.getSource() == this.GuaranaBtn){
            newOrganism = createOrganism("guarana");
        }
        else if(e.getSource() == this.HogweedBtn){
            newOrganism = createOrganism("hogweed");
        }
        else if(e.getSource() == this.HumanBtn){
            newOrganism = createOrganism("human");
        }
        if(newOrganism != null) {
            int checkIfEmpty = newOrganism.checkIfCollision(newOrganism);
            if (checkIfEmpty != -1) {
                JOptionPane.showMessageDialog(null, "Could not create a new organism: This place is occupied by "+world.getOrganismHolder().getOrganism(checkIfEmpty).getName(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                world.getOrganismHolder().addElement(newOrganism);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Could not create a new organism: New organism is null", "Error", JOptionPane.ERROR_MESSAGE);
        }
        selectOrganism.setVisible(false);
        selectOrganism.dispose();
        world.setHumanDirection(Directions.NOWHERE);
        world.nextTurn();
    }
}
