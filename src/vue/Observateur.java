package vue;

import modele.Dataset;

public interface Observateur {
	public void afficherNotification(Dataset dataset);
}
