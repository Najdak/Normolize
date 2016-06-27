import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import s.*;

/**
 * Created by vladimir on 6/4/16.
 */
public class Main {


    public static void main(String[] args) {

        float kToken = (float) 7 / (float) 10;
        System.out.printf("%.3f%n", kToken);
        System.out.println(String.format("%.3f%n", kToken));
       Set<String> infoTokens = normalizeWords(StrUtils.getTokens("bouches-du-rhone, metropolitan, provence-alpes-cote, marseille, bouches-du-rhône, d'azur, france, provence-alpes-côte"));
       // Set<String> infoTokens = normalizeWords(StrUtils.getTokens("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ"));
        System.out.println(infoTokens);
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
