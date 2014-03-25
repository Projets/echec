import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;

public class Echiquier {
	private Piece[][] plateau;
	private Piece[] cimetiereBlanc;
	private int nbMortsBlanc;
	private Piece[] cimetiereNoir;
	private int nbMortsNoir;
	private String cheminImg="/img/";
	
	
	public Echiquier() {
		this.plateau=new Piece[8][8];
		this.cimetiereBlanc=new Piece[16];
		this.nbMortsBlanc=0;
		this.cimetiereNoir=new Piece[16];
		this.nbMortsNoir=0;
	}
	
	public Echiquier getEchiquier(){
		return this;
	}
		//insère la piece dans le tableau avec les coordonnées de la piece
		//ecrase la piece existante
	public void setPiece(Piece p) {
		this.plateau[p.getLigne()][p.getColonne()]=p;
	}
		//recupère la piece aux coordonnées lignes et colones
	public Piece getPiece(int l, int c) {
		return this.plateau[l][c];
	}
	public int getNbMortsBlanc(){
		return nbMortsBlanc;
	}
	public int getNbMortsNoir(){
		return nbMortsNoir;
	}
	public boolean existePieceCimetiere(String color, int pos){
		if(color=="blanc") {
			if (this.cimetiereBlanc[pos]!=null) return true;
		}
		else if(color=="noir"){
			if (this.cimetiereNoir[pos]!=null) return true;
		}
		return false;
	}
	public Piece getPieceCimetiere(String color, int pos){
		if(color=="blanc") return cimetiereBlanc[pos];
		else if(color=="noir") return cimetiereNoir[pos];
		return null;
	}
	
