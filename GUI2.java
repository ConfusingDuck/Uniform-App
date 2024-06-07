import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GUI2 extends JFrame {
    private JFrame window;
    private JPanel clothingPanel;
    private List<Clothing> clothes;

    public GUI2() {
        // Initialize clothing list
        clothes = new ArrayList<>();
        // Add clothing items with actual image paths
        clothes.add(new Clothing("T-Shirt", "Lightly-used", 19.99, "jeans example.png", "large"));
        clothes.add(new Clothing("Jeans", "Brand-new", 39.99, "jeans example.png", "small"));
        // Add more clothing items as needed

        // Set up the main window
        window = new JFrame("Clothing Marketplace");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1500, 1000);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        // Add a title label
        JLabel titleLabel = new JLabel("Clothing Marketplace", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        window.add(titleLabel, BorderLayout.NORTH);

        // Create the panel with GridBagLayout for clothing items
        clothingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Create a panel for each clothing item
        int row = 0;
        for (Clothing clothing : clothes) {
            gbc.gridx = 0;
            gbc.gridy = row;
            clothingPanel.add(createClothingPanel(clothing), gbc);
            row++;
        }

        // Add a scroll pane to the clothing panel
        JScrollPane scrollPane = new JScrollPane(clothingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(scrollPane, BorderLayout.CENTER);

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
}