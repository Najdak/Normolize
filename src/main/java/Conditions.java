import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by vladimir on 04.08.16.
 */
public class Conditions {
    public static void main(String[] args) {

        String text1 = ApplicationProperties.TEXT1;

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

    public static List<String> readRows(String fileName) {
        List<String> rows = new ArrayList<>();
        try {
            rows.addAll(Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8));
        } catch (IOException e) {
             e.getMessage();
        }
        return rows;
    }

    public static void writeRowsToFile(String filePath, List<String> rows) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        for(String row : rows) {
            writer.println(row);
        }
        writer.close();
    }
}
