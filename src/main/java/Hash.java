import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rondy on 14/04/2017.
 */
public class Hash {

    public static String md5(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        BigInteger hash = new BigInteger(1, md.digest());
        String str = hash.toString(16);
        return str;
    }

    public static String sha1(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(value.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }
}
