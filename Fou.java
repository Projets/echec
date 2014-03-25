public class Fou extends Piece {

	public Fou(String c, int l, int col) {
		this.forme="fou";
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
			for(int i=1; i<8; i++){
				if(ligne+i==l && colonne+i==c) return true;
				if(ligne-i==l && colonne-i==c) return true;
				if(ligne+i==l && colonne-i==c) return true;
				if(ligne-i==l && colonne+i==c) return true;
			}
		}
		return false;
	}

}
