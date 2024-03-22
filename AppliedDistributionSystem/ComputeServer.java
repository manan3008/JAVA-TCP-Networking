// ----- Imported packages -----

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

// ----- Server class declaration -----

public class ComputeServer 
{
    public static void main(String[] args) throws Exception 
    {
        // ----- Try block declaration -----
        
        try
        {
            int serverPort = 8888; // Int variable declaration for server port number
            ServerSocket listenSocket = new ServerSocket(serverPort); // Server socket object declaration
            System.out.println("TCP Server running...");
                        
            KeyPair keyPair = Cryptography.buildKeyPair(); // Keypair object declaration for buildinng key pair
            PublicKey pubKey = keyPair.getPublic(); // Getting public key using key pair method 
            PrivateKey prKey = keyPair.getPrivate(); // Getting private key using key pair method 

            // ----- While condition for running all the time and communicating with clients -----
            
            while(true) 
            {
		Socket clientSocket = listenSocket.accept(); // creates a new thread to deal with each client(Thread-per-connection)
				
		Connection c = new Connection(pubKey,prKey,clientSocket); // calling Connection class 
            }

	} 
        
        // ----- Catch block declaration -----
        
        catch(IOException e) 
        {
            System.out.println("Listen socket:"+e.getMessage());
        }
    }
    
} // End ComputeServer class

// ----- Connection class declaration extending thread class for multiple client process -----

class Connection extends Thread 
{

        // ----- Creating instance of objects -----
    
	ObjectInputStream in; // For input the stream
	ObjectOutputStream out; // For output the stream
	Socket clientSocket; // For communication
        DatabaseConnection db; // For database access
        PublicKey publicKey; // Getting public key for encryption 
        PrivateKey privateKey; // Getting private key for decryption

        // ----- parameterized constructor declaration -----
        
	public Connection (PublicKey pubKey, PrivateKey prKey, Socket aClientSocket) 
        {

            // ----- try block declaration -----
            
		try 
                {
                    // ----- initializing the objects -----
                    
			clientSocket = aClientSocket;
			in = new ObjectInputStream( clientSocket.getInputStream());
			out =new ObjectOutputStream( clientSocket.getOutputStream());
                        db = new DatabaseConnection();
                        publicKey = pubKey;
                        privateKey = prKey;
                         
			this.start(); // Starting the tread process 
                        
                        out.writeObject(publicKey); // encrypting the data
                        
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}

        // ----- run method overrided -----
        
        @Override
	public void run()
        {
            
           // ----- try block declaration -----
            
            try 
            {
                // ----- keep communicating the objects sent from the client ------
                
                while(true)
                {
                      Object obj = in.readObject(); // reading the objects
                      
                      // Condition will get true when receive the instance of byte -----
                      
                      if(obj instanceof byte[])
                      {
                          String m=new String(Cryptography.decrypt(privateKey, (byte[])obj)); // decrypting the data
                          
                          String []w=m.split(":"); // Spliting the data
                                         
                          // ------ Validating the data from database ------
                          
                          if(db.checkUser(w[0])) // Checking the UserID from database
                          {
                                                           
                              if(db.checkPassword(w[0], w[1])) // Checking the UserID and password from database
                              {
                                  out.writeObject("********** SUCCESS **********");
                                  System.out.println("User authenticated");
                                  
                                  
                              }
                              else
                              {
                                  out.writeObject("********** IN VALID PASSWORD **********");
                              }
                          }
                          else
                          {
                              out.writeObject("********** INVALID USER **********");
                          }
                         
                      }
                      
                      // Condition will get true when receive the instance of fibonacci class -----
                      
                      if(obj instanceof Fibonacci)
                      {
                            ((Fibonacci)obj).executeTask();
                            
                             out.writeObject(obj);
                             
                             System.out.println("Fibonacci task received and Computed");
                      }
                      
                      // Condition will get true when receive the instance of Factorial class -----
                      
                      if(obj instanceof Factorial)
                      {
                            ((Factorial)obj).executeTask();
                            
                             out.writeObject(obj);
                             
                             System.out.println("Factorial task received and Computed");
                      }
                      
                      // Condition will get true when receive the instance of GCD class -----
                      
                      if(obj instanceof Gcd)
                      {
                            ((Gcd)obj).executeTask();
                            
                             out.writeObject(obj);
                             
                             System.out.println("GCD task received and Computed");
                      }
                }
            }




		catch (EOFException e)
                {
                    System.out.println("EOF:"+e.getMessage());
		} 
                catch(ClassNotFoundException ex)
                {
                    ex.printStackTrace();
		} 
                catch (IOException ex) 
                {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                { 
                    try 
                    {
                        clientSocket.close();
                    }
                    catch (IOException e)
                    {/*close failed*/}
                }
	}  // End run method  
        
} // End connection class
