import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class GUI2 extends javax.swing.JFrame {

    private JFrame window;
    private JPanel panel;

    public GUI2() {
        // creates a new screen and adds text to it, we can change the text later
        window = new JFrame("Main SCreen");
        String all = "hrfriuashfiuhaisdhfiysdfhas";
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // creates the area for the text, and binds the vertical scrollbar to the text
        final JTextArea textArea = new JTextArea(10, 20);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // text and window styling
        textArea.setText(all);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        window.add(scroll);
        window.setSize(1500, 1000);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

}
