
import java.util.ArrayList;

/**
 * @version 2.0
 * @author Lidiane
 */
public class LU {
    private double[] b;//b é o vetor com os termos independentes
     private double[][] L;//A = L*U
    private double[][] U;//A = L*U
    private double[] y;//L*y=b
    private double[] x;//U*x=y
    private int linhas;
    private int colunas;
    private Matriz temp;
    private ArrayList termosDaMatrizL = new ArrayList();

    /* metodo padrao de todos os metodos numericos
     * @param Matriz pivo
     * @return int [] vetor com resuldados de x
     */
    public double[] executar(Matriz m)
    {
        
        temp = m;
        alocarVetores();
        U = calcularTriangularSuperior(U);// matriz U deve ser calculada primeiro para pegar os elementos da matriz L
       // printaMatriz(U);
        L = calcularTriangularInferior(L);
        //printaMatriz(L);
        y = calcularResultadoInferior(b,L);
        x = calcularResultadoSuperior(y,U);
      
        return x;  
    }
    
    /* metod que calcula a matriz triangular superiror por referencia
     * @param void
     * @return void
     */
    public double[][] calcularTriangularSuperior(double[][] matriz)
    {
        for(int k = 0; k < (linhas - 1); k++){
            // Percorre linhas secundárias... Até o fim da matriz ou seja... até n
            for(int i = (k + 1); i < linhas; i++){
                if(matriz[k][k]==0){//diagonal principal deve ser unitaria, nao pode conter zeros
                    // Neste casó é necessário trocar as linhas k e i
                    System.out.printf("Trocando Linhas: %d por %d\n", k + 1, i + 1);
                    TrocarLinhas(k, i,matriz);
                }

                double pivo = matriz[i][k] / matriz[k][k];//aki divide o pivo pela linha acima
                matriz[i][k]  = 0;//zera o elemento da linha abaixo
                termosDaMatrizL.add(pivo);//os elementos que seriam zerados vao forma a matriz L

                // laco que calcula os demais elementos da linha
                for(int j = (k + 1); j < linhas; j++){
                    matriz[i][j] = matriz[i][j] - pivo * matriz[k][j];
                }
            }
        }
        return matriz;
    }
    
    public double[][] calcularTriangularInferior(double[][] matriz)
    {
        int k=0;
        for(int i = 0;i<temp.colunas-1;i++)
        {
            for(int j=0;j<linhas;j++)
            {
                if(i==j) {
                    matriz[j][i]=1;//diagonal principal deve ser unitaria
                }
                else if (i<j) {
                    matriz[j][i]=(double) termosDaMatrizL.get(k++);
                }
                else {
                    matriz[j][i]=0;//matriz triangular superior deve ser zerada isso acontece quando i<j
                }
            }
        }
        return matriz;
    }
    
    private double[] calcularResultadoSuperior(double[]vetor,double[][]matriz) {
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
    
    private double[] calcularResultadoInferior(double[] vetor, double[][] matriz) 
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
    
  
    private void TrocarLinhas(int linha1, int linha2,double[][] matriz) {
        double aux = 0.00;
        // Troca elementos das linhas na matriz: a
        for (int i = 0; i < linhas; i++) {
            aux = matriz[linha1][i];
            matriz[linha1][i] = matriz[linha2][i];
            matriz[linha2][i] = aux;
        }
        // Troca os elementos do vetor: b
        aux = b[linha1];
        b[linha1] = b[linha2];
        b[linha2] = aux;
    }
    

    private double[] multiplicarMatrizesPorVetor(double[][] matriz, double[] vetor) 
    {
        double[] resposta = new double[linhas];
        double parcial;
        for(int i =0;i<linhas;i++)
        {
            for(int j=0;j<colunas;j++)
            {
                parcial = matriz[i][j]*vetor[j]; 
                resposta[i]+=parcial;
            }
            
        }
        return resposta;
    }

    private void alocarVetores() {
        linhas = temp.linhas;
        colunas = temp.colunas-1;//menos 1 pois o nº de colunas é da matriz ampliada
        b = new double[linhas];
        x = new double[linhas];//cada linha vai ter um resultado
        y = new double[linhas];
        L = new double[linhas][colunas];//a L vai estar cheia de zeros
        U = new double[linhas][colunas];
        System.arraycopy(temp.termosIndependentes, 0, b, 0, linhas);
        for(int i=0;i<linhas;i++)
        {
            System.arraycopy(temp.matriz[i], 0, U[i], 0, temp.colunas-1);
        }
    }
    
        /* metodo que imprime o vetor resposta
     * @param void
     * @return void
     */
    public void printaMatriz(double[][] matriz)
    {
        System.out.print("Resultado:\n ");
        for(int i=0;i<linhas;i++)
        {
            for(int j=0;j<colunas;j++)
            {
                System.out.print(matriz[i][j]+" | ");
            }
            System.out.println();
        }
    }

}