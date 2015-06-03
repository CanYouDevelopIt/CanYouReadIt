package cyri.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MangaData {

	public MangaData() {
		try {
			initData();
		} catch (SQLException sqlError) {
			System.out
					.println("Erreur lors de la récuperation des manga dans la base de donées : "
							+ sqlError.getMessage());
		}
	}

	private void initData() throws SQLException {
		
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        	System.out.println("Exception");
        }
		
		String url = "jdbc:mysql://localhost/cyri";
		String login = "root";
		String password = "root";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from manga");
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				System.out.println(id + "\t" + nom + "\t");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
