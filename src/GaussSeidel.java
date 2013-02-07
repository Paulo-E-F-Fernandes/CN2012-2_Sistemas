public class GaussSeidel extends GaussJacobi {
	//private double []resultado;
	//private int iteracoes = 0;
	//private double erro;
	
	/*
	public double[] getResultado() {
		return this.resultado;
	}
	
	public void printaResultados() {
		System.out.println("--Gauss Seidel--");
		for(int i = 0; i < this.resultados.length; i++) {
			System.out.println("x"+i+" = "+this.resultado[i]);
		}
		System.out.println("Número de iterações = "+this.iteracoes);
		System.out.println("Erro = "+this.erro);
	}
	*/
	public double[] executar(Matriz m, double[] vetor, double erro) {
		double []anterior = new double[m.linhas];
		double []atual = new double[m.linhas];
		//this.resultado = new double[m.linhas];
		//this.erro = erro;
		
		//Syste,.arraycopy(vetor, 0, this.resultado, 0, vetor.length);
		for(int i = 0; i < m.linhas; i++) {
			atual[i] = vetor[i];
		}
		
		do {
			for(int i = 0; i < m.linhas; i++) {
				//System.arraycopy(this.resultado, 0, anterior, 0, resultado.length);
				System.arraycopy(atual, 0, anterior, 0, atual.length);
				//this.resultado[i] = m.termosIndependentes[i] / m.matriz[i][i];
				atual[i] = m.termosIndependentes[i] / m.matriz[i][i];
				for(int j = 0; j < m.linhas; j++) {
					if(i != j) {
						//this.resultado[i] = this.resultado[i] - ((m.matriz[i][j] * this.resultado[j]) / m.matriz[i][i]);
						atual[i] = atual[i] - ((m.matriz[i][j] * atual[j]) / m.matriz[i][i]);
					}
				}
			}
			//this.iteracoes = this.iteracoes + 1;
		//} while(!testar(this.resultado, anterior, erro));	
		} while(!testar(atual, anterior, erro));
		//this.printaResultados();
		return atual;
	}
/*	
	public boolean sassenfeld() {
		//mc
		double beta = 0;
		double []betaParcial = new double[mc.linha];
		
		for(int i = 0; i < mc.linhas; i++) {
			betaParcial[i] = 1;
		}
		
		for(int i = 0; i < mc.linhas; i++) {
			for(int j = 0; j < mc.linhas; j++) {
				if(i != j) {
					beta = beta + (Math.abs(mc[i][j]) * betaParcial[j]);
				}
			}
			betaParcial[i] = beta / Math.abs(mc[i[i);
			if(betaParcial[i] > 1) {
				return false; // Não converge
			}
			beta = 0;
		}
		
		return true; // Converge
		
	}
	
*/
}
