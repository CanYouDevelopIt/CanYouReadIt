package cyri.model;

public class Manga {
	
	private String nom;
	private int dernierChapitre;
	
	public Manga(String _nom){
		nom = _nom;
	}
	
	public String getNom(){
		return nom;
	}
	
}
