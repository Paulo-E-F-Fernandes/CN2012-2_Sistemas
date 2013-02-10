
public class GaussJacobi {

	protected Matriz matriz;
	protected double []resultado;
	protected int iteracoes = 0;
	protected double erro;
	
	/* metodo construtor
     * 
     */
	public GaussJacobi () {
		
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
				this.erro = 0;
			}
        }
        else {
        	this.erro = erro;
    		this.calcular(vetor);
    		this.printaResultados("--Gauss Jacobi--");
        }
		return this.resultado;
	}
    
    /* metodo que calcula o resultado
     * 
     * @param double[]
     * @return void
     */
    protected void calcular(double[] vetor) {
    	double[] anterior = new double[this.matriz.linhas];
		
		System.arraycopy(vetor, 0, this.resultado, 0, vetor.length);
		
		do {
			System.arraycopy(this.resultado, 0, anterior, 0, this.resultado.length);
			for(int i = 0; i < this.matriz.linhas; i++) {
				this.resultado[i] = this.matriz.termosIndependentes[i] / this.matriz.matriz[i][i];
				for(int j = 0; j < this.matriz.linhas; j++) {
					if(i != j) {
						this.resultado[i] = this.resultado[i] - ((this.matriz.matriz[i][j] * anterior[j]) / this.matriz.matriz[i][i]);
					}
				}
			}
			this.iteracoes = this.iteracoes + 1;
		} while(!testeParada(this.resultado, anterior));
    }
    
    /* metodo que retorna o resultado
     * 
     * @param void
     * @return double[]
     */
    public double[] getResultado() {
		return this.resultado;
	}
    
    /* metodo que retorna as iteracoes
     * 
     * @param void
     * @return int
     */
    public int getIteracoes() {
		return this.iteracoes;
    }
    
    /* metodo que retorna o erro
     * 
     * @param void
     * @return double
     */
    public double getErro() {
		return this.erro;
    }
    
    /* metodo que printa o resultado
     * 
     * @param void
     * @return void
     */
    protected void printaResultados(String str) {
		System.out.println("\n"+str);
		for(int i = 0; i < this.resultado.length; i++) {
			System.out.println("x"+i+" = "+this.resultado[i]);
		}
		System.out.println("Número de iterações = "+this.iteracoes);
		System.out.println("Erro = "+this.erro);
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
        		converge = criterioLinhas();
        		if(!converge) {
        			trocarLinhas(i,j);
        		}
        	}
        }
        
    	return converge;    
    }
	
	/* metodo que calcula o criterio das linhas
     * 
     * @param void
     * @return boolean
     */
    protected boolean criterioLinhas() {
    	double alfa = 0;
    	
    	for(int i = 0; i < this.matriz.linhas; i++) {
    		for(int j = 0; j < this.matriz.linhas; j++) {
    			if (i != j) {
    				alfa = alfa + Math.abs(this.matriz.matriz[i][j]);
    			}
    		}
    		alfa = alfa / Math.abs(this.matriz.matriz[i][i]);
    		
    		if(alfa > 1) {
    			return false; // não converge
    		}
    		
    		alfa = 0;
    	}
    	return true; // converge
    	
    }
	
    /* metodo que calcula o criterio de parada
     * 
     * @param double[], double[]
     * @return boolean
     */
    protected boolean testeParada(double []atual, double []anterior) {

		double maior = atual[0] - anterior[0];

		for (int i = 1; i < atual.length; i++) {
			maior = Math.max(maior, Math.abs(atual[i] - anterior[i]));
		}

		if (maior < this.erro) {
			return true;
		}
		else {
			return false;
		}
	}
	
    /* metodo que troca as linhas da matriz
     * 
     * @param int, int
     * @return void
     */
    protected void trocarLinhas (int linha1, int linha2) {
    	double temp;
    	for (int j = 0; j < this.matriz.linhas; j++) {
    		temp = this.matriz.matriz[linha1][j];
    		this.matriz.matriz[linha1][j] = this.matriz.matriz[linha2][j];
    		this.matriz.matriz[linha2][j] = temp;
    	}
    	temp = this.matriz.termosIndependentes[linha1];
    	this.matriz.termosIndependentes[linha1] = this.matriz.termosIndependentes[linha2];
    	this.matriz.termosIndependentes[linha2] = temp;
    }
	
}