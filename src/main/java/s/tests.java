package s;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vladimir on 11.07.16.
 */
public class tests {

    public static void main(String[] args) {
        String s = " aaaaabaa     b    ";
        System.out.println("GO");
        s = s.trim().replaceAll("b", "");

        System.out.println(s);
    }

}