	private void ajoutCimetiere(Piece p){
		if(p.getCouleur()=="blanc"){
			cimetiereBlanc[nbMortsBlanc]=p;
			nbMortsBlanc++;
		}
		if(p.getCouleur()=="noir"){
			cimetiereNoir[nbMortsNoir]=p;
			nbMortsNoir++;
		}
	}
		//une case est vide si elle contient null
	public boolean existePiece(int l, int c) {
		if (this.plateau[l][c]!=null) return true;
		return false;
	}
		//retire la piece du tableau, la case passe a null
	private void retirePiece(Piece p) {
		if (this.plateau[p.getLigne()][p.getColonne()]!=null) this.plateau[p.getLigne()][p.getColonne()]=null;
	}
		//renvoie true si les nouvelles coordonées l et c sont valides par rapport aux anciennes
		//on ne peut plus passer a travers des pieces
		//return true si il y a une piece de meme couleur
		//p: piece a bouger; l et c nouvelles coordonées
	public boolean deplacementValide(Piece p, int l, int c){
		if(p.getForme()=="cavalier"){
			Cavalier cav=new Cavalier(p.getCouleur(), p.getLigne(), p.getColonne());
			if(cav.coupValide(l, c)){
				if(existePiece(l, c) && p.getCouleur()==(getPiece(l,c)).getCouleur()) return false;
				return true;
				//le cavalier peut passer a travers d'autres pieces
			}
		}
		if(p.getForme()=="fou"){
			Fou f=new Fou(p.getCouleur(), p.getLigne(), p.getColonne());
			if(f.coupValide(l,c)){
				if(existePiece(l, c) && p.getCouleur()==(getPiece(l,c)).getCouleur()) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale droite haute
				boolean droitehaut=false;
				int c1=f.getColonne()+1;
				int l1=f.getLigne()-1;
				while(c1<8 && l1>=0 && !droitehaut){
					if(existePiece(l1,c1)) droitehaut=true;
					l1--;
					c1++;
				}
				if(droitehaut && c>=c1 && l<=l1 ) return false;//x et y les coordonnées de la piece derriere celle que l'on peut manger
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale droite basse
				boolean droitebas=false;
				int c2=f.getColonne()+1;
				int l2=f.getLigne()+1;
				while(c2<8 && l2<8 && !droitebas){
					if(existePiece(l2,c2)) droitebas=true;
					l2++;
					c2++;
				}
				if(droitebas && c>=c2 && l>=l2) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale gauche haute
				boolean gauchehaut=false;
				int c3=f.getColonne()-1;
				int l3=f.getLigne()-1;
				while(c3>=0 && l3>=0 && !gauchehaut){
					if(existePiece(l3,c3)) gauchehaut=true;
					l3--;
					c3--;
				}
				if(gauchehaut && c<=c3 && l<=l3) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale gauche basse
				boolean gauchebas=false;
				int c4=f.getColonne()-1;
				int l4=f.getLigne()+1;
				while(c4>=0 && l4<8 && !gauchebas){
					if(existePiece(l4,c4)) gauchebas=true;
					c4--;
					l4++;
				}
				if(gauchebas && c<=c4 && l>=l4) return false;
				return true;
			}
			
		}
		if(p.getForme()=="pion"){
			Pion pi=new Pion(p.getCouleur(), p.getLigne(), p.getColonne());
			if(pi.coupValide(l,c)){
				if(existePiece(l, c) && p.getCouleur()==(getPiece(l,c)).getCouleur()) return false;
				//------------pion blanc-------------------------------------------------------------------------
				if(c==pi.getColonne() && l==pi.getLigne()-1 && existePiece(l,c)) return false;
				//coups en diagonale seulement si il y a une piece enemie
				//pas besoin de verifier si la piece est de couleur différente car on vérifie avant si elle n'est pas de la meme couleur
				if(l==pi.getLigne()-1 && c==pi.getColonne()-1){
					if(!existePiece(l,c)) return false;
				}
				if(l==pi.getLigne()-1 && c==pi.getColonne()+1){
					if(!existePiece(l,c)) return false;
				}
				//cas des 2 cases
				if(l==pi.getLigne()-2 && c==pi.getColonne() && !p.getPremierCoup()) return false;
				if(l==pi.getLigne()-2 && c==pi.getColonne() && existePiece(l,c)) return false;
				if(l==pi.getLigne()-2 && c==pi.getColonne() && existePiece(l+1,c)) return false;
				
				//------------pion noir-------------------------------------------------------------------------------------
				if(c==pi.getColonne() && l==pi.getLigne()+1 && existePiece(l,c)) return false;
				//coups en diagonale seulement si il y a une piece enemie
				if(l==pi.getLigne()+1 && c==pi.getColonne()-1){
					if(!existePiece(l,c)) return false;
				}
				if(l==pi.getLigne()+1 && c==pi.getColonne()+1){
					if(!existePiece(l,c)) return false;
				}
				//cas des 2 cases
				if(l==pi.getLigne()+2 && c==pi.getColonne() && !p.getPremierCoup()) return false;
				if(l==pi.getLigne()+2 && c==pi.getColonne() && existePiece(l,c)) return false;
				if(l==pi.getLigne()+2 && c==pi.getColonne() && existePiece(l-1,c)) return false;
				
				return true;
			}
		}
		if(p.getForme()=="reine"){
			Reine re=new Reine(p.getCouleur(), p.getLigne(), p.getColonne());
			if(re.coupValide(l,c)){
				if(existePiece(l, c) && p.getCouleur()==(getPiece(l,c)).getCouleur()) return false;
					//COLONNES ET LIGNES----------------
				//verifie si pion adverse pour ne pas passer a travers dans la colonne vers le haut
				boolean piececol1=false;
				int li1=re.getLigne()-1;
				while( li1>=0 && (!piececol1)){
					if(existePiece(li1, c)) piececol1=true;
					li1--;
				}
				if(piececol1 && l<=li1 && c==re.getColonne()) return false;//li1 retient la ligne du pion derriere
				//verifie si pion adverse pour ne pas passer a travers dans la colonne vers le bas
				boolean piececol2=false;
				int li2=re.getLigne()+1;
				while( li2<8 && (!piececol2)){
					if(existePiece(li2, c)) piececol2=true;
					li2++;
				}
				if(piececol2 && l>=li2 && c==re.getColonne()) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la ligne vers la gauche
				boolean pieceligne1=false;
				int co1=re.getColonne()-1;
				while(co1>=0 && !pieceligne1){
					if(existePiece(l, co1)) pieceligne1=true;
					co1--;
				}
				if(pieceligne1 && c<=co1 && l==re.getLigne()) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la ligne vers la droite
				boolean pieceligne2=false;
				int co2=re.getColonne()+1;
				while(co2<8 && !pieceligne2){
					if(existePiece(l, co2)) pieceligne2=true;
					co2++;
				}
				if(pieceligne2 && c>=co2 && l==re.getLigne()) return false;
					//DIAGONALES--------------------------
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale droite haute
				boolean droitehaut=false;
				int c1=re.getColonne()+1;
				int l1=re.getLigne()-1;
				while(c1<8 && l1>=0 && !droitehaut){
					if(existePiece(l1,c1)) droitehaut=true;
					l1--;
					c1++;
				}
				if(droitehaut && c>=c1 && l<=l1 ) return false;//c1 et l1 les coordonnées de la piece derriere celle que l'on peut manger
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale droite basse
				boolean droitebas=false;
				int c2=re.getColonne()+1;
				int l2=re.getLigne()+1;
				while(c2<8 && l2<8 && !droitebas){
					if(existePiece(l2,c2)) droitebas=true;
					l2++;
					c2++;
				}
				if(droitebas && c>=c2 && l>=l2) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale gauche haute
				boolean gauchehaut=false;
				int c3=re.getColonne()-1;
				int l3=re.getLigne()-1;
				while(c3>=0 && l3>=0 && !gauchehaut){
					if(existePiece(l3,c3)) gauchehaut=true;
					l3--;
					c3--;
				}
				if(gauchehaut && c<=c3 && l<=l3) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la diagonale gauche basse
				boolean gauchebas=false;
				int c4=re.getColonne()-1;
				int l4=re.getLigne()+1;
				while(c4>=0 && l4<8 && !gauchebas){
					if(existePiece(l4,c4)) gauchebas=true;
					c4--;
					l4++;
				}
				if(gauchebas && c<=c4 && l>=l4) return false;
				return true;
			}
		}
		if(p.getForme()=="roi"){
			Roi ro=new Roi(p.getCouleur(), p.getLigne(), p.getColonne());
			if(ro.coupValide(l,c,this)){
				if(existePiece(l, c) && p.getCouleur()==(getPiece(l,c)).getCouleur() && getPiece(l,c).getForme()!="tour") return false;  // faux si meme couleur et pas une tour ( pour roque blanc)
				else if( roque("blanc") || roque("noir") || grandRoque("blanc") || grandRoque("noir") ) return true; // pour le roque et grand roque 
				return true;
			}
		}
		if(p.getForme()=="tour"){
			Tour to=new Tour(p.getCouleur(), p.getLigne(), p.getColonne());
			if(to.coupValide(l,c)){
				if(existePiece(l, c) && p.getCouleur()==(getPiece(l,c)).getCouleur()) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la colonne vers le haut
				boolean piececol1=false;
				int li1=to.getLigne()-1;
				while( li1>=0 && (!piececol1)){
					if(existePiece(li1, c)) piececol1=true;
					li1--;
				}
				if(piececol1 && l<=li1 && c==to.getColonne()) return false;//li1 retient la ligne du pion derriere
				//verifie si pion adverse pour ne pas passer a travers dans la colonne vers le bas
				boolean piececol2=false;
				int li2=to.getLigne()+1;
				while( li2<8 && (!piececol2)){
					if(existePiece(li2, c)) piececol2=true;
					li2++;
				}
				if(piececol2 && l>=li2 && c==to.getColonne()) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la ligne vers la gauche
				boolean pieceligne1=false;
				int co1=to.getColonne()-1;
				while(co1>=0 && !pieceligne1){
					if(existePiece(l, co1)) pieceligne1=true;
					co1--;
				}
				if(pieceligne1 && c<=co1 && l==to.getLigne()) return false;
				//verifie si pion adverse pour ne pas passer a travers dans la ligne vers la droite
				boolean pieceligne2=false;
				int co2=to.getColonne()+1;
				while(co2<8 && !pieceligne2){
					if(existePiece(l, co2)) pieceligne2=true;
					co2++;
				}
				if(pieceligne2 && c>=co2 && l==to.getLigne()) return false;
				return true;
			}
		}
		return false;
	}
	
