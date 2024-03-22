// ------ Imported packages -----
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// ----- Compute client class declaration -----

public class ComputeClient 
{
    public static void main(String[] args)
    {
        // ----- object instance declaration -----
        
        Socket s = null; // Socket object
        Scanner inputMain=new Scanner(System.in); // Scanner object for inputs
        Scanner inputChoice=new Scanner(System.in); // Scanner object for inputs

        // ------ try block declaration ------
        
		try
                {

			int serverPort = 8888; // int variable for server port declaration 

                        // ----- Bind the socket with the server name and port number -----
                        
			s = new Socket("localhost", serverPort);

			//----- Establish inout and output streams -----
                        
			ObjectInputStream in = null;
			ObjectOutputStream out =null;

			out =new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream( s.getInputStream());

                        // ----- Getting public key for encryption -----
                        
                        PublicKey publicKey=(PublicKey)in.readObject();
                        
			// ----- client is engaged with the server until he chooses to exit -----
                        
			while(true)
			{
                            
                        // ----- prompts for user name and password -----
                            
			System.out.println("ENTER USER NAME :");
                        String userName = inputMain.next();
                                                                      
			System.out.println("ENTER PASSWORD :");
                        String password = inputMain.next();
                        
                        String log = userName+":"+password; // merging the username and password using delimeter
                        
                        out.writeObject(Cryptography.encrypt(publicKey,log)); // Encrpyt the data
                        
                        Object obj = in.readObject(); // Send the data to server
                        
                        // ----- executes when the instance is string -----
                        if(obj instanceof String)
                        {
                            System.out.println(((String)obj)); // getting the data
                            
                            // ----- condition occurs when the login success -----
                            
                            if(((String)obj).equals("********** SUCCESS **********"))
                            {
                                // ----- perform the tasks until the user press exit -----
                                
                                while(true)
                                {
                                    // ----- prompts for tasks selection -----
                                    
                                    System.out.println("PLEASE MAKE YOUR SELECTION "+userName);
                                    System.out.println("**************************");
                                    System.out.println("1. Fibonnaci");
                                    System.out.println("2. Factorial");
                                    System.out.println("3. GCD");
                                    System.out.println("4. Exit");
                                    System.out.println("**************************");
                                    
                                    System.out.println("Enter your opinion :");
                                    int opinion = inputChoice.nextInt(); // getting the opinion
                                    
                                    // for exiting the user
                                    
                                    if(opinion == 4)
                                    {
                                        break;
                                    }
                                    
                                    // for performing the fibonacci series
                                    
                                    if(opinion == 1)
                                    {
                                        // ----- prompt for getting the range limit for sequence -----
                                        
                                        System.out.println("Enter the range limit :");
                                        int limit = inputChoice.nextInt(); 
                                        
                                        Task t=new Fibonacci(limit); // sending the limit as a parameter to fibonacci class constructor
                                        out.writeObject(t);
                                        System.out.println( ((Fibonacci)in.readObject()).getResult()); // fetching the result
                                    }
                                    
                                    // for performing the factorial series
                                    
                                    if(opinion == 2)
                                    {
                                        // ----- prompt for getting the range limit for factorial -----
                                        
                                        System.out.println("Enter the range limit :");
                                        int limit = inputChoice.nextInt();
                                        
                                        Task t=new Factorial(limit); // sending the limit as a parameter to factorial class constructor
                                        out.writeObject(t);
                                        System.out.println( ((Factorial)in.readObject()).getResult()); // fetching the result
                                       
                                    }
                                    
                                    // for performing the GCD series
                                    
                                    if(opinion == 3)
                                    {
                                        // ----- prompt for getting the both number to find gcd -----
                                        
                                        System.out.println("Enter the number 1 :");
                                        int num1 = inputChoice.nextInt();
                                        
                                        System.out.println("Enter the number 2 :");
                                        int num2 = inputChoice.nextInt();
                                        
                                        
                                        Task t=new Gcd(num1,num2); // sending the both number as a parameter to gcd class constructor
                                        out.writeObject(t);
                                        System.out.println( ((Gcd)in.readObject()).getResult()); // fetching the result

                                    }
                                                                  
                                }
                            }
                        }
		   }
		}
                
                // ----- catch and finally block declaration for exception -----
                
                catch (UnknownHostException e)
                {
                    System.out.println("Socket:"+e.getMessage());
		}
                catch (EOFException e)
                {
                    System.out.println("EOF:"+e.getMessage());
		}
                catch (IOException e)
                {
                    System.out.println("readline:"+e.getMessage());
		}
                catch (Exception ex) 
                {
                    Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally 
                {
                    if(s!=null) try 
                    {s.close();}
                    catch (IOException e)
                    {System.out.println("close:"+e.getMessage());
                    }
                }
    }
    
}
