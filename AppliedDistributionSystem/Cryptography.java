// ----- Imported packages -----

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

// ----- Cryptography class declaration -----

public class Cryptography 
{
    // ----- KeyPair method declared -----
    
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException 
    {
        final int keySize = 2048;  // Int variable for declaring the KeySize
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // Getting the instance of RSA using key pair generator
        
        keyPairGenerator.initialize(keySize); // Initialization of the KeySize
        
        return keyPairGenerator.genKeyPair(); // returning the generated key pair 
    }
    
    // ----- Encrypt method declaration -----

    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception 
    {
        // ----- Encryption mode performation -----
        
        Cipher cipher = Cipher.getInstance("RSA"); // Cipher object declared
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); //Encrypt using public key

        return cipher.doFinal(message.getBytes()); // returning the encrypted messages
    }

    // ----- decrypt method declaration -----
    
    public static byte[] decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception 
    {
        // ----- Decryption mode performation -----
        
        Cipher cipher = Cipher.getInstance("RSA"); // Cipher object declared
        cipher.init(Cipher.DECRYPT_MODE, privateKey); //decrypt using private key

        return cipher.doFinal(encrypted); // returning the decrypted key
    }
    
} // End Cryptography class
