import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladimir on 16.08.16.
 */
public class CSVebatiVBazu {
	public static String user = "postgres";
	public static String password = "Aqpl308E";
	public static String url = "jdbc:postgresql://70.167.8.45:5433/faves_db_new_2?characterEncoding=utf8";
	public static Connection connection;
	public static String EXCEL_PATH = "/home/vladimir/Downloads/Names-_1_.csv";
	static {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws SQLException {
		List<String[]> excel = getFromExcel();

		for (String[] strings : excel) {
			if (strings[4].equals("1")){
				UpdateTitle(strings[1], new Integer(strings[0]));
			}else if (strings[4].equals("0")){
				Delete(new Integer(strings[0]));
			}
		}

		connection.close();

	}

	public static void UpdateTitle(String service_name, Integer id) throws SQLException {
		PreparedStatement stm2 = connection.prepareStatement("SELECT * FROM bangs.bangs WHERE id = ?");
		stm2.setInt(1, id);
		ResultSet rs = stm2.executeQuery();
		rs.next();
		String upsql = "UPDATE bangs.bangs SET service_name = ? where id = ?";
		PreparedStatement stm = connection.prepareStatement(upsql);
		stm.setString(1, service_name);
		stm.setInt(2, id);
		stm.executeUpdate();
		connection.commit();
		stm.close();
		System.out.println("Строка c ID: "+ id + " service_name изменен c [" + rs.getString("service_name")+"] на ["+service_name+"]" );
		stm2.close();
		rs.close();
	}

	public static void Update(String bang_url, Integer id) throws SQLException {
		String upsql = "UPDATE bangs.bangs SET bang_url = ? where id = ?";
		PreparedStatement stm = connection.prepareStatement(upsql);
		stm.setString(1, bang_url);
		stm.setInt(2, id);
		stm.executeUpdate();
		connection.commit();
		stm.close();
		System.out.println(id + "URL UAPDATED");
	}
	public static void Delete(int id) throws SQLException {
		String delsql = "DELETE from bangs.bangs WHERE id = ?";
		PreparedStatement stm = connection.prepareStatement(delsql);
		stm.setInt(1, id);
		boolean chek = stm.execute();
		connection.commit();
		stm.close();
		if (chek){
		System.out.println("Строка c ID: "+ id + " НЕ УДАЛОСЬ УДАЛИТЬ" );
		}else {
			System.out.println("Строка c ID: "+ id + " УДАЛЕНА" );
		}

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

	public static List<String[]> getFromExcel(){
		List<String> lines = readRows(EXCEL_PATH);
		List<String[]> excel = new ArrayList<>();
		for (String line : lines) {
			String[] s = line.split(",");
			for (int i = 0; i < s.length; i++) {
				s[i]=s[i].replaceAll("\"", "").trim();
			}
			excel.add(s);
		}
		return excel;
	}
}
