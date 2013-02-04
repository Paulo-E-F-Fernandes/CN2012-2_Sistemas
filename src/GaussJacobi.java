
public class GaussJacobi extends TesteParada {

	// EDUARDO, já deixei criada está classe para já deixar herdado
	// o método de parada que tu vai usar também.
	//
	// Para usar o teste de parada é só usar o método testar da seguinte forma:
	//
	
    
    
    private double[] x0;
    private double[] x;
    private double e;
    private double  temp = 0;
    private int linhas;
    private int colunas;
    private int i;
    private int j;
    
    
    public double [] executa(Matriz z, int[] vetor ){
        
        
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
}