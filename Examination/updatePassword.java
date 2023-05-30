package examination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class updatePassword extends JFrame implements ActionListener{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton changePasswordButton;
    
    public updatePassword() {
        setTitle("Change Password Form");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        changePasswordButton = new JButton("Change Password");
        
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(changePasswordButton);
        
        changePasswordButton.addActionListener(this);
        
        add(panel);
        setVisible(true);
    }
    public static void main(String[] args) {
        new updatePassword();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
                String newPassword = String.valueOf(passwordField.getPassword());

                if (validateUsername(username)) {
                    boolean isPasswordUpdated = updatePassword(username, newPassword);
                    if (isPasswordUpdated) {
                        JOptionPane.showMessageDialog(null, "Password updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update the password");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username");
                }
            }

    private boolean validateUsername(String username) {
        String jdbcurl = "jdbc:mysql://localhost:3306/examination";
        String dbusername = "root";
        String dbpassword = "";
        
        try(Connection conn = DriverManager.getConnection(jdbcurl, dbusername, dbpassword)) {
            String query = "SELECT username FROM login WHERE username = '" + username + "'";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next(); // Return true if a row is found, indicating valid username
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error validating username");
        }
        return false;
    }

    private boolean updatePassword(String username, String newPassword) {
        String jdbcurl = "jdbc:mysql://localhost:3306/examination";
        String dbusername = "root";
        String dbpassword = "";
        try(Connection conn = DriverManager.getConnection(jdbcurl, dbusername, dbpassword)) {
            String query = "UPDATE login SET password = '" + newPassword + "' WHERE username = '" + username + "'";
            PreparedStatement statement = conn.prepareStatement(query);
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected > 0; 
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating password");
        }
        return false;
    }
    
}
