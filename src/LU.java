/**
 *
 * @author Lidiane
 */
public class LU {
  private Matriz matriz;
    private double[] resultado;
    //metodo construtor
    public LU()
    {
        matriz= new Matriz();
    }

    /* metodo padrao de todos os metodos numericos
     * @param Matriz m
     * @return int [] vetor com resuldados de x
     */
    public double[] executar(Matriz m)
    {
        matriz = m;
        resultado = new double[matriz.linhas];//linhas e a quantidade de resultados
        for(int i=0;i<matriz.linhas-1;i++)
        {
            for(int j=i+1;i<matriz.linhas;i++)
            {
                double div = matriz.matrizAmpliada[i][i]/matriz.matrizAmpliada[j][i];
                for(int k=i+1;k<matriz.colunas;k++)
                {
                    matriz.matrizAmpliada[j][k]=matriz.matrizAmpliada[j][k]-div*matriz.matrizAmpliada[i][k];
                }
            }
        }
        for(int i=0;i<matriz.linhas;i++)
        {
            resultado[i]= matriz.matrizAmpliada[i][matriz.colunas-1];
        }
        return resultado;
    }
    
    /* metodo que imprime o vetor resposta
     * @param void
     * @return void
     */
    public void printaResposta()
    {
        
    }

}