import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.*;
public class Harness {


    public static void main(String[] args) {
        String input= "Del Mar Photonics, Inc. 4119 Twilight Ridge San Diego, CA 92130 tel: (858) 876-3133 fax: (858) 630-2376 Skype: delmarphotonics e-mail: ruksineanu@gmail.com delmar@delmar.ru";
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        Set<String> emails = new HashSet<>();
        while(matcher.find()) {
            System.out.println("I found '"+matcher.group()+"' starting at index "+matcher.start()+" and ending at index "+matcher.end()+".");
            emails.add(matcher.group());
        }
        for (String email : emails) {
            if (input.contains(email)) {
                input = input.replace(email, "");
            }
        }
        System.out.println(emails);
        System.out.println(input);
    }
}
