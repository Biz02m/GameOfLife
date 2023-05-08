package GameOfLife.GUIS.MainMenuGuis.ScreenPanels;

import javax.swing.*;
import java.awt.*;

public class Board  extends JFrame {
    Board(int width, int height){
        this.setVisible(true);
        this.setTitle("Game Of Life");
        this.setSize(width,height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("GOL.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.black);
    }
}
