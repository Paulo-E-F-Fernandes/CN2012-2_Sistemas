/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angelin
 */
public class Cholesky {
    private Matriz matriz;
    private double[] resultado;
    //metodo construtor
    public Cholesky()
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
        //programar o metodo de cholesky
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
