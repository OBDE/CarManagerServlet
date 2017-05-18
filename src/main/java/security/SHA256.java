package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class SHA256
{
    public static String generateHash(String plain)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plain.getBytes());
            String hex = new HexBinaryAdapter().marshal(digest);
            return hex;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean checkHash(String plain, String hash)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plain.getBytes());
            String hex = new HexBinaryAdapter().marshal(digest);
            return hex.equals(hash);
        }
        catch (NoSuchAlgorithmException ex)
        {
            return false;
        }
    }
}