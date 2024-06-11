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
    private JPanel filterPanel;
    private List<Clothing> clothes;
    private List<Clothing> allClothes;
    private JButton btnAddItem;
    private JButton btnRemoveItem;

    public GUI2(String username, User user) {
        // Initialize clothing lists
        clothes = new ArrayList<>();
        allClothes = new ArrayList<>();
        // Add clothing items with actual image paths
        allClothes.add(
                new Clothing(username, "Short-Sleeve Polo", "Lightly worn", 19.99, "jeans example.png", "l", "men's"));
        allClothes.add(new Clothing(username, "Pants", "new", 39.99, "jeans example.png", "s", "women's"));
        // Add more clothing items as needed

        clothes.addAll(allClothes);

        // Set up the main window
        window = new JFrame("Clothing Marketplace");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1500, 1000);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        // Add a panel at the bottom to hold the new item button
        JPanel addItemPanel = new JPanel();
        btnAddItem = new JButton("Add New Item");
        btnAddItem.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        addItemPanel.add(btnAddItem);
        btnRemoveItem = new JButton("Remove Item");
        btnRemoveItem.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        addItemPanel.add(btnRemoveItem);
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

        JButton shortSleevesButton = new JButton("Short-Sleeve Polo");
        JButton longSleevesButton = new JButton("Long-Sleeve Polo");
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

        // Add a side panel for filters
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));

        // Add filter options
        JCheckBox extraSmallCheckBox = new JCheckBox("Extra Small");
        JCheckBox smallCheckBox = new JCheckBox("Small");
        JCheckBox mediumCheckBox = new JCheckBox("Medium");
        JCheckBox largeCheckBox = new JCheckBox("Large");
        JCheckBox extraLargeCheckBox = new JCheckBox("Extra Large");
        JCheckBox menCheckBox = new JCheckBox("Men");
        JCheckBox womenCheckBox = new JCheckBox("Women");
        JCheckBox lightlyWornCheckBox = new JCheckBox("Lightly Worn");
        JCheckBox moderatelyWornCheckBox = new JCheckBox("Moderately Worn");
        JCheckBox heavilyWornCheckBox = new JCheckBox("Heavily Worn");
        JCheckBox brandNewCheckBox = new JCheckBox("Brand New");

        filterPanel.add(extraSmallCheckBox);
        filterPanel.add(smallCheckBox);
        filterPanel.add(mediumCheckBox);
        filterPanel.add(largeCheckBox);
        filterPanel.add(extraLargeCheckBox);
        filterPanel.add(menCheckBox);
        filterPanel.add(womenCheckBox);
        filterPanel.add(lightlyWornCheckBox);
        filterPanel.add(moderatelyWornCheckBox);
        filterPanel.add(heavilyWornCheckBox);
        filterPanel.add(brandNewCheckBox);

        JButton applyFiltersButton = new JButton("Apply Filters");
        filterPanel.add(applyFiltersButton);

        window.add(filterPanel, BorderLayout.WEST);

        // Add action listeners to buttons
        shortSleevesButton.addActionListener(e -> filterClothingByType("Short-Sleeve Polo"));
        longSleevesButton.addActionListener(e -> filterClothingByType("Long-Sleeve Polo"));
        sweaterButton.addActionListener(e -> filterClothingByType("Sweater"));
        pantsButton.addActionListener(e -> filterClothingByType("Pants"));

        applyFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters(extraSmallCheckBox.isSelected(), smallCheckBox.isSelected(),
                        mediumCheckBox.isSelected(), largeCheckBox.isSelected(),
                        extraLargeCheckBox.isSelected(), menCheckBox.isSelected(), womenCheckBox.isSelected(),
                        lightlyWornCheckBox.isSelected(), moderatelyWornCheckBox.isSelected(),
                        heavilyWornCheckBox.isSelected(), brandNewCheckBox.isSelected());
            }
        });

        window.setVisible(true);

        // Add action listener to the btnAddItem button
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItem addItemWindow = new AddItem(user, GUI2.this);
                addItemWindow.show();
            }
        });

        // Add action listener to the btnRemoveItem button
        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveItem removeItemWindow = new RemoveItem(user);
                removeItemWindow.show();
            }
        });

    }

    public void populateClothingPanel() {
        ArrayList<Clothing> clothes = FileEditor.retreiveAll();
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

    private void filterClothingByType(String type) {
        clothes.clear();
        for (Clothing clothing : allClothes) {
            if (clothing.getName().equalsIgnoreCase(type)) {
                clothes.add(clothing);
            }
        }
        populateClothingPanel();
    }

    private void applyFilters(boolean extraSmall, boolean small, boolean medium, boolean large,
            boolean extraLarge, boolean men, boolean women,
            boolean lightlyWorn, boolean moderatelyWorn, boolean heavilyWorn, boolean brandNew) {
        clothes.clear();
        for (Clothing clothing : allClothes) {
            boolean matchesSize = (extraSmall && clothing.getSize().equalsIgnoreCase("xs"))
                    || (small && clothing.getSize().equalsIgnoreCase("s"))
                    || (medium && clothing.getSize().equalsIgnoreCase("m"))
                    || (large && clothing.getSize().equalsIgnoreCase("l"))
                    || (extraLarge && clothing.getSize().equalsIgnoreCase("xl"));

            boolean matchesGender = (men && clothing.getGender().equalsIgnoreCase("men's"))
                    || (women && clothing.getGender().equalsIgnoreCase("women's"));

            boolean matchesCondition = (lightlyWorn && clothing.getCondition().equalsIgnoreCase("lightly worn"))
                    || (moderatelyWorn && clothing.getCondition().equalsIgnoreCase("moderately worn"))
                    || (heavilyWorn && clothing.getCondition().equalsIgnoreCase("heavily worn"))
                    || (brandNew && clothing.getCondition().equalsIgnoreCase("new"));

            if (matchesSize && matchesGender && matchesCondition) {
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