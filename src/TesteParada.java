
public abstract class TesteParada {
	
	public boolean testar(double []atual, double []anterior, double erro) {
		
		double maior = atual[0] - anterior[0];
		
		for (int i = 1; i < atual.length; i++) {
			maior = Math.max(maior, Math.abs(atual[i] - anterior[i]));
		}
		
		if (maior < erro) {
			return true;
		}
		else {
			return false;
		}
	}

}
