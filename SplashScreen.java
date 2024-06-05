import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends JWindow {
    private JPanel panel;
    private ImageIcon picture;
    private JLabel label;
    private Timer timer;
    private JFrame frame;


    public SplashScreen() {

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1600, 1200);
        frame.setLocationRelativeTo(null);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        frame.add(panel);
        

        picture = new ImageIcon(getClass().getResource("butt picture.png"));
        label = new JLabel(picture);
        panel.add(label);

    }

    public void show() {
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
    }
}
