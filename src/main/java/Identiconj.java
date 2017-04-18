import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rondy on 14/04/2017.
 */
public class Identiconj {

    public void generate(String value) throws IOException, NoSuchAlgorithmException {
        String hash = Hash.sha1(value);
        Painter painter = new Painter(value, hash);
        painter.paint();


    }

}
