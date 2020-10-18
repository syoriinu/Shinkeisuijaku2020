import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        setTitle("神経衰弱");
        setSize(new Dimension(Settings.FRAME_WIDTH.getValue(),Settings.FRAME_HEIGHT.getValue()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel GAME_PANEL = new GamePanel();
        Container container = getContentPane();
        container.setBackground(Color.decode(Settings.BACKGROUND_COLOR.getWord()));
        container.add(GAME_PANEL);

        setVisible(true);
    }
}
