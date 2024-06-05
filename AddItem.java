import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class AddItem extends JFrame {
    private JFrame frame;
    private JPanel panel;

    private JLabel lblPrice;
    private JLabel lblCondition;
    private JLabel lblGender;
    private JLabel lblSize;

    private JTextField txtPrice;
    private JComboBox cmbCondition;
    private JComboBox cmbGender;
    private JComboBox cmbSize;

    private final String[] conditions = {"Heavily Worn", "Moderately Worn", "Lightly Worn", "New"};
    private final String[] genders = {"Men's", "Women's"};
    private final String[] sizes = {"XS", "S", "M", "L", "XL"};

    public AddItem() {
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        //c determines where an object is within the gridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        //c is given insets to pad objects in the layout
        c.insets = new Insets(0, 5, 20, 0);

        //Automatically close program when frame is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Uniform Marketplace");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(panel);


        lblPrice = new JLabel("Set Your Price: $");
        lblCondition = new JLabel("Condition: ");
        lblGender = new JLabel("Gender: ");
        lblSize = new JLabel("Size: ");

        txtPrice = new JTextField(5);

    }
}
