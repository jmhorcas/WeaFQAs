package encryption;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	private static final String ALGO = "RSA";
    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
    private static Cipher c;
    
    public RSA() throws Exception {
    	// Get an instance of the RSA key generator
    	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    	// Generate the keys — might take sometime on slow computers
    	KeyPair myPair = kpg.generateKeyPair();
         c = Cipher.getInstance(ALGO);
         c.init(Cipher.ENCRYPT_MODE, myPair.getPublic());
    }    
    
    public String encrypt(String Data) throws Exception {
       /* Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);*/
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception {
       /* Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);*/
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}

}
