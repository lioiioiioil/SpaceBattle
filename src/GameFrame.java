import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    public GameFrame(int size) throws HeadlessException {
        setBounds(0, 0, size, size);
        gamePanel = new GamePanel();
        add(gamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
