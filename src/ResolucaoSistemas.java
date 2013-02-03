/**
 * @author paulo
 * @version 1.0
 */
public class ResolucaoSistemas {
    
    private Matriz matriz;
    private enum metodos{Todos, Gauss,Cholesky,LU,Jacobi,Seidel;}
    private Gauss ga;
    //private Cholesky cho;
    //private LU lu;

    //metodo construtor
    public ResolucaoSistemas(Matriz m)
    {
        this.matriz =m;
        ga = new Gauss();
        //cho= new Cholesky();
        //lu = new LU();

    }

/* metodo que vai executar os metodos diretos
 * executando apenas os selecinados
 * @param String metodo
 * @return int[] vetor com os resultados de x
 */
    public double[] executar(String metodo) {
        double[] temp = {2, 2};
        
        switch (metodos.valueOf(metodo)) {
            case Gauss:
                temp = ga.executar(matriz);
                break;
            case Cholesky:
                break;

            case LU:
                break;
        }
        
        return temp;
    }
    /* metodo que vai executar os metodos iterativos que precisam tbem de um vetor
     * @param String metodo, int[] vetor
     * @return int[] resultado
     */
    public int[] executar(String metodo, int[]vetor) {
        int[] temp = {2, 2};
        
        switch (metodos.valueOf(metodo)) {
            case Jacobi:
                break;
            case Seidel:
                break;
        }
        
        return temp;
    }
}
