package cyri.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import cyri.model.DBManga;
import cyri.model.Manga;

public class MangaData {
	private static Logger log = Logger.getLogger(MangaData.class.getName());
	private String url = "jdbc:mysql://localhost/cyri";
	private String login = "cyri";
	private String password = "cyri";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet rs = null;
	private static final String SELECT_MANGA = "select * from manga";

	private ArrayList<Manga> mangaList = new ArrayList<Manga>();

	public MangaData() {
		initData();
	}

	public ArrayList<Manga> getMangaList() {
		return mangaList;
	}

	public boolean estAdmin(String pseudo, String pwd) {

		DBManga dbm = DBManga.getInstance();
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from admin where login = '"+pseudo+"' and password = '"+pwd+"'");
			dbm.setAdmin(rs.next());
			rs.close();
			statement.close();
			connection.close();			
		}
		catch (SQLException e) {
			log.info(e.getMessage());
		}

		return dbm.getAdmin();
	}

	public boolean existe(String nom) {
		boolean trouve = false;
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from manga where nom = '"+nom+"'");
			trouve = rs.next();
			rs.close();
			statement.close();
			connection.close();			
		}
		catch (SQLException e) {
			log.info(e.getMessage());
		}
		return trouve;
	}

	public void add(String nom){
		if(!existe(nom)) {
			DBManga dbm = DBManga.getInstance();
			try {
				connection = DriverManager.getConnection(url, login, password);
				statement = connection.createStatement();
				statement.executeUpdate("insert into manga(nom) values ('"+nom+"')");
				mangaList.add(new Manga(nom));
				statement.close();
				connection.close();
			}
			catch (SQLException e) {
				log.info(e.getMessage());
			}
		}
	}

	public void delete(String nom) {
		if(existe(nom)) {
			try {
				connection = DriverManager.getConnection(url, login, password);
				statement = connection.createStatement();
				statement.executeUpdate("delete from manga where nom ='"+nom+"'");
				for(Manga manga : mangaList) {
					System.out.println(manga.getNom());
					System.out.println(nom);
					if(manga.getNom().equals(nom)) {
						mangaList.remove(manga);
						break;
					}
				}
				statement.close();
				connection.close();
			}
			catch (SQLException e) {
				log.info(e.getMessage());
			}
		}
	}



	private void initData(){
		initJDBCDriver();
		log.info("Récupération des nom de mangas dans la base de données.");
		mangaList = new ArrayList<Manga>();
		try {
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement();
			rs = statement.executeQuery(SELECT_MANGA);
			while (rs.next()) {
				String nom = rs.getString("nom");
				mangaList.add(new Manga(nom));
			}
			rs.close();
			statement.close();
			connection.close();			
		}
		catch (SQLException e) {
			log.info(e.getMessage());
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
