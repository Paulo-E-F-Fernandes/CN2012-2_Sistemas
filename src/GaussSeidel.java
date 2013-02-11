
public class GaussSeidel extends GaussJacobi {
	
	public GaussSeidel () {
		
	}
	
	/* metodo que executa o metodo de Gauss-Jacobi do calculo numerico
     * 
     * @param Matriz, double[], double
     * @return double[]
     */
    public double[] executar (Matriz m, double[] vetor, double erro) {
		this.matriz = new Matriz(m.linhas, m.colunas);
		this.matriz.setMatrizAmpliada(m.matrizAmpliada);
		this.resultado = new double[m.linhas];
		
		if (!this.verificarConvergencia()) {
			for(int i = 0; i < this.matriz.linhas; i++) {
				this.resultado[i] = 0;
				
			}
			this.erro = 0;
			System.out.println("--Gauss Seidel--");
			System.out.println("Não converge");
        }
        else {
        	this.erro = erro;
    		this.calcular(vetor);
    		this.printaResultados("--Gauss Seidel--");
        }
		return this.resultado;
	}
    
    /* metodo que calcula o resultado
     * 
     * @param double[]
     * @return void
     */
    protected void calcular(double[] vetor) {
    	double []anterior = new double[this.matriz.linhas];
    	
    	System.arraycopy(vetor, 0, this.resultado, 0, vetor.length);
    	
    	do {
			for(int i = 0; i < this.matriz.linhas; i++) {
				System.arraycopy(this.resultado, 0, anterior, 0, resultado.length);
				this.resultado[i] = this.matriz.termosIndependentes[i] / this.matriz.matriz[i][i];
				for(int j = 0; j < this.matriz.linhas; j++) {
					if(i != j) {
						this.resultado[i] = this.resultado[i] - ((this.matriz.matriz[i][j] * this.resultado[j]) / this.matriz.matriz[i][i]);
					}
				}
			}
			this.iteracoes = this.iteracoes + 1;	
		} while(!testeParada(this.resultado, anterior));
    }
	
	/* metodo que verifica a convergencia
     * 
     * @param void
     * @return boolean
     */
    protected boolean verificarConvergencia() {
    	boolean converge = false;
        for (int i = 0; i < this.matriz.linhas && !converge; i++) {
        	for (int j = i + 1; j < this.matriz.linhas && !converge; j++) {
        		
        		if (this.criterioLinhas() && this.sassenfeld()) {
        			converge = true;
        		}
        		else if (this.sassenfeld()) {
        			converge = true;
        		}
        		else {
        			converge = false;
        		}
        		
        		if(!converge) {
        			trocarLinhas(i,j);
        		}
        	}
        }
    	return converge;    
    }

	/* metodo que calcula o criterio de sassenfeld
     * 
     * @param void
     * @return boolean
     */
    private boolean sassenfeld() {
		double beta = 0;
		double []betaParcial = new double[this.matriz.linhas];
		int i;
		
		for(i = 0; i < this.matriz.linhas; i++) {
			betaParcial[i] = 1;
		}
		
		for(i = 0; i < this.matriz.linhas; i++) {
			for(int j = 0; j < this.matriz.linhas; j++) {
				if(i != j) {
					beta = beta + (Math.abs(this.matriz.matriz[i][j]) * betaParcial[j]);
				}
			}
			betaParcial[i] = beta / Math.abs(this.matriz.matriz[i][i]);
			if(betaParcial[i] > 1) {
				return false; // Não converge
			}
			beta = 0;
		}
		
		return true; // Converge
		
	}
	
}
