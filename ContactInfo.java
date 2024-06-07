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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class ContactInfo{
    private JPanel contactInfoPanel;
    private JFrame contactInfoFrame;

    private JLabel lblFullName;
    private JLabel lblEmail;
    private JLabel lblPhoneNumber;
    private JTextField txtFullName;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JButton confirmButton;

    private String fullName;
    private String email;
    private String phoneNumber;
    private User user;


    public ContactInfo(String username, String password) {
        //Makes new contact info frame and panel
        contactInfoFrame = new JFrame();
        contactInfoPanel = new JPanel();
        //Sets margins for the border
        contactInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contactInfoPanel.setLayout(new GridBagLayout());
        //c determines where an object is within the gridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        //c is given insets to pad objects in the layout
        c.insets = new Insets(0, 5, 20, 0);

        //Automatically close program when frame is closed.
        contactInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contactInfoFrame.setTitle("Contact Info");
        contactInfoFrame.setSize(800, 500);
        contactInfoFrame.setLocationRelativeTo(null);
        contactInfoFrame.add(contactInfoPanel);

        //Create new labels, text boxes, and buttons for usernames and passwords
        lblFullName = new JLabel("Enter Full Name: ");
        lblEmail = new JLabel("Enter Email: ");
        lblPhoneNumber = new JLabel("Enter Phone Number: ");
        confirmButton = new JButton("Confirm");

        //Creating text fields that will take in a user input
        txtFullName = new JTextField(15);
        txtFullName.setMaximumSize(txtFullName.getPreferredSize());
        txtEmail = new JTextField(15);
        txtEmail.setMaximumSize(txtEmail.getPreferredSize());
        txtPhoneNumber = new JTextField(15);
        txtPhoneNumber.setMaximumSize(txtPhoneNumber.getPreferredSize());

        //Place each object in the grid layout by giving c x and y coordinates
        c.gridx = 0;
        c.gridy = 0;
        contactInfoPanel.add(lblFullName, c);
        c.gridx = 1;
        contactInfoPanel.add(txtFullName, c);
        c.gridx = 0;
        c.gridy = 1;
        contactInfoPanel.add(lblEmail, c);
        c.gridx = 1;
        contactInfoPanel.add(txtEmail, c);
        c.gridx = 0;
        c.gridy = 2;
        contactInfoPanel.add(lblPhoneNumber, c);
        c.gridx = 1;
        contactInfoPanel.add(txtPhoneNumber, c);
        c.gridx = 1;
        c.gridy = 3;
        contactInfoPanel.add(confirmButton, c);

        //Making the confirm button have action when it is pressed
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Gets the text from the text boxes and holds it in their respective strings
                fullName = txtFullName.getText();
                email = txtEmail.getText();
                phoneNumber = txtPhoneNumber.getText();

                boolean validName = false;
                boolean validEmail = false;
                boolean validPhoneNumber = false;

                if (fullName.length() > 0 && fullName.contains(" ")) {
                    validName = true;
                }
                else {
                    JOptionPane.showMessageDialog(contactInfoFrame, "Invalid name.");
                }

                if (email.contains("@") && (email.contains(".com") || email.contains(".ca"))) {
                    validEmail = true;
                }
                else {
                    JOptionPane.showMessageDialog(contactInfoFrame, "Invalid email.");
                }

                if (phoneNumber.length() == 10) {
                    for (int x = 0; x < 10; x++) {
                        if ((int) phoneNumber.charAt(x) >= 48 && (int) phoneNumber.charAt(x) <= 57) {
                            if (x == 9) {
                                validPhoneNumber = true;
                            }
                        } 
                        if ((int) phoneNumber.charAt(x) < 48 || (int) phoneNumber.charAt(x) > 57) {
                            JOptionPane.showMessageDialog(contactInfoFrame, "Invalid phone number.");
                            break;
                        }
                    }
                }

                if (validEmail && validPhoneNumber && validName) {
                    //Creates new user object
                    user = new User();
                    //Calls the addContactInfo method to add all of their info onto their user file
                     user.addContactInfo(username, password, fullName, email, phoneNumber);

                    //Adds a pop up that tells the user that the information has been added
                    JOptionPane.showMessageDialog(contactInfoFrame, "Your contact information has been added.");
                    //Move to next window if login successful
                    //Pass username to be used in program as identifier of each user
                    GUI2 gui2 = new GUI2(username);
                    gui2.setVisible(true);
                    close();
                }
            }
        });
    }
    /*This method sets the frame to visible */
    public void show() {
        contactInfoFrame.setVisible(true);
    }

    /*This method sets the frame to invisible */
    public void close() {
        contactInfoFrame.setVisible(false);
    }
}
