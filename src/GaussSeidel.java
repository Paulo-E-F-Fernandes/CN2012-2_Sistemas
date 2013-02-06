
public class GaussSeidel extends GaussJacobi {
	
	public double[] executar(Matriz m, double[] vetor, double erro) {
		double []anterior = new double[m.linhas];
		double []atual = new double[m.linhas];
		
		for(int i = 0; i < m.linhas; i++) {
			atual[i] = vetor[i];
		}
		
		do {
			for(int i = 0; i < m.linhas; i++) {
				System.arraycopy(atual, 0, anterior, 0, atual.length);
				atual[i] = m.termosIndependentes[i] / m.matriz[i][i];
				for(int j = 0; j < m.linhas; j++) {
					if(i != j) {
						atual[i] = atual[i] - ((m.matriz[i][j] * atual[j]) / m.matriz[i][i]);
					}
				}
			}
			
		} while(!testar(atual, anterior, erro));
		
		return atual;
	}
}