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
import java.util.Arrays;
import java.util.List;

/**
 * Created by vladimir on 16.08.16.
 */
public class CSVebatiVBazu {
	public static String user = "postgres";
	public static String password = "Aqpl308E";
	public static String url = "jdbc:postgresql://70.167.8.45:5433/faves_db_new_2?characterEncoding=utf8";
	public static Connection connection;
	public static String EXCEL_PATH = "/home/vladimir/Downloads/Names-_2_.csv";
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
			if (strings[4].equals("0")){
				Delete(new Integer(strings[0]));
			}
			if (strings[4].equals("1") || strings[4].isEmpty()){
				UpdateTitle(strings[1], strings[2], new Integer(strings[0]));
			}
		}

/*		List<Integer> l = new ArrayList<>(Arrays.asList(1266,1043,919,693,1109));
		for (Integer integer : l) {
			UpdateParentID(1266, integer);
		}*/

		connection.close();

	}

	public static void UpdateTitle(String service_name, String bang_url, Integer id) throws SQLException {
		PreparedStatement stm2 = connection.prepareStatement("SELECT * FROM bangs.bangs WHERE id = ?");
		stm2.setInt(1, id);
		ResultSet rs = stm2.executeQuery();
		String upsql = "UPDATE bangs.bangs SET service_name = ?, bang_url = ? where id = ?";
		PreparedStatement stm = connection.prepareStatement(upsql);
		stm.setString(1, service_name);
		stm.setString(2, bang_url);
		stm.setInt(3, id);
		stm.executeUpdate();
		connection.commit();
		stm.close();
		System.out.println("Строка c ID: "+ id + " service_name и bang_url изменен c :"  );
		while (rs.next()){
			System.out.println(rs.getString("service_name"));
			System.out.println(rs.getString("bang_url"));
		}
		System.out.println("На : " + service_name + " и " + bang_url);
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
	public static void UpdateParentID(Integer parenthID, Integer id) throws SQLException {
/*		PreparedStatement stm2 = connection.prepareStatement("SELECT * FROM public.categories WHERE id = ?");
		stm2.setInt(1, id);
		ResultSet rs = stm2.executeQuery();
		rs.next();*/
		String upsql = "UPDATE public.faves SET parent_id = ? where parent_id = ?";
		PreparedStatement stm = connection.prepareStatement(upsql);
		stm.setInt(1, parenthID);
		stm.setInt(2, id);
		stm.executeUpdate();
		connection.commit();
		stm.close();
/*		System.out.println("Строка c ID: "+ id + " service_name изменен c [" + rs.getString("parent_id")+"] на ["+parenthID+"]" );
		stm2.close();
		rs.close();*/
	}
}
