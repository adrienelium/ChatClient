package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.SocketManager;
import vue.Windows;

public class GestionEvent implements ActionListener{
	
	private Windows fen;
	private SocketManager socket = null;
	private Thread th;
	
	public GestionEvent(Windows fen) {
		// TODO Auto-generated constructor stub
		this.fen = fen;
		this.socket = SocketManager.getInstance();
		this.socket.addObservateur(fen);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		System.out.println(str);
		
		if (str == "Connexion") {
			if (fen.getPseudo().getText() != "" && fen.getAdresse().getText() != "") {
				
					this.socket.launchManager(fen.getPseudo().getText(),fen.getAdresse().getText());
					
					th = new Thread(this.socket);
					th.start();

				
			}
			else {
				JOptionPane.showMessageDialog(this.fen.getFrame(), "Veuillez renseigner un pseudo et une adresse IP locale", "Attention", JOptionPane.ERROR_MESSAGE);
			}
			 
		}
		else if (str == "Deconnexion") {
			JOptionPane.showMessageDialog(this.fen.getFrame(), "Vous avez été déconnecté !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			th.interrupt();
			this.socket.closeAll(); 
		} 
		else if (str == "Envoyer") {
			if (this.socket.isReady())
			{
				if (fen.getMessageuser().getText() != "") {
					
					socket.sendMessage(fen.getMessageuser().getText());
					fen.getMessageuser().setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(this.fen.getFrame(), "Vous ne pouvez pas envoyer un message vide", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
	}

}
