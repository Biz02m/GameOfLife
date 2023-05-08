package GameOfLife;

import GameOfLife.Organism.Animal.Human;
import GameOfLife.Organism.Organism;

import java.util.ArrayList;
import java.util.Comparator;

public class OrganismHolder {
    World world;
    private ArrayList<Organism> organisms;

    OrganismHolder(World world) {
        this.world = world;
        organisms = new ArrayList<Organism>();
    }

    public int getNumberOfOrganisms(){
        return organisms.size();
    }

    public ArrayList<Organism> getOrganism() {
        return organisms;
    }

    public Organism getOrganism(int i) {
        return organisms.get(i);
    }

    public int getIndex(Organism organism){
        return organisms.indexOf(organism);
    }

    public void startPower() {
        Human human = getHuman();
        if(human == null){
            System.out.println("Warning, human received null");
            return;
        }
        if (human.getPowerReady()) {
            human.setPowerRounds(7);
            human.setPowerReady(false);
            human.setWaitRounds(0);
        }
        else{
            world.getLogs().addLog("Human could not use his power!");
        }
    }

    public Human getHuman() {
        for (Organism i : organisms) {
            if(i instanceof Human) {
                return (Human) i;
            }
        }
        return null;
    }

    public void addElement(Organism organism){
        organisms.add(organism);
        organisms.sort(Comparator.comparing(Organism::getInitiative).reversed());
    }

    public void killElement(int index){
        organisms.remove(index);
    }

    public boolean compareStrength(Organism organism, int i){
        if (organism.getStrength() > this.getOrganism(i).getStrength()){
            return true;
        }
        return false;
    }
}
