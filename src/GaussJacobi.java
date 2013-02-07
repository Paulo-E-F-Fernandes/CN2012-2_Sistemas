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
        
        
        /*
        private Matriz mc = new Matriz() cópia de z //--> declarar
        
        if(!this.verficarConvergencia()) {
		//Sair da operação pois não converge
        }
        else {
        	//calcular
        }
        
        */
        
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
    	double maior = 0;
    	double alfa = 0;
    	
    	for(int i = 0; i < m.linhas; i++) {
    		for(int j = 0; j < m.linhas; j++) {
    			if (i != j) {
    				alfa = alfa + math.abs(m[i][j]);
    			}
    		}
    		alfa = alfa / math.abs(m[i][i]);
    		
    		if(alfa > maior) {
    			maior = alfa;
    		}	
    	}
    	
    	if(maior < 1) {
    		return true; // converge
    	}
    	else {
    		return false; // nao converge
    	}
    	
    }
    
    */
    
    /*
    

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


    */
    
    
}
