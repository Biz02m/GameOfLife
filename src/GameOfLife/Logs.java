package GameOfLife;

import java.util.ArrayList;

public class Logs {
    private ArrayList<String> log;
    Logs(){
        log = new ArrayList<String>();
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void addLog(String log) {
        this.log.add(log);
    }

    public void viewLogs() {
        for (String i : log) {
            System.out.println(i);
        }
    }

    public void clearLogs() {
        log.clear();
    }
}
