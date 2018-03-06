
package javamysqlloginsystem;


public class JavaMySQLLoginSystem 
{

  
    public static void main(String[] args) 
    {        
        LoginGUI login = new LoginGUI();
        for(;;)
        {
            if(login.getLoginSuccess())
            {
                System.out.println("User " + login.getId() + " signed in");
                break;
            }
            else
            {
                System.out.println("No Login");
            }
        }
    }
    
}
