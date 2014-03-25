public class Tour extends Piece {

	public Tour(String c, int l, int col) {
		this.forme="tour";
		this.couleur=c;
		this.ligne=l;
		this.colonne=col;
		this.vie=true;
	}
	/*-PROBLEME la tour peut passer a travers les pieces-Resolu dans la classe echiqier-*/
	//défini le patern de la piece
	public boolean coupValide(int l, int c){//l et c les nouvelles coordonnées
		if(l<8 && c<8){
			if(this.ligne==l && this.colonne==c) return false;
			if(this.ligne==l || this.colonne==c) return true;
		}
		return false;
	}
}
