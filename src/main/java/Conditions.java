import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by vladimir on 04.08.16.
 */
public class Conditions {
    public static void main(String[] args) {

        Float integer[] = {7.69F,7.69F,7.69F,7.69F,7.69F,5.77F};

        Float sum = 0F;
        for (int i = 0; i < 10; i++) {
            if (integer.length > i) {
                sum += integer[i];
            }else sum += 0F;
        }
        String query = "dimbrava moldova soska";
        List<String> queryTokens = new ArrayList<>();
        queryTokens.add(query);
        String[] q = query.split("\\s{1,2}|,\\s");
        if (q.length>1){
            for (int i = 0; i < 2; i++) {
                queryTokens.add(q[i]);
            }}

    }
}
