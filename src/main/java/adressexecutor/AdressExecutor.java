package adressexecutor;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import opennlp.tools.util.StringList;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
        textTokens.add("USO San Diego - Neil Ash Airport Center. 0 Reviews. 3707 North Harbor Dr, Terminal 1, Lindbergh Field, San Diego, CA 92101; Add Photo. Add Review. Get Directions. Phone");
        textTokens.add("Isearch is open-source text retrieval software first developed in 1994 by Nassib Nassar as part of the Isite Z39. 50 information framework. The project");
        Pattern numREGEX = Pattern.compile("([\\d]{1,4})($)", Pattern.CASE_INSENSITIVE);
        Pattern filterREGEX = Pattern.compile("[^\\w\\s\\d]|(Tel)", Pattern.CASE_INSENSITIVE);
        Pattern addressREGEX = Pattern.compile("([0-9]{3,4})([^0-9]{4}.*)([A-Z]{2}\\s{1,5}[0-9]{5})", Pattern.CASE_INSENSITIVE);
        // Load the model file downloaded from OpenNLP
        // http://opennlp.sourceforge.net/models-1.5/
        TokenNameFinderModel loc = new TokenNameFinderModel(new File("en-ner-location.bin"));
        // Create a NameFinder using the model
        NameFinderME locF = new NameFinderME(loc);

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        List<String> results = new ArrayList<>();
        List<String> regResult = new ArrayList<>();
        for (int i = 0; i < textTokens.size(); i++) {
            String sentence = textTokens.get(i);
            Matcher regAddress = addressREGEX.matcher(sentence);
            while (regAddress.find()){
                regResult.add(regAddress.group());
            }
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
                        Matcher adressMatcher = numREGEX.matcher(sent.trim());
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
        Collections.replaceAll(regResult, ",", "");
        for (String result : results) {
            System.out.println("OpenNLP:        " + result);

        }
        for (String s : regResult) {
            System.out.println("REGEX:          "+ s);
        }    }
    }
