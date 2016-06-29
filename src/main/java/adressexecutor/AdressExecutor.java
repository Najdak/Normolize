package adressexecutor;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dfyz on 29.06.2016.
 */
public class AdressExecutor {

    public static List<String> input = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input.add("Del Mar Photonics, Inc. 4119 Twilight Ridge San Diego, CA 92130 tel: (858) 876-3133 fax: (858) 630-2376 Skype: delmarphotonics e-mail: ruksineanu@gmail.com");
        input.add("DEL MAR PHOTONICS 4119 Twilight Ridge San Diego, CA 92130 USA Tel::(858) 876-3133 Fax::(858) 630-2376 E-mail::optics@dmphotonics. com");
        input.add("DEL MAR PHOTONICS INC. 4119 Twilight Ridge San Diego, CA 92130 United States. Phone: (858) 876-3133 Fax: (858) 630-2376. Visit website. Product");
        input.add("Del Mar Photonics, Inc. product portfolio includes ultrafast laser oscillators and amplifiers based on Ti:Sapphire, Cr:Forsterite, Er- and Yb- doped fibers;");
        input.add("Learn about working at Del Mar Photonics. Join LinkedIn today for free. See who you know at Del Mar Photonics, leverage your professional network, ");
        Pattern adressREGEX = Pattern.compile("\\d{2,5}$");
        // Load the model file downloaded from OpenNLP
        // http://opennlp.sourceforge.net/models-1.5/
        TokenNameFinderModel loc = new TokenNameFinderModel(new File("en-ner-location.bin"));
        // Create a NameFinder using the model
        NameFinderME locF = new NameFinderME(loc);

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;

        for (int i = 0; i < input.size(); i++){
            String sentence = input.get(i);
            // Split the sentence into tokens
            String[] tokens = tokenizer.tokenize(sentence);
            // Find the names in the tokens and return Span objects
            Span[] locations = locF.find(tokens);
            List<String> location = (Arrays.asList(Span.spansToStrings(locations, tokens)));
            if (location != null && location.size() > 0) {
                sentence = sentence.substring(0, sentence.indexOf(location.get(0).toString())).trim();
                Matcher adressMatcher = adressREGEX.matcher(sentence);
                String adress = "";
               if (adressMatcher.find()) {
                   adress = adressMatcher.group();
                   adress += " " + location.toString();
                   adress = adress.replaceAll("[(\\[)|(\\])|,]", "");
               }
                System.out.println("Результат с " + ++i + " сниппета: " + adress);

            }
        }
        System.out.println("FINISH");

    }
}
