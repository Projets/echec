package jeu_echec;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class DTimer implements ActionListener {
	protected JLabel viewTime;  // composant permettant l'affichage du temps ecoule
	protected int timeCount;	// variable permettant de memoriser le temps ecoule
	protected Timer timer;		// objet javax.swing.Timer

	// (par defaut) initialise le compteur a 0 et le delay a 1 seconde
	public DTimer () {	
		this (60, 1000);
	}
	
	// construit un timer avec le temps et le delay donne
	public DTimer (int initialTime, int delay) {	
		this.timeCount = initialTime;
		this.viewTime = new JLabel ("Vous avez 60 secondes pour jouer un coup : "  +this.timeCount);
		this.timer = new Timer (delay, this);
	}
	
	
	// lance le compteur de temps 
	public void startDTimer ()
	{	this.timer.start ();
	}
	
	// stop le compteur de temps 
	public void stopDTimer ()
	{	this.timer.stop ();
	}
	
	// permet de recuperer le temps deja ecoule
	public int getTime (){
		return  this.timeCount;
	}
	
	public void setTime(){
		this.timeCount=60;
	}
	
	// permet de connaitre l'etat d'activite du timer (lance ou non)
	public boolean isRunning () {
		return ( this.timer.isRunning () );
	}
	
	public JLabel getViewTime(){
		return this.viewTime;
	}
		
	public void actionPerformed (ActionEvent e) {
		this.timeCount--;
		if(this.timeCount<0) this.viewTime.setText (" Temps écoulé ! ");
		else this.viewTime.setText ("Vous avez 60 secondes pour jouer un coup : " +this.timeCount);
	}
}
