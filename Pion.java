public class Pion extends Piece {

	public Pion(String c, int l, int col) {
		this.forme="pion";
		this.couleur=c;
		this.ligne=l;
		this.colonne=col;
		this.vie=true;
		this.premierCoup=true;
	}
	/*-PROBLEME peut passer a travers les pieces-Resolu dans la classe echiqier-*/
	public boolean coupValide(int l, int c){//l et c les nouvelles coordonnées
		if(this.couleur=="blanc"){//pion blancs sont placés en bas
			if(l<8 && c<8){
				if(this.ligne==l && this.colonne==c) return false; //Peut pas se deplacer sur la case d'origine
				if(ligne-2==l && colonne==c) return true;
				if(ligne-1==l){
					if(colonne-1==c || colonne==c || colonne+1==c) return true;
				}
			}
		}
		if(this.couleur=="noir"){//pion noirs placés en haut
			if(l<8 && c<8){
				if(this.ligne==l && this.colonne==c) return false;  //Peut pas se deplacer sur la case d'origine
				if(ligne+2==l && colonne==c) return true;
				if(ligne+1==l){
					if(colonne-1==c || colonne==c || colonne+1==c) return true;
				}
			}
		}
		return false;
	}
}
