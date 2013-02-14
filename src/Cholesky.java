/**
 * @author angelin
 */
public class Cholesky {
    private Matriz matrizTemp;
    private double[] resultado;
    private double[][] A;
    int linhas,colunas;
    private double[] b;//termos independentes
    private double[][] L;
    private double[][] LT;
    private double[] y;//L*y=b
    private double[] x;//LT*x=y

    /* metodo que executa o cholesky
     * @param Matriz m
     * @return double[] reposta
     */
    public double[] executar(Matriz m) {
        matrizTemp = m;
        alocarVetores();
        if(!ehSimetrica(A)) {
        	System.out.println("\n--Fatorção de Cholesky--");
            System.out.println("Matriz não é simétrica para o método de Cholesky");
            return this.resultado;
        }
        else if (!ehPositivaDefinida(A)){
        	System.out.println("\n--Fatorção de Cholesky--");
            System.out.println("Matriz não é positiva definada para o método de Cholesky");
            return this.resultado;
        }
        else
            L = cholesky(A);
            LT = transposta(L);
            y = calcularResultadoInferior(L,b);//L*y=b
            resultado = calcularResultadoSuperior(LT,y);//LT*x=y
        return resultado;//se entrar em algum if entao vai devolver o vetor zerado
    }
    
    /* metodo que verifica se uma matriz e simetrica
     * @param double[][] matriz
     * @return boolena ehSimetrica
     */
     public boolean ehSimetrica(double[][] matriz){
        for(int i = 0,j = matriz.length -1 ; i < matriz.length ; i++,j--){
            if(matriz[i][j] != matriz[j][i]){
                return false;
            }
        }
        return true;
    }
    
    /* metodo que avalia se uma matriz tem sua diagonal principal positiva
     * @param double[][] matriz
     * @return boolean ehPositivaDefinida
     */
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
			return false;
		}                
	}
	return true;    
}
    
    /* metodo que retorna a matriz identidade de uma matriz
     * @param double matriz[][]
     * @return double[][] matrizIdentidade
     */
    public double[][] setIdentidadeToMatriz(double[][] matriz){
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
    
    /* metodo que multiplica duas matrizes
     * @param double[][] double[][] matrizes
     * @param double[][] matrizResposta
     */
    public double[][] multiplicarMatrizes(double[][] multiplicadora,double[][] multiplicando){
	if(multiplicadora[0].length != multiplicando.length){
		System.err.println("O tamanho das matrizes não são compatíveis");
		return null;
	}                                 
	
	double[][] resultadoTemp = new double[multiplicadora.length][multiplicando[0].length];
	for(int i = 0 ; i < resultadoTemp.length ; i++){
		for(int j = 0 ; j < resultadoTemp[0].length ; j++){
			resultadoTemp[i][j] = 0;
		}
	}               
	for(int i = 0 ; i < multiplicadora.length ; i++){
		for(int j = 0 ; j < multiplicando[0].length ; j++){
			for(int k = 0 ; k < multiplicando.length ; k++){
				resultadoTemp[i][j] += multiplicadora[i][k] * multiplicando[k][j];
			}
		}
	}
	return resultadoTemp;        
}

    

/* metodo que calcula a matriz triangular inferior L
 * @param double[][] matriz
 * @return double[][] matriz triangular inderior
 */
    public double[][] cholesky(double[][] matriz) {
        double temp[][] = new double[linhas][colunas];
        for (int i = 0; i < linhas; i++)  {
            for (int j = 0; j <= i; j++) {
                double soma = 0.0;
                for (int k = 0; k < j; k++) {
                    soma += temp[i][k] * temp[j][k];
                }
                if (i == j) temp[i][i] = Math.sqrt(matriz[i][i] - soma);
                else        temp[i][j] = 1.0 / temp[j][j] * (matriz[i][j] - soma);
            }
            if (temp[i][i] <= 0) {
                System.err.println("Diagonal Principal não é positiva");
            }
        }
        return temp;
    }
    
     /* metodo que transpoe a matrizAmpliada
     * @param void
     * @return void
     */
    public double [][] transposta(double[][]matriz)
    {
        double[][] temp = new double[linhas][colunas];
        for (int i=0; i < linhas; ++i){
            for (int j=0; j < colunas; ++j){
                temp[j][i] = matriz [i][j];
            }
        }
        return temp;
}
    
     private double[] calcularResultadoSuperior(double[][]matriz,double[]vetor) {
        double[] resultado = new double[linhas];
        linhas--;    // para facilitar os cáculos já que o indece de matrizes em java começam em zero!
        resultado[linhas] = vetor[linhas] / matriz[linhas][linhas];//calcula o ultimo x, ja que o resultado e praticamente direto
        for (int k = linhas; k > -1; k--) {
            double s = 0;
            for (int j = (k + 1); j < (linhas + 1); j++) {
                s = s + matriz[k][j] * resultado[j];
                resultado[k] = (vetor[k] - s) / matriz[k][k];
            }
        }
        linhas++;    // para acertar o valor das linhas
        return resultado;
    }
    
    private double[] calcularResultadoInferior(double[][]matriz,double[]vetor) 
    {
        double[] resultado = new double[linhas];
        int i =0;
        resultado[i] = vetor[i] / matriz[i][i];//calcula o primeiro x, ja que o resultado e praticamente direto
        for (int k = 1; k <linhas; k++) {//começa em 1 pois o primeiro elemento e direto
            double s = 0;
            for (int j = 0; j < k; j++) {
                s = s + matriz[k][j] * resultado[j];
                resultado[k] = (vetor[k] - s) / matriz[k][k];
            }
        }
        return resultado;
    }

    /* metodo que faz a alocação das variaveis
     * @param void
     * @return void
     */
        private void alocarVetores() {
        linhas = matrizTemp.linhas;
        colunas = matrizTemp.colunas-1;//menos 1 pois o nº de colunas é da matrizTemp ampliada
        resultado = new double[linhas];
        
        for (int i = 0; i < this.linhas; i++) {
        	this.resultado[i] = 0; 
        }
        
        b = new double[linhas];
        y = new double[linhas];
        x= new double[linhas];
        A = new double[linhas][colunas];//a A vai estar cheia de zeros
        System.arraycopy(matrizTemp.termosIndependentes, 0, b, 0, linhas);
        for(int i=0;i<linhas;i++)
        {
            System.arraycopy(matrizTemp.matriz[i], 0, A[i], 0, matrizTemp.colunas-1);
        }
    }
}