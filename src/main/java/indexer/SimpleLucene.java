package indexer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import javax.xml.datatype.DatatypeConstants;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vladimir on 26.08.16.
 */
public class SimpleLucene {
	public static final String FILES_TO_INDEX_DIRECTORY = "filesToIndex";
	public static final String INDEX_DIRECTORY = "indexDirectory";

	public static final String FIELD_PATH = "path";
	public static final String FIELD_CONTENTS = "contents";
	public static void main(String[] args) throws IOException {
		List<String> list = readRows("/home/vladimir/Downloads/alternateNames/geoindex.txt");
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		// Store the index in memory:
//		Directory directory = new RAMDirectory();
//		 To store an index on disk, use this instead (note that the
//		 parameter true will overwrite the index in that directory
//		 if one exists):
		 Directory directory = FSDirectory.open(new File("tmp/geoIndex"));
		IndexWriter iwriter = new IndexWriter(directory,analyzer, IndexWriter.MaxFieldLength.UNLIMITED);
		int i = 0;
		for (String text : list) {
			Document doc = new Document();
			Field contentField = new Field("name",text, Field.Store.YES, Field.Index.toIndex(true,true));
			doc.add(contentField);
			iwriter.addDocument(doc);
			System.out.println("Добавлен документ №: "+ ++i);
		}
		iwriter.optimize();
		iwriter.close();


/*	// Now search the index:
	IndexSearcher isearcher = new IndexSearcher(directory);
	// Parse a simple query that searches for "text":
	QueryParser parser = new QueryParser("fieldname", analyzer);
	Query query = parser.parse("text");
	Hits hits = isearcher.search(query);
	assertEquals(1, hits.length() throws IOException

	);
	// Iterate through the results:
	for (int i = 0; i < hits.length(); i++)
	{
		Document hitDoc = hits.doc(i);
		assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
	}
	isearcher.close();
	directory.close();*/
	}

/*	public static void searchIndex(String searchString) throws IOException, ParseException {
		System.out.println("Searching for '" + searchString + "'");
		Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(FIELD_CONTENTS, analyzer);
		Query query = queryParser.parse(searchString);
		Hits hits = indexSearcher.search(query);
		System.out.println("Number of hits: " + hits.length());

		Iterator<Hit> it = hits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get(FIELD_PATH);
			System.out.println("Hit: " + path);
		}

	}*/
public static List<String> readRows(String fileName) {
	List<String> rows = new ArrayList<>();
	try {
		rows.addAll(Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8));
	} catch (IOException e) {
		e.getMessage();
	}
	return rows;
}
}
