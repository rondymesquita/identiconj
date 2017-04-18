import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rondy on 14/04/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Identiconj identiconj = new Identiconj();
        identiconj.generate("Fulano");
        identiconj.generate("Rondy");
        identiconj.generate("RondyMesquita");
        identiconj.generate("rondymesquita");
    }


}
