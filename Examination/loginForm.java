
package examination;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class loginForm extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, exitButton;
    
    public loginForm() {
        setTitle("Login Form");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");
        JLabel forgetPasswordLabel = new JLabel("Forget Password ?");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(exitButton);
        panel.add(loginButton);
        panel.add(forgetPasswordLabel);
        

        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        forgetPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        forgetPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                updatePassword up = new updatePassword();
                up.setVisible(true);
            }
        });

        add(panel);
        setVisible(true);
    }
    public static void main (String args[]){
        new loginForm();
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                Examination ex1 = new Examination();
                ex1.setVisible(true);
                this.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
    
    private boolean authenticate(String username,String password){
        String jdbcurl = "jdbc:mysql://localhost:3306/examination";
        String dbusername = "root";
        String dbpassword = "";
        
        try (Connection conn = DriverManager.getConnection(jdbcurl, dbusername, dbpassword)){
            String query="select * from login where username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs =statement.executeQuery();
            return rs.next();
            
        }catch(Exception e){
            e.printStackTrace();
        }
                
       return false; 
    }
}