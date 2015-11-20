package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import controleur.Crypto;
import vue.Observateur;

public class SocketManager implements Runnable,Observable{
	
	private SocketManager() {
		setReady(false);
		this.mesObservateurs = new ArrayList<Observateur>();
	}
	
	private static SocketManager INSTANCE = new SocketManager();
	
	private String pseudo;
	private Socket socket;
	
	private BufferedReader in = null;
	private PrintWriter out;
	private boolean ready;
	
	private ArrayList<Observateur> mesObservateurs;
	
	public static SocketManager getInstance() {
		return INSTANCE;
	}
	
	public void launchManager(String pseudo, String adresseip) {
		this.setPseudo(pseudo);		
		
		try {
			this.socket = new Socket(InetAddress.getByName(adresseip), 5000); // Connexion au server
			System.out.println("Demande de connexion");
			out = new PrintWriter(socket.getOutputStream());
			out.println(Crypto.encodeChaine(pseudo));
			out.flush(); // Envoie du pseudo au server
			
			in = new BufferedReader (new InputStreamReader (socket.getInputStream())); // Recuperation du message de bienvenue
	        String message_distant = in.readLine();
	        
	        Dataset data = new Dataset();
	        data.setMessage(Crypto.decodeChaine(message_distant));	 
	        notifyAllObservateur(data);
	        //System.out.println(message_distant);
	        setReady(true);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("PROBLEM ICI");
			//e.printStackTrace();
			
		}
	}

	@Override
	public void run() {
		// TODO Ecoute tout les message provenant du socket
		while (!Thread.interrupted()) {
			if (in != null) {
				String message_distant;
				try {
					
					message_distant = in.readLine();
					Dataset data = new Dataset();
			        data.setMessage(Crypto.decodeChaine(message_distant));	 
			        
			        System.out.println(message_distant);
			        System.err.println(Crypto.decodeChaine(message_distant));
			        
			        notifyAllObservateur(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				
						System.out.println("Vous avez été déconnecté");
				
					
				}
			}
			
		}
	}
	
	public void sendMessage(String message) {
		// Envoie un message
		out.println(Crypto.encodeChaine(message));
		out.flush();
	}

	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		this.mesObservateurs.add(obs);
	}

	@Override
	public void notifyAllObservateur(Dataset dataset) {
		// TODO Auto-generated method stub
		for (Observateur obs : mesObservateurs) {
			obs.afficherNotification(dataset);		
		}
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void closeAll() {
		setReady(false);
		try {
			
			socket.close();
			setReady(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	
}
