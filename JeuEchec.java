import java.util.*;

public class JeuEchec {
	private boolean abandon;
	private int tour;
	private String winner;
	
	public JeuEchec(){
		abandon=false;
		tour=2;
		winner="null";
	}
	public int getTour() {
		return tour;
	}
	public String getTourJoueur() {
		if(tour%2==0) return "blanc";
		else return "noirs";
	}
	//verifie si la partie est terminée; true si c'est le cas
	//on passe l"echiquier en parametre pour avoir l'etat du jeu
	public boolean partieTerminee(Echiquier e,Interface i){
		if(abandon) return true;
		if(e.echecEtMat("blanc")) return true;
		if(e.echecEtMat("noir")) return true;
		if(i.getTimer().getTime()<0){
			 i.getTimer().stopDTimer();
			 return true;
		 }
		return false;
	}
	//fait jouer un joueur en terminal
	public void jouerTerminal(String couleurJoueur, Echiquier e, Interface i){
		Scanner scan=new Scanner(System.in);
		int x,y,x2,y2;
		do {
		System.out.println("joueur: "+couleurJoueur);
		System.out.print("bouger piece (x,y) >8 pour abandonner: ");
		x=scan.nextInt();
		y=scan.nextInt();
		}while(!e.existePiece(x,y) || e.getPiece(x,y).getCouleur()!=couleurJoueur || !e.peutBougerPiece(e.getPiece(x,y)) || x>=8 || y>=8);
		if(x>=8 || y>=8) abandon=true;
		do {
		System.out.println("joueur: "+couleurJoueur);
		System.out.print("vers (x,y) >8 pour abandonner: ");
		x2=scan.nextInt();
		y2=scan.nextInt();
		}while(!e.deplacementValide(e.getPiece(x,y),x2, y2) || x2>=8 || y2>=8);
		if(x2>=8 || y2>=8) abandon=true;
		else e.bougerPiece(e.getPiece(x,y), x2, y2);
		
	}
	//jouer avec les clics
	public void jouer(String couleurJoueur, Echiquier e, Interface i){
		int x,y,x2,y2;
		boolean jouer=true;
		rejouer:while(jouer==true){
				do {// saisie de le piece a bouger
					if(i.newPart()) nouvellePartie(e,i);
					x=i.getClicX();
					y=i.getClicY();
					System.out.println();
				}while(!e.existePiece(x,y) || e.getPiece(x,y).getCouleur()!=couleurJoueur || !e.peutBougerPiece(e.getPiece(x,y)));
					i.couleurValide(e,e.getPiece(x,y));
				do {// vers où on veut bouger la piece
					if(i.newPart()) nouvellePartie(e,i);
					x2=i.getClicX();
					y2=i.getClicY();
					System.out.println();
				if(e.existePiece(x2,y2) && e.getPiece(x2,y2).getCouleur()==couleurJoueur && e.getPiece(x2,y2)!=e.getPiece(x,y) && !e.roque("noir") && !e.roque("blanc") && !e.grandRoque("noir") && !e.grandRoque("blanc")){
						i.couleurOrigine(e);
						continue rejouer;//on refait la saisie
						}
				}while(!e.deplacementValide(e.getPiece(x,y),x2, y2));
				if(i.getTimer().getTime()<=0) jouer=false; 
				else if(x==7 && y==4 && x2==7 && y2==7){
					if(e.roque("blanc")) // si un roque blanc 
						e.bougerRoque("blanc");
				}
				else if(x==0 && y==4 && x2==0 && y2==7){ // si roque noir
					if(e.roque("noir"))
							e.bougerRoque("noir");
				}
				else if(x==7 && y==4 && x2==7 && y2==0){ // si grand roque blanc
					if(e.grandRoque("blanc"))
							e.bougerGrandRoque("blanc");
				}
				else if(x==0 && y==4 && x2==0 && y2==0){ // si grand roque noir
					if(e.grandRoque("noir"))
							e.bougerGrandRoque("noir");
				}
				else e.bougerPiece(e.getPiece(x,y), x2, y2);
			//	e.promotion(i);
				jouer=false;	
			}
	}
	
	public void demarrerPartie () {
		Echiquier e=new Echiquier();
		Interface i=new Interface();
		e.remplirEchiqier();
		e.remplirCimetiere();
		i.remplirLettre();
		i.remplirChiffre();
		do{
			i.afficherPlateau();//--A mettre dans le do while-------------
			i.afficherCimetiere();
			i.positionnerCases();//----pour ne pas dédoubler les pieces---
			i.positionnerCimetiere();
			i.clicPlateau();
			i.afficherPieces(e);
			i.afficherPiecesCimetiere(e);
			i.revalidate();
			if(tour%2==0 && !partieTerminee(e,i)){
				i.affichJoueur("blanc");//c'est aux blancs de jouer
				if(e.roiEnEchec("blanc")) i.affichEchec("blanc");
				this.jouer("blanc", e, i);
			}
			else if (tour%2==1 && !partieTerminee(e,i)){
				i.affichJoueur("noir"); //c'est aux noirs de jouer
				if(e.roiEnEchec("noir")) i.affichEchec("noir");
				this.jouer("noir", e, i);
			}
			tour++;
		}while(!partieTerminee(e,i));
		if(partieTerminee(e,i)){
			if(tour%2==1){
				if(i.getTimer().getTime()<=0) winner="noir";
				else winner="blanc";
			}
			if(tour%2==0){
				if(i.getTimer().getTime()<=0) winner="blanc";
				else winner="noir";
			}
			i.affichGagnant(winner,i);
			i.getTimer().setTime();
		}
		nouvellePartie(new Echiquier(), i);
	}
	
	public void nouvellePartie(Echiquier e, Interface i){
		i.setPart();
		tour=2;
		e.remplirEchiqier();
		e.remplirCimetiere();
		i.remplirLettre();
		i.remplirChiffre();
		do{
			i.afficherPlateau();//--A mettre dans le do while-------------
			i.afficherCimetiere();
			i.positionnerCases();//----pour ne pas dédoubler les pieces---
			i.positionnerCimetiere();
			i.clicPlateau();
			i.afficherPieces(e);
			i.afficherPiecesCimetiere(e);
			i.revalidate();
			
			if(tour%2==0 && !partieTerminee(e,i)){
				i.affichJoueur("blanc");//c'est aux blancs de jouer
				if(e.roiEnEchec("blanc")) i.affichEchec("blanc");
				this.jouer("blanc", e, i);
			}
			else if (tour%2==1 && !partieTerminee(e,i)){
				i.affichJoueur("noir"); //c'est aux noirs de jouer
				if(e.roiEnEchec("noir")) i.affichEchec("noir");
				this.jouer("noir", e, i);
			}
			tour++;
		}while(!partieTerminee(e,i));
		if(partieTerminee(e,i)){
			if(tour%2==1){
				if(i.getTimer().getTime()<=0) winner="noir";
				else winner="blanc";
				i.getTimer().setTime();
			}
			if(tour%2==0){
				if(i.getTimer().getTime()<=0) winner="blanc";
				else winner="noir";
				i.getTimer().setTime();
			}
			i.affichGagnant(winner,i);
		}
		nouvellePartie(new Echiquier(), i);
	}
	
}
