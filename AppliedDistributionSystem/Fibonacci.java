
import java.io.Serializable;

// ----- FIBONACCI CLASS DECLARATION IMPLEMETS TASK AND SERIALIZABLE -----

public class Fibonacci implements Task,Serializable
{
    // ----- VARIABLE DECLARATION -----

    int num; // Integer variable declaration for finding the fibonacci series
    String result; // String variable declaration for printing the result
    
    // ----- Parameterized constructor declaration ----- 
    
    Fibonacci(int num) 
    {
        this.num = num; // Setting the value of the num variable from constructor parameterized variable
    }

    // ----- Overrided methods from task interface -----
    
    @Override
    public String getResult() 
    {
        return String.format("%s", result); // returning the result using String.format function 
    }

    @Override
    public void executeTask() 
    {
        int initialNumber = 0; // Integer variable declaration for Setting initial value
        int secondNumber = 1;  // Integer variable declaration for Setting second value
        int finalNumber;       // integer variable declaaration for final variable value
        
        // ----- String builder class declaration -----
        
        StringBuilder setString = new StringBuilder();
        setString.append("0+1+");  // Append the first two initial data in the string
      
        // ----- For loop declaration for calculation -----
        
        for(int i=2;i<num;i++)
        {
            finalNumber = initialNumber+secondNumber; // setting the finalNumber variable in the loop
            
            setString.append(""+finalNumber+"+"); // appending the finalNumber variable in the string
            
            getResult();  // calling getResult method for printing the sequence of the value ever time
            
            initialNumber = secondNumber; // setting the initial number
            
            secondNumber = finalNumber; // setting the second number
            
            result = setString.toString(); // Converting the Integer gcd value to String result using String.vslueOf function
            
        }
    } // End fibonacci class
}
