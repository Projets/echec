public class Cavalier extends Piece {

	public Cavalier(String c, int l, int col) {
		this.forme="cavalier";
		this.couleur=c;
		this.ligne=l;
		this.colonne=col;
		this.vie=true;
	}
	/*-PROBLEME peut passer a travers les pieces-Resolu dans la classe echiqier-*/
	//défini le patern de la piece
	public boolean coupValide(int l, int c){//l et c les nouvelles coordonnées
		if(l<8 && c<8){
			if(this.ligne==l && this.colonne==c) return false;
			if(ligne+2==l || ligne-2==l){
				if(colonne-1==c || colonne+1==c) return true;
			}
			if(colonne+2==c || colonne-2==c){
				if(ligne+1==l || ligne-1==l) return true;
			}
		}
		return false;
	}

}
