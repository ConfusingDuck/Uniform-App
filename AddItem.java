import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

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

    private JFileChooser fcUpload;
    private JButton btnUpload;
    private JButton btnAdd;

    private double price;
    private String condition;
    private String gender;
    private String size;
    private String item;
    private String imagePath;
    private String strPrice;
    private boolean validPrice;

    // Make lists of all available options for each combo box
    private final String[] conditions = { " ", "Heavily Worn", "Moderately Worn", "Lightly Worn", "New" };
    private final String[] genders = { " ", "Men's", "Women's" };
    private final String[] sizes = { " ", "XS", "S", "M", "L", "XL" };
    private final String[] items = { " ", "Short-Sleeve Polo", "Long-Sleeve Polo", "Sweater", "Pants" };

    public AddItem(User user, GUI2 gui2) {
        imagePath = " ";
        strPrice = "";
        validPrice = true;
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        // c determines where an object is within the gridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        // c is given insets to pad objects in the layout
        c.insets = new Insets(0, 5, 20, 0);

        frame.setTitle("Uniform Marketplace");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        // Initialize all the labels and boxes
        lblItem = new JLabel("Pick the type of item:");
        lblPrice = new JLabel("Set Your Price: $");
        lblCondition = new JLabel("Condition:");
        lblGender = new JLabel("Gender:");
        lblSize = new JLabel("Size:");

        txtPrice = new JTextField(5);
        cmbCondition = new JComboBox<>(conditions);
        cmbGender = new JComboBox<>(genders);
        cmbSize = new JComboBox<>(sizes);
        cmbItem = new JComboBox<>(items);

        fcUpload = new JFileChooser();
        btnUpload = new JButton("Upload Picture");
        btnAdd = new JButton("Add Item");

        // Add all the labels and boxes to the panel.
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(lblItem, c);
        c.gridy = 1;
        panel.add(lblCondition, c);
        c.gridy = 2;
        panel.add(lblGender, c);
        c.gridy = 3;
        panel.add(lblSize, c);
        c.gridy = 4;
        panel.add(lblPrice, c);
        c.gridy = 5;
        panel.add(btnUpload, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 4;
        panel.add(txtPrice, c);
        c.gridy = 3;
        panel.add(cmbSize, c);
        c.gridy = 2;
        panel.add(cmbGender, c);
        c.gridy = 1;
        panel.add(cmbCondition, c);
        c.gridy = 0;
        panel.add(cmbItem, c);
        c.gridy = 5;
        panel.add(btnAdd, c);

        // Give each label and box a font
        lblPrice.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblCondition.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblGender.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblSize.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        lblItem.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        txtPrice.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbCondition.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbGender.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbSize.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        cmbItem.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        btnAdd.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        btnUpload.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        // Add an actionListener for the upload picture button
        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Opens the computer's file directory so user can browse
                fcUpload = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int x = fcUpload.showOpenDialog(null);
                // If the user selects an image, then the file path is obtained and stored in
                // imagePath
                if (x == JFileChooser.APPROVE_OPTION) {
                    imagePath = fcUpload.getSelectedFile().getAbsolutePath();
                }
                // Otherwise, nothing happens and the window closes
                else {
                }
            }
        });

        // Add an actionListener for the button to add a new item
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                strPrice = txtPrice.getText();
                // Check the price text box to ensure that only digits and decimal points can be
                // added
                validPrice = strPrice.length() > 0;
                for (int i = 0; i < strPrice.length(); i++) {
                    if ((int) strPrice.charAt(i) >= 48 && (int) strPrice.charAt(i) <= 57 || strPrice.charAt(i) == 46) {
                    } else {
                        // Otherwise, price is false and the user is notified
                        validPrice = false;
                        JOptionPane.showMessageDialog(frame, "Invalid price.");
                        break;
                    }
                }
                if (validPrice) {
                    // If the string only contains digits and a decimal point, then the price can be
                    // made a double
                    price = Double.valueOf(strPrice);
                }
                // Gather all the specifics from the user's entry
                condition = cmbCondition.getSelectedItem().toString();
                gender = cmbGender.getSelectedItem().toString();
                size = cmbSize.getSelectedItem().toString();
                item = cmbItem.getSelectedItem().toString();
                // If the inputted fields are not spaces (default value), then the submission is
                // valid
                if (condition.equals(" ") || gender.equals(" ") || size.equals(" ") || item.equals(" ")
                        || validPrice == false) {
                    JOptionPane.showMessageDialog(frame, "Invalid submission.");
                } else {
                    Clothing clothing = new Clothing(user.getUsername(), item, condition, price, imagePath, size,
                            gender, Clothing.getLatestBinNum() + 1);
                    user.setClothingItem(clothing);
                    FileEditor.storePicture(clothing);
                    FileEditor.storeClothingItem(clothing);
                    gui2.populateClothingPanel();
                    close();

                }
            }
        });

    }

    /* This method makes the pane visible */
    public void show() {
        frame.setVisible(true);
    }

    /* This method closes the pane */
    public void close() {
        frame.setVisible(false);
    }
}