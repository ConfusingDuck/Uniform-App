import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
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
    //private JTextField txtPassword;
    private JButton btnLogin;
    private JButton btnSignup;
    private String username;
    private String password;
    private User user;
    private JPasswordField pfPassword;
    private char[] pf;


    public GUI() {

        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 5, 20, 0);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Uniform Marketplace");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        lblUsername = new JLabel("Username (E-Mail): ");
        lblPassword = new JLabel("Password: ");

        txtUsername = new JTextField(15);
        txtUsername.setMaximumSize(txtUsername.getPreferredSize());

        pfPassword = new JPasswordField(15);
        //txtPassword = new JTextField(15);
        //txtPassword.setMaximumSize(txtPassword.getPreferredSize());

        btnLogin = new JButton("Login");
        btnSignup = new JButton("Sign up");

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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = txtUsername.getText();
                pf = pfPassword.getPassword();
                password = String.valueOf(pf);
                System.out.println("Password: " + password);
                user = new User();
                if(user.login(username, password)){
                    JOptionPane.showMessageDialog(frame, "You are logged in.");
                    //Move to next window, program is started
                    //Pass username to be used in program as identifier of each user
                    GUI2 gui2 = new GUI2();
                    gui2.setVisible(true);
                    close();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    //Print "incorrect password"
                    username = "";
                    password = "";
                }
            }
        });

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Signed up + " + txtUsername.getText());
                username = txtUsername.getText();
                pf = pfPassword.getPassword();
                password = String.valueOf(pf);
                user = new User();
                if (user.signUp(username, password)) {
                    JOptionPane.showMessageDialog(frame, "You are now signed up.");
                    //Move to next window, user has signed up
                    //Pas username in program to be used as identifier

                }
                else {
                    JOptionPane.showMessageDialog(frame, "Username already exists.");
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
        txtUsername.setToolTipText("Use e-mail as username");
        txtUsername.setMargin(new Insets(0, 3, 0, 3));

        pfPassword.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        pfPassword.setToolTipText("Enter password here");
        pfPassword.setMargin(new Insets(0, 3, 0, 3));
        //txtPassword.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        //txtPassword.setForeground(Color.BLACK);
        //txtPassword.setToolTipText("Enter password here");
        //txtPassword.setMargin(new Insets(0, 3, 0, 3));

    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }
}
