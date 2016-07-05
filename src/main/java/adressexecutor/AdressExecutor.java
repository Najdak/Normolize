package adressexecutor;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import opennlp.tools.util.StringList;

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

    public static List<String> textTokens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        textTokens.add("Del Mar Photonics, inc. 4119 Twilight Ridge San Diego, CA 92130 tel: (858) 876-3133 fax: (858) 630-2376 skype: delmarphotonics sales@dmphotonics.com");
        textTokens.add("Del Mar Photonics, inc. product portfolio includes ultrafast laser oscillators and amplifiers based on ti:sapphire, cr:forsterite, er- and yb- doped fibers; ... ");
        textTokens.add("Del Mar Photonics 4119 Twilight Ridge | San Diego, ca 92130 usa | tel::(858) 876-3133 fax::(858) 630-2376 e-mail::optics@dmphotonics.com url::www.dmphotonics.com ");
        textTokens.add("Del Mar Photonics, Inc. product portfolio includes ultrafast laser oscillators and amplifiers based on Ti:Sapphire, Cr:Forsterite, Er- and Yb- doped fibers;");
        textTokens.add("Learn about working at Del Mar Photonics. Join LinkedIn today for free. See who you know at Del Mar Photonics, leverage your professional network, ");
        textTokens.add("city of san diego official website the city of san diego's official website offers information and online services for departments, business assistance, job opportunities, attractions, beaches, community services, and more. service line warranty program launches, offered by slwa, the preferred service line warranty partner of the city of san diego' visit the slwa website at www.slwofa.com or call 1-855-261-9830 (toll free) to sign up or get more details. to learn about the partnership with the city, please visit the slwa page.");
        textTokens.add("(800) 682-9680 · 1945 Gardena Ave Suite 100. Glendale, CA 91204 · Web Design, Marketing ... trendsetter! You could be the first review for USA Link System.");
        textTokens.add("5500 Campanile Drive San Diego, CA 92182 Tel: 619-594-5200 Copyright 2016");

        Pattern adressREGEX = Pattern.compile("([\\d]{1,4})($)");
        Pattern filterREGEX = Pattern.compile("[^\\w\\s\\d]|([T|t]el)");
        // Load the model file downloaded from OpenNLP
        // http://opennlp.sourceforge.net/models-1.5/
        TokenNameFinderModel loc = new TokenNameFinderModel(new File("en-ner-location.bin"));
        // Create a NameFinder using the model
        NameFinderME locF = new NameFinderME(loc);

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        List<String> results = new ArrayList<>();

        for (int i = 0; i < textTokens.size(); i++) {
            String sentence = textTokens.get(i);
            // Split the sentence into tokens
            String[] tokens = tokenizer.tokenize(sentence);
            // Find the names in the tokens and return Span objects
            Span[] locations = locF.find(tokens);
         //   System.out.println(Arrays.toString(Span.spansToStrings(locations, tokens)));

            List<String> location = Arrays.asList(Span.spansToStrings(locations, tokens));

            if (locations != null && locations.length > 1) {
                List<String> filtrLocation = new ArrayList<>();
                filtrLocation.addAll(location);
                for (String s : location) {
                    if (s.length() < 3) {
                        filtrLocation.remove(s);
                    } else {
                        Matcher matcher = filterREGEX.matcher(s);
                        if (matcher.find()) {
                            filtrLocation.remove(s);
                        }
                    }
                }
                String address = "";
                for (String fLoc : filtrLocation) {
                    if (fLoc != null) {
                        String sent = sentence.substring(0, sentence.indexOf(fLoc));
                        Matcher adressMatcher = adressREGEX.matcher(sent.trim());
                        if (adressMatcher.find()) {
                            address = adressMatcher.group();
                            address += " " + fLoc;
                            address = address.replaceAll("[(\\[)|(\\])|,]", "");
                            results.add(address.trim());

                        }
                    }
                }


            }
        }
        System.out.println("Найденные адресса: \n" + "-------------------" + results.toString());
    }
    }
