package maps;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by vladimir on 17.06.16.
 */
public class openNLP {
    public static void main(String[] args) throws InvalidFormatException, IOException {


       /* String[] sentences = {
                "If President John F. Kennedy, after visiting France in 1961 with his immensely popular wife,"
                        + " famously described himself as 'the man who had accompanied Jacqueline Kennedy to Paris,'"
                        + " Mr. Hollande has been most conspicuous on this state visit for traveling alone.",
                "Mr. Draghi spoke on the first day of an economic policy conference here organized by"
                        + " the E.C.B. as a sort of counterpart to the annual symposium held in Jackson"
                        + " Hole, Wyo., by the Federal Reserve Bank of Kansas City. "};*/
        String[] sentences = {
                "Moscow Russia",
                "Del Mar Photonics, Inc. 4119 Twilight Ridge San Diego, CA 92130 tel: (858) 876-3133 fax: (858) 630-2376 Skype: delmarphotonics e-mail: ruksineanu@gmail.com",
                "DEL MAR PHOTONICS 4119 Twilight Ridge San Diego, CA 92130 USA Tel::(858) 876-3133 Fax::(858) 630-2376 E-mail::optics@dmphotonics. com",
                "DEL MAR PHOTONICS INC. 4119 Twilight Ridge San Diego, CA 92130 United States. Phone: (858) 876-3133 Fax: (858) 630-2376. Visit website. Product",
                "Del Mar Photonics, Inc. product portfolio includes ultrafast laser oscillators and amplifiers based on Ti:Sapphire, Cr:Forsterite, Er- and Yb- doped fibers;",
                "Learn about working at Del Mar Photonics. Join LinkedIn today for free. See who you know at Del Mar Photonics, leverage your professional network, ",
                //VERSACE HOTEL DUBAI
                "Welcome to Palazzo Versace. One of Dubai’s Leading 5 Star Fashion Hotels. Experience luxury & fashion on Dubai’s creek",
                "Use 16 real guest reviews to book Palazzo Versace Dubai, Dubai with confidence. Earn free nights & get our Best Price Guarantee on Palazzo Versace",
                "12 jobs with Palazzo Versace Hotel Dubai to view and apply for now with Catererglobal. com",
                "Reminiscent of a 16th century Italian Palace, Palazzo Versace Dubai features a high ceiling entrance, landscaped gardens, and a range of",
                "Palazzo Versace Dubai, Dubai: See 122 traveller reviews, 243 user photos and best deals for Palazzo Versace Dubai, ranked #96 of 536 Dubai hotels,",
                //2640 Soderblom Ave in San Diego
                "View detailed information and reviews for 2640 Soderblom Ave in San Diego, ",
                "Includes Mission Bay Montessori Academy Reviews, maps & directions to Mission Bay Montessori Academy in San Diego and more from Yahoo US",
                "Mission Bay Montessori Academy 2640 Soderblom Ave. San Diego, CA 92122 858-457-5895 Dates: Session 1: June 28 – July 23 (closed July 5th)",
                "We are a traditional Montessori school located in beautiful San Diego, CA with programs for children in Preschool . . . 2640 Soderblom Ave San Diego",
                "Learn more about MISSION BAY MONTESSORI ACADEMY, a school located in San Diego, . . . 2640 SODERBLOM AVE SAN DIEGO",
                "Mission Bay Montessori Academy. . . . San Diego schools — Mission Bay Montessori Academy is located at 2640 Soderblom Ave, San Diego CA 92122.",
                };

        // Load the model file downloaded from OpenNLP
        // http://opennlp.sourceforge.net/models-1.5/
        TokenNameFinderModel loc = new TokenNameFinderModel(new File("en-ner-location.bin"));
        TokenNameFinderModel org = new TokenNameFinderModel(new File("en-ner-organization.bin"));
        TokenNameFinderModel pers = new TokenNameFinderModel(new File("en-ner-person.bin"));

        // Create a NameFinder using the model
        NameFinderME locF = new NameFinderME(loc);
        NameFinderME orgF = new NameFinderME(org);
        NameFinderME persF = new NameFinderME(pers);

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;

        for (String sentence : sentences) {

            // Split the sentence into tokens
            String[] tokens = tokenizer.tokenize(sentence);

            // Find the names in the tokens and return Span objects
            Span[] locations = locF.find(tokens);
            Span[] organizations = orgF.find(tokens);
            Span[] persons = persF.find(tokens);
            System.out.print("Локации: " + Arrays.toString(Span.spansToStrings(locations, tokens)));
            System.out.println(" Организации: " + Arrays.toString(Span.spansToStrings(organizations, tokens)));
        }
    }
}