package javamysqlloginsystem;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author daviddryburgh
 */
public class RegisterGUI 
{
    private JFrame window = new JFrame();
    private JLabel usernameLabel = new JLabel("Username:");
    private JTextField usernameTextfield = new JTextField(50);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordField = new JPasswordField(50);
    private JLabel confirmPasswordLabel = new JLabel("Retype:");
    private JPasswordField confirmPasswordField = new JPasswordField(50);
    private JButton registerButton = new JButton("Register");
    private JLabel errorLabel = new JLabel("");
    
    public RegisterGUI(){run();}
    
    private void run()
    {
        createGUI();
    }
    
    private void createGUI()
    {
        window.getContentPane().setLayout(null);
	window.setSize(new Dimension(300, 400));
	window.setTitle("Register");
        
        usernameLabel.setBounds(10,10,100,20);
        window.getContentPane().add(usernameLabel);
        usernameTextfield.setBounds(90, 10, 200, 20);
        window.getContentPane().add(usernameTextfield);
        
        passwordLabel.setBounds(10,50,100,20);
        window.getContentPane().add(passwordLabel);
        passwordField.setBounds(90, 50, 200, 20);
        window.getContentPane().add(passwordField);
        
        confirmPasswordLabel.setBounds(10,90,100,20);
        window.getContentPane().add(confirmPasswordLabel);
        confirmPasswordField.setBounds(90, 90, 200, 20);
        window.getContentPane().add(confirmPasswordField);
        
        registerButton.setBounds(10, 130, 100, 50);
        window.getContentPane().add(registerButton);
        
        errorLabel.setBounds(10, 200, 250, 20);
        window.getContentPane().add(errorLabel);
        
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                registerUser(usernameTextfield.getText(), passwordField.getText(), confirmPasswordField.getText());
            }
        
        });
    }
    
    private void registerUser(String username, String password, String retype)
    {
        if(password.equals(retype))
        {
            try
		{

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EnterpriseComputing?user=root&password=Scotland1");

			Statement statement = conn.createStatement();

			String update = "INSERT INTO users (username, password) VALUES ('"+username+"', '"+password+"');";

			statement.executeUpdate(update);

			statement.close();

			conn.close();
			System.out.println("new user registered!");
		}
		catch (ClassNotFoundException cnf)
		{
			System.err.println("Could not load driver");
			System.err.println(cnf.getMessage());
			System.exit(-1);
		}
		catch (SQLException sqe)
		{
			System.err.println("Error performing SQL Update");
			System.err.println(sqe.getMessage());
			System.exit(-1);
		}
            
            window.setVisible(false);
        }
        else
        {
            errorLabel.setText("Error! Passwords don't match");
        }
    }
}
