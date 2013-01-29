/**
 *
 * @author pargles
 * @version 3.0
 */
public class Matriz {
    public int linhas;
    public int colunas;
    public double matrizAmpliada[][];
    public double matriz[][];
    public double coeficientes[];
    private String solucao;//string utilizada para ver se sistema tem unica solucao, infinitas ou é impossível

    public Matriz(int linhas,int colunas, double[][] vetor)
    {
        this.linhas = linhas;
        this.colunas = colunas;
        matrizAmpliada = new double [linhas][colunas];
        coeficientes=new double[linhas];//coeficientes separados 
        setMatrizAmpliada(vetor);
    }
    
    //metodo construtor
    public Matriz(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        matrizAmpliada = new double [linhas][colunas];//matriz e separa dos coeficientes pois necessario efetuar calculos apenas com ela
        coeficientes=new double[linhas];//coeficientes separados       
    }

    public Matriz()
    {
        //construtor vazio
    }

    /* metodo que transpoe a matrizAmpliada
     * @param void
     * @return void
     */
    public void transposta()
    {

    }

    /* metodo que retorna a matrizAmpliada identidade da matrizAmpliada atual
     * @param void
     * @return Matriz identidade
     */
    public Matriz identidade()
    {
        Matriz identidade = new Matriz();
        return identidade;
    }


    /* calcula o determinante de uma matrizAmpliada 2x2
     * exemplo parametro {{2,2},{2,2}}
     * @param int mat2x2
     * @return int determinante
     */
    public int det2x2(int[][]mat2x2)
    {
        return mat2x2[0][0]*mat2x2[1][1]-mat2x2[0][1]*mat2x2[1][0];
    }


    /* metodo que imprime no terminal a matrizAmpliada ampliada
     * @param void
     * @return
     */
    public void printaMatrizAmpliada()
    {

    }

    /* metodo que seta a dimensao da matrizAmpliada
     * @param int linhas e colunas
     * @return void
     */
    public void setDimensao(int linhas, int colunas)
    {
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new double [linhas][colunas];
    }

    public void setMatrizAmpliada(double[][]m)
    {
        for(int i =0;i<linhas;i++)
        {
            System.arraycopy(m[i], 0, this.matrizAmpliada[i], 0, colunas);
        }
        setCoeficientes();//coloca os coeficientes em um vetor separado
        setMatriz();//separa a matriz sem os coeficientes
    }
    
    /* metodo privado que seta automaticamente a matriz sem coeficientes
     * @param void
     * @retur void
     */
    private void setMatriz()
    {
        matriz = new double[linhas][colunas-1];
        for(int i=0;i<linhas;i++)
        {
            System.arraycopy(matrizAmpliada[i], 0, matriz[i], 0, colunas-1);//nao coloca a ultima coluna, por isso colunas-1
        }
    }
    
     /* metodo privado que seta automaticamente os coeficientes
     * @param void
     * @return void
     */
    private void setCoeficientes()
    {
        coeficientes = new double[linhas];
        for(int i = 0;i<linhas;i++)
        {
            coeficientes[i]= matrizAmpliada[i][colunas-1];
        }
    }
    
    /* metodo que retorna o vetor de coeficientes
     * @param void
     * @return int[] matrizCoeficientes
     */
    public double[] getCoeficientes()
    {
        return coeficientes;
    }
    
    /* metodo que retorna a matriz sem os coeficientes
     * @param void
     * @return double[][] matriz
     */
    public double[][] getMatriz()
    {
        return matriz;
    }
    
        /* metodo que retorna se matrizAmpliada tem solucao unica
     * infinita ou nao tem solucao
     * @param void
     * @return void
     */
    public String verificador() {
    	double fatorMult = 0;
		int i, j;
		
		if (matriz[0][0] > matriz[1][0]) {
			fatorMult = this.divisor(matriz[0][0], matriz[1][0]);
		}
		else {
			fatorMult = this.divisor(matriz[1][0], matriz[0][0]);
		}
		
		for (j = 0; j < colunas; j++) {
			for (i = 1; i < linhas; i++) {
				if (this.divisor(matriz[i][j], matriz[i - 1][j]) != fatorMult) {
					return "SLCD";
				}
			}
		}
		
		for (i = 1; i < linhas; i++) {
			if (this.divisor(coeficientes[i], coeficientes[i - 1]) != fatorMult) {
				return "SLI";
			}
		}
		return "SLCI";///====>>> coloca um comentario informando oq qer dizer as siglas
	}
    
    private double divisor(double valor1, double valor2) {
    	valor1 = Math.abs(valor1);
    	valor2 = Math.abs(valor2);
    	if (valor1 > valor2) {
    		return valor1 / valor2;
    	}
		else {
			return valor2 / valor1;
		}
	}

}
