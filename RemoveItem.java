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
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

/*This screen appears when the user wants to remove an item they have listed*/
public class RemoveItem {

    private JFrame frame;
    private JPanel panel;
    private JLabel lblBinNum;
    private JLabel lblPassword;
    private JTextField txtBinNum;
    private JPasswordField pfPassword;
    private JButton btnRemove;
    private boolean validBin;
    private String strBin;
    private String password;
    private char[] pf;


    public RemoveItem(User user, GUI2 gui2) {
        validBin = true;
        strBin = "";
        password = "";
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        // c determines where an object is within the gridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        // c is given insets to pad objects in the layout
        c.insets = new Insets(0, 5, 20, 0);

        // Automatically close program when frame is closed.
        frame.setTitle("Uniform Marketplace");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        lblBinNum = new JLabel("Enter the bin number of the item you want to remove:");
        txtBinNum = new JTextField(3);
        lblPassword = new JLabel("Enter password for confirmation: ");
        pfPassword = new JPasswordField(10);
        btnRemove = new JButton("Remove listed item");
        lblBinNum.setFont(new Font("Sans'serif", Font.PLAIN, 16));
        lblPassword.setFont(new Font("Sans'serif", Font.PLAIN, 16));
        btnRemove.setFont(new Font("Sans'serif", Font.PLAIN, 16));

        c.anchor = GridBagConstraints.EAST;
        c.weightx = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(lblBinNum, c);
        c.gridy = 1;
        panel.add(lblPassword, c);
        c.gridy = 2;
        panel.add(btnRemove, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(txtBinNum, c);
        c.gridy = 1;
        panel.add(pfPassword, c);

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strBin = txtBinNum.getText();
                // Check the price text box to ensure that only digits and decimal points can be
                // added
                validBin = strBin.length() > 0;
                for (int i = 0; i < strBin.length(); i++) {
                    if ((int) strBin.charAt(i) >= 48 && (int) strBin.charAt(i) <= 57) {
                    } else {
                        // Otherwise, price is false and the user is notified
                        validBin = false;
                        JOptionPane.showMessageDialog(frame, "Invalid bin number.");
                        break;
                    }
                }
                pf = pfPassword.getPassword();
                // Password field return an array of chars, which we convert to a string
                password = String.valueOf(pf);

                if (user.getPassword().equals(password)) {
                    if (validBin) {
                        FileEditor.removeByBin(strBin);
                        gui2.populateClothingPanel();
                        close();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect password.");
                }

            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }
}
