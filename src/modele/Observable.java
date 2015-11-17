package modele;

import vue.Observateur;

public interface Observable {
	public void addObservateur(Observateur obs);
	public void notifyAllObservateur(Dataset dataset);
}
