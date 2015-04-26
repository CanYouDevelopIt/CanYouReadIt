package cyri.model;

public class Manga {
	
	private String nom;
	private int nbChapitres;
	
	public Manga(String _nom, int _nbChapitres){
		nom = _nom;
		nbChapitres = _nbChapitres;
	}
	
	public String getNom(){
		return nom;
	}
	
	public int getNbChapitres(){
		return nbChapitres;
	}
	
}
