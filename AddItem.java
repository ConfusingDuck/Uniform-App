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
    private JLabel lblItem;

    private JTextField txtPrice;
    private JComboBox cmbCondition;
    private JComboBox cmbGender;
    private JComboBox cmbSize;
    private JComboBox cmbItem;

    private final String[] conditions = {" ", "Heavily Worn", "Moderately Worn", "Lightly Worn", "New"};
    private final String[] genders = {" ", "Men's", "Women's"};
    private final String[] sizes = {" ", "XS", "S", "M", "L", "XL"};
    private final String[] items = {" ", "Short-Sleeve Polo", "Long-Sleeve Polo", "Sweater", "Pants"};

<<<<<<< Updated upstream
    private final String[] conditions = { "Heavily Worn", "Moderately Worn", "Lightly Worn", "New" };
    private final String[] genders = { "Men's", "Women's" };
    private final String[] sizes = { "XS", "S", "M", "L", "XL" };
=======
>>>>>>> Stashed changes

    public AddItem() {
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        // c determines where an object is within the gridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        // c is given insets to pad objects in the layout
        c.insets = new Insets(0, 5, 20, 0);

        // Automatically close program when frame is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Uniform Marketplace");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

<<<<<<< Updated upstream
=======
        lblItem = new JLabel("Pick the type of item:");
>>>>>>> Stashed changes
        lblPrice = new JLabel("Set Your Price: $");
        lblCondition = new JLabel("Condition:");
        lblGender = new JLabel("Gender:");
        lblSize = new JLabel("Size:");

        txtPrice = new JTextField(5);
        cmbCondition = new JComboBox<>(conditions);
        cmbGender= new JComboBox<>(genders);
        cmbSize = new JComboBox<>(sizes);
        cmbItem = new JComboBox<>(items);

        //Add all the labels and boxes to the panel.
        c.gridx = 0;
        c.gridy = 0;
        panel.add(lblPrice, c);
        c.gridy = 1;
        panel.add(lblCondition, c);
        c.gridy = 2;
        panel.add(lblGender, c);
        c.gridy = 3;
        panel.add(lblSize, c);
        c.gridx = 1;
        panel.add(cmbSize, c);
        c.gridy = 2;
        panel.add(cmbGender, c);
        c.gridy = 1;
        panel.add(cmbCondition, c);
        c.gridy = 0;
        panel.add(txtPrice, c);
        
        lblPrice.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblCondition.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblGender.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblSize.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        txtPrice.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbCondition.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbGender.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbSize.setFont(new Font("Sans-serif", Font.PLAIN, 16));


    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }
}