	private boolean roiDeplacementEchec(String color, int l, int c){
		Echiquier tmp=copieEchiquier();
		tmp.getRoi(color).setCoordonnee(l,c);
		if(tmp.roiEnEchecBis( tmp.getRoi(color) )) return true;
		return false;
	}
		//verifie si une piece peut effectuer au moins un deplacement valide----------------
	public boolean peutBougerPiece(Piece p) {
		for(int l=0; l<8; l++){
			for(int c=0; c<8; c++){
				if(deplacementValide(p, l, c)){
					 return true;
				 }
			}
		}
		return false;
	}
	
	
		//déplace une piece---------------------
		//p: piece a bouger; l et c nouvelles coordonées
	public void bougerPiece(Piece p, int l, int c) {
		if(deplacementValide(p,l,c)){
			/*if(grandRoque("blanc")){
				plateau[7][2]=plateau[7][4];//bouge le roi blanc en 7,2
				plateau[7][4]=null;//retire le roi blanc de 7,4
				plateau[7][2].setCoordonnee(7,2);//met à jour les coordonnées du roi blanc
				plateau[7][3]=plateau[7][0];//bouge la tour blanche en 7,3
				plateau[7][0]=null;//retire la tour blanche de 7,0
				plateau[7][3].setCoordonnee(7,3);//met à jour les coordonnées de la tour blanche
			}
			else if (grandRoque("noir")){
				plateau[0][2]=plateau[7][4];//ajoute la piece à l,c
				plateau[7][4]=null;//retire l'ancienne piece
				plateau[7][2].setCoordonnee(0,2);//met à jour les coordonnées de la piece
				plateau[0][3]=plateau[0][0];//ajoute la piece à l,c
				plateau[0][0]=null;//retire l'ancienne piece
				plateau[0][3].setCoordonnee(0,3);//met à jour les coordonnées de la piece
			}
			else if(roque("blanc")){
				plateau[7][6]=plateau[7][4];//bouge le roi blanc en 7,2
				plateau[7][4]=null;//retire le roi blanc de 7,4
				plateau[7][6].setCoordonnee(7,6);//met à jour les coordonnées du roi blanc
				
				plateau[7][5]=plateau[7][7];//bouge la tour blanche en 7,3
				plateau[7][7]=null;//retire la tour blanche de 7,0
				plateau[7][5].setCoordonnee(7,5);//met à jour les coordonnées de la tour blanche
			}
			else if (roque("noir")){
				plateau[0][6]=plateau[0][4];//bouge le roi blanc en 7,2
				plateau[0][4]=null;//retire le roi blanc de 7,4
				plateau[0][6].setCoordonnee(0,6);//met à jour les coordonnées du roi blanc
				
				plateau[0][5]=plateau[0][7];//bouge la tour blanche en 7,3
				plateau[0][7]=null;//retire la tour blanche de 7,0
				plateau[0][5].setCoordonnee(0,5);//met à jour les coordonnées de la tour blanche
			}
			else if(existePiece(l,c) && p.getCouleur()!=(getPiece(l,c)).getCouleur()){
				this.ajoutCimetiere(getPiece(l,c));
				getPiece(l,c).setVie(false);
				//this.retirePiece(getPiece(l,c));
				plateau[l][c]=null;
			}
			else{*/
			this.plateau[l][c]=p;//ajoute la piece à l,c
			this.retirePiece(p);//retire l'ancienne piece
			p.setCoordonnee(l,c);//met à jour les coordonnées de la piece
			/*}*/			//le pion ne peux plus avancer de 2
			if(p.getForme()=="pion" && p.getPremierCoup()){
				p.setPremierCoup(false);
			}
		}
	}
	
