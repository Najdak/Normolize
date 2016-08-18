import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import s.*;

/**
 * Created by vladimir on 6/4/16.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String s = "/fave-controller-music.html";
        String spl = s.substring(s.lastIndexOf("-")+1, s.lastIndexOf("."));
        System.out.println(spl);
        System.out.println(s.replace("b", "t"));
        float kToken = (float) 7 / (float) 10;
        LOGGER.info(" dad" + kToken);
        System.out.println(String.format("%.3f%n", kToken));
       Set<String> infoTokens = normalizeWords(StrUtils.getTokens("bouches-du-rhone, metropolitan, provence-alpes-cote, marseille, bouches-du-rhône, d'azur, france, provence-alpes-côte"));
       // Set<String> infoTokens = normalizeWords(StrUtils.getTokens("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ"));

        LOGGER.info(infoTokens);
    }

    public static Set<String> normalizeWords(Set<String> words) {
        Set<String> result = new HashSet<>();
        if ((words != null) && words.size() > 0) {
            for (String word : words) {
                result.add(StrUtils.normalizeNFD(word));
               // result.add(StrUtils.normalizeASCII(word));
              //result.add(StrUtils.normalizeText(word));
            }
            result.addAll(words);
        }
        return result;
    }

}
