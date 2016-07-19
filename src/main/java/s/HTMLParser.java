package s;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vladimir on 11.07.16.
 */
public class HTMLParser {

        public static void main(String[] args) {
            try {
                Parser parser = new Parser("http://www.alliance-bags.ru/catalog.php?tov=576");
                parser.setEncoding("windows-1251");

                NodeFilter atrb1 = new TagNameFilter("a");
                NodeList nodeList = parser.parse(atrb1);

                for(int i=0; i<nodeList.size(); i++) {
                    Node node = nodeList.elementAt(i);
                    System.out.println(node.toHtml());
                }

            } catch (ParserException e) {
                e.printStackTrace();
            }
        }
    }
