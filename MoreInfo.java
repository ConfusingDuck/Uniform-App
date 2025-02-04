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

/*This simple screen provides contact information of the user who wants to sell a uniform
 *Buyers view this screen to get in contact with the sellers*/
public class MoreInfo {
    private JFrame frame;
    private JPanel panel;
    private JLabel lblName;

    public MoreInfo(Clothing clothing) {
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        // c determines where an object is within the gridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        // c is given insets to pad objects in the layout
        c.insets = new Insets(0, 5, 20, 0);

        frame.setTitle("Uneed-form Marketplace");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        // Display the user's contact info with the getContactInfo method
        lblName = new JLabel("Contact Info: " + User.getContactInfo(clothing.getUsername()));
        lblName.setFont(new Font("Sans-serif", Font.PLAIN, 16));

        c.gridx = 0;
        c.gridy = 0;
        panel.add(lblName, c);
    }

    public void show() {
        frame.setVisible(true);
    }

}
