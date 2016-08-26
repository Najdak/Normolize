import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.*;

import static java.util.Arrays.asList;

public class Tests {
    private static Logger LOGGER = Logger.getLogger(Tests.class);
    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 20; i++) {
            System.out.println("Счетчик "+ x++);
        }
        Map<Integer, String> map = new TreeMap<>();
        Integer i = 3;
        map.put(3, "hui");
        map.put(6, "pizda");
        map.put(1, "vagaina");
        System.out.println(map.containsKey(3));
        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
            System.out.println(integerStringEntry.getKey() + " "+integerStringEntry.getValue());
        }
        String s="I love this phone, its super fast and there's so much new and cool things with jelly bean....but of recently I've seen some bugs.";
        LOGGER.info(s);
        Set<String> aa = Tests.deleteStopWords(s);
        System.out.println(aa);
        Map<String, Object> returnMap = returnMap("vasea");
        System.out.println(returnMap);
        LinkedList<String> stringsList = (LinkedList<String>) returnMap.get("map");


        String[] itfqs = s.split("\\s{1,2}|,\\s");
        List<String> infoTokensForQueryScore = new ArrayList<>(Arrays.asList(itfqs));

        System.out.println(new Date().toString());
    }

    /**
     * Delete all Stop Words from String
     * @param s edited line
     * @return  List<String> without StopWords
     */
    public static Set<String> deleteStopWords(String s){
        Set<String> stopwords = new HashSet<>(Arrays.asList("a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"));
        Set<String> listOfStrings = new TreeSet<>(asList(s.split("\\s{1,3}|,\\s")));
        listOfStrings.removeAll(stopwords);
       // StringUtils.join(listOfStrings, " ");

        return listOfStrings;
    }
    public static Map<String, Object> returnMap(String s){
        Map<String, Object> result = new HashMap<>();
List<String> list = new LinkedList<>();
        list.add("sdads");
        list.add("sdadsdas");
        list.add("sdadfdsgs");
        list.add("sdadgdfgdfs");
        Integer i = 4777777;
        String s1 = s;
        result.put("Integer", i);
        result.put("String", s);
        result.put("map", list);
        return result;
    }

}
