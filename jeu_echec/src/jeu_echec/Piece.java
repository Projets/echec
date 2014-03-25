package jeu_echec;

public abstract class Piece {
	protected String forme;
	protected String couleur;
	protected int ligne;
	protected int colonne;
	protected boolean vie;
	protected boolean premierCoup;
	
	public void setForme(String f) { this.forme=f; }
	public void setCouleur(String c) { this.couleur=c; }
	public void setPremierCoup(boolean b){ premierCoup=b; }
	public boolean getVie(){ return this.vie; }
	public boolean getPremierCoup(){ return premierCoup; }
	public int getLigne() { return this.ligne; }
	public int getColonne() { return this.colonne; }
	public String getForme() { return this.forme; }
	public String getCouleur() { return this.couleur; }
	
	public void setCoordonnee(int l, int col) {
		this.ligne=l;
		this.colonne=col;
	}
	
	public void setVie(boolean v){
		if(v!=false) this.vie=false;
		if(v==true) this.vie=true;
	}
	
	//vérifie si les coordonnées ne "sortent" pas du tableau (qui est du type [8][8])
	public boolean appartientPlateau() {
		if(this.ligne<8 && this.colonne<8)
			return true;
		return false;
	}
}
