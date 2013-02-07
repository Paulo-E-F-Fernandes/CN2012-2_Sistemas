public class GaussJacobi /*extends TesteParada*/ {

	// EDUARDO, já deixei criada está classe para já deixar herdado
	// o método de parada que tu vai usar também.
	//
	// Para usar o teste de parada é só usar o método testar da seguinte forma:
	//

	/*
	//private Matriz matriz;
	//private double []resultado;
	//private int iteracoes = 0;
	//private double erro;
	*/
    
    
    private double[] x0;
    private double[] x;
    private double e;
    private double  temp = 0;
    private int linhas;
    private int colunas;
    private int i;
    private int j;
    
    
    public double [] executa(Matriz z, double[] vetor,double erro ){
        
        
        
        
        linhas = z.linhas;
        colunas = z.colunas;
	x0[1] = 0.7;
	x0[2] = -1.6;
	x0[3] = 0.6;
        
        System.out.println("cheguei!");
        
	
        z.getCoeficientes();
        
        
        
	
	//---------------------------//
	
	for(i = 1; i<linhas; i++){
		for(j = 1; j<colunas; j++){
			if(i!=j){
				temp += -z.matrizAmpliada[i][j] * x0[j];
			}
		}
		x[i] = (temp + z.matriz[i][colunas])/z.matrizAmpliada[i][i];
		temp = 0;
			
	}
	
         System.out.println("Critério de parada com relação a: " + e);
         
         
         
         if(this.testar(x, x0, e) == false){
			System.out.println("Satisfaz!");
                        
        }
        else{
            System.out.println("Não deu! ");
            for(i =1; i<linhas; i++)
            x0[i] = x[i];
        }
	
         for(i = 1; i<linhas; i++)
             x[i] = 22;
         
	return x;
	
	
		

			
    
    }
    
    /*
    	public double[] executar(Matriz m, double[] vetor, double erro) {
    	
    		
	        private Matriz mc = new Matriz() cópia de z //--> declarar
	        
	        if(!this.verficarConvergencia()) {
			//Sair da operação pois não converge
	        }
	        else {
	        	//calcular
	        }
	        
	*/
	/*
    	
		double []anterior = new double[m.linhas];
		//double []atual = new double[m.linhas];
		this.resultado = new double[m.linhas];
		this.erro = erro;
		
		System.arraycopy(vetor, 0, this.resultado, 0, vetor.length);
		//for(int i = 0; i < m.linhas; i++) {
		//	atual[i] = vetor[i];
		//}
		
		do {
			System.arraycopy(this.resultado, 0, anterior, 0, resultado.length);
			for(int i = 0; i < m.linhas; i++) {
				System.arraycopy(this.resultado, 0, anterior, 0, resultado.length);
				//System.arraycopy(atual, 0, anterior, 0, atual.length);
				this.resultado[i] = m.termosIndependentes[i] / m.matriz[i][i];
				//atual[i] = m.termosIndependentes[i] / m.matriz[i][i];
				for(int j = 0; j < m.linhas; j++) {
					if(i != j) {
						this.resultado[i] = this.resultado[i] - ((m.matriz[i][j] * anterior[j]) / m.matriz[i][i]);
						//atual[i] = atual[i] - ((m.matriz[i][j] * atual[j]) / m.matriz[i][i]);
					}
				}
			}
			this.iteracoes = this.iteracoes + 1;
		} while(!testeParada(this.resultado, anterior, erro));
		//} while(!testar(atual, anterior, erro));
		this.printaResultados();
		return resultado;
    */
    
    
    
    /*
    public boolean verificarConvergencia() {
    	boolean converge = false;
        for (int i = 0; i < mc.linhas && !converge; i++) {
        	for (int j = i + 1; j < mc.linhas && !converge; j++) {
        		converge = criterioLinhas(mc);
        		if(!converge) {
        			trocarLinhas(mc,i,j);
        		}
        	}
        }
    	return converge;    
    }
    
    */
    
    /*
    public void trocarLinhas(Matriz m, int linha1, int linha2) {
    	double temp;
    	for(int j = 0; j < m.linhas; j++) {
    		temp = m[linha1][j];
    		m[linha1][j] = m[linha2][j];
    		m[linha2][j] = temp;
    	}
    	temp = m.TI[linha1];
    	m.TI[linha1] = m.TI[linha2];
    	m.TI[linha2] = temp;
    }
    */
    
    /*
    public boolean criterioLinhas(Matriz m) {
    	double alfa = 0;
    	
    	for(int i = 0; i < m.linhas; i++) {
    		for(int j = 0; j < m.linhas; j++) {
    			if (i != j) {
    				alfa = alfa + math.abs(m[i][j]);
    			}
    		}
    		alfa = alfa / math.abs(m[i][i]);
    		
    		if(alfa > 1) {
    			return false; // não converge
    		}
    		
    		alfa = 0;
    	}
    	return true; // converge
    	
    }
    
    */
    
    /*
    

	public boolean testeParada(double []atual, double []anterior, double erro) {

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


    */
    
    
}
