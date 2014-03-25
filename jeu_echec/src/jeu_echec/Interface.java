package jeu_echec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* -------ATTENTION---------*/
/* Si les pieces ne s'affichent pas, il faut indiquer le chemin de votre dossier img dans le constructeur */
 
public class Interface extends JFrame {
	private final JFrame fenetre;
	private JFrame promotion;
	private final JPanel poschrono;
	private final	JPanel plateau[][];
	private final JPanel cimetiereBlanc[][];
	private final JPanel cimetiereNoir[][];
	private JPanel posPlateau;
	private JPanel posCimetiereBlanc;
	private JPanel posCimetiereNoir;
	private JPanel tour;
	private JPanel fou;
	private JPanel cavalier;
	private JPanel reine;
	private final JLabel lettre[];
	private final JLabel chiffre[];
	private final JMenuBar menu;
	private final JMenu menu1;
	private final JMenu menu2;
	private final JMenu menu3;
	private final JMenu menu4;
	private final JMenu joueur;
	private int is;
	private int js;
	private int clicX;
	private int clicY;
	private boolean newPartie;
	private final String cheminImg;
	private String formePromo;
	private final DTimer time;
	
	public Interface(){
		cheminImg="../../img/"; //chemin du dossier img
		fenetre = new JFrame();
		poschrono=new JPanel();
		plateau = new JPanel[9][9];
		cimetiereBlanc=new JPanel[2][8];
		cimetiereNoir=new JPanel[2][8];
		lettre = new JLabel [8];
		chiffre = new JLabel [8];
		newPartie=false;
		// CONFIG DE LA FENETRE //
		fenetre.setTitle("Jeu d'échec");
		fenetre.setSize(800,620); // taille en pixel de la largeur et hauteur de la fenetre
		fenetre.setLayout(new BorderLayout());
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false); // empeche le redimenssionnement de la fenetre  
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// MENU //	
		menu = new JMenuBar();
        menu1=new JMenu("Nouvelle partie");
        menu1.addMouseListener(new MouseAdapter(){;	
                                        @Override
					public void mouseClicked(MouseEvent e) {
						newPartie=true;
					}
					});
        menu.add(menu1);  // On ajoute nouvelle partie au menu
        menu2=new JMenu("Abandonner");
        menu2.addMouseListener(new MouseAdapter(){;			
                                        @Override
					public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(null, "Un joueur a abandonné la partie!", "ABANDON", JOptionPane.INFORMATION_MESSAGE);	
						newPartie=true;
					}
					});
        menu.add(menu2);  // On ajoute abandonner au menu
        menu3=new JMenu("Quitter");
        menu3.addMouseListener(new MouseAdapter(){;
                                        @Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);							
					}
					});
        menu.add(menu3);  // On ajoute quitter au menu
        menu4=new JMenu("Aide");
        menu4.addMouseListener(new MouseAdapter(){;
                                        @Override
					public void mouseClicked(MouseEvent e) {
						Aide a=new Aide();	
						a.regle();	
						a.pion();
						a.tour();
						a.fou();
						a.cavalier();
						a.roi();
						a.reine();								
					}
					});
	menu.add(menu4);
        joueur=new JMenu();
        menu.add(joueur);  // On ajoute la couleur du joueur qui doit jouer
        fenetre.setJMenuBar(menu); // On ajoute le menu à la fenetre
        // CONFIG DU CHRONO //
        poschrono.setLayout(new BorderLayout());
        time=new DTimer();
        poschrono.add(time.getViewTime(),BorderLayout.CENTER);
        fenetre.add(poschrono,BorderLayout.NORTH);
        fenetre.revalidate();
	}
	public DTimer getTimer(){
		return time;
	}
	
	public boolean newPart(){
		return newPartie;
	}
	
	public void setPart(){
		newPartie=false;
	}
	
	public void remplirLettre(){
		lettre[0]=new JLabel("A");
		lettre[1]=new JLabel("B");
		lettre[2]=new JLabel("C");
		lettre[3]=new JLabel("D");
		lettre[4]=new JLabel("E");
		lettre[5]=new JLabel("F");
		lettre[6]=new JLabel("G");
		lettre[7]=new JLabel("H");	
		for(int i=0;i<8;i++) lettre[i].setForeground(Color.white); 
	}
	
	public void remplirChiffre(){
		chiffre[0]=new JLabel("  8");
		chiffre[1]=new JLabel("  7");
		chiffre[2]=new JLabel("  6");
		chiffre[3]=new JLabel("  5");
		chiffre[4]=new JLabel("  4");
		chiffre[5]=new JLabel("  3");
		chiffre[6]=new JLabel("  2");
		chiffre[7]=new JLabel("  1");
		for(int i=0;i<8;i++) chiffre[i].setForeground(Color.white); 
	}
	
	public void afficherPlateau(){
		 for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if((i%2==0 && j%2==1) || (i%2==1 && j%2==0)){
					plateau[i][j] = new JPanel(new BorderLayout());				 
					plateau[i][j].setBackground(new Color(122,76,42));
					plateau[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					plateau[i][j].setPreferredSize(new Dimension(50,50));
				}
				else {
					plateau[i][j] = new JPanel();				
					plateau[i][j].setBackground(new Color(206,158,86));
					plateau[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					plateau[i][j].setPreferredSize(new Dimension(50, 50));
				}
			}
		}
		for (int j=0;j<8;j++){	
			plateau[8][j]=new JPanel(new BorderLayout());
			plateau[8][j].setPreferredSize(new Dimension(30,60));
			plateau[8][j].setBackground(new Color(69,34,14));
			plateau[8][j].setBorder(BorderFactory.createLineBorder(Color.black));
			plateau[8][j].add(chiffre[j],BorderLayout.CENTER);
		}	
		for (int i=0;i<8;i++){	
			plateau[i][8]=new JPanel();
			plateau[i][8].setPreferredSize(new Dimension(60,30));
			plateau[i][8].setBackground(new Color(69,34,14));
			plateau[i][8].setBorder(BorderFactory.createLineBorder(Color.black));
			plateau[i][8].add(lettre[i]);
		}
		plateau[8][8]=new JPanel();
		plateau[8][8].setPreferredSize(new Dimension(30,30));
		plateau[8][8].setBackground(new Color(69,34,14));	
		plateau[8][8].setBorder(BorderFactory.createLineBorder(Color.black));	
	}
	
	public void clicPlateau(){
		 for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				is=i;
				js=j;
				plateau[i][j].addMouseListener(new MouseAdapter(){;
					int x=getJs();
					int y=getIs();
                                        @Override
					public void mouseClicked(MouseEvent e) {
						clicX=x;
						clicY=y;
					}
					});
			}
		}
	}
	
	public int getClicX(){ return clicX; }
	public int getClicY(){ return clicY; }
	public int getIs(){	return is; }
	public int getJs(){	return js; }
	
	public void afficherCimetiere(){
		for(int i=0;i<2;i++){
			for (int j=0;j<8;j++){
				if((i%2==0 && j%2==1) || (i%2==1 && j%2==0)){
				cimetiereBlanc[i][j] = new JPanel(new BorderLayout());
				cimetiereBlanc[i][j].setBackground(new Color(122,76,42));
				cimetiereBlanc[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				cimetiereBlanc[i][j].setPreferredSize(new Dimension(60,60));
				
				cimetiereNoir[i][j] = new JPanel(new BorderLayout());
				cimetiereNoir[i][j].setBackground(new Color(122,76,42));
				cimetiereNoir[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				cimetiereNoir[i][j].setPreferredSize(new Dimension(60,60));
				}
				else{
				cimetiereBlanc[i][j] = new JPanel(new BorderLayout());
				cimetiereBlanc[i][j].setBackground(new Color(206,158,86));
				cimetiereBlanc[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				cimetiereBlanc[i][j].setPreferredSize(new Dimension(60,60));
				
				cimetiereNoir[i][j] = new JPanel(new BorderLayout());
				cimetiereNoir[i][j].setBackground(new Color(206,158,86));
				cimetiereNoir[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				cimetiereNoir[i][j].setPreferredSize(new Dimension(60,60));
				}		
			}
		}
	}
	
	public void positionnerCases(){
		posPlateau = new JPanel();
		posPlateau.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;  // case de depart
		gbc.gridy = 0;  // case de depart
		gbc.gridheight = 1;  // taille hauteur
        gbc.gridwidth = 1;  // taille largeur
        for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				gbc.gridx = i;
				gbc.gridy = j;
				posPlateau.add(plateau[i][j], gbc);
			}
		}
	//	posPlateau.setBackground(new Color(240,163,73)); // si on veut mettre un fond de couleur
		fenetre.add(posPlateau,BorderLayout.CENTER);
    }
    
    public void positionnerCimetiere(){  
		posCimetiereBlanc=new JPanel();
		posCimetiereNoir=new JPanel();
		posCimetiereBlanc.setLayout(new GridBagLayout());
		posCimetiereNoir.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;  // case de depart
		gbc.gridy = 0;  // case de depart
		gbc.gridheight = 1;  // taille hauteur
        gbc.gridwidth = 1;  // taille largeur
		for(int i=0;i<2;i++){
			for(int j=0;j<8;j++){
				gbc.gridx = i;
				gbc.gridy = j;
				posCimetiereBlanc.add(cimetiereBlanc[i][j],gbc);
				posCimetiereNoir.add(cimetiereNoir[i][j],gbc);
			}
		}
	//	posCimetiereBlanc.setBackground(new Color(240,163,73));  // si on veut mettre un fond de couleur
	//	posCimetiereNoir.setBackground(new Color(240,163,73));
		fenetre.add(posCimetiereNoir,BorderLayout.WEST);
		fenetre.add(posCimetiereBlanc,BorderLayout.EAST);
	}
	
	public void afficherPiecesCimetiere(Echiquier e){
        for(int i=0;i<e.getNbMortsBlanc();i++) {/*-cimetiere blanc-*/
			if(e.existePieceCimetiere("blanc" ,i)){
				if("tour".equals(e.getPieceCimetiere("blanc" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource("./img/tour_blanc.png"));
					JLabel img = new JLabel(icon);
					cimetiereBlanc[i%2][i/2].add(img);
				}
				if("cavalier".equals(e.getPieceCimetiere("blanc" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"cavalier_blanc.png"));
					JLabel img = new JLabel(icon);
					cimetiereBlanc[i%2][i/2].add(img);
				}
				if("fou".equals(e.getPieceCimetiere("blanc" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"fou_blanc.png"));
					JLabel img = new JLabel(icon);
					cimetiereBlanc[i%2][i/2].add(img);
				}
				if("pion".equals(e.getPieceCimetiere("blanc" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"pion_blanc.png"));
					JLabel img = new JLabel(icon);
					cimetiereBlanc[i%2][i/2].add(img);
				}
				if("reine".equals(e.getPieceCimetiere("blanc" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"reine_blanc.png"));
					JLabel img = new JLabel(icon);
					cimetiereBlanc[i%2][i/2].add(img);
				}
				if("roi".equals(e.getPieceCimetiere("blanc" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"roi_blanc.png"));
					JLabel img = new JLabel(icon);
					cimetiereBlanc[i%2][i/2].add(img);
				}
			}
		} /*-cimetiere noir -*/
		for(int i=0;i<e.getNbMortsNoir();i++) {
			if(e.existePieceCimetiere("noir" ,i)){
				if("tour".equals(e.getPieceCimetiere("noir" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"tour_noir.png"));
					JLabel img = new JLabel(icon);
					cimetiereNoir[i%2][i/2].add(img);
				}
				if("cavalier".equals(e.getPieceCimetiere("noir" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"cavalier_noir.png"));
					JLabel img = new JLabel(icon);
					cimetiereNoir[i%2][i/2].add(img);
				}
				if("fou".equals(e.getPieceCimetiere("noir" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"fou_noir.png"));
					JLabel img = new JLabel(icon);
					cimetiereNoir[i%2][i/2].add(img);
				}
				if("pion".equals(e.getPieceCimetiere("noir" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"pion_noir.png"));
					JLabel img = new JLabel(icon);
					cimetiereNoir[i%2][i/2].add(img);
				}
				if("reine".equals(e.getPieceCimetiere("noir" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"reine_noir.png"));
					JLabel img = new JLabel(icon);
					cimetiereNoir[i%2][i/2].add(img);
				}
				if("roi".equals(e.getPieceCimetiere("noir" ,i).getForme())){
					ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"roi_noir.png"));
					JLabel img = new JLabel(icon);
					cimetiereNoir[i%2][i/2].add(img);
				}
			}
		}
	}
	
		
		//permet d'afficher les pieces du tableau de pieces de la classe Echiquier
	public void afficherPieces(Echiquier e){
        for(int i=0;i<8;i++) {
			for(int j=0; j<8;j++){
				if(e.existePiece(i,j)){
					if("blanc".equals(e.getPiece(i,j).getCouleur())){
						if("tour".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"tour_blanc.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("cavalier".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"cavalier_blanc.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("fou".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"fou_blanc.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("pion".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"pion_blanc.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("reine".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"reine_blanc.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("roi".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"roi_blanc.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
					}
					if("noir".equals(e.getPiece(i,j).getCouleur())){
						if("tour".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"tour_noir.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("cavalier".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"cavalier_noir.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("fou".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"fou_noir.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("pion".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"pion_noir.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("reine".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"reine_noir.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
						if("roi".equals(e.getPiece(i,j).getForme())){
							ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"roi_noir.png"));
							JLabel img = new JLabel(icon);
							plateau[j][i].add(img);
						}
					}
				}
			}
		}
	}
	
        @Override
	public void revalidate(){
		for(int i=0;i<8;i++) {
			for(int j=0; j<8;j++){
				plateau[i][j].revalidate();
			}
		}
	}
	
	public void affichJoueur (String ColorJoueur){ 
		time.setTime();
		joueur.setText("Au tour du joueur " + ColorJoueur ); //  Met la couleur du joueur dans le menu	
		time.startDTimer();
	}
	
	public JPanel getPlateau(int i, int j){ return plateau[i][j]; }
	
	public void couleurValide(Echiquier e, Piece p){
		plateau[p.getColonne()][p.getLigne()].setBackground(Color.BLUE);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if (e.deplacementValide(p, i, j)){
					if(e.existePiece(i,j) && (e.getPiece(i,j).getCouleur() == null ? p.getCouleur() != null : !e.getPiece(i,j).getCouleur().equals(p.getCouleur()))){
						 plateau[j][i].setBorder(BorderFactory.createLineBorder(Color.red));
						 plateau[j][i].setBackground(new Color(255,0,0,100)); // rouge opaque
					 }
					else {
						plateau[j][i].setBorder(BorderFactory.createLineBorder(Color.green));
						plateau[j][i].setBackground(new Color(6,160,0,100)); // vert opaque
					}
				}
			}
		}
	}
	
	public void couleurOrigine(Echiquier e){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if((i%2==0 && j%2==1) || (i%2==1 && j%2==0)){	
					plateau[i][j].setBackground(new Color(122,76,42));
					plateau[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				}
				else {
					plateau[i][j].setBackground(new Color(206,158,86));
					plateau[i][j].setBorder(BorderFactory.createLineBorder(Color.black));	
				}
			}
		}
	}
	
	public void affichEchec(String couleur){
		if("blanc".equals(couleur)) JOptionPane.showMessageDialog(null, "Le roi blanc est en echec!", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(null, "Le roi noir est en echec!", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void affichGagnant(String couleur,Interface i){
		if("blanc".equals(couleur) && i.getTimer().getTime()>=0) JOptionPane.showMessageDialog(null, "Le joueur blanc gagne la partie", "VICTOIRE", JOptionPane.INFORMATION_MESSAGE);
		else if("blanc".equals(couleur) && i.getTimer().getTime()<0 ) JOptionPane.showMessageDialog(null, "Le joueur blanc gagne la partie car le joueur noir n'a pas jouer dans les temps", "VICTOIRE", JOptionPane.INFORMATION_MESSAGE);
		else if("noir".equals(couleur) && i.getTimer().getTime()>=0) JOptionPane.showMessageDialog(null, "Le joueur noir gagne la partie", "VICTOIRE", JOptionPane.INFORMATION_MESSAGE);
		else if("noir".equals(couleur) && i.getTimer().getTime()<0 ) JOptionPane.showMessageDialog(null, "Le joueur noir gagne la partie car le joueur blanc n'a pas jouer dans les temps", "VICTOIRE", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public JPanel tour(String couleur){ // image de la tour pour choisir la promotion
		tour=new JPanel();
		tour.setLayout(new BorderLayout());
		tour.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"tour_" + couleur + ".png"));
		JLabel img = new JLabel(icon);
		tour.add(img,BorderLayout.CENTER);
		tour.addMouseListener(new MouseAdapter(){;
                                        @Override
					public void mouseClicked(MouseEvent e) {
						setFormePromo("tour");
						promotion.dispose();
					}});
		return tour;
	}
	
	public JPanel cavalier(String couleur){ // image du cavalier pour choisir la promotion
		cavalier=new JPanel();
		cavalier.setLayout(new BorderLayout());
		cavalier.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"cavalier_" + couleur + ".png"));
		JLabel img = new JLabel(icon);
		cavalier.add(img,BorderLayout.CENTER);
		cavalier.addMouseListener(new MouseAdapter(){;
                                        @Override
					public void mouseClicked(MouseEvent e) {
						 setFormePromo("cavalier");
						 promotion.dispose();
					}});
		return cavalier;
	}
	
	public JPanel fou(String couleur){ // image du fou pour choisir la promotion
		fou=new JPanel();
		fou.setLayout(new BorderLayout());
		fou.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"fou_" + couleur + ".png"));
		JLabel img = new JLabel(icon);
		fou.add(img,BorderLayout.CENTER);
		fou.addMouseListener(new MouseAdapter(){;
                                        @Override
					public void mouseClicked(MouseEvent e) {
						setFormePromo("fou");
						promotion.dispose();
					}});
		return fou;
	}
	
	public JPanel reine(String couleur){  // image de la reine pour choisir la promotion
		reine=new JPanel();
		reine.setLayout(new BorderLayout());
		reine.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"reine_" + couleur + ".png"));
		JLabel img = new JLabel(icon);
		reine.add(img,BorderLayout.CENTER);
		reine.addMouseListener(new MouseAdapter(){;
                                        @Override
					public void mouseClicked(MouseEvent e) {
						setFormePromo("reine");
						promotion.dispose();
					}});
		return reine;
	}
	
	public void affichPromotion(String couleur){
		promotion = new JFrame();
		promotion.setSize(400,310); // taille en pixel de la largeur et hauteur de la fenetre
		promotion.setLayout(new GridLayout(0,2));
		promotion.setLocationRelativeTo(null);
		promotion.setResizable(false); // empeche le redimenssionnement de la fenetre  
		promotion.setTitle(" Promotion d'un pion " + couleur); 
		promotion.add(tour(couleur));
		promotion.add(cavalier(couleur));
		promotion.add(fou(couleur));
		promotion.add(reine(couleur));
		promotion.setVisible(true);
	}
	
	public String getFormePromo(){
		return formePromo;
	}
	
	public void setFormePromo(String promo){
		formePromo=promo;
	}
}
