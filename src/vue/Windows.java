package vue;


import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JTextField;

import controleur.GestionEvent;
import modele.Dataset;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JSeparator;

public class Windows implements Observateur{

	private JFrame frame;
	private JTextField messageuser;
	private JTextField pseudo;
	private JTextArea chattext;
	private JTextField adresse;
	private JLabel lblPseudo;
	private JLabel lblAdresseServer;
	private JSeparator separator;
	private JScrollPane scrollPane;
	
	private GestionEvent event;
	
	/**
	 * Create the application.
	 */
	public Windows() {
		event = new GestionEvent(this);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 751, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(event);
		btnConnexion.setBounds(284, 33, 126, 23);
		frame.getContentPane().add(btnConnexion);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.addActionListener(event);
		btnDeconnexion.setBounds(562, 33, 163, 23);
		frame.getContentPane().add(btnDeconnexion);
		
		messageuser = new JTextField();
		messageuser.setBounds(10, 410, 612, 23);
		frame.getContentPane().add(messageuser);
		messageuser.setColumns(10);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(event);
		btnEnvoyer.setBounds(636, 410, 89, 23);
		frame.getContentPane().add(btnEnvoyer);
		
		JLabel lblFentreDeChat = new JLabel("Fenetre de chat :");
		lblFentreDeChat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFentreDeChat.setBounds(10, 78, 106, 14);
		frame.getContentPane().add(lblFentreDeChat);
		
		pseudo = new JTextField();
		pseudo.setBounds(10, 34, 116, 20);
		frame.getContentPane().add(pseudo);
		pseudo.setColumns(10);
		
		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(138, 34, 116, 20);
		frame.getContentPane().add(adresse);
		
		lblPseudo = new JLabel("Pseudo :");
		lblPseudo.setBounds(10, 15, 106, 14);
		frame.getContentPane().add(lblPseudo);
		
		lblAdresseServer = new JLabel("Adresse server :");
		lblAdresseServer.setBounds(138, 15, 106, 14);
		frame.getContentPane().add(lblAdresseServer);
		
		separator = new JSeparator();
		separator.setBounds(0, 65, 735, 2);
		frame.getContentPane().add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 715, 296);
		frame.getContentPane().add(scrollPane);
		
		chattext = new JTextArea();
		scrollPane.setViewportView(chattext);
		chattext.setRows(10);
		chattext.setEditable(false);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void afficherNotification(Dataset dataset) {
		// TODO Auto-generated method stub
		this.chattext.setText(this.chattext.getText() + "\n" + dataset.getMessage());
		//this.chattext.setCaretPosition(this.chattext.getHeight());
	}
	
	public JTextField getMessageuser() {
		return messageuser;
	}

	public JTextField getPseudo() {
		return pseudo;
	}


	public JTextField getAdresse() {
		return adresse;
	}
}
