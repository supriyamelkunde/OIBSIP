package examination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Registration extends JFrame implements ActionListener {
    private JTextField firstnameField,lastnameField,usernameField;
    private JPasswordField passwordField;
    private JButton registrationButton;
    
    public Registration() {
        setTitle("Registration Form");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        
        JLabel firstnameLabel = new JLabel("Firstname:");
        JLabel lastnameLabel = new JLabel("Lastname:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        
        firstnameField = new JTextField();
        lastnameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        registrationButton = new JButton("Register Here");
        
        panel.add(firstnameLabel);
        panel.add(firstnameField);
        panel.add(lastnameLabel);
        panel.add(lastnameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(registrationButton);
        
        registrationButton.addActionListener(this);
        add(panel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Registration();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        
        if (registerUser(firstname,lastname,username,password)) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            loginForm obj = new loginForm();
            obj.setVisible(true);
            this.setVisible(false);
        }
        else {
                JOptionPane.showMessageDialog(this, "Registration Unsuccessful.");
            }
    }

    private boolean registerUser(String firstname, String lastname, String username, String password) {
        String jdbcurl = "jdbc:mysql://localhost:3306/examination";
        String dbusername = "root";
        String dbpassword = "";
        
        try (Connection conn = DriverManager.getConnection(jdbcurl, dbusername, dbpassword)){
            String query = "INSERT INTO login (firstname, lastname, username, password) VALUES ('" + firstname + "','" + lastname + "','" + username + "', '" + password + "')";
            PreparedStatement statement = conn.prepareStatement(query);
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
