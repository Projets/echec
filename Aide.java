import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;

public class Aide extends JFrame{
	private JFrame help;
	private JPanel regle;
	private JLabel regle2;
	private JPanel pion;
	private JPanel tour;
	private JPanel fou;
	private JPanel cavalier;
	private JPanel roi;
	private JPanel reine;
	private String cheminImg;
	
	public Aide(){
		help = new JFrame();
		help.setTitle(" Fiche d'aide ");
		help.setSize(400,310); // taille en pixel de la largeur et hauteur de la fenetre
		help.setLayout(new GridLayout(0,2));
		help.setLocationRelativeTo(null);
		help.setResizable(false); // empeche le redimenssionnement de la fenetre  
		help.setVisible(true);
		cheminImg="/img/";
	}
	
	public void regle(){
		regle=new JPanel();
		regle.setLayout(new BorderLayout());
		regle.setVisible(true);
		regle2=new JLabel("               Les régles ");
		regle2.setVisible(true);
		regle.add(regle2,BorderLayout.CENTER);
		regle.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame regle = new JFrame();
						regle.setTitle("Les règles");
						regle.setSize(440,350);	
						regle.setLocationRelativeTo(null);
						regle.setResizable(false);
						regle.setVisible(true);
						regle.setSize(440,350);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"regle_echec.jpg"));
						JLabel img = new JLabel(icon);
						regle.add(img);
					}});
		help.add(regle);
	}
	
	public void pion(){
		pion=new JPanel();
		pion.setLayout(new BorderLayout());
		pion.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"pion_blanc.png"));
		JLabel img = new JLabel(icon);
		pion.add(img,BorderLayout.CENTER);
		pion.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame pion = new JFrame();
						pion.setLocationRelativeTo(null);
						pion.setVisible(true);
						pion.setResizable(false);
						pion.setTitle("Le pion");
						pion.setSize(300,300);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"deplacementPion.gif"));
						JLabel img = new JLabel(icon);
						pion.add(img);
					}});
		help.add(pion);
	}
	
	public void tour(){
		tour=new JPanel();
		tour.setLayout(new BorderLayout());
		tour.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"tour_blanc.png"));
		JLabel img = new JLabel(icon);
		tour.add(img,BorderLayout.CENTER);
		tour.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame tour = new JFrame();
						tour.setLocationRelativeTo(null);
						tour.setResizable(false);
						tour.setVisible(true);
						tour.setTitle("La tour");
						tour.setSize(300,300);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"deplacementTour.gif"));
						JLabel img = new JLabel(icon);
						tour.add(img);
					}});
		help.add(tour);
	}
	
	public void cavalier(){
		cavalier=new JPanel();
		cavalier.setLayout(new BorderLayout());
		cavalier.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"cavalier_blanc.png"));
		JLabel img = new JLabel(icon);
		cavalier.add(img,BorderLayout.CENTER);
		cavalier.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame cavalier = new JFrame();
						cavalier.setLocationRelativeTo(null);
						cavalier.setResizable(false);
						cavalier.setVisible(true);
						cavalier.setTitle("Le cavalier");
						cavalier.setSize(300,300);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"deplacementCavalier.gif"));
						JLabel img = new JLabel(icon);
						cavalier.add(img);
					}});
		help.add(cavalier);
	}
	
	public void fou(){
		fou=new JPanel();
		fou.setLayout(new BorderLayout());
		fou.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"fou_blanc.png"));
		JLabel img = new JLabel(icon);
		fou.add(img,BorderLayout.CENTER);
		fou.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame fou = new JFrame();
						fou.setLocationRelativeTo(null);
						fou.setResizable(false);
						fou.setVisible(true);
						fou.setTitle("Le fou");
						fou.setSize(300,300);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"deplacementFou.gif"));
						JLabel img = new JLabel(icon);
						fou.add(img);
					}});
		help.add(fou);
	}
	
	public void roi(){
		roi=new JPanel();
		roi.setLayout(new BorderLayout());
		roi.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"roi_blanc.png"));
		JLabel img = new JLabel(icon);
		roi.add(img,BorderLayout.CENTER);
		roi.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame roi = new JFrame();
						roi.setLocationRelativeTo(null);
						roi.setResizable(false);
						roi.setVisible(true);
						roi.setTitle("Le roi");
						roi.setSize(300,300);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"deplacementRoi.gif"));
						JLabel img = new JLabel(icon);
						roi.add(img);
					}});
		help.add(roi);
	}
	
	public void reine(){
		reine=new JPanel();
		reine.setLayout(new BorderLayout());
		reine.setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"reine_blanc.png"));
		JLabel img = new JLabel(icon);
		reine.add(img,BorderLayout.CENTER);
		reine.addMouseListener(new MouseAdapter(){;
					public void mouseClicked(MouseEvent e) {
						JFrame reine = new JFrame();
						reine.setLocationRelativeTo(null);
						reine.setResizable(false);
						reine.setVisible(true);
						reine.setTitle("La reine");
						reine.setSize(300,300);
						ImageIcon icon = new ImageIcon(getClass().getResource(cheminImg+"deplacementDame.gif"));
						JLabel img = new JLabel(icon);
						reine.add(img);
					}});
		help.add(reine);
	}
}
