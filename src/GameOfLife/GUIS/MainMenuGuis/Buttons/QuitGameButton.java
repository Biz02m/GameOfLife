package GameOfLife.GUIS.MainMenuGuis.Buttons;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class QuitGameButton extends JButton {
    public QuitGameButton(){
        this.setIcon(new ImageIcon("QuitGameButton.png"));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.addActionListener(e -> exit(0));
    }
}
