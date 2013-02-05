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
    private GaussJacobi jo;

    //metodo construtor
    public ResolucaoSistemas(Matriz m)
    {
        this.matriz =m;
        ga = new Gauss();
        //cho= new Cholesky();
        //lu = new LU();
        jo = new GaussJacobi();

    }

/* metodo que vai executar os metodos diretos
 * executando apenas os selecinados
 * @param String metodo
 * @return int[] vetor com os resultados de x
 */
    public double[] executar(String metodo) {
        double[] temp = {25, 25};
        
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
    public double[] executar(String metodo, double[]vetor,double erro) {
        double[] temp = {21, 21};
        
        switch (metodos.valueOf(metodo)) {
            case Jacobi:
                System.out.println(temp.length);
                temp = jo.executa(matriz, vetor,erro);
                break;
            case Seidel:
                break;
        }
        
        return temp;
    }
}
