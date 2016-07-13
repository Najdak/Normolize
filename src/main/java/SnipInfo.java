import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.*;

public class SnipInfo {

    public static List<String> textTokens = new ArrayList<>();

    // System.out.println("I found '" + matcher.group() + "' starting at index " + matcher.start() + " and ending at index " + matcher.end() + ".");


    public static void main(String[] args) {
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
        textTokens.add("IZSEARCH - write and read reviews and find this brand information for products/services associated with the IZSEARCH ... IZSEARCH, INC. Carlsbad, CA 92008. ");
        Pattern e_mail = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Pattern tel = Pattern.compile("tel([:]{1,2})\\(*\\+*[1-9]{0,3}\\)*-*[1-9]{0,3}[-. /]*\\(*[2-9]\\d{2}\\)*[-. /]*\\d{3}[-. /]*\\d{4} *e*x*t*\\.* *\\d{0,4}", Pattern.CASE_INSENSITIVE);
        Pattern fax = Pattern.compile("fax([:]{1,2})\\(*\\+*[1-9]{0,3}\\)*-*[1-9]{0,3}[-. /]*\\(*[2-9]\\d{2}\\)*[-. /]*\\d{3}[-. /]*\\d{4} *e*x*t*\\.* *\\d{0,4}", Pattern.CASE_INSENSITIVE);
        Pattern number = Pattern.compile("\\(*\\+*[1-9]{0,3}\\)*-*[1-9]{0,3}[-. /]*\\(*[2-9]\\d{2}\\)*[-. /]*\\d{3}[-. /]*\\d{4} *e*x*t*\\.* *\\d{0,4}", Pattern.CASE_INSENSITIVE);
        Pattern skype = Pattern.compile("[S|s]kype:[\\s]{1,5}[a-z0-9]{1,15}", Pattern.CASE_INSENSITIVE);

        List<Map<String, List<String>>> result = new ArrayList<>();
      //  Map<Integer, Map<String, List<String>>> result2 = new HashMap<>();
        for (int i = 0; i < textTokens.size(); i++){
            String textToken = textTokens.get(i);
            Map<String, List<String>> snippet = new HashMap<>();
            List<String> emails = new ArrayList<>();
            List<String> telephones = new ArrayList<>();
            List<String> faxes = new ArrayList<>();
            List<String> skypes = new ArrayList<>();

            //emails
            Matcher matcher = e_mail.matcher(textToken);
            while (matcher.find()) {
                emails.add(matcher.group());
            }
            //tel
            matcher = tel.matcher(textToken);
            while (matcher.find()) {
                telephones.add(matcher.group());
            }
            //fax
            matcher = fax.matcher(textToken);
            while (matcher.find()) {
                faxes.add(matcher.group());
            }
            if (telephones.size() < 1 && faxes.size() < 1) {
                matcher = number.matcher(textToken);
                while (matcher.find()) {
                    telephones.add("Tel: "+matcher.group());
                }
            }
            //skype
            matcher = skype.matcher(textToken);
            while (matcher.find()) {
                skypes.add(matcher.group());
            }

            if (!skypes.isEmpty()){snippet.put("skypes", skypes);}
            if (!telephones.isEmpty()){snippet.put("telephones", telephones);}
            if (!faxes.isEmpty()){snippet.put("faxes", faxes);}
            if (!emails.isEmpty()){snippet.put("emails", emails);}
            if (!snippet.isEmpty()){result.add(snippet);}
           // if (!snippet.isEmpty()){result2.put(i+1, snippet);}


        }
        Map<String, List<String>> hit = new HashMap<>();
        int max = 0;
        for (Map<String, List<String>> stringListMap : result) {
            if (stringListMap.size()>max){
                max = stringListMap.size();
                hit = Collections.synchronizedMap(stringListMap);
            }
        }
        for (Map<String, List<String>> stringListMap : result) {
            System.out.println("____________________________\n"+" Сниппет ");
            for (List<String> list : stringListMap.values()) {
                for (String s : list) {
                    System.out.println("           " + s);
                }
            }
        }

        System.out.println("Самый релевантный резульат: " + hit.toString());
    }
}