	public void bougerGrandRoque(String couleur){
		if(couleur=="blanc"){
			plateau[7][4].setCoordonnee(7,2);
			plateau[7][2]=plateau[7][4]; // bouge le roi blanc
			plateau[7][4]=null;
			plateau[7][0].setCoordonnee(7,3);
			plateau[7][3]=plateau[7][0]; // bouge la tour blanche
			plateau[7][0]=null;
		}
		else if(couleur=="noir"){
			plateau[0][4].setCoordonnee(0,2);
			plateau[0][2]=plateau[0][4]; // bouge le roi noir
			plateau[0][4]=null;
			plateau[0][0].setCoordonnee(0,3);
			plateau[0][3]=plateau[0][0]; // bouge la tour noir
			plateau[0][0]=null;
		}		
	}
	
	public void bougerRoque(String couleur){
		if(couleur=="blanc"){
			plateau[7][4].setCoordonnee(7,6);
			plateau[7][6]=plateau[7][4]; // bouge le roi blanc
			plateau[7][4]=null;
			plateau[7][7].setCoordonnee(7,5);
			plateau[7][5]=plateau[7][7]; // bouge la tour blanche
			plateau[7][7]=null;
		}
		else if(couleur=="noir"){
			plateau[0][4].setCoordonnee(0,6);
			plateau[0][6]=plateau[0][4]; // bouge le roi noir
			plateau[0][4]=null;
			plateau[0][7].setCoordonnee(0,5);
			plateau[0][5]=plateau[0][7]; // bouge la tour noir
			plateau[0][7]=null;
		}		
	}

