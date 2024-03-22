
import java.io.Serializable;

// ----- GCD CLASS DECLARATION IMPLEMETS TASK AND SERIALIZABLE -----

public class Gcd implements Task,Serializable
{
    
    // ----- VARIABLE DECLARATION -----
    
    int n1,n2; // int variable declaration for finding the Gcd value of both variable
    String result; // String variable declaration for printing the result
    
    // ----- Parameterized constructor declaration ----- 
    
    public Gcd( int n1, int n2) 
    {
        this.n1 = n1; // Setting the value of num1 from constructor parameterized variable
        this.n2 = n2; // Setting the value of num2 from constructor parameterized variable
    }
    
    // ----- Overrided methods from task interface -----

    @Override
    public String getResult() 
    {
        return String.format("GCD of given number is : %s", result); // returning the result using String.format function 
    }

    @Override
    public void executeTask() 
    {
        int gcdNumber = 1; // local variable for setting the gcd initial value
        
        for(int i= 1; i<=n1 && i<=n2; i++) // For loop declaration for performing the calculation
        {
            // ----- Calculating the GCD -----
            
            if(n1%i==0 && n2%i==0) // if condition for getting the remainder 0
            {
                gcdNumber = i; // setting the value of GCD
            }
        }
        
        result = String.valueOf(gcdNumber); // Converting the Integer gcd value to String result using String.vslueOf function
       
    }
    
} // End GCD class
