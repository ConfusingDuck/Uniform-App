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

/*This screen displays the main marketplace
 *It shows all the available listing
 Allows users to add or removed their own items
 Buyers can filter clothing by various filters to accomodate their needs*/
public class GUI2 extends JFrame {
    private JFrame window;
    private JPanel clothingPanel;
    private JPanel filterPanel;
    private List<Clothing> clothes;
    private List<Clothing> allClothes;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnSeeAll;

    public GUI2(String username, User user) {
        // Initialize clothing lists
        clothes = new ArrayList<>();
        allClothes = FileEditor.retreiveAll(); // Retrieve all clothing items from the file

        // Initialize the clothes list
        clothes.addAll(allClothes);

        // Set up the main window
        window = new JFrame("Uneed-Form Marketplace");
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
        JLabel titleLabel = new JLabel("Uneed-Form Marketplace", SwingConstants.CENTER);
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
        btnSeeAll = new JButton("See All"); // New "See All" button

        buttonPanel.add(shortSleevesButton);
        buttonPanel.add(longSleevesButton);
        buttonPanel.add(sweaterButton);
        buttonPanel.add(pantsButton);
        buttonPanel.add(btnSeeAll); // Add "See All" button to the panel

        topPanel.add(buttonPanel);

        window.add(topPanel, BorderLayout.NORTH);

        // Create the panel with GridBagLayout for clothing items
        clothingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Create a panel for each clothing item
        populateClothingPanel(); // Populating with all clothes initially

        // Add a scroll pane to the clothing panel
        JScrollPane scrollPane = new JScrollPane(clothingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(scrollPane, BorderLayout.CENTER);

        // Add a side panel for filters
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));

        // Add Size filter group
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.Y_AXIS));
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));

        JCheckBox extraSmallCheckBox = new JCheckBox("Extra Small");
        JCheckBox smallCheckBox = new JCheckBox("Small");
        JCheckBox mediumCheckBox = new JCheckBox("Medium");
        JCheckBox largeCheckBox = new JCheckBox("Large");
        JCheckBox extraLargeCheckBox = new JCheckBox("Extra Large");

        sizePanel.add(extraSmallCheckBox);
        sizePanel.add(smallCheckBox);
        sizePanel.add(mediumCheckBox);
        sizePanel.add(largeCheckBox);
        sizePanel.add(extraLargeCheckBox);

        filterPanel.add(sizePanel);

        // Add Gender filter group
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));
        genderPanel.setBorder(BorderFactory.createTitledBorder("Gender"));

        JCheckBox menCheckBox = new JCheckBox("Men");
        JCheckBox womenCheckBox = new JCheckBox("Women");

        genderPanel.add(menCheckBox);
        genderPanel.add(womenCheckBox);

        filterPanel.add(genderPanel);

        // Add Condition filter group
        JPanel conditionPanel = new JPanel();
        conditionPanel.setLayout(new BoxLayout(conditionPanel, BoxLayout.Y_AXIS));
        conditionPanel.setBorder(BorderFactory.createTitledBorder("Condition"));

        JCheckBox lightlyWornCheckBox = new JCheckBox("Lightly Worn");
        JCheckBox moderatelyWornCheckBox = new JCheckBox("Moderately Worn");
        JCheckBox heavilyWornCheckBox = new JCheckBox("Heavily Worn");
        JCheckBox brandNewCheckBox = new JCheckBox("Brand New");

        conditionPanel.add(lightlyWornCheckBox);
        conditionPanel.add(moderatelyWornCheckBox);
        conditionPanel.add(heavilyWornCheckBox);
        conditionPanel.add(brandNewCheckBox);

        filterPanel.add(conditionPanel);

        JButton applyFiltersButton = new JButton("Apply Filters");
        filterPanel.add(applyFiltersButton);

        window.add(filterPanel, BorderLayout.WEST);

        // Add action listeners to buttons
        shortSleevesButton.addActionListener(e -> filterClothingByType("Short-Sleeve Polo"));
        longSleevesButton.addActionListener(e -> filterClothingByType("Long-Sleeve Polo"));
        sweaterButton.addActionListener(e -> filterClothingByType("Sweater"));
        pantsButton.addActionListener(e -> filterClothingByType("Pants"));
        btnSeeAll.addActionListener(e -> seeAllClothing()); // Add action listener for "See All" button

        /* This action listener applies the filters that the user selected */
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
                RemoveItem removeItemWindow = new RemoveItem(user, GUI2.this);
                removeItemWindow.show();
            }
        });
    }

    /* This method populates the screen with panels for each clothing item */
    public void populateClothingPanel() {
        allClothes = FileEditor.retreiveAll();
        // Clears it to update
        clothingPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Continuously adds an item row by row
        int row = 0;
        for (Clothing clothing : allClothes) { // Using allClothes to populate all items
            gbc.gridx = 0;
            gbc.gridy = row;
            clothingPanel.add(createClothingPanel(clothing), gbc);
            row++;
        }
        clothingPanel.revalidate();
        clothingPanel.repaint();
    }

    /*
     * This method populates the screen with panels for each filtered clothing item
     */
    public void populateFilteredClothingPanel(List<Clothing> filteredClothes) { // New method
        clothingPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        int row = 0;
        for (Clothing clothing : filteredClothes) {
            gbc.gridx = 0;
            gbc.gridy = row;
            clothingPanel.add(createClothingPanel(clothing), gbc);
            row++;
        }
        clothingPanel.revalidate();
        clothingPanel.repaint();
    }

    /* This function filters clothing items by their type */
    private void filterClothingByType(String type) {
        List<Clothing> filteredClothes = new ArrayList<>();
        for (Clothing clothing : allClothes) {
            if (clothing.getName().equalsIgnoreCase(type)) {
                filteredClothes.add(clothing);
            }
        }
        populateFilteredClothingPanel(filteredClothes); // Using new method
    }

    /*
     * This function applies the filters selected and displays only items with the
     * selected filters
     */
    private void applyFilters(boolean extraSmall, boolean small, boolean medium, boolean large,
            boolean extraLarge, boolean men, boolean women,
            boolean lightlyWorn, boolean moderatelyWorn, boolean heavilyWorn, boolean brandNew) {
        List<Clothing> filteredClothes = new ArrayList<>();
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
                filteredClothes.add(clothing);
            }
        }
        populateFilteredClothingPanel(filteredClothes); // Using new method
    }

    /* Resets the list with all clothes in it */
    private void seeAllClothing() {
        populateClothingPanel(); // Using existing method
    }

    /* This function creates a panel to host the clothing image and details */
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

        JLabel binNumLabel = new JLabel("Bin number: " + clothing.getBinNum());
        gbc.gridy = 3;
        panel.add(binNumLabel, gbc);

        JButton btnSeeMore = new JButton("See contact info");
        gbc.gridy = 4;
        panel.add(btnSeeMore, gbc);

        // The button when pressed opens a window to show contact info
        btnSeeMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoreInfo infoWindow = new MoreInfo(clothing);
                infoWindow.show();
            }
        });

        // Resize the image and add to JLabel
        JLabel imageLabel = new JLabel(new ImageIcon(resizeImage(clothing.getImagePath(), 100, 100)));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        panel.add(imageLabel, gbc);

        return panel;
    }

    /* This function resizes the image so it can fit in the panel */
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