	public boolean roque(String couleur){
		if(couleur=="blanc"){
			if(!existePiece(7,5) && !existePiece(7,6) && getPiece(7,7).getForme()=="tour" && getPiece(7,7).getCouleur()=="blanc" && getPiece(7,4).getForme()=="roi" && getPiece(7,4).getCouleur()=="blanc")
				return true;
		}
		else if(couleur=="noir"){
			if(!existePiece(0,5) && !existePiece(0,6) && getPiece(0,7).getForme()=="tour" && getPiece(0,7).getCouleur()=="noir" && getPiece(0,4).getForme()=="roi" && getPiece(0,4).getCouleur()=="noir")
				return true;
		}
		return false;
	}

	
	public boolean grandRoque(String couleur){
		if(couleur=="blanc"){
			if(!existePiece(7,1) && !existePiece(7,2) && !existePiece(7,3) && getPiece(7,0).getForme()=="tour" && getPiece(7,0).getCouleur()=="blanc" && getPiece(7,4).getForme()=="roi" && getPiece(7,4).getCouleur()=="blanc")
				return true;
		}
		else if(couleur=="noir"){
			if(!existePiece(0,1) && !existePiece(0,2) && !existePiece(0,3) && getPiece(0,0).getForme()=="tour" && getPiece(0,0).getCouleur()=="noir" && getPiece(0,4).getForme()=="roi" && getPiece(0,4).getCouleur()=="noir")
				return true;
		}
		return false;
	}
	
