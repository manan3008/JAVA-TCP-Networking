// ----- IMPORTED PACKAGES -----

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// ----- DATABASE CONNECTION CLASS DECLARATION -----

public class DatabaseConnection 
{
    // ----- Varialbe declaration ------
    
   static final String URL = "jdbc:derby://localhost:1527/Clients"; // Final String variable for storing the url location of database
   static final String USERNAME = ""; // Final String variable for storing the Username of database
   static final String PASSWORD = ""; // Final String variable for storing the Password of database

   java.sql.Connection connection = null; // Java connection declaration which manages connection with databse
   private PreparedStatement queryCheckUser = null; // Prepared statement for query checking the user
   private PreparedStatement queryCheckPassword = null; // Prepared statement for query checking the password
   ResultSet resultSet;
   
   // ----- Default constructor -----
   
   public DatabaseConnection()
   {
       // ----- Try block declaration -----
       
       try
       {
           connection = DriverManager.getConnection( URL ); // Connecting the database
            
           queryCheckUser = connection.prepareStatement(
            "SELECT USERID FROM USERS " );  // Sql query for selecting the username
           
           queryCheckPassword = connection.prepareStatement(
            "SELECT * FROM USERS " );  // Sql query for selecting the username and password
                
       }
       
      // ----- Catch block declaration -----
       
      catch ( SQLException sqlException )
      {
          // ----- called when any exception occurs -----
          
         System.exit( 1 );
         sqlException.printStackTrace();
      }
   }
   
   // ----- Parameterized Boolean method declaration for checking the username -----
   
   public boolean checkUser(String user)
   {
      boolean check = false; // Setting the initial value false 
      
      // ----- Try block declaration -----
      
       try 
       {
           resultSet = queryCheckUser.executeQuery(); // empty string which will be built with all the records from the table
           
           // ----- While loop with .next function to read line in database -----
           
           while( resultSet.next())
           {
               //read first column from the resultset and build the message.
               
               if(user.equals(resultSet.getString(1)))
               {
                   check = true; // If the user find then the check vaue will true
                   break;
               }
           }  
       } 
       
       // ----- Catch block declaration -----
       
       catch (SQLException ex) 
       {
           Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);  
       }
       
       // ----- Finally block declarfation -----
       
       finally
       {
         try
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();
            close();
         } // end catch
      } // end finally
       
    return check; // Returning the check data
    
   }
   
   // ----- Parameterized Boolean method declaration for checking the username and password -----
   
   public boolean checkPassword(String user,String password)
   {
      boolean check = false;  // Setting the initial value false 
      
      // ----- Try block declaration -----
      
       try 
       {
           resultSet = queryCheckPassword.executeQuery();  // empty string which will be built with all the records from the table
            
           // ----- While loop with .next function to read line in database -----
            
           while( resultSet.next())
           {
               //read first and second columns from the resultset and build the message.
               
               if(user.equals(resultSet.getString(1)) && password.equals(resultSet.getString(2)))
               {
                   check = true;  // If username and password matches then the boolean value will set true
                   break;
               }
           }
       } 
       
       // ----- Catch block declaration -----
       
       catch (SQLException ex) 
       {
           Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       // ----- Finally block declaration -----
       
       finally
       {
         try
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();
            close();
         } // end catch
      } // end finally
       
    return check; // Returning the check value
   }
   
   // ----- Close method declaration -----
   
   public void close()
   {
      try
      {
         connection.close(); // Closing the connection using close() method
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch

   } 
} // End class
