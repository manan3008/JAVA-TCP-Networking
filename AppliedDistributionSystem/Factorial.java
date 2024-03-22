import java.io.Serializable;

// ----- FACTORIAL CLASS DECLARATION IMPLEMETS TASK AND SERIALIZABLE -----

public class Factorial implements Task,Serializable
{

    // ----- VARIABLE DECLARATION -----
    
    int n; // int variable declaration for finding the factorial value of range limit variable
    String result; // String variable declaration for printing the result
    
    // ----- Parameterized constructor declaration ----- 
    
    Factorial(int n)
    {
        this.n = n; // Setting the value of n from constructor parameterized variable
    }
    
    // ----- Overrided methods from task interface -----
    
    @Override
    public String getResult() 
    {
        return String.format("Factorial of %d = %s",n,result); // returning the result using String.format function 
    }
       
    @Override
    public void executeTask() 
    {
        int factNumber = 1; // local Integer variable factNumber declared to store the factorial 
        
        // ----- Calculating the factorial -----
        
        for(int i=1;i<=n;i++) // For loop declaration for performing the calculation
        {
            factNumber = factNumber*i; // Formula for finding the factorial
        }
        result = String.valueOf(factNumber); // Converting the Integer gcd value to String result using String.vslueOf function
    }
} // End factorial class
