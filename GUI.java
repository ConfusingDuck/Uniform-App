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
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class GUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JButton btnLogin;
    private JButton btnSignup;
    private String username;
    private String password;
    private User user;
    private JPasswordField pfPassword;
    private char[] pf;

    /*This screen is the login screen and allows the user to sign up or login*/
    public GUI() {

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
        frame.setTitle("Uneed-Form");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        // Create new labels, text boxes, and buttons for usernames and passwords
        lblUsername = new JLabel("Username: ");
        lblPassword = new JLabel("Password: ");

        txtUsername = new JTextField(15);
        txtUsername.setMaximumSize(txtUsername.getPreferredSize());

        pfPassword = new JPasswordField(15);

        btnLogin = new JButton("Login");
        btnSignup = new JButton("Sign up");

        // Place each object in the grid layout by giving c x and y coordinates
        c.gridx = 0;
        c.gridy = 0;
        panel.add(lblUsername, c);
        c.gridy = 1;
        panel.add(lblPassword, c);
        c.gridx = 1;
        panel.add(pfPassword, c);
        c.gridy = 0;
        panel.add(txtUsername, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(btnLogin, c);
        c.gridx = 1;
        panel.add(btnSignup, c);

        // Add an action listener to the login button to perform a task when pressed
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the username and password from their text boxes
                username = txtUsername.getText();
                pf = pfPassword.getPassword();
                // Password field return an array of chars, which we convert to a string
                password = String.valueOf(pf);
                // Create a new user object
                user = new User();
                // Check if the login credentials are valid
                if (user.login(username, password)) {
                    JOptionPane.showMessageDialog(frame, "You are logged in.");
                    // Move to next window if login successful
                    // Pass username to be used in program as identifier of each user
                    GUI2 gui2 = new GUI2(username, user);
                    gui2.show();
                    close();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    username = "";
                    password = "";
                }
            }
        });

        // Create a similar action listener for the sign up button
        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = txtUsername.getText();
                pf = pfPassword.getPassword();
                password = String.valueOf(pf);
                user = new User();
                // There are two types of errors and one success when signing up
                if (user.signUp(username, password) == 1) {
                    JOptionPane.showMessageDialog(frame, "You are now signed up.");
                    // Move to next window, user has signed up successfully
                    // Pass username in program to be used as identifier
                    close();
                    ContactInfo contactInfo = new ContactInfo(username, password);
                    contactInfo.show();
                }
                // If the error type is -1, print the username is already in use
                else if (user.signUp(username, password) == -1) {
                    JOptionPane.showMessageDialog(frame, "Username already in use.");
                    username = "";
                    password = "";
                }
                // If the error type is -2, the username is invalid (contains spaces etc.)
                else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    username = "";
                    password = "";
                }

            }
        });

        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Sans-serif", Font.BOLD, 16));

        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Sans-serif", Font.BOLD, 16));

        txtUsername.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        txtUsername.setForeground(Color.BLACK);
        txtUsername.setToolTipText("Username cannot contain spaces");
        txtUsername.setMargin(new Insets(0, 3, 0, 3));

        pfPassword.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        pfPassword.setToolTipText("Password cannot contain spaces");
        pfPassword.setMargin(new Insets(0, 3, 0, 3));

    }

    /* This method sets the frame to visible */
    public void show() {
        frame.setVisible(true);
    }

    /* This method sets the frame to invisible */
    public void close() {
        frame.setVisible(false);
    }
}
