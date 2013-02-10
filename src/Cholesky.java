/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angelin
 */
public class Cholesky {
    private double[] resultado;
    private String tipo = "OK";
    
    //teste de simetria
    public boolean ehSimetrica(double[][] matriz){
    	if(matriz.length != matriz[0].length){
        	this.tipo = "Não é simétrica";
        	return false;
        }
    	for (int i = 0; i < matriz.length; i++) {
    		for (int j = 0; j < matriz.length; j++) {
    			if ((i != j) && (matriz[i][j] != matriz[j][i])) {
    				this.tipo = "Não é simétrica";
    		        return false;
    			}
    		}
    	}
        return true;
    }
    
    //definir se matriz  eé positiva definida
    public boolean ehPositivaDefinida(double[][] a){
    	double[][] u = a;
		double[][] m = new double[a.length][a[0].length];
		for(int i = 0 ; i < a.length -1 ; i++){
			setIdentidadeToMatriz(m);
			for(int j = i + 1 ; j < a.length ; j++){
				m[j][i] = -u[j][i] / u[i][i];
			}
			u = multiplicarMatrizes(m, u);          
		}
		//printMatriz(u);
	                
		for(int i = 0 ; i < u.length ; i++){
			if(u[i][i] < 0){
				this.tipo = "Não é positiva definida";
				return false;
			}                
		}
		return true;    
	}
    
    //seta identidade para matriz
    public static double[][] setIdentidadeToMatriz(double[][] matriz){
        for(int index = 0 ; index < matriz.length ; index++){
            for(int j = 0; j < matriz[0].length ; j++ ){
                if(j == index){
                    matriz[index][j] = 1;
                }
                else{
                    matriz[index][j] = 0;
                }
            }
        }
	    return matriz;
	}
    
    //multiplicar raizes
    public static double[][] multiplicarMatrizes(double[][] multiplicadora,double[][] multiplicando){
		if(multiplicadora[0].length != multiplicando.length){
			System.out.println("O tamanho das matrizes não são compatíveis");
			return null;
		}                                 
		
		double[][] resultado = new double[multiplicadora.length][multiplicando[0].length];
		for(int i = 0 ; i < resultado.length ; i++){
			for(int j = 0 ; j < resultado[0].length ; j++){
				resultado[i][j] = 0;
			}
		}               
		for(int i = 0 ; i < multiplicadora.length ; i++){
			for(int j = 0 ; j < multiplicando[0].length ; j++){
				for(int k = 0 ; k < multiplicando.length ; k++){
					resultado[i][j] += multiplicadora[i][k] * multiplicando[k][j];
				}
			}
		}
		return resultado;        
	}
    
    // método executar
    public double[] executar(Matriz matriz) {
    	this.resultado = new double[matriz.linhas];
    	
    	for (int i = 0; i < matriz.linhas; i++) {
    		this.resultado[i] = 0;
    	}
    	
    	double[][]G = this.cholesky(matriz.matriz);
    	if (G != null) {
    		double temp;
    		int i, j;
	    	
    		for (i = 0; i < G.length; i++) {
    			temp = 0;
        		for (j = 0; j < G.length; j++) {
        			if (i != j) {
        				temp = temp + (G[i][j] * this.resultado[j]);
        			}
        		}
        		this.resultado[i] = (matriz.termosIndependentes[i] - temp) / G[i][i];
    		}
    		
    		for (j = G.length - 1; j >= 0; j--) {
    			temp = 0;
        		for (i = G.length - 1; i >= 0; i--) {
        			if (j != i) {
        				temp = temp + (G[i][j] * this.resultado[i]);
        			}
        		}
        		this.resultado[j] = (this.resultado[j] - temp) / G[j][j];
    		}
    	}
    	this.printaResposta();
    	return this.resultado;
    }

    //METODO CHOLESKY
    
    public double[][] cholesky(double[][] a){
		//testes de propriedades para aplicação do cholesky
		if(!ehSimetrica(a)){
			return null;
		}
		if(!ehPositivaDefinida(a)){
			return null;
		}
	
		double[][] r = a;
		for(int k=0 ; k < a.length ; k++){
			double primeiroSomatorio = 0;
			for(int p = 0 ; p < k ; p++){
				primeiroSomatorio += Math.pow(r[k][p], 2);
			}
			r[k][k] = Math.sqrt( r[k][k] - primeiroSomatorio );
			//printMatriz(r);
			//System.out.println();
			for(int i = k + 1 ; i < a.length ; i++){
				double segundoSomatorio = 0;
				for(int p = 0 ; p < k ; p++){
					segundoSomatorio += r[i][p] * r[k][p];
				}
				r[i][k] = (r[i][k] - segundoSomatorio) / r[k][k];
				r[k][i] = r[i][k];
			}                       
		}
		for (int i = 0; i < r.length; i++) {
    		for (int j = i + 1; j < r.length; j++) {
    			r[i][j] = 0;
    		}
    	}
		return r;
	}
    /* metodo que imprime o vetor resposta
     * @param void
     * @return void
     */
    public void printaResposta()
    {
    	System.out.println("\n--Fatorção de Cholesky--");
    	for(int i = 0; i < this.resultado.length; i++) {
			System.out.println("x"+i+" = "+this.resultado[i]);
		}
    	if (this.tipo.compareTo("OK") != 0) {
    		System.out.println(tipo);
    	}
    }
}
