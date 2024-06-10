import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GUI2 extends JFrame {
    private JFrame window;
    private JPanel clothingPanel;
    private JPanel addItemPanel;
    private List<Clothing> clothes;
    private List<Clothing> allClothes; // To keep all clothing items for filtering
    private JButton btnAddItem;

    public GUI2(String username, User user) {
        // Initialize clothing lists
        clothes = new ArrayList<>();
        allClothes = new ArrayList<>();
        // Add clothing items with actual image paths
        allClothes.add(new Clothing("T-Shirt", "Lightly-used", 19.99, "jeans example.png", "large"));
        allClothes.add(new Clothing("Jeans", "Brand-new", 39.99, "jeans example.png", "small"));
        // Add more clothing items as needed

        clothes.addAll(allClothes);

        // Set up the main window
        window = new JFrame("Clothing Marketplace");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1500, 1000);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        //Add a panel at the bottom to hold the new item button
        addItemPanel = new JPanel();
        addItemPanel.setLayout(new BorderLayout());
        btnAddItem = new JButton("Add New Item");
        btnAddItem.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        addItemPanel.add(btnAddItem);
        window.add(addItemPanel, BorderLayout.SOUTH);

        // Add a panel for the title and buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Add a title label
        JLabel titleLabel = new JLabel("Clothing Marketplace", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(titleLabel);

        // Add filter buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton shortSleevesButton = new JButton("Short Sleeves");
        JButton longSleevesButton = new JButton("Long Sleeves");
        JButton sweaterButton = new JButton("Sweater");
        JButton pantsButton = new JButton("Pants");

        buttonPanel.add(shortSleevesButton);
        buttonPanel.add(longSleevesButton);
        buttonPanel.add(sweaterButton);
        buttonPanel.add(pantsButton);

        topPanel.add(buttonPanel);

        window.add(topPanel, BorderLayout.NORTH);

        // Create the panel with GridBagLayout for clothing items
        clothingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Create a panel for each clothing item
        populateClothingPanel();

        // Add a scroll pane to the clothing panel
        JScrollPane scrollPane = new JScrollPane(clothingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(scrollPane, BorderLayout.CENTER);

        // Add action listeners to buttons
        shortSleevesButton.addActionListener(e -> filterClothing("Short Sleeves"));
        longSleevesButton.addActionListener(e -> filterClothing("Long Sleeves"));
        sweaterButton.addActionListener(e -> filterClothing("Sweater"));
        pantsButton.addActionListener(e -> filterClothing("Pants"));

        window.setVisible(true);

        //Add action listener to the btnAddItem button
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItem addItemWindow = new AddItem(user);
                addItemWindow.show();
            }
        });
        
    }

    private void populateClothingPanel() {
        clothingPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        int row = 0;
        for (Clothing clothing : clothes) {
            gbc.gridx = 0;
            gbc.gridy = row;
            clothingPanel.add(createClothingPanel(clothing), gbc);
            row++;
        }
        clothingPanel.revalidate();
        clothingPanel.repaint();
    }

    private void filterClothing(String filter) {
        clothes.clear();
        for (Clothing clothing : allClothes) {
            if (clothing.getName().equalsIgnoreCase(filter)) {
                clothes.add(clothing);
            }
        }
        populateClothingPanel();
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

        // Resize the image and add to JLabel
        JLabel imageLabel = new JLabel(new ImageIcon(resizeImage(clothing.getImagePath(), 100, 100)));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        panel.add(imageLabel, gbc);

        return panel;
    }

    private Image resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void show() {
        window.setVisible(true);
    }

    public void close() {
        window.setVisible(false);
    }

}