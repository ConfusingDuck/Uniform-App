import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
    private List<Clothing> clothes;

    public GUI2() {
        // Initialize clothing list
        clothes = new ArrayList<>();
        // fill in teh actual pictures here
        clothes.add(new Clothing());
        clothes.add(new Clothing());
        // Add more clothing items as needed

        // Set up the main window
        window = new JFrame("Clothing Marketplace");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1500, 1000);
        window.setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Add a title label
        JLabel titleLabel = new JLabel("Clothing Marketplace");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Reset gridwidth for other components
        gbc.gridwidth = 1;

        // Create a panel for each clothing item
        int row = 1;
        for (Clothing clothing : clothes) {
            gbc.gridx = 0;
            gbc.gridy = row;
            panel.add(createClothingPanel(clothing), gbc);
            row++;
        }

        // Add a scroll pane to the panel
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        window.add(scrollPane);
        window.setVisible(true);
    }

    private JPanel createClothingPanel(Clothing clothing) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel nameLabel = new JLabel(clothing.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        JLabel descriptionLabel = new JLabel(clothing.getCondition());
        gbc.gridy = 1;
        panel.add(descriptionLabel, gbc);

        JLabel priceLabel = new JLabel("$" + clothing.getPrice());
        gbc.gridy = 2;
        panel.add(priceLabel, gbc);

        JLabel imageLabel = new JLabel(new ImageIcon(clothing.getImagePath()));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        panel.add(imageLabel, gbc);

        return panel;
    }

}
