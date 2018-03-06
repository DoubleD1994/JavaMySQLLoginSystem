
package javamysqlloginsystem;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author daviddryburgh
 */
public class LoginGUI extends JComponent
{
    private JFrame window = new JFrame();
    private JLabel usernameLabel = new JLabel("Username:");
    private JTextField usernameTextfield = new JTextField(50);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordField = new JPasswordField(50);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");
    private int userId;
    private boolean loginSuccess;
    
    public LoginGUI(){run();}
    
    private void run()
    {
        createGUI();
    }
    
    private void createGUI()
    {
        window.getContentPane().setLayout(null);
	window.setSize(new Dimension(300, 200));
	window.setTitle("Login System");
        
        usernameLabel.setBounds(10,10,100,20);
        window.getContentPane().add(usernameLabel);
        usernameTextfield.setBounds(90, 10, 200, 20);
        window.getContentPane().add(usernameTextfield);
        
        passwordLabel.setBounds(10,50,100,20);
        window.getContentPane().add(passwordLabel);
        passwordField.setBounds(90, 50, 200, 20);
        window.getContentPane().add(passwordField);
        
        loginButton.setBounds(10, 80, 100, 50);
        window.getContentPane().add(loginButton);
        registerButton.setBounds(120, 80, 100, 50);
        window.getContentPane().add(registerButton);
        
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(login(usernameTextfield.getText(), passwordField.getText()))
                {
                    setLoginSuccess(true);
                }
            }
            
        });
        
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                register();
            }
        
        });
    }
    
    private boolean login(String username, String password)
    {
        try
	{
            boolean b = false;
            
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EnterpriseComputing?user=root&password=Scotland1");

            Statement statement = conn.createStatement();

            String query = "SELECT * FROM users";

            ResultSet results = statement.executeQuery(query);

            while (results.next())
            {
                String id = results.getString("id");
		String un = results.getString("username");
		String pw = results.getString("password");
                
                if(username.equals(un) && password.equals(pw))
                {
                    b = true;
                    
                    setId(Integer.parseInt(id));
                }
            }
            statement.close();
            conn.close();
            return b;
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
        return false;
    }
    
    private void register()
    {
        RegisterGUI register = new RegisterGUI();
    }
    
    public boolean getLoginSuccess()
    {
        return loginSuccess;
    }
    
    public void setLoginSuccess(boolean b)
    {
        loginSuccess =  b;
    }
    
    public int getId()
    {
        return userId;
    }
    
    public void setId(int id)
    {
        userId = id;
    }
}
