public class Roi extends Piece {
	private Echiquier e;

	public Roi(String c, int l, int col) {
		this.forme="roi";
		this.couleur=c;
		this.ligne=l;
		this.colonne=col;
		this.vie=true;
	}
	/*-PROBLEME peut passer a travers les pieces-Resolu dans la classe echiqier-*/
	/*-REGLE ? le roi ne peut pas aller a moins de 3 lignes du camp adverse-*/
	//défini le patern de la piece
	public boolean coupValide(int l, int c,Echiquier e){//l et c les nouvelles coordonnées
		if(l<8 && c<8){
			if(this.ligne==l && this.colonne==c) return false;
			if(ligne+1==l || ligne-1==l){
				if(colonne-1==c || colonne+1==c || colonne==c) return true;
			}
			if(ligne==l){
				if(colonne+1==c || colonne-1==c) return true;
			}
			if(this.ligne==7 && this.colonne==4 && l==7 && c==7 && e.getPiece(7,5)==null && e.getPiece(7,6)==null) return true; // pour le petit roque blanc
			if(this.ligne==0 && this.colonne==4 && l==0 && c==7 && e.getPiece(0,5)==null && e.getPiece(0,6)==null) return true; // pour le petit roque noir
			if(this.ligne==7 && this.colonne==4 && l==7 && c==0 && e.getPiece(7,1)==null && e.getPiece(7,2)==null && e.getPiece(7,3)==null ) return true; // pour le grand roque blanc
			if(this.ligne==0 && this.colonne==4 && l==0 && c==0 && e.getPiece(0,1)==null && e.getPiece(0,2)==null && e.getPiece(0,3)==null ) return true; // pour le grand roque blanc
		}
		return false;
	}

}
