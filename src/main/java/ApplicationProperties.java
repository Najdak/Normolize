import java.io.IOException;
import java.util.Properties;

/**
 * Created by vladimir on 15.08.16.
 */
public class ApplicationProperties {
	public static final String APP_PROPERTIES_FILE = "/application.properties";

	public static String TEXT1 = "";
	public static String TEXT2 = "";

	static {
		Properties properties = new Properties();
		try {
			properties.load(ApplicationProperties.class.getResourceAsStream(APP_PROPERTIES_FILE));

			TEXT1 = properties.getProperty("path1");
			TEXT2 = properties.getProperty("path2");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