	public void promotion(Interface i){
		i.setFormePromo(null);
		for(int c=0; c<8 ; c++){
			if(existePiece(0,c) && getPiece(0,c).getForme()=="pion" && getPiece(0,c).getCouleur()=="blanc"){
				i.affichPromotion("blanc");
				while(i.getFormePromo()==null){
					if(i.getFormePromo()=="tour"){
						Tour tourPromo=new Tour("blanc",0,c);
						this.setPiece(tourPromo);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"tour_blanc.png"));
						JLabel img = new JLabel(icon);
						i.getPlateau(0,c).add(img);
						i.revalidate();
					}
					else if(i.getFormePromo()=="cavalier") plateau[0][c]=new Cavalier("blanc",0,c);
					else if(i.getFormePromo()=="fou") plateau[0][c]=new Fou("blanc",0,c);
					else if(i.getFormePromo()=="reine") plateau[0][c]=new Reine("blanc",0,c);
				}
			}
			else if(existePiece(7,c) && getPiece(7,c).getForme()=="pion" && getPiece(7,c).getCouleur()=="noir"){
				i.affichPromotion("noir");
				while(i.getFormePromo()==null){
					if(i.getFormePromo()=="tour") plateau[7][c]=new Tour("noir",7,c);
					else if(i.getFormePromo()=="cavalier") plateau[7][c]=new Cavalier("noir",7,c);
					else if(i.getFormePromo()=="fou") plateau[7][c]=new Fou("noir",7,c);
					else if(i.getFormePromo()=="reine") plateau[7][c]=new Reine("noir",7,c);
				}
			}
		}
	}
	
		//liste les pieces d'une couleur--------------------
		//retourne un tableau de taille le nbre de pieces
	private Piece[] listePieces(String couleur){
		Piece[] tab=new Piece[20];
		int i=0;
		for(int l=0; l<8; l++){
			for(int c=0; c<8; c++){
				if(existePiece(l,c) && getPiece(l,c).getCouleur()==couleur){
					tab[i]=getPiece(l,c);
					i++;
				}
			}
		}
		Piece[] liste=new Piece[i];
		for(int j=0; j<i; j++){
			liste[j]=tab[j];
		}
		
		return liste;
	}
	
	//récupère le roi d'une couleur------------------
	private Roi getRoi(String couleur)
	{
		int lRoi=0, cRoi=0;
		Roi roi;
		//cherche les coordonnées du roi
		for(int l=0; l<8; l++){
			for(int c=0; c<8; c++){
				if(existePiece(l,c)){
					if(getPiece(l,c).getForme()=="roi" && getPiece(l,c).getCouleur()==couleur){
						return new Roi(couleur, l, c);
						//l=8; c=8;//sort de la boucle pour aller plus vite
					}
				}
			}
		}
		return null;
	}
		
		//verifie si le roi est en echec---------------------
	public boolean roiEnEchec(String couleur){
		if(getRoi(couleur)==null) return false;
		Roi roi=getRoi(couleur);
		for(int l=0; l<8; l++){
			for(int c=0; c<8; c++){
				if(existePiece(l,c)){
					if(getPiece(l,c).getCouleur()!=roi.getCouleur()){
						if(deplacementValide(getPiece(l,c), roi.getLigne(), roi.getColonne() ))return true; //si le roi peut se faire manger
						
					}
				}
			}
		}
		return false;
	}
		//version où il faut entrer le roi en parametre------------
	private boolean roiEnEchecBis(Roi r) {
		if(r==null) return false;
		for(int l=0; l<8; l++){
			for(int c=0; c<8; c++){
				if(existePiece(l,c)){
					if(getPiece(l,c).getCouleur()!=r.getCouleur()){
						if(deplacementValide(getPiece(l,c), r.getLigne(), r.getColonne() )){ //si une piece peu manger le roi
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private Echiquier copieEchiquier() {
		Echiquier tmp=new Echiquier();
		Piece[] noirs=listePieces("noir");
		Piece[] blancs=listePieces("blanc");
		for(int i=0;i<noirs.length; i++) {
			if(noirs[i].getForme()=="cavalier")tmp.setPiece(new Cavalier("noir", noirs[i].getLigne(), noirs[i].getColonne()));
			if(noirs[i].getForme()=="fou") tmp.setPiece(new Fou("noir", noirs[i].getLigne(), noirs[i].getColonne()));
			if(noirs[i].getForme()=="pion") tmp.setPiece(new Pion("noir", noirs[i].getLigne(), noirs[i].getColonne()));
			if(noirs[i].getForme()=="reine") tmp.setPiece(new Reine("noir", noirs[i].getLigne(), noirs[i].getColonne()));
			if(noirs[i].getForme()=="tour") tmp.setPiece(new Tour("noir", noirs[i].getLigne(), noirs[i].getColonne()));
			if(noirs[i].getForme()=="roi") tmp.setPiece(new Roi("noir", noirs[i].getLigne(), noirs[i].getColonne()));
			
		}
		for(int i=0;i<blancs.length; i++){
			if(blancs[i].getForme()=="cavalier")tmp.setPiece(new Cavalier("blanc", blancs[i].getLigne(), blancs[i].getColonne()));
			if(blancs[i].getForme()=="fou") tmp.setPiece(new Fou("blanc", blancs[i].getLigne(), blancs[i].getColonne()));
			if(blancs[i].getForme()=="pion") tmp.setPiece(new Pion("blanc", blancs[i].getLigne(), blancs[i].getColonne()));
			if(blancs[i].getForme()=="reine") tmp.setPiece(new Reine("blanc", blancs[i].getLigne(), blancs[i].getColonne()));
			if(blancs[i].getForme()=="tour") tmp.setPiece(new Tour("blanc", blancs[i].getLigne(), blancs[i].getColonne()));
			if(blancs[i].getForme()=="roi") tmp.setPiece(new Roi("blanc", blancs[i].getLigne(), blancs[i].getColonne()));
		}
		return tmp;
	}
	
		//verifie si il y a echec et mat pour une couleur donnée--------------------
	public boolean echecEtMat(String couleur){
		//création d'un echiquier temporaire identique a l'echiquier courant
		Echiquier tmp=copieEchiquier();
		
		if(tmp.getRoi(couleur)==null) return true;
		Roi roi=tmp.getRoi(couleur);	//on recupere le roi
		if(!tmp.roiEnEchecBis(roi)) { //si pas echec
			return false;		//n'est pas echec donc pas echec et mat
		}
		//vérifie s'il peut bouger de façon à n'être plus échec
		Roi tmp_roi=roi;
		for(int l=0; l<8; l++){
			for(int c=0; c<8; c++){
				if(tmp.deplacementValide(roi,l,c)){
					roi.setCoordonnee(l,c);
					tmp.bougerPiece(roi,l,c);
					if(!tmp.roiEnEchecBis(roi)) {
						return false;	//peut bouger
					}
					tmp.retirePiece(roi);
					roi=tmp_roi;
					tmp.setPiece(roi);
				}
			}
		}
		// si on peut manger la pièce menaçante -- ET -- si on peut  mettre une pièce sur la trajectoire de la pièce menaçant le roi
		//on vérifie en fait si on peut empecher l'echec pour chaque deplacement de piece
		Piece[] liste1=tmp.listePieces(couleur);
		roi=tmp.getRoi(couleur);	//on recupere le roi
		boolean echec=true;
		for(int i=0; i<liste1.length; i++){ //pour chaque piece
			for(int l=0; l<8; l++){
				for(int c=0; c<8; c++){
					if(tmp.deplacementValide(liste1[i],l,c)){
						int lpiece=liste1[i].getLigne();
						int cpiece=liste1[i].getColonne();
						tmp.bougerPiece((liste1[i]),l,c);
						if(!tmp.roiEnEchecBis(roi)){	// on peut manger la piece ou empecher l'echec
							return false;
						}
						tmp.bougerPiece((liste1[i]),lpiece,cpiece);	//on remet la piece a son état d'origine (dans le doute :D)
					}
				}
			}
		}
		tmp=null;
		return true;
	}
	
	public void remplirEchiqier(){
		//remet à null
		for(int l=0;l<8;l++){
			for(int c=0;c<8;c++){
				plateau[l][c]=null;
			}
		}
		//BLANCS
		Tour tourBlanc1=new Tour("blanc",7,0);
		setPiece(tourBlanc1);
		Cavalier cavalierBlanc1=new Cavalier("blanc",7,1);
		setPiece(cavalierBlanc1);
		Fou fouBlanc1=new Fou("blanc",7,2);
		setPiece(fouBlanc1);
		Reine reineBlanc=new Reine("blanc",7,3);
		setPiece(reineBlanc);
		Roi roiBlanc=new Roi("blanc",7,4);
		setPiece(roiBlanc);
		Fou fouBlanc2=new Fou("blanc",7,5);
		setPiece(fouBlanc2);
		Cavalier cavalierBlanc2=new Cavalier("blanc",7,6);
		setPiece(cavalierBlanc2);
		Tour tourBlanc2=new Tour("blanc",7,7);
		setPiece(tourBlanc2);
		Pion pionBlanc1=new Pion("blanc",6,0);
		setPiece(pionBlanc1);
		Pion pionBlanc2=new Pion("blanc",6,1);
		setPiece(pionBlanc2);
		Pion pionBlanc3=new Pion("blanc",6,2);
		setPiece(pionBlanc3);
		Pion pionBlanc4=new Pion("blanc",6,3);
		setPiece(pionBlanc4);
		Pion pionBlanc5=new Pion("blanc",6,4);
		setPiece(pionBlanc5);
		Pion pionBlanc6=new Pion("blanc",6,5);
		setPiece(pionBlanc6);
		Pion pionBlanc7=new Pion("blanc",6,6);
		setPiece(pionBlanc7);
		Pion pionBlanc8=new Pion("blanc",6,7);
		setPiece(pionBlanc8);
		//NOIRS
		Tour tourNoir1=new Tour("noir",0,0);
		setPiece(tourNoir1);
		Cavalier cavalierNoir1=new Cavalier("noir",0,1);
		setPiece(cavalierNoir1);
		Fou fouNoir1=new Fou("noir",0,2);
		setPiece(fouNoir1);
		Reine reineNoir=new Reine("noir",0,3);
		setPiece(reineNoir);
		Roi roiNoir=new Roi("noir",0,4);
		setPiece(roiNoir);
		Fou fouNoir2=new Fou("noir",0,5);
		setPiece(fouNoir2);
		Cavalier cavalierNoir2=new Cavalier("noir",0,6);
		setPiece(cavalierNoir2);
		Tour tourNoir2=new Tour("noir",0,7);
		setPiece(tourNoir2);
		Pion pionNoir1=new Pion("noir",1,0);
		setPiece(pionNoir1);
		Pion pionNoir2=new Pion("noir",1,1);
		setPiece(pionNoir2);
		Pion pionNoir3=new Pion("noir",1,2);
		setPiece(pionNoir3);
		Pion pionNoir4=new Pion("noir",1,3);
		setPiece(pionNoir4);
		Pion pionNoir5=new Pion("noir",1,4);
		setPiece(pionNoir5);
		Pion pionNoir6=new Pion("noir",1,5);
		setPiece(pionNoir6);
		Pion pionNoir7=new Pion("noir",1,6);
		setPiece(pionNoir7);
		Pion pionNoir8=new Pion("noir",1,7);
		setPiece(pionNoir8);
	}
	
	public void remplirCimetiere(){
		nbMortsBlanc=0;
		nbMortsNoir=0;
		for(int i=0;i<16;i++){
			cimetiereBlanc[i]=null;
			cimetiereNoir[i]=null;
		}
	}
	
		//affiche l'échiquier(en terminal :D)
	public void afficheTerminal(){
		for(int i=0;i<8;i++) {
			for(int j=0; j<8;j++){
				if (existePiece(i,j)) System.out.print( (getPiece(i,j)).getForme()+" "+(getPiece(i,j)).getCouleur()+" - " );
				else System.out.print("null    -    ");
			}
			System.out.println();
		}
	}
	
	
}
