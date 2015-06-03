package cyri.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import cyri.model.Manga;

public class MangaData {
	private static Logger log = Logger.getLogger(MangaData.class.getName());
	private String url = "jdbc:mysql://localhost/cyri";
	private String login = "root";
	private String password = "root";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet rs = null;
	private static final String SELECT_MANGA = "select * from manga";

	private ArrayList<Manga> mangaList = new ArrayList<Manga>();

	public MangaData() {
		try {
			initData();
		} catch (SQLException sqlError) {
			log.info("Erreur lors de la récuperation des manga dans la base de donées : "
					+ sqlError.getMessage());
		}
	}

	public ArrayList<Manga> getMangaList() {
		return mangaList;
	}

	private void initData() throws SQLException {
		initJDBCDriver();
		log.info("Récupération des nom de mangas dans la base de données.");
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			rs = statement.executeQuery(SELECT_MANGA);
			while (rs.next()) {
				String nom = rs.getString("nom");
				mangaList.add(new Manga(nom));
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

	public void initJDBCDriver() {
		log.info("Initialisation du Driver JDBC");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			log.info("Le driver n'a pas été trouvé : " + ex.getMessage());
		}
	}
}
