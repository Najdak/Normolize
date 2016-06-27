package s;

import org.apache.log4j.Logger;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by vladimir on 6/4/16.
 */
public class StrUtils {

    public static Set<String> getTokens(String text) {
        Set<String> res = new LinkedHashSet<>();
        text = text.toLowerCase();
        String[] split = text.split("(,\\s|\\s)");
        for(String el : split){
            if (el.contains("'")){
                el = el.substring(el.lastIndexOf("'")+1); //дохуя крутая тема убирает все что до апострофа
            }
            if(el.length() > 3){
                res.add(el);
            }
            if(res.size() == 20 ){
                break;
            }
        }
        return res;
    }



    public static String normalizeNFD(String text) {
        if (text.trim().isEmpty()) {
            return text;
        }
        String str = java.text.Normalizer.normalize(text.trim(), java.text.Normalizer.Form.NFD);
        str=str.replaceAll("'","");
        return str.replaceAll("[^\\p{L}\\p{Z}\\p{Digit}]", "").replaceAll("[\\p{P}]", "");
    }

    public static String normalizeASCII(String text){
        String result = "";
        if ((text != null) && (!text.isEmpty())){
            result = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        }
        return result;
    }
    /**
     * Выравнивает текст согласно стандарту [Unicode Standard Annex #15]
     *
     * @param text - Строка
     * @return - Выравненный текст
     */
    public static String normalizeText(String text) {
        String result = "";
        if (text != null && !text.isEmpty()) {
            String str = text.trim();
            // str = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD)
            str = str.replaceAll("[\\p{InCombiningDiacriticalMarks}]+", "")
                    .replaceAll("[\\p{InMiscellaneousSymbolsAndPictographs}|\\p{InEmoticons}]+", "")
                    .replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "")
                    .replaceAll("[\\p{InSpecials}]+", "")
                    .replaceAll("\u00AE", "")
                    .replaceAll("\uFFFD", "")
                    .replaceAll("[\u005B|\u005D]", "");
            result = str;
        }
        return result;
    }

}
