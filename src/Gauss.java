/**
 * @author gaci
 * @version 1.0
 * @see http://introcs.cs.princeton.edu/java/95linear/
 */
public class Gauss {
    private Matriz matriz;
    private double[] resultado;
    //metodo construtor
    public Gauss()
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
                double div = matriz.matriz[i][i]/matriz.matriz[j][i];
                for(int k=i+1;k<matriz.colunas;k++)
                {
                    matriz.matriz[j][k]=matriz.matriz[j][k]-div*matriz.matriz[i][k];
                }
            }
        }
        for(int i=0;i<matriz.linhas;i++)
        {
            resultado[i]= matriz.matriz[i][matriz.colunas-1];
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
